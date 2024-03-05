package cn.hhu.service.impl;

import cn.hhu.Bean.*;
import cn.hhu.mapper.*;
import cn.hhu.mode.slhPublicClass;
import cn.hhu.mode.ModCalClass;
import cn.hhu.service.ForecastManualService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tlj
 * @description TODO
 * @date 2022/12/25 19:30
 */
@Service
public class ForecastManualServiceImpl implements ForecastManualService {
    @Autowired
    ST_PPTN_RMapper st_pptn_rMapper;
    @Autowired
    ParaForecastMapper paraForecastMapper;

    @Autowired
    ST_RIVER_RMapper st_river_rMapper;
    @Autowired
    ST_RSVR_RMapper st_rsvr_rMapper;

    @Autowired
    ParaWeatherMapper paraWeatherMapper;

    @Autowired
    ParaUphydstMapper paraUphydstMapper;

    @Autowired
    ParaCalplanMapper paraCalplanMapper;
    @Override
    public ForecastManualInitParams FrmOninit() {
        ForecastManualInitParams result = new ForecastManualInitParams();

        //1.获取计算方案
        List<ParaForecast> paraForecasts = paraForecastMapper.getParaForecastsPMakePNotNull();

        List<String>  paras= paraForecasts.stream().map(paraForecast -> {
            if ("1".equals(paraForecast.getIsOnLine())) {
                result.setChecked(paraForecast.getParaName() + "(当前运行方案)");
                return result.getChecked();
            }
            return paraForecast.getParaName();
        }).collect(Collectors.toList());
        result.setParaForecasts(paras);
        //2.获取降雨数据
        LocalDateTime seTM= LocalDateTime.now();
        //2.1设置时间 和小时
        result.setDate(seTM.toLocalDate());
        result.setHour(seTM.getHour());

        LocalDateTime sbTM=seTM.minusHours(1);
        System.out.println(sbTM+":"+seTM);
        List<ST_PPTN_R> list = st_pptn_rMapper.searchAllByTMAndIntv(sbTM, seTM);

        //计算每个测站的降雨量
        list.forEach(st_pptn_r -> {
            switch (st_pptn_r.getSTCD()){
                case "51131350":
                    result.setP1(result.getP1()==null?BigDecimal.ZERO.add(st_pptn_r.getDRP()):result.getP1().add(st_pptn_r.getDRP()));
                    break;
                case "51131300":
                    result.setP2(result.getP2()==null?BigDecimal.ZERO.add(st_pptn_r.getDRP()):result.getP2().add(st_pptn_r.getDRP()));
                    //p2 = p2 + Convert.ToDecimal(gDt.Tables[tabNmPP].Rows[i]["DRP"].ToString());
                    break;
                case "51112500":
                    //p3 = p3 + Convert.ToDecimal(gDt.Tables[tabNmPP].Rows[i]["DRP"].ToString());
                    result.setP3(result.getP3()==null?BigDecimal.ZERO.add(st_pptn_r.getDRP()):result.getP3().add(st_pptn_r.getDRP()));
                    break;
                case "51112000":
                    //p4 = p4 + Convert.ToDecimal(gDt.Tables[tabNmPP].Rows[i]["DRP"].ToString());
                    result.setP4(result.getP4()==null?BigDecimal.ZERO.add(st_pptn_r.getDRP()):result.getP4().add(st_pptn_r.getDRP()));
                    break;
                case "51112101":
                    //p5 = p5 + Convert.ToDecimal(gDt.Tables[tabNmPP].Rows[i]["DRP"].ToString());
                    result.setP5(result.getP5()==null?BigDecimal.ZERO.add(st_pptn_r.getDRP()):result.getP5().add(st_pptn_r.getDRP()));
                    break;
            }
        });

        //3.获取上游站来水
        ST_RIVER_R riverInfo = st_river_rMapper.getALLRiverInfoBySTCDAndTM("51112000", seTM);

        if(riverInfo ==null||riverInfo.getZ()==null){
            result.setRiverInfo(BigDecimal.ZERO);
        }else {
            result.setRiverInfo(riverInfo.getZ().setScale(3,1));
        }
        //4.获取水库水位
        ST_RSVR_R rsvrInfo = st_rsvr_rMapper.getRsvrInfoBYTMAndSTCD("51112101", seTM);
        result.setRsvrInfo(rsvrInfo==null||rsvrInfo.getRZ()==null?BigDecimal.valueOf(20): BigDecimal.valueOf(rsvrInfo.getRZ()).setScale(3,1));

        return result;
    }

    private String achId;
    @Override
    public String backId(){
        return  achId;
    }
    public String saveborderPara(ForecastManualInitParams initParams) {
        achId="SLHRES"+LocalDateTime2String(LocalDateTime.now())+"测试员";
        ModCalClass modSour0 = new ModCalClass();
        String achNm;
        String calBgTime;
        String CalRegCD = "";
        BigDecimal flowQ=initParams.getRiverInfo();
        BigDecimal w=initParams.getP8();
        BigDecimal flowDeep=initParams.getP9();
        BigDecimal ResFlow=initParams.getP7();
        BigDecimal[] RainHour={initParams.getP1(),initParams.getP2(),initParams.getP3(),initParams.getP4(),initParams.getP5()};
        BigDecimal ZI=initParams.getRsvrInfo();
        Double V = slhPublicClass.sglLineInsertZI("51112101",ZI.doubleValue());
        BigDecimal tf=BigDecimal.valueOf(24);
        int infectRow=0, rTmp;
        LocalDateTime now=LocalDateTime.now();
        for (int i = 0; i < 5; i++)
        {
            CalRegCD = "SLHRES000" + (i + 1);
             rTmp=paraWeatherMapper.insertParaWeather(achId,CalRegCD,now,RainHour[i],RainHour[i].multiply(tf), RainHour[i].multiply(tf),RainHour[i].multiply(tf),BigDecimal.valueOf(3),w,flowDeep);
            infectRow = infectRow + rTmp;

        }
        infectRow+=paraUphydstMapper.insertParaUphydst(achId,now,  flowQ, ResFlow,ZI, V);
        //此处不可以直接向里面插入getchecked,ID和名字不一样。
        achNm = LocalDate2String(LocalDate.now() )+"预报计算成果" + "(测试员)"+now.getHour()+now.getMinute()+now.getSecond();
        calBgTime=LocalDateTime2String(initParams.getDate().atTime(initParams.getHour(),0,0));
        infectRow+=paraCalplanMapper.insertCalplan(achId,achNm,initParams.getChecked(),calBgTime,now,initParams.getP10());
        return "成功保存了初始条件,共 " + infectRow + "行";
    }
    public static String LocalDateTime2String(LocalDateTime dt){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return  dtf.format(dt);
    }
    public static String LocalDate2String(LocalDate date){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return  dtf.format(date);
    }
    public static void main(String[] args) {
        System.out.println(LocalDateTime.now());
    }
}
