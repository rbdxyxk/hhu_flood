package cn.hhu.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author tlj
 * @description 定义一些防汛工具箱的通用方法
 * @date 2022/10/26 14:43
 */

public interface FloodPreventionKit <T>{
      List<T> searchAllByStcdAndTm(String[] stcd,
                                 LocalDateTime startDatetime,
                                 LocalDateTime endDatetime);
    default Map<String, List<T>> searchAllByStcdAndTm2Map(String[] stcd,
                                                          LocalDateTime startDatetime,
                                                          LocalDateTime endDatetime)
    {
        List<T> list =searchAllByStcdAndTm(stcd,startDatetime,endDatetime);
        Logger logger = LoggerFactory.getLogger(this.getClass());
        Map<String, List<T>> collect =
                list.stream().collect(Collectors.groupingBy(item->{
                    String stcd1="";
                    try {
                        Method method = item.getClass().getMethod("getSTCD");
                        stcd1= method.invoke(item).toString();
                    } catch (Exception e) {
                        logger.error(e.toString());
                    }
                    return stcd1;
                }));
        return collect;
    }
}
