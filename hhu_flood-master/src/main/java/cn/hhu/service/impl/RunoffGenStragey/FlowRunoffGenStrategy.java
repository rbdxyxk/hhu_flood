package cn.hhu.service.impl.RunoffGenStragey;

import cn.hhu.service.impl.RunoffGenServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tlj
 * @description TODO
 * @date 2022/11/24 18:35
 */


public class FlowRunoffGenStrategy implements RunoffGenStrategy{
    private static final BigDecimal a = new BigDecimal("0.9524");
    private static final BigDecimal KA = new BigDecimal("0.9682");
    private static final BigDecimal b = new BigDecimal("100");
    private static final BigDecimal pa0 = new BigDecimal("75");
    private static final BigDecimal Wm = new BigDecimal("110");//pa<=Wm

    /*Q=a(P+Pa)-b
    *Q=0.9524(P+pa)-100
    *Pa[t+1] = Ka * ( Pa[t] + P[t] ) Pa[t]初始值70
    *
    * */
    @Override
    public Map<String,List<RunoffGenServiceImpl.RunoffGenData>> ComputeRunoffGen(Map<String, Map<LocalDate, BigDecimal>> map, LocalDateTime begin, LocalDateTime end) {
        Map<String,List<RunoffGenServiceImpl.RunoffGenData>> results = new HashMap<>();
        for(Map.Entry<String, Map<LocalDate, BigDecimal>> entry :map.entrySet()){
            //new AbstractMap.SimpleEntry<String,t1>()
            int i=0;
            //前雨量
            BigDecimal pa=pa0;
            List<RunoffGenServiceImpl.RunoffGenData> list = new ArrayList<>();
            for(Map.Entry<LocalDate, BigDecimal> entry1 : entry.getValue().entrySet()){
                //雨量
                BigDecimal p=entry1.getValue();

                if(i<10){

                    i++;
                }else {
                    //产流量
                    //Q=0.9524(P+pa)-100
                    BigDecimal Q=a.multiply(p.add(pa)).subtract(b);
                    //entry1.setValue(Q.setScale(6,3));

                    list.add(new RunoffGenServiceImpl.RunoffGenData(entry1.getKey()
                            ,p.setScale(1,BigDecimal.ROUND_HALF_UP)
                            ,Q.setScale(1,BigDecimal.ROUND_HALF_UP).max(BigDecimal.ZERO)));
                }

                //Pa[t+1] = Ka * ( Pa[t] + P[t] )
                pa=KA.multiply(p.add(pa));
                //pa<=Wm
                if(pa.compareTo(Wm)>0){
                    pa=Wm;
                }
            }
            results.put(entry.getKey(),list);
        }

        return results;
    }
}

