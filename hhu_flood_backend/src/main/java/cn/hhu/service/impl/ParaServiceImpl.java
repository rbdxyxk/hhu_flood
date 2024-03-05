package cn.hhu.service.impl;

import cn.hhu.Bean.*;
import cn.hhu.mapper.*;
import cn.hhu.service.ParaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ParaServiceImpl implements ParaService {
    @Autowired
    ParaForecastMapper paraForecastMapper;

    @Autowired
    ParaMapper paraMapper;

    @Autowired
    AnaResultMapper anaMapper;


    @Override
    public ParaPlanInitParams PsOninit(){
        ParaPlanInitParams result = new ParaPlanInitParams();
        List<String> part = new ArrayList<>();
        int first=0;

        //1.获取计算方案
        List<ParaForecast> paraForecasts = paraForecastMapper.getParaForecastsPMakePNotNull();

        List<String>  paras= paraForecasts.stream().map(paraForecast -> {
            if ("1".equals(paraForecast.getIsOnLine())) {
                result.setChecked(paraForecast.getParaName() + "(当前运行方案)");
                return result.getChecked();
            }
            return paraForecast.getParaName();
        }).collect(Collectors.toList());
        for  ( int  i  =   0 ; i  <  paras.size()  -   1 ; i ++ )  {
            for  ( int  j  =  paras.size()  -   1 ; j  >  i; j -- )  {
                if  (paras.get(j).equals(paras.get(i)))  {
                    paras.remove(j);
                }
            }
        }
        int temp=0;
        for  ( int  i  =   0 ; i  <  paras.size() ; i ++ ){
            if(paras.get(i).contains("(当前运行方案)")){
                temp=i;
            }
        }
        Collections.swap(paras,0,temp);
        System.out.println(paras);
        result.setParaForecasts(paras);

        //2.获取对应分区
        for (int i = 0; i <paraForecasts.size() ; i++) {
            if("1".equals(paraForecasts.get(i).getIsOnLine())){
                 part.add(paraForecasts.get(i).getCalRegNM());
                 if(first==0){
                     result.setChecked0(paraForecasts.get(i).getCalRegNM());
                 }
                 first++;
            }
        }
        result.setPart(part);

        //3.参数值显示
        String para=new String();
        for (int i = 0; i <paraForecasts.size() ; i++) {
            if("1".equals(paraForecasts.get(i).getIsOnLine())){
                 para=paraForecasts.get(i).getParaName();
                 break;
            }
        }
        String part0=result.getChecked0();
        List<BigDecimal> para_set=new ArrayList<>();
        for (int i = 0; i <paraForecasts.size() ; i++) {
            if(paraForecasts.get(i).getParaName()==para&&paraForecasts.get(i).getCalRegNM()==part0){
                para_set.add(paraForecasts.get(i).getA());
                para_set.add(paraForecasts.get(i).getDATT());
                para_set.add(paraForecasts.get(i).getB());
                para_set.add(paraForecasts.get(i).getEX());
                para_set.add(paraForecasts.get(i).getIM());
                para_set.add(paraForecasts.get(i).getKI());
                para_set.add(paraForecasts.get(i).getKG());
                para_set.add(paraForecasts.get(i).getCI());
                para_set.add(paraForecasts.get(i).getCG());
                para_set.add(paraForecasts.get(i).getCS());
                para_set.add(paraForecasts.get(i).getL());
                para_set.add(paraForecasts.get(i).getX());
                para_set.add(paraForecasts.get(i).getK());
                para_set.add(paraForecasts.get(i).getSM());
                para_set.add(paraForecasts.get(i).getWUM());
                para_set.add(paraForecasts.get(i).getWLM());
                para_set.add(paraForecasts.get(i).getWDM());
                para_set.add(paraForecasts.get(i).getC());
            }
        }


        int count=para_set.size();

        result.setP1(para_set.get(0));
        result.setP2(para_set.get(1));
        result.setP3(para_set.get(2));
        result.setP4(para_set.get(3));
        result.setP5(para_set.get(4));
        result.setP6(para_set.get(5));
        result.setP7(para_set.get(6));
        result.setP8(para_set.get(7));
        result.setP9(para_set.get(8));
        result.setP10(para_set.get(9));
        result.setP11(para_set.get(10));
        result.setP12(para_set.get(11));
        result.setP13(para_set.get(12));
        result.setP14(para_set.get(13));
        result.setP15(para_set.get(14));
        result.setP16(para_set.get(15));
        result.setP17(para_set.get(16));
        result.setP18(para_set.get(17));

        return result;

    };

    @Override
    public ParaPlanInitParams SelectValue(String c0,String c){

        List<ParaForecast> paraForecasts = paraForecastMapper.getParaForecastsPMakePNotNull();
        ParaPlanInitParams result = new ParaPlanInitParams();
        List<String> part = new ArrayList<>();
        int first=0;

        List<String>  paras= paraForecasts.stream().map(paraForecast -> {
            if ("1".equals(paraForecast.getIsOnLine())) {
                result.setChecked(paraForecast.getParaName() + "(当前运行方案)");
                return result.getChecked();
            }
            return paraForecast.getParaName();
        }).collect(Collectors.toList());
        result.setChecked(c);
        for  ( int  i  =   0 ; i  <  paras.size()  -   1 ; i ++ )  {
            for  ( int  j  =  paras.size()  -   1 ; j  >  i; j -- )  {
                if  (paras.get(j).equals(paras.get(i)))  {
                    paras.remove(j);
                }
            }
        }
        System.out.println(paras);
        result.setParaForecasts(paras);

        //2.获取对应分区
        if(c.contains("当前运行方案")){
            c=c.substring(0,c.length()-8);
        }
        for (int i = 0; i <paraForecasts.size() ; i++) {
            if(c.equals(paraForecasts.get(i).getParaName())){
                part.add(paraForecasts.get(i).getCalRegNM());
                if(first==0){
                    result.setChecked0(paraForecasts.get(i).getCalRegNM());
                    c0=paraForecasts.get(i).getCalRegNM();
                }
                first++;
            }
        }
        result.setPart(part);

        List<BigDecimal> para_set=new ArrayList<>();
        if(c.contains("当前运行方案")){
            c=c.substring(0,c.length()-8);
        }
        System.out.println(c0);
        System.out.println(c);
        System.out.println(paraForecasts);
        for (int i = 0; i <paraForecasts.size() ; i++){
            System.out.println(paraForecasts.get(i).getParaName());
            System.out.println(paraForecasts.get(i).getCalRegNM());
            if(Objects.equals(paraForecasts.get(i).getParaName(), c) && Objects.equals(paraForecasts.get(i).getCalRegNM(), c0)){
                para_set.add(paraForecasts.get(i).getA());
                para_set.add(paraForecasts.get(i).getDATT());
                para_set.add(paraForecasts.get(i).getB());
                para_set.add(paraForecasts.get(i).getEX());
                para_set.add(paraForecasts.get(i).getIM());
                para_set.add(paraForecasts.get(i).getKI());
                para_set.add(paraForecasts.get(i).getKG());
                para_set.add(paraForecasts.get(i).getCI());
                para_set.add(paraForecasts.get(i).getCG());
                para_set.add(paraForecasts.get(i).getCS());
                para_set.add(paraForecasts.get(i).getL());
                para_set.add(paraForecasts.get(i).getX());
                para_set.add(paraForecasts.get(i).getK());
                para_set.add(paraForecasts.get(i).getSM());
                para_set.add(paraForecasts.get(i).getWUM());
                para_set.add(paraForecasts.get(i).getWLM());
                para_set.add(paraForecasts.get(i).getWDM());
                para_set.add(paraForecasts.get(i).getC());
            }
        }
        System.out.println(para_set.size());
        if(para_set.size()==0){
            return  result;
        }

        result.setP1(para_set.get(0));
        result.setP2(para_set.get(1));
        result.setP3(para_set.get(2));
        result.setP4(para_set.get(3));
        result.setP5(para_set.get(4));
        result.setP6(para_set.get(5));
        result.setP7(para_set.get(6));
        result.setP8(para_set.get(7));
        result.setP9(para_set.get(8));
        result.setP10(para_set.get(9));
        result.setP11(para_set.get(10));
        result.setP12(para_set.get(11));
        result.setP13(para_set.get(12));
        result.setP14(para_set.get(13));
        result.setP15(para_set.get(14));
        result.setP16(para_set.get(15));
        result.setP17(para_set.get(16));
        result.setP18(para_set.get(17));

        return result;
    }

    @Override
    public void save(ParaPlanInitParams initParams){
        String c,c0;
        c=initParams.getChecked();
        if(c.contains("当前运行方案")){
            c=c.substring(0,c.length()-8);
        }
        c0=initParams.getChecked0();
        anaMapper.save(c,c0,initParams.getP1(),initParams.getP2(),initParams.getP3(),initParams.getP4(),initParams.getP5(),initParams.getP6(),initParams.getP7(),initParams.getP8(),initParams.getP9(),initParams.getP10(),initParams.getP11(),initParams.getP12(),initParams.getP13(),initParams.getP14(),initParams.getP15(),initParams.getP16(),initParams.getP17(),initParams.getP18());
    }

    @Override
    public void saveAs(ParaPlanInitParams initParams){
        String c0,achId,achNm,c0_id = " ",m_time;
        LocalDateTime now=LocalDateTime.now();
        c0=initParams.getChecked0();
        m_time=LocalDateTime2String(now);
        achId="SLHRES"+m_time+"测试员";
        achNm = LocalDate2String(LocalDate.now())+"石梁河预报测试员方案"+now.getHour()+":"+now.getMinute()+":"+now.getSecond();
        if(c0.contains("临沭分区")){
            c0_id="SLHRES0001";
        }
        if(c0.contains("石门分区")){
            c0_id="SLHRES0002";
        }
        if(c0.contains("大兴镇分区")){
            c0_id="SLHRES0003";
        }
        if(c0.contains("张疃分区")){
            c0_id="SLHRES0004";
        }
        if(c0.contains("库区周边分区")){
            c0_id="SLHRES0005";
        }
        anaMapper.saveAs(achId,achNm,c0_id,c0,"测试员",0,m_time,initParams.getP1(),initParams.getP2(),initParams.getP3(),initParams.getP4(),initParams.getP5(),initParams.getP6(),initParams.getP7(),initParams.getP8(),initParams.getP9(),initParams.getP10(),initParams.getP11(),initParams.getP12(),initParams.getP13(),initParams.getP14(),initParams.getP15(),initParams.getP16(),initParams.getP17(),initParams.getP18());
    }

    @Override
    public void setOnline(ParaPlanInitParams initParams){
         anaMapper.setOnline(initParams.getChecked());
    }

    @Override
    public void del(ParaPlanInitParams initParams){
        anaMapper.del(initParams.getChecked(),initParams.getChecked0());
    }
    public static String LocalDateTime2String(LocalDateTime dt){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return  dtf.format(dt);
    }

    public static String LocalDate2String(LocalDate date){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return  dtf.format(date);
    }


}
