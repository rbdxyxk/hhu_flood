package cn.hhu.service.impl;

import cn.hhu.Bean.ST_PUMP_R;
import cn.hhu.Bean.ST_RSVR_R;
import cn.hhu.Bean.ST_STINFO_B;
import cn.hhu.mapper.ST_PUMP_Mapper;
import cn.hhu.mapper.ST_RIVER_RMapper;
import cn.hhu.service.ST_PUMPService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ST_PUMPServiceImpl implements ST_PUMPService {

    @Autowired
    ST_PUMP_Mapper st_pump_mapper;
    public ST_PUMPServiceImpl(ST_PUMP_Mapper st_pump_mapper){
        this.st_pump_mapper=st_pump_mapper;
    }

    @Override
    public List<ST_STINFO_B> getAllSTCDAndSTNM() {
        return st_pump_mapper.getAllSTCDAndSTNM();
    }

    @Override
    public List<ST_PUMP_R> getDataByStcdAndDate(String STCD, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        QueryWrapper<ST_PUMP_R> wrapper = new QueryWrapper<>();
        wrapper.select("STCD","TM","PPUPZ","PPDWZ","PMPQ");
        wrapper.eq("STCD",STCD);
        wrapper.between("TM",startDateTime,endDateTime);
        wrapper.orderByAsc("TM");
        return this.st_pump_mapper.selectList(wrapper);
    }


}
