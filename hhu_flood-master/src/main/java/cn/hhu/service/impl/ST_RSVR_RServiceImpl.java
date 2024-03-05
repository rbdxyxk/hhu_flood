package cn.hhu.service.impl;

import cn.hhu.Bean.ST_RSVR_R;
import cn.hhu.Bean.ST_STINFO_B;
import cn.hhu.mapper.ST_RSVR_RMapper;
import cn.hhu.service.ST_RSVR_RService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author tlj
 * @description TODO
 * @date 2022/7/7 16:34
 */
@Service
public class ST_RSVR_RServiceImpl implements ST_RSVR_RService{
    @Autowired
    ST_RSVR_RMapper st_rsvr_rMapper;
    @Override
    public List<ST_RSVR_R> getRsvrInfo(String STCD, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        QueryWrapper<ST_RSVR_R> wrapper = new QueryWrapper<>();
        wrapper.select("STCD","TM","RZ","INQ","W","OTQ","INQDR");
        wrapper.eq("STCD",STCD);
        wrapper.between("TM",startDateTime,endDateTime);
        wrapper.orderByAsc("TM");
        return st_rsvr_rMapper.selectList(wrapper);
    }

    @Override
    public List<ST_STINFO_B> getAllSTCDAndSTNM() {
        return st_rsvr_rMapper.getAllSTCDAndSTNM();
    }

    @Override
    public List<ST_RSVR_R> searchAllByStcdAndTm(String[] stcd, LocalDateTime startDatetime) {
        return st_rsvr_rMapper.getRsvrByStcdAndTm(stcd,startDatetime,startDatetime.plusHours(72));
    }

    //按测站分组
    @Override
    public Map<String, List<ST_RSVR_R>> searchAllByStcdAndTm2Map(String[] stcd, LocalDateTime startDatetime) {
        List<ST_RSVR_R> list = searchAllByStcdAndTm(stcd,startDatetime);
        Map<String, List<ST_RSVR_R>> collect = list.stream()
                //.sorted()
                .collect(Collectors.groupingBy(item -> item.getSTCD().toString()));
        return collect;
    }

    @Override
    public List<ST_STINFO_B> getAllSTCDAndSTNMstartWith5() {
        return st_rsvr_rMapper.getAllSTCDAndSTNMstartWith5("st_rsvr_r","5%");
    }

    @Override
    public Map<String, List<ST_RSVR_R>> searchAllByStcdAndTm2Map(String[] stcd, LocalDateTime startDatetime, LocalDateTime endDatetime) {
        List<ST_RSVR_R> list =st_rsvr_rMapper.getRsvrByStcdAndTm(stcd,startDatetime,endDatetime);
        Map<String, List<ST_RSVR_R>> collect =
                list.stream().collect(Collectors.groupingBy(item->item.getSTCD().toString()));
        return collect;
    }
}
