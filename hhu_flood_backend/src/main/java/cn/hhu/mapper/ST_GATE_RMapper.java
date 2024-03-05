package cn.hhu.mapper;

import cn.hhu.Bean.ST_GATE_R;
import cn.hhu.Bean.ST_STINFO_B;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author tlj
 */
@Mapper
@Repository
public interface ST_GATE_RMapper extends BaseMapper<ST_GATE_R> {


    /**
     * @description 获取与Was 相关的所有测站
     * @return
     */
    @Select(
            value = {
                    "SELECT stcd,stnm FROM jsycwater.st_stbprp_b WHERE stcd \n" +
                            " IN(SELECT stcd FROM (SELECT DISTINCT(stcd) FROM jsycwater.st_was_r ) a JOIN ( SELECT DISTINCT(stcd) FROM jsycwater.st_gate_r ) b  USING(stcd))"
            }
    )
    @Options
    List<ST_STINFO_B> getAllSTInfoAboutWas();
}
