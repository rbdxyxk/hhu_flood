package cn.hhu.controller;

import cn.hhu.Bean.ForecastManualInitParams;
import cn.hhu.Bean.ParaPlanInitParams;
import cn.hhu.service.ForecastManualService;
import cn.hhu.service.ParaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;

@Controller
public class ParaPlanController {
    @Autowired
    ParaService ps;
    @RequestMapping("/ParaInit")
    @ResponseBody
    public ParaPlanInitParams PsInit(){
        return ps.PsOninit();
    }
    @RequestMapping("/checkValue")
    @ResponseBody
    public ParaPlanInitParams PsCheck(@RequestBody ParaPlanInitParams initParams){
        System.out.println("测试"+initParams);
        return ps.SelectValue(initParams.getChecked0(),initParams.getChecked());
    }
    @RequestMapping("/save")
    @ResponseBody
    public String  PsSave(@RequestBody ParaPlanInitParams initParams){
         ps.save(initParams);
         return "保存成功！";
    }
    @RequestMapping("/saveAs")
    @ResponseBody
    public String  PsSaveAs(@RequestBody ParaPlanInitParams initParams){
        ps.saveAs(initParams);
        return "另存成功！";
    }
    @RequestMapping("/setOnline")
    @ResponseBody
    public String  PsSetOnline(@RequestBody ParaPlanInitParams initParams){

        ps.setOnline(initParams);
//        ps.PsOninit();
        return "设置成功！";
    }
    @RequestMapping("/del")
    @ResponseBody
    public String PsDel(@RequestBody ParaPlanInitParams initParams){
        ps.del(initParams);
        return "删除成功！";
    }
}
