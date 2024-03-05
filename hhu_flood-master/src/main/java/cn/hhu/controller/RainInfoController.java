package cn.hhu.controller;

import cn.hhu.Bean.ST_STINFO_B;
import cn.hhu.service.ST_PPTN_RService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author tlj
 * @description TODO
 * @date 2022/9/30 19:32
 */

@Controller
public class RainInfoController {
    @Autowired
    ST_PPTN_RService st_pptn_rService;
    //获取所有新的测站
    @GetMapping("/pptn/st")
    public List<ST_STINFO_B> getAllNewRainSTNMAndSTCD(){

        return st_pptn_rService.getAllSTCDAndSTNM();
    }
    //获取所有新的测站
    @GetMapping("/pptn/st5")
    @ResponseBody
    public List<ST_STINFO_B> getAllNewRainSTNMAndSTCDstart5(){

        return st_pptn_rService.getAllNewSTCDAndSTNM();
    }
    @GetMapping("/pptn/{tm}/querries")
    @ResponseBody
    public Map getAllRainInfo(@RequestParam("list") String[] list,
                              @PathVariable("tm") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startDateTime
                                 ){
        //new HashMap<>().remove();
        return st_pptn_rService.searchAllByStcdAndTm2Map2(list,startDateTime);
    }


}
