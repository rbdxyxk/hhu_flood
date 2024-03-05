package cn.hhu.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author tlj
 * @description TODO
 * @date 2022/12/26 16:31
 */
@Mapper
@Repository
public interface ParaWeatherMapper {
    @Insert("insert into forcastflood.para_weather(Id,CalRegCD,ModifiedDate,RainHour,RainFirst,RainSecond,RainThird,Eva,W,FlowDeep) " +
            "values(#{param1},#{param2},#{param3},#{param4},#{param5},#{param6},#{param7},#{param8},#{param9},#{param10})")
    Integer insertParaWeather(String id,
                              String CalRegCD,
                              LocalDateTime modifiedDate,
                              BigDecimal RainHour,
                              BigDecimal RainFirst,
                              BigDecimal RainSecond,
                              BigDecimal RainThird,
                              BigDecimal Eva,
                              BigDecimal W,
                              BigDecimal FlowDeep);
}
