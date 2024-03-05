package cn.hhu.mapper;

import cn.hhu.Bean.ST_RIVER_R;
import cn.hhu.Bean.ST_STINFO_B;
import cn.hhu.Bean.ST_WAS_R;
import cn.hhu.mapper.SqlProvider.STinfoSqlProvder;
import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author tlj
 * @description TODO
 * @date 2022/11/5 16:48
 */
@Mapper
@Repository
@DS("jsycwater")
public interface ST_STINFO_BMapper {
    @Select("select STCD,STNM from jsycwater.st_stbprp_b where STCD in ('51111911','51112040','51113900','51114005','51105811','51101201','51112711','51112101')")
    public List<ST_STINFO_B> getAllSTCDAndSTNM();

    @Select("select STCD,TM,Z from jsycwater.st_was_r where STCD=#{stcd} and TM=#{tm} order by STCD asc")
    public ST_WAS_R SelectWasInfoByStcdsAndTMScope(String stcd, LocalDateTime tm, LocalDateTime endDatetime);
}
