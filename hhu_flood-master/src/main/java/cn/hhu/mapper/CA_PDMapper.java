package cn.hhu.mapper;

import cn.hhu.Bean.CA_PD;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author tlj
 */

@Mapper
@Repository
public interface CA_PDMapper extends BaseMapper<CA_PD> {
    public Integer getMaxId();
}
