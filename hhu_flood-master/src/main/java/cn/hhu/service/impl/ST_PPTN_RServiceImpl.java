package cn.hhu.service.impl;


import cn.hhu.Bean.ST_PPTN_R;
import cn.hhu.Bean.ST_STINFO_B;
import cn.hhu.mapper.ST_PPTN_RMapper;
import cn.hhu.service.ST_PPTN_RService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;


/**
 * @author tlj
 * @description TODO
 * @date 2022/6/29 10:57
 */

@Service
public class ST_PPTN_RServiceImpl implements ST_PPTN_RService {
    @Autowired
    ST_PPTN_RMapper st_pptn_rMapper;

    @Override
    public List<ST_PPTN_R> getRainInfo(String STCD, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        QueryWrapper<ST_PPTN_R> wrapper=new QueryWrapper<>();
        wrapper.select("DRP","TM");
        wrapper.eq("STCD",STCD);
        wrapper.between("TM",startDateTime,endDateTime);
        wrapper.orderByAsc("TM");

        return st_pptn_rMapper.selectList(wrapper);
    }

    @Override
    public List<ST_STINFO_B> getAllSTCDAndSTNM() {

        return st_pptn_rMapper.getAllSTCDAndSTNM();
    }

    @Override
    public List<ST_STINFO_B> getAllNewSTCDAndSTNM() {
        return st_pptn_rMapper.getAllNewSTCDAndSTNM();
    }

    //同时获取多个测站的时段雨量数据
    @Override
    public List<ST_PPTN_R> searchAllByStcdAndTm(String[] stcd, LocalDateTime startDatetime) {
        List<ST_PPTN_R> re = st_pptn_rMapper.searchAllByStcdAndTm(stcd,startDatetime,startDatetime.plusHours(72));
        System.out.println(startDatetime);
        return re;
    }

    //同时获取多个测站的时段雨量数据并按测站分组
    @Override
    public Map<String, List<ST_PPTN_R>> searchAllByStcdAndTm2Map(String[] stcd, LocalDateTime startDatetime) {
        List<ST_PPTN_R> list =searchAllByStcdAndTm(stcd,startDatetime);
        HashMap<String,List<ST_PPTN_R>> map =new HashMap<>();
        //将数据按stcd分开
        list.forEach( item->{
            if(!map.containsKey(item.getSTCD())){
                List<ST_PPTN_R> l = new LinkedList<>();
                l.add(item);
                map.put(item.getSTCD(),l);
            }else {
                map.get(item.getSTCD()).add(item);
            }
        });
        //若没有数据则加入null
        for(String st : stcd){
            if(!map.containsKey(st)){
                map.put(st,new LinkedList<ST_PPTN_R>());
            }
        }
        return map;
    }

    @Override
    public Map<String, List<ST_PPTN_R>> searchAllByStcdAndTm2Map(String[] stcd, LocalDateTime startDatetime, LocalDateTime endDatetime) {
        List<ST_PPTN_R> list  = st_pptn_rMapper.searchAllByStcdAndTm(stcd,startDatetime,endDatetime);

        //将数据按stcd分开


        return list.stream().collect(Collectors.groupingBy(
                ST_PPTN_R::getSTCD));
    }

    //同时获取多个测站的时段雨量数据并按测站分组再计算72小时，24小时，12小时雨量数据
    @Override
    public Map<String, Map> searchAllByStcdAndTm2Map2(String[] stcd, LocalDateTime startDatetime) {
        Map<String, List<ST_PPTN_R>> DataListMap = searchAllByStcdAndTm2Map(stcd, startDatetime);
        //得到开始时间72,12,24小时
        LocalDateTime startPlus12 = startDatetime.plusHours(12);
        LocalDateTime startPlus24 = startDatetime.plusHours(24);
        //LocalDateTime startPlus72 = startDatetime.plusHours(72);
        Iterator<Map.Entry<String, List<ST_PPTN_R>>> iterator = DataListMap.entrySet().iterator();
        Map<String, Map> resutMap =  new HashMap<>();
        resutMap.put("DataMap",DataListMap);
        while (iterator.hasNext()){
            Map.Entry<String, List<ST_PPTN_R>> next = iterator.next();
            HashMap<String,BigDecimal> map =new HashMap<String,BigDecimal>();
            List<ST_PPTN_R> list= next.getValue();
            //判断是否有值
            if(list.size()==0){

                resutMap.put(next.getKey(),null);
                continue;
            }
            Iterator<ST_PPTN_R> ST_PPTN_Rs = list.iterator();

            BigDecimal RainOf12Hour = new BigDecimal("0.0");
            BigDecimal RainOf24Hour = new BigDecimal("0.0");
            BigDecimal RainOf72Hour = new BigDecimal("0.0");
            //遍历list

            while (ST_PPTN_Rs.hasNext()){
                ST_PPTN_R next1 = ST_PPTN_Rs.next();
                //得到日期
                Instant instant = next1.getTM().toInstant();
                ZoneId zoneId = ZoneId.systemDefault();
                LocalDateTime ldt = instant.atZone(zoneId).toLocalDateTime();
                BigDecimal drp=next1.getDRP();
                if( drp==null|| ldt.compareTo(startDatetime)==0){

                }
                else if(ldt.compareTo(startPlus12)<=0){
                    RainOf12Hour =RainOf12Hour.add(drp);
                    RainOf24Hour =RainOf24Hour.add(drp);
                    RainOf72Hour =RainOf72Hour.add(drp);
                }else if(ldt.compareTo(startPlus24)<=0){

                    RainOf24Hour =RainOf24Hour.add(drp);
                    RainOf72Hour =RainOf72Hour.add(drp);
                }else {
                    RainOf72Hour =RainOf72Hour.add(drp);
                }
            }



                //将72小时降雨放入map中
                map.put("12hour",RainOf12Hour);
                map.put("24hour",RainOf24Hour);
                map.put("72hour",RainOf72Hour);
                resutMap.put(next.getKey(),map);


        }
        System.out.println(resutMap);
        return resutMap;
    }

}
