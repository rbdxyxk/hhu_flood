package cn.hhu.mapper;

import cn.hhu.Bean.STTest;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * @author tlj
 */
@Repository
@Mapper
public interface STTestMapper extends BaseMapper<STTest> {
    List<STTest> getInfosByName(String name);
}
