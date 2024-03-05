package cn.hhu.mapper;

import cn.hhu.Bean.ST_PPTN_R;
import cn.hhu.Bean.ST_STINFO_B;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author tlj
 * @description TODO
 * @date 2022/6/29 10:49
 */

@Mapper
@Repository
public interface ST_PPTN_RMapper extends BaseMapper<ST_PPTN_R> {
    List<ST_STINFO_B> getAllSTCDAndSTNM();
    @Select({
            "use jsycwater;",
            " SELECT  a.STCD,a.STNM FROM `st_stbprp_b` a , (\n" +
                    "SELECT DISTINCT STCD FROM `st_PPTN_r` where STCD like '5%' \n" +
                    ") b WHERE a.`STCD`=b.`STCD` ;",
            "use waterflood;"
    })
     List<ST_STINFO_B> getAllNewSTCDAndSTNM();
    List<ST_PPTN_R> searchAllByStcdAndTm(String[] stcd, LocalDateTime startDatetime,LocalDateTime endDatetime);
    @Select("select STCD,TM,DRP  from jsycwater.ST_PPTN_R where STCD in('51131350','51131300','51112500','51112000','51112101')and TM between #{sbTM} and #{seTM} and INTV<0.9  order by STCD,TM asc")
    List<ST_PPTN_R> searchAllByTMAndIntv(@Param("sbTM") LocalDateTime sbTM, @Param("seTM") LocalDateTime seTM);
}
