package cn.hhu.mapper;

import cn.hhu.Bean.ST_RIVER_R;
import cn.hhu.Bean.ST_STINFO_B;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author tlj
 */
@Repository
@Mapper
@DS("jsycwater")
public interface ST_RIVER_RMapper extends BaseMapper<ST_RIVER_R> {

//    @Select({"use jsycwater;",
//    " SELECT  a.STCD,a.STNM FROM `st_stbprp_b` a , (\n" +
//            "SELECT DISTINCT STCD FROM `st_river_r`\n" +
//            ") b WHERE a.`STCD`=b.`STCD`;",
//    "use waterflood;"
//    })
    @Select("select STCD,STNM from jsycwater.st_stbprp_b where STCD in ('51112000','51113800','51113801','51101101','51111750')")
    public List<ST_STINFO_B> getAllSTCDAndSTNM();
    @Select("select STCD,TM,Z from jsycwater.ST_RIVER_R where STCD=#{stcd} and TM=#{tm} order by STCD asc")
    public ST_RIVER_R getALLRiverInfoBySTCDAndTM(String stcd, LocalDateTime tm);
}
