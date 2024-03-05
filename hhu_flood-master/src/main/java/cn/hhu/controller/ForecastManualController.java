package cn.hhu.controller;

import cn.hhu.Bean.ForecastManualInitParams;
import cn.hhu.mode.ModCalClass;
import cn.hhu.service.ForecastManualService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDateTime;

/**
 * @author tlj
 * @description TODO
 * @date 2022/12/25 19:35
 */

@Controller
public class ForecastManualController {
    @Autowired
    ForecastManualService fms;
    @RequestMapping("/FrmInit")
    @ResponseBody
    public ForecastManualInitParams FrmInit(){
        return fms.FrmOninit();
    }
    @RequestMapping("/FrmInit0")
    @ResponseBody
    public ForecastManualInitParams FrmInit0(){
        return fms.FrmOninit();
    }
    @RequestMapping("/btn1Click")
    @ResponseBody
    public String btn1Click(@RequestBody ForecastManualInitParams initParams, HttpServletResponse response) throws IOException, ParseException {
        System.out.println("测试"+initParams);
        //参数为空
        response.setCharacterEncoding("utf-8");
        boolean hasNull=initParams.getDate()==null
                ||initParams.getHour()==null
                ||initParams.getP1()==null
                ||initParams.getP2()==null
                ||initParams.getP3()==null
                ||initParams.getP4()==null
                ||initParams.getP5()==null
                ||initParams.getP9()==null
                ||initParams.getP7()==null
                ||initParams.getP8()==null
                ||initParams.getP10()==null
                ||initParams.getChecked()==null
                ||initParams.getRiverInfo()==null
                ||initParams.getRsvrInfo()==null;
        if(hasNull){

                //response.getWriter().write("<script>alert(\"" + "输入有误,各参数不能为空" + "\");</script>");
            return "输入有误,各参数不能为空";
            }
            //时段长都问题
            if(
                    initParams.getP10().compareTo(BigDecimal.valueOf(120))>0
                    ||initParams.getP10().compareTo(BigDecimal.ONE)<0){
                //response.getWriter().write("<script>alert(\"" + "计算时长输入有误,不能为空，且小于120小时" + "\");</script>");
                return"计算时长输入有误,不能为空，且小于120小时";
            }
        if( initParams.getHour()<0
                ||initParams.getHour()>24)
        {
            //response.getWriter().write("<script>alert(\"" + "计算起始时间输入有误" + "\");</script>");
            return "计算起始时间输入有误";
        }
        System.out.println(initParams.getChecked());
        System.out.print("222222222222222");
        String feedback1,id,name,name0="";
        feedback1=fms.saveborderPara(initParams);
        //以下调用ModClass模型开始写入数据
        id=fms.backId();
        System.out.println(id);
        System.out.println("1111111111");
        ModCalClass modSour0;
        modSour0=new ModCalClass();
        modSour0.setCal_Id(id);
        name=initParams.getChecked();
        if(name.contains("当前运行方案")){
            name0=name.substring(0,name.length()-8);
        }
        else{
            name0=name;
        }
        modSour0.setCal_ParaName(name0);
        modSour0.CalClickBegin('0');
//         modSour0.writeData(date,initParams.getHour(),(double)initParams.getHour(),'1');
        //LocalDateTime tm= initParams.getDate().atTime(initParams.getHour(),0,0);
        return feedback1;
    }

}
