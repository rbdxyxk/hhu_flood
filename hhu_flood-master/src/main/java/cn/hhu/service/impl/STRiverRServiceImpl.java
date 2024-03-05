package cn.hhu.service.impl;

import cn.hhu.Bean.ST_RIVER_R;
import cn.hhu.Bean.ST_STINFO_B;
import cn.hhu.mapper.ST_RIVER_RMapper;
import cn.hhu.service.STRiverRService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service

public class STRiverRServiceImpl implements STRiverRService {
    ST_RIVER_RMapper st_river_rMapper;
    public STRiverRServiceImpl(ST_RIVER_RMapper st_river_rMapper){
        this.st_river_rMapper=st_river_rMapper;
    }
    @Override
    public List<ST_RIVER_R> getDataByStcdAndDate(String STCD, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        QueryWrapper<ST_RIVER_R> wrapper=new QueryWrapper<>();
        wrapper.select("Z","Q","XSAVV","TM");
        wrapper.eq("STCD",STCD);
        wrapper.between("TM",startDateTime,endDateTime);
        wrapper.orderByAsc("TM");
        return  this.st_river_rMapper.selectList(wrapper);
    }

    @Override
    public List<ST_STINFO_B> getAllSTCDAndSTNM() {
        return st_river_rMapper.getAllSTCDAndSTNM();
    }
}
