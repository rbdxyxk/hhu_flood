package cn.hhu.service;

import cn.hhu.Bean.ST_RSVR_R;
import cn.hhu.Bean.ST_STINFO_B;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author tlj
 * @description TODO
 * @date 2022/7/7 16:33
 */

public interface ST_RSVR_RService {
    public List<ST_RSVR_R> getRsvrInfo(String STCD, LocalDateTime startDateTime, LocalDateTime endDateTime);
    public List<ST_STINFO_B> getAllSTCDAndSTNM();
    List<ST_RSVR_R> searchAllByStcdAndTm(String[] stcd, LocalDateTime startDatetime);
    Map<String,List<ST_RSVR_R>> searchAllByStcdAndTm2Map(String[] stcd, LocalDateTime startDatetime);
    public List<ST_STINFO_B> getAllSTCDAndSTNMstartWith5();
    Map<String,List<ST_RSVR_R>> searchAllByStcdAndTm2Map(String[] stcd, LocalDateTime startDatetime,LocalDateTime endDatetime);
}
