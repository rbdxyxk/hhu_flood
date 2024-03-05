package cn.hhu.service.impl;

import cn.hhu.Bean.ST_PPTN_R;
import cn.hhu.service.RunoffGenService;
import cn.hhu.service.ST_PPTN_RService;
import cn.hhu.service.impl.RunoffGenStragey.FlowRunoffGenStrategy;
import cn.hhu.service.impl.RunoffGenStragey.RunoffGenStrategy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author tlj
 * @description TODO 产流量计算
 * @date 2022/11/24 16:12
 */

@Service
public class RunoffGenServiceImpl implements RunoffGenService {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor

    public static class RunoffGenData{
        private LocalDate date;
        private BigDecimal rain;
        private BigDecimal runoffGen;
    }

    @Autowired
    ST_PPTN_RService st_pptn_rService;


    private RunoffGenStrategy strategy = new FlowRunoffGenStrategy();
   // private static final BigDecimal ZER0 = new BigDecimal("0.0");

//    //获取每个测站每天的降雨量s
    @Deprecated
    private Map<String, Map<LocalDate, BigDecimal>> getRainOneDay(String[] stcds, LocalDateTime startDate, LocalDateTime endDate){
        Map<String, List<ST_PPTN_R>> map = st_pptn_rService.searchAllByStcdAndTm2Map(stcds, startDate, endDate);
//        LocalDate startDate = startDatetime.toLocalDate();
        System.out.println(map);
        //由于没有降雨不会记录需要填充
        for(String stcd : stcds){
            if (!map.containsKey(stcd)){
                List<ST_PPTN_R> list = new ArrayList<>();
//                ST_PPTN_R st_pptn_r = new ST_PPTN_R();
//                st_pptn_r.setDRP(BigDecimal.ZERO);
//                st_pptn_r.setDYP(BigDecimal.ZERO);
//                st_pptn_r.setTM(LocalDateTime2Date(startDate));
//                list.add(st_pptn_r);
                map.put(stcd,list);
            }
        }
        Map<String, Map<LocalDate, BigDecimal>> results = new HashMap<>();

        for(Map.Entry<String, List<ST_PPTN_R>> entry:map.entrySet()){
            //需要按插入顺序
            Map<LocalDate, BigDecimal> stRainMap=new LinkedHashMap<>();
            LocalDateTime start =startDate.plusDays(0);
            LocalDateTime end = startDate.plusDays(1);
//            double sum = entry.getValue().stream().filter(
//                    item ->
//                    {
//                        LocalDateTime itemTime = Date2LocalDateTime(item.getTM());
//                        return itemTime.isAfter(start) && itemTime.isBefore(end);
//                    }
//            ).mapToDouble(item -> item.getDRP().doubleValue()).sum();

            //按时间有序的数据 //.stream().sorted()
            List<ST_PPTN_R> list = entry.getValue();
            for (int i = 0 , n = list.size(); start.isBefore(endDate) ; i++) {
                    //当没有数据时默认为雨量0
                    if(i>=n){
                        stRainMap.put(start.toLocalDate(),BigDecimal.ZERO);
                        start=start.plusDays(1);
                        end=end.plusDays(1);
                        continue;
                    }
                    ST_PPTN_R item= list.get(i);
                    LocalDateTime itemTime = Date2LocalDateTime(item.getTM());
                    //如果小于当前起始时间便跳过
                    if(itemTime.isBefore(start)){
                        continue;
                    }
                    //小于当前结束时间
                    if(itemTime.isBefore(end)){
                        LocalDate keyDate = start.toLocalDate();
                        //若DYP!=0
                        if(item.getDYP().compareTo(BigDecimal.ZERO)!=0){
                            stRainMap.put(keyDate,item.getDYP());
                            startDate=startDate.plusDays(1);
                            end=end.plusDays(1);
                            continue;
                        }
                        BigDecimal num =new BigDecimal("0");
                        //不断循环叠加
                        while(itemTime.isBefore(end)){

                            num=num.add(item.getDRP());
                            i++;
                            if(i==n){
                                break;
                            }
                            item= list.get(i);
                            itemTime = Date2LocalDateTime(item.getTM());

                        }
                        stRainMap.put(keyDate,num);

                        //stRainMap.put(start1,stRainMap.getOrDefault(start1,new BigDecimal(0)).add(item.getDRP()));
                    }else {
                            //使 i不增加
                          i--;
                          stRainMap.put(start.toLocalDate(),BigDecimal.ZERO);

                    }
                start=start.plusDays(1);
                end=end.plusDays(1);


            }

        results.put(entry.getKey(),stRainMap);
        }
    return results;
    }
    //获取每个测站每天的降雨量每天8点为前一天的降雨量
    private Map<String, Map<LocalDate, BigDecimal>> getRainDataOneDay(Map<String,List<ST_PPTN_R>> rainData, LocalDateTime startDate, LocalDateTime endDate){
        Map<String, Map<LocalDate, BigDecimal>> results = new HashMap<>();
        for (Map.Entry<String,List<ST_PPTN_R>> entry:rainData.entrySet()){
            //得到map
            Map<LocalDateTime, BigDecimal> collect = entry.getValue().stream().
                 collect(Collectors.toMap(
                    item -> Date2LocalDateTime(item.getTM()),
                    ST_PPTN_R::getDYP,
                         (key1,key2)->key1

            ));
//            Map<LocalDateTime, BigDecimal> collect = new HashMap<>();
//            entry.getValue().forEach(
//                   item->{
//                       System.out.println(Date2LocalDateTime(item.getTM())+":"+item.getTM());
//                       collect.put(Date2LocalDateTime(item.getTM()),item.getDYP());
//                   }
//
//            );
          //ArrayList<Map.Entry<LocalDate, BigDecimal>> list = new ArrayList<>();
//            System.out.println(collect);
           Map<LocalDate, BigDecimal> map= new LinkedHashMap<>();
            LocalDate start  = startDate.toLocalDate();
            LocalDate end = endDate.toLocalDate();
            while (start.isBefore(end)){
                //每天的雨量数据在下一天8:00
                map.put(start,collect.getOrDefault(start.plusDays(1).atTime(8,0,0),BigDecimal.ZERO));
                start=start.plusDays(1);
            }
            results.put(entry.getKey(),map);
        }
        return results;
    }

