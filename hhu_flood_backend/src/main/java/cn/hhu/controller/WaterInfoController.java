package cn.hhu.controller;

import cn.hhu.Bean.*;
import cn.hhu.service.STRiverRService;
import cn.hhu.service.ST_PPTN_RService;
import cn.hhu.service.ST_PUMPService;
import cn.hhu.service.ST_RSVR_RService;
import cn.hhu.service.impl.DischarveServiceImpl;
import cn.hhu.service.impl.ST_PUMPServiceImpl;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
/**
 * @author tlj
 */
@Controller
@RequestMapping("waterInfo")
public class WaterInfoController {
    @Autowired
    ST_PUMPServiceImpl pumpService;
    @Autowired
    DischarveServiceImpl dis_service;
    @Autowired
    STRiverRService STRRS;
    @Autowired
    ST_PPTN_RService st_pptn_rService;
    @Autowired
    ST_RSVR_RService st_rsvr_rService;

    //获取指定测站和时间的河道水情信息
    @ResponseBody
    @GetMapping("river/{STCD}/{startDateTime}/{endDateTime}")
    public List<ST_RIVER_R> getRiverInfo(@PathVariable String STCD,
                                         @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startDateTime,
                                         @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endDateTime){
        return STRRS.getDataByStcdAndDate(STCD,startDateTime,endDateTime);
    }
    //获取雨量站
    @ResponseBody
    @GetMapping("rain/ST")
    public List<ST_STINFO_B> getAllRainSTNMAndSTCD(){
        return st_pptn_rService.getAllSTCDAndSTNM();
    }
    @ResponseBody
    @GetMapping("rain/getRainInfo/{STCD}/{startDateTime}/{endDateTime}")
    public List<ST_PPTN_R> getRainInfo(@PathVariable String STCD,
                                       @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startDateTime,
                                       @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endDateTime){
        return st_pptn_rService.getRainInfo(STCD,startDateTime,endDateTime);
    }
    @ResponseBody
    @GetMapping("was/getWasInfo/{STCD}/{startDateTime}/{endDateTime}")
    public List<ST_WAS_R> getWasInfo(@PathVariable String STCD,
                                      @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startDateTime,
                                      @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endDateTime){
        
        return dis_service.getDataByStcdAndDate(STCD,startDateTime,endDateTime);
    }
    @ResponseBody
    @GetMapping("pump/getPumpInfo/{STCD}/{startDateTime}/{endDateTime}")
    public List<ST_PUMP_R> getPumpInfo(@PathVariable String STCD,
                                     @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startDateTime,
                                     @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endDateTime){

        return pumpService.getDataByStcdAndDate(STCD,startDateTime,endDateTime);
    }
    //获取所有河流测站编码
    @ResponseBody
    @GetMapping("RSVR/ST")
    public List<ST_STINFO_B> getAllRiverSTNMAndSTCD(){
        return st_rsvr_rService.getAllSTCDAndSTNM();
    }

    //水库水情
    @ResponseBody
    @GetMapping("river/ST")
    public List<ST_STINFO_B> getAllRsvrSTNMAndSTCD(){
        return STRRS.getAllSTCDAndSTNM();
    }

    @ResponseBody
    @GetMapping("dd/ST")
    public List<ST_STINFO_B> getAllDdSTNMAndSTCD(){
        return dis_service.getAllSTCDAndSTNM();
    }

    @ResponseBody
    @GetMapping("dp/ST")
    public List<ST_STINFO_B> getAllDpSTNMAndSTCD(){
        return pumpService.getAllSTCDAndSTNM();
    }


    @ResponseBody
    @GetMapping("RSVR/{STCD}/{startDateTime}/{endDateTime}")
    public List<ST_RSVR_R> getRSVRInfo(@PathVariable String STCD,
                                       @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startDateTime,
                                       @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endDateTime){
        return st_rsvr_rService.getRsvrInfo(STCD,startDateTime,endDateTime);
    }

