package cn.hhu.mapper;

import cn.hhu.Bean.ST_WAS_R;
import cn.hhu.mapper.SqlProvider.ST_WAS_RSqlProvider;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author tlj
 */
@Mapper
@Repository
public interface ST_WAS_RMapper extends BaseMapper<ST_WAS_R> {


    @SelectProvider(value = ST_WAS_RSqlProvider.class ,method = "SelectWasInfoByStcdsAndTMScopeSql")
    List<ST_WAS_R> SelectWasInfoByStcdsAndTMScope(String[] stcd, LocalDateTime startDatetime, LocalDateTime endDatetime);
}
