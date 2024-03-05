package cn.hhu.mapper;

import cn.hhu.Bean.ST_RSVR_R;
import cn.hhu.Bean.ST_STINFO_B;
import cn.hhu.mapper.SqlProvider.RsvrSqlProvider;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author tlj
 * @description TODO
 * @date 2022/7/7 16:29
 */
@Mapper
@Repository
public interface ST_RSVR_RMapper  extends BaseMapper<ST_RSVR_R> {
    public List<ST_STINFO_B> getAllSTCDAndSTNM();
    @SelectProvider(type = RsvrSqlProvider.class,method = "queryRsvRBySTCDAndTm")
    public List<ST_RSVR_R> getRsvrByStcdAndTm(String[] stcd, LocalDateTime startDatetime, LocalDateTime endDatetime);
//    @Select(
//            {
//
//                    " SELECT  a.STCD,a.STNM FROM jsycwater.st_stbprp_b a , (\n" +
//                            "SELECT DISTINCT STCD FROM jsycwater.st_rsvr_r where STCD like '5%' \n" +
//                            ") b WHERE a.`STCD`=b.`STCD` ;"
//
//            }
//    )
    @SelectProvider(type = RsvrSqlProvider.class,method = "queryAllStcdInfo")
    List<ST_STINFO_B> getAllSTCDAndSTNMstartWith5(String table,String likeCondition);
    @Select("select STCD,TM,RZ from jsycwater.ST_RSVR_R where STCD=#{stcd} and TM=#{tm} order by TM desc")
    ST_RSVR_R getRsvrInfoBYTMAndSTCD(@Param("stcd") String stcd,@Param("tm") LocalDateTime tm);
}
