package cn.hhu.controller;

import cn.hhu.Bean.AutoCalInitParams;
import cn.hhu.Bean.DisResFlood;
import cn.hhu.Bean.ForecastManualInitParams;
import cn.hhu.mode.ModCalClass;
import cn.hhu.service.AutoCalService;
import cn.hhu.service.ForecastManualService;
import cn.hhu.service.impl.AutoCalServiceImpl;
import cn.hhu.service.impl.DisResServiceImpl;
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
 * @date 2023/5/5 19:35
 */

@Controller

public class DisResFloodController {

    @Autowired
    DisResServiceImpl drs;

    @RequestMapping("/but1CalDispatch")
    @ResponseBody
    public DisResFlood CalDispatch(@RequestBody DisResFlood initParms,HttpServletResponse response){
       return drs.CalDis(initParms.getP1(),initParms.getP2(),initParms.getP3(),initParms.getP4(),initParms.getP5());
    }

}

