package cn.hhu.mapper;

import cn.hhu.Bean.ParaForecast;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Mapper
@Repository
public interface ParaMapper {
    public List<ParaForecast> select_para(@Param("para_name") String para_name, @Param("cal_reg_nm") String cal_reg_nm);
}
