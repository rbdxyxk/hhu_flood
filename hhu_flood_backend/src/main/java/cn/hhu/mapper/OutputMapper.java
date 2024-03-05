package cn.hhu.mapper;

import cn.hhu.Bean.Output;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * @author tlj
 */
@Mapper
@Repository
public interface OutputMapper extends BaseMapper<Output> {
    public List<Output> getAll();
    public Output qurreyById(Integer id);
}
