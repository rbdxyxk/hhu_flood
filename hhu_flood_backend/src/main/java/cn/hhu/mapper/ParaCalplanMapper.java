package cn.hhu.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author tlj
 * @description TODO
 * @date 2022/12/26 17:33
 */

@Mapper
@Repository
public interface ParaCalplanMapper {
    @Insert("insert into forcastflood.para_calplan(Id,Name,ParamId,Start,CalculateDate,CalTimeLong,Worker)" +
            " values( #{achId},#{achNm},#{SelectedValue},#{calBgTime},#{tm},#{p10},'测试员')")
    Integer insertCalplan(String achId, String achNm, String SelectedValue,String calBgTime
            , LocalDateTime tm, BigDecimal p10);
}
