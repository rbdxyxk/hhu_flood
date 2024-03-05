package cn.hhu.controller;

import cn.hhu.Bean.ST_STINFO_B;
import cn.hhu.service.ST_RSVR_RService;
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
 * @description TODO
 * @date 2022/10/20 17:40
 */

@Controller
public class RsvrInfoController {
    @Autowired
    ST_RSVR_RService st_rsvr_rService;
    @GetMapping("/RSVR/{tm}/querries")
    @ResponseBody
    public Map getAllRainInfo(@RequestParam("list") String[] list,
                              @PathVariable("tm") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startDateTime
    ){

       // return st_pptn_rService.searchAllByStcdAndTm2Map2(list,startDateTime);
        return st_rsvr_rService.searchAllByStcdAndTm2Map(list,startDateTime);
    }

    @GetMapping("/RSVR/{tm}/{tm1}/querries")
    @ResponseBody
    public Map getAllRainInfo(@RequestParam("list") String[] list,
                              @PathVariable("tm") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startDateTime
                              ,@PathVariable("tm1") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endDateTime
    ){

        // return st_pptn_rService.searchAllByStcdAndTm2Map2(list,startDateTime);
        return st_rsvr_rService.searchAllByStcdAndTm2Map(list,startDateTime,endDateTime);
    }

    @GetMapping("/rsvr/st/5")
    @ResponseBody
    public List<ST_STINFO_B> getAllSTNMAndSTCD(){

        return st_rsvr_rService.getAllSTCDAndSTNMstartWith5();
    }
}
