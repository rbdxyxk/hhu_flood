package cn.hhu.controller;

import cn.hhu.Bean.AutoCalInitParams;
import cn.hhu.Bean.ForecastManualInitParams;
import cn.hhu.mode.ModCalClass;
import cn.hhu.service.AutoCalService;
import cn.hhu.service.ForecastManualService;
import cn.hhu.service.impl.AutoCalServiceImpl;
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
 * @author yk
 * @description TODO
 * @date 2023/12/25 19:35
 */

@Controller

public class AutoCalController {

    @Autowired
    AutoCalService acs;
    @RequestMapping("/AutoInit")
    @ResponseBody
    public AutoCalInitParams AutoCalInit(){
        return acs.AutoOninit();
    }
    @RequestMapping("/btnClick1")
    @ResponseBody
    public String btnClick1(@RequestBody AutoCalInitParams initParams, HttpServletResponse response) throws ParseException {
        response.setCharacterEncoding("utf-8");
        boolean hasNull=initParams.getDate()==null
                ||initParams.getHour()==null
                ||initParams.getP1()==null
                ||initParams.getP2()==null
                ||initParams.getP3()==null
                ||initParams.getP4()==null
                ||initParams.getP5()==null
                ||initParams.getP7()==null
                ||initParams.getChecked()==null
                ||initParams.getRiverInfo()==null
                ||initParams.getRsvrInfo()==null;
        if(hasNull){
            return "输入有误,各参数不能为空";
        }
        if( initParams.getHour()<0
                ||initParams.getHour()>24)
        {
            //response.getWriter().write("<script>alert(\"" + "计算起始时间输入有误" + "\");</script>");
            return "计算起始时间输入有误";
        }
        String feedback1,id,name,name0="";
        feedback1=acs.saveborderPara(initParams);
        //以下调用ModClass模型开始写入数据
        id=acs.backId();
        System.out.println(id);
        System.out.println("1111111111");
        ModCalClass modSour0;
        modSour0=new ModCalClass();
        modSour0.setCal_Id(id);
//        name=initParams.getChecked();
//        if(name.contains("当前运行方案")){
//            name0=name.substring(0,name.length()-8);
//        }
//        else{
//            name0=name;
//        }
        modSour0.setCal_ParaName(initParams.getParaForecastNow());
        modSour0.CalClickBegin('0');
//         modSour0.writeData(date,initParams.getHour(),(double)initParams.getHour(),'1');
        //LocalDateTime tm= initParams.getDate().atTime(initParams.getHour(),0,0);
        return feedback1;
    }

}
