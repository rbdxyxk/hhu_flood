package cn.hhu.controller;

import cn.hhu.Bean.Mod_Fruit;
import cn.hhu.Bean.Mod_MidResult;
import cn.hhu.Bean.Para_Calplan;
import cn.hhu.mapper.AnaResultMapper;
import cn.hhu.mode.ModCalClass;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author Gyk
 */
@Controller
@RequestMapping(path = "/anaResult", method = RequestMethod.POST)
public class AnaResultController {

    @Autowired
    private AnaResultMapper anaResultMapper;
    private ModCalClass modSour = new ModCalClass();

    @RequestMapping(path = "findAll", method = RequestMethod.POST)
    @ResponseBody
    public String findAll(){
        List<Para_Calplan> all = anaResultMapper.findAll();

        for(Para_Calplan cur : all){
            System.out.println(cur.toString());
        }

        return JSONObject.toJSONString(all);
    }

    @RequestMapping(path = "/findAuto", method = RequestMethod.GET)
    @ResponseBody
    public String findAuto() throws ParseException {
        List<Para_Calplan> all = anaResultMapper.findAutoCal();
        String planid=" ";
        String paramid=" ";
        String strTmp=" ";

        if(!all.isEmpty()){
            paramid=all.get(0).getParamId();
            planid=all.get(0).getId();
            modSour.setCal_Id(planid);
            modSour.setCal_ParaId(paramid);
            strTmp = modSour.CalClickBegin('1');
            if(strTmp.contains("calculating is ok. ")){
                return "最近自动预报时间"+LocalDateTime2String(LocalDateTime.now());
            }
        }

        return "do not have any record";
    }

    @RequestMapping(path = "/findById", method = RequestMethod.POST)
    @ResponseBody
    public String findById(@RequestParam(name = "id") String id){
        System.out.println(id);
        Para_Calplan byId = anaResultMapper.findById(id);
        System.out.println(byId.toString());
        return JSONObject.toJSONString(byId);
    }

    @RequestMapping(path = "/findByStatus", method = RequestMethod.POST)
    @ResponseBody
    public String findByStatus(@RequestParam(name = "id") String id,
                               @RequestParam(name = "startTime") int startTime,
                               @RequestParam(name = "endTime") int endTime,
                               @RequestParam(name = "status") int status,
                               @RequestParam(name = "CalRegCD", required = false) String CalRegCD){

        if (status != 3){
            List<Mod_Fruit> byStatus12 = anaResultMapper.findByStatus12(id, startTime, endTime);
            return JSONObject.toJSONString(byStatus12);
        }else {
            List<Mod_MidResult> byStatus3 = anaResultMapper.findByStatus3(id, startTime, endTime, CalRegCD);
            return JSONObject.toJSONString(byStatus3);
        }

    }

    @RequestMapping(path = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public String edit(@RequestParam(name = "id") String id,
                               @RequestParam(name = "flowtm") String flowtm,
                               @RequestParam(name = "data1") double data1,
                               @RequestParam(name = "data2") double data2,
                               @RequestParam(name = "status", required = false) int status){

        anaResultMapper.updateData(id, flowtm, data1, data2, status);

        return "null";
    }

    @RequestMapping(path = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public String delete(@RequestParam(name = "id") String id,
                       @RequestParam(name = "flowtm",required = false) String flowtm){

        if (flowtm != null){
            anaResultMapper.deleteDataBy2(id, flowtm);
        }else {
            anaResultMapper.deleteData1(id);
            anaResultMapper.deleteData2(id);
        }

        return "null";
    }

    public static String LocalDateTime2String(LocalDateTime dt){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return  dtf.format(dt);
    }

}
