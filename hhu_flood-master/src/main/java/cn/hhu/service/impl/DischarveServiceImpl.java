package cn.hhu.service.impl;

import cn.hhu.Bean.ST_RIVER_R;
import cn.hhu.Bean.ST_STINFO_B;
import cn.hhu.Bean.ST_WAS_R;
import cn.hhu.mapper.ST_STINFO_BMapper;
import cn.hhu.mapper.ST_WAS_RMapper;
import cn.hhu.service.DischargeService;
import cn.hhu.service.FloodPreventionKit;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author tlj
 * @description 泄流量相关
 * @date 2022/11/4 16:39
 */

@Service
public class DischarveServiceImpl  implements DischargeService,FloodPreventionKit<ST_WAS_R>  {

    @Autowired
    private ST_STINFO_BMapper st_stinfo_bMapper;
    @Autowired
    private ST_WAS_RMapper st_was_rMapper;

    @Override
    public List<ST_STINFO_B> getAllSTCDAndSTNM() {
        return st_stinfo_bMapper.getAllSTCDAndSTNM();
    }

    @Override
    public List<ST_WAS_R> getDataByStcdAndDate(String STCD, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        QueryWrapper<ST_WAS_R> wrapper=new QueryWrapper<>();
        wrapper.select("DWZ","UPZ","TGTQ","TM");
        wrapper.eq("STCD",STCD);
        wrapper.between("TM",startDateTime,endDateTime);
        wrapper.orderByAsc("TM");
        List<ST_WAS_R> s;
        s=this.st_was_rMapper.selectList(wrapper);
        System.out.println(s);
        System.out.println("1111111111");
        return  s;
    }

    @Override
    public List<ST_WAS_R> searchAllByStcdAndTm(String[] stcd, LocalDateTime startDatetime, LocalDateTime endDatetime) {
        return st_was_rMapper.SelectWasInfoByStcdsAndTMScope( stcd,  startDatetime,  endDatetime);
    }

}
