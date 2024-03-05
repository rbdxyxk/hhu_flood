package cn.hhu.service;

import cn.hhu.Bean.ST_RIVER_R;
import cn.hhu.Bean.ST_STINFO_B;

import java.time.LocalDateTime;
import java.util.List;
/**
 * @author tlj
 */
public interface STRiverRService {
    public List<ST_RIVER_R> getDataByStcdAndDate(String STCD, LocalDateTime startDateTime, LocalDateTime endDateTime);
    public List<ST_STINFO_B> getAllSTCDAndSTNM();
}
