package cn.hhu.mapper;

import cn.hhu.Bean.ST_PUMP_R;
import cn.hhu.Bean.ST_RIVER_R;
import cn.hhu.Bean.ST_STINFO_B;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


import java.time.LocalDateTime;
import java.util.List;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author tlj
 */
@Repository
@Mapper
@DS("jsycwater")

public interface ST_PUMP_Mapper extends BaseMapper<ST_PUMP_R> {

    @Select("select STCD,STNM from jsycwater.st_stbprp_b where STCD in ('51112150','51113950','51114230','51115890')")
    public List<ST_STINFO_B> getAllSTCDAndSTNM();
}