    //获取平均雨量
    private  Map<LocalDate, BigDecimal> computeAvgRain(Map<String, Map<LocalDate, BigDecimal>> map){
        Map<LocalDate, BigDecimal> avg = new LinkedHashMap<>();

        int n =map.size();
        if(n<=1){
            return avg;
        }
        BigDecimal len = new BigDecimal(n);
        String stcd=null;
        //获取第一个测站名
        for (String key :map.keySet()){
            stcd=key;
            break;
        }
        //取平均值
       for(LocalDate key :map.get(stcd).keySet()){
           BigDecimal temp =BigDecimal.ZERO;
           for(Map<LocalDate, BigDecimal> value:map.values()){
               temp=temp.add(value.get(key));
           }
           avg.put(key,temp.divide(len, RoundingMode.HALF_UP));
       }
       return  avg;
    }

    public static LocalDateTime Date2LocalDateTime(Date date){
        return Instant.ofEpochMilli(date.getTime())
                .atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
    public static Date LocalDateTime2Date(LocalDateTime dateTime){
        return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    //当map中没有该测站的数据进行填充
    public static <T> void mapFill(String[] stcds ,Map<String,List<T>>map){
        for(String stcd : stcds){
            if (!map.containsKey(stcd)){
                List<T> list = new ArrayList<>();
                map.put(stcd,list);
            }
        }
    }

    @Override
    public Map<String,List<RunoffGenData>> getAllRunoffGenData(String[] stcds, LocalDateTime startDate, LocalDateTime endDate){
        Map<String, List<ST_PPTN_R>> rainData = st_pptn_rService.searchAllByStcdAndTm2Map(stcds, startDate, endDate);
        //填充数据
        mapFill(stcds,rainData);
        //计算当天的雨量
         Map<String, Map<LocalDate, BigDecimal>> rainOneDay = getRainDataOneDay(rainData, startDate, endDate);
         rainOneDay.put("avg",computeAvgRain(rainOneDay));
         // Map<String, Map<LocalDate, BigDecimal>> rainOneDay = getRainOneDay(stcds, startDate, endDate);
        //System.out.println(rainOneDay);

        //Map<String,Map> map=new HashMap<>();
        //计算产流量
        return strategy.ComputeRunoffGen(rainOneDay,startDate,endDate);
    }
//public static void main(String[] args) {
//    LocalDate now = LocalDate.now();
//
//    System.out.println(now);
//
//    Date date = new Date();
//    LocalDateTime localDateTime = Instant.ofEpochMilli(date.getTime())
//            .atZone(ZoneId.systemDefault()).toLocalDateTime();
//    System.out.println("Date类型输出：" + date);
//    System.out.println(localDateTime);
//    System.out.println(new BigDecimal(0.0).compareTo(new BigDecimal(0)));
//    //ZER0.add(new BigDecimal(1));
//    //System.out.println(ZER0);
//    System.out.println(LocalDate.now().plusDays(1).equals(LocalDate.now().plusDays(1)));
//}
}
