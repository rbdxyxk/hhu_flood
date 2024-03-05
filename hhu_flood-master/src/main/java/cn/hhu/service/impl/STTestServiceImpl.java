package cn.hhu.service.impl;

import cn.hhu.Bean.STTest;
import cn.hhu.mapper.STTestMapper;
import cn.hhu.service.STTestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
/**
 * @author tlj
 */
public class STTestServiceImpl extends ServiceImpl<STTestMapper, STTest> implements STTestService {

    @Override
    public List<STTest> getInfosByName(String name) {
        return getBaseMapper().getInfosByName(name);
    }
}
