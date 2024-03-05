package cn.hhu.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author tlj
 * @description TODO
 * @date 2022/12/22 17:23
 */
@Mapper
@Repository
public interface ParaUphydstMapper {
    @Insert("insert into forcastflood.para_uphydst(Id,STCD,STNM,Distance,ModifiedDate,FlowFirst,FlowSec,FlowThird,ResFlow,ZI,V,NOTE) values ( #{achId},'51234567','大官庄',70,#{ldt},#{flowQ},#{flowQ},#{flowQ},#{ResFlow},#{ZI},#{V},'The Q of three days is same')")

    public Integer insertParaUphydst(String achId,LocalDateTime ldt, BigDecimal flowQ,BigDecimal ResFlow,BigDecimal ZI,Double V);
}