    //获取部分水库水情信息
    @ResponseBody
    @GetMapping("rain/getRainInfo/{STCD}/{STCD1}/{startDateTime}/{endDateTime}")
    public List<JSONObject> getRsvrAndPptnInfo(@PathVariable String STCD,
                                               @PathVariable String STCD1,
                                               @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startDateTime,
                                               @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endDateTime){
        List<ST_PPTN_R> rainInfo = st_pptn_rService.getRainInfo(STCD, startDateTime, endDateTime);
        List<ST_RSVR_R> rsvrInfo = st_rsvr_rService.getRsvrInfo(STCD1, startDateTime, endDateTime);

        int n= rainInfo.size();
        int m = rsvrInfo.size();

        System.out.println("开始了");
        System.out.println(STCD1);

        List<JSONObject> list =new LinkedList<>();

        int i=0,j= 0;
        for (; (i< n)&&(j<m); ) {
            JSONObject data = new JSONObject();
            ST_PPTN_R temp1=rainInfo.get(i);
            ST_RSVR_R temp2=rsvrInfo.get(j);
            Date tm1=temp1.getTM();
            Date tm2=temp2.getTM();
            if(tm1.compareTo(tm2)==0){

                data.put("TM",tm1);
                data.put("DRP",temp1.getDRP());
                data.put("RZ",temp2.getRZ());
                data.put("OTQ",temp2.getOTQ());
                i++;
                j++;
            }else if(tm1.compareTo(tm2)>0){

                data.put("TM",tm2);
                data.put("DRP",null);
                data.put("RZ",temp2.getRZ());
                data.put("OTQ",temp2.getOTQ());
                j++;
            }else {

                data.put("TM",tm1);
                data.put("DRP",temp1.getDRP());
                data.put("RZ",null);
                data.put("OTQ",null);
                i++;
            }
            list.add(data);
        }
        if(i<n){
            for(;i<n;i++){
                JSONObject data = new JSONObject();

                data.put("TM",rainInfo.get(i).getTM());
                data.put("DRP",rainInfo.get(i).getDRP());
                data.put("RZ",null);
                data.put("OTQ",null);
                list.add(data);
            }
        }
        else{
            for(;j<m;j++){

                JSONObject data = new JSONObject();
                data.put("TM",rsvrInfo.get(j).getTM());
                data.put("DRP",null);
                data.put("RZ",rsvrInfo.get(j).getRZ());
                data.put("OTQ",rsvrInfo.get(j).getOTQ());
                i++;
                list.add(data);
            }
        }


        System.out.println(list);
        return list;
    }

    @ResponseBody
    @GetMapping("rain/getRainInfo2/{STCD}/{STCD2}/{startDateTime}/{endDateTime}")
    public List<JSONObject> getRiverAndPptnInfo(@PathVariable String STCD,
                                               @PathVariable String STCD2,
                                               @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startDateTime,
                                               @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endDateTime){
        List<ST_PPTN_R> rainInfo = st_pptn_rService.getRainInfo(STCD, startDateTime, endDateTime);
        List<ST_RIVER_R> riverInfo =  STRRS.getDataByStcdAndDate(STCD2, startDateTime, endDateTime);

        int n= rainInfo.size();
        int m = riverInfo.size();

        System.out.println("开始了111111111");
        System.out.println(STCD2);

        List<JSONObject> list =new LinkedList<>();

        int i=0,j= 0;
        for (; (i< n)&&(j<m); ) {
            JSONObject data = new JSONObject();
            ST_PPTN_R temp1=rainInfo.get(i);
            ST_RIVER_R temp2=riverInfo.get(j);
            Date tm1=temp1.getTM();
            Date tm2=temp2.getTM();
            if(tm1.compareTo(tm2)==0){

                data.put("TM",tm1);
                data.put("DRP",temp1.getDRP());
                data.put("Z",temp2.getZ());
                data.put("Q",temp2.getQ());
                i++;
                j++;
            }else if(tm1.compareTo(tm2)>0){

                data.put("TM",tm2);
                data.put("DRP",null);
                data.put("Z",temp2.getZ());
                data.put("Q",temp2.getQ());
                j++;
            }else {

                data.put("TM",tm1);
                data.put("DRP",temp1.getDRP());
                data.put("Z",null);
                data.put("Q",null);
                i++;
            }
            list.add(data);
        }
        if(i<n){
            for(;i<n;i++){
                JSONObject data = new JSONObject();

                data.put("TM",rainInfo.get(i).getTM());
                data.put("DRP",rainInfo.get(i).getDRP());
                data.put("Z",null);
                data.put("Q",null);
                list.add(data);
            }
        }
        else{
            for(;j<m;j++){

                JSONObject data = new JSONObject();
                data.put("TM",riverInfo.get(j).getTM());
                data.put("DRP",null);
                data.put("Z",riverInfo.get(j).getZ());
                data.put("Q",riverInfo.get(j).getQ());
                i++;
                list.add(data);
            }
        }


        System.out.println(list);
        return list;
    }
}
