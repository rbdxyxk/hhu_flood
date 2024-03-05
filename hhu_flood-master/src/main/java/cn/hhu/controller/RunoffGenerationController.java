package cn.hhu.controller;

import cn.hhu.service.RunoffGenService;
import cn.hhu.service.impl.RunoffGenServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author tlj
 * @description TODO 产流量计算
 * @date 2022/11/24 16:09
 */

@RestController
public class RunoffGenerationController {
    @Autowired
    RunoffGenService runoffGenService;
    //获取各个测站的产流量数据
    @GetMapping("/runoffGen/{tm}/{tm1}/quarries")
    public Map<String, List<RunoffGenServiceImpl.RunoffGenData>> getAllRunoffGenData(
        @RequestParam("list") String[] stcds,
        @PathVariable("tm") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate
         ,@PathVariable("tm1") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate){
//        /*
//          前10天为计算初始pa所需数据

//        */

        return runoffGenService.getAllRunoffGenData(stcds,startDate.atTime(0,0,0).minusDays(10),
                endDate.plusDays(1).atTime(0,0,0));
    }
}
