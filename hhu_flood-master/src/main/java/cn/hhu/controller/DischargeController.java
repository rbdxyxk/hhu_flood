package cn.hhu.controller;

import cn.hhu.Bean.ST_STINFO_B;
import cn.hhu.service.DischargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author tlj
 * @description 实现防汛工具箱中泄流量相关功能
 * @date 2022/11/4 15:39
 */
@Controller
public class DischargeController {

    @Autowired
    private DischargeService dischargeService;
    /**
     * @description 获取所有的相关测站名和测站编码
     * @return List<ST_STINFO_B>
     */
    @GetMapping("/waterInfo/was/ST")
    @ResponseBody
    public List<ST_STINFO_B> getAllSTCDAndSTNM(){
        return  dischargeService.getAllSTCDAndSTNM();
    }

    /**
     * @description 返回所有请求测站在时间内的was信息
     * @param list
     * @param startDateTime
     * @param endDateTime
     * @return Map
     */
    @GetMapping("/was/{tm}/{tm1}/querries")
    @ResponseBody
    public Map getAllRainInfo(@RequestParam("list") String[] list,
                              @PathVariable("tm") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startDateTime
            , @PathVariable("tm1") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endDateTime
    ){

        // return st_pptn_rService.searchAllByStcdAndTm2Map2(list,startDateTime);
        return dischargeService.searchAllByStcdAndTm2Map(list,startDateTime,endDateTime);
    }

}
