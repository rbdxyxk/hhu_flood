package cn.hhu.service.impl;

import cn.hhu.Bean.CA_PD;
import cn.hhu.mapper.CA_PDMapper;
import cn.hhu.service.CA_PDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
/**
 * @author tlj
 */
public class CA_PDServiceImpl implements CA_PDService  {
    @Autowired
    CA_PDMapper ca_pdMapper;
    @Override
    public List<CA_PD> getAll() {
        return ca_pdMapper.selectList(null);
    }
}
