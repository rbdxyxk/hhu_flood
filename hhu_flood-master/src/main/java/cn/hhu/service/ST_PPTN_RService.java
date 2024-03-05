package cn.hhu.service;

import cn.hhu.Bean.ST_PPTN_R;
import cn.hhu.Bean.ST_STINFO_B;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author tlj
 * @description TODO
 * @date 2022/6/29 10:56
 */

public interface ST_PPTN_RService {
    public List<ST_PPTN_R> getRainInfo(String STCD, LocalDateTime startDateTime, LocalDateTime endDateTime);
    public List<ST_STINFO_B> getAllSTCDAndSTNM();
    //获取新测站
    public List<ST_STINFO_B> getAllNewSTCDAndSTNM();
    List<ST_PPTN_R> searchAllByStcdAndTm(String[] stcd, LocalDateTime startDatetime);
    Map<String,List<ST_PPTN_R>> searchAllByStcdAndTm2Map(String[] stcd, LocalDateTime startDatetime);
    Map<String,List<ST_PPTN_R>> searchAllByStcdAndTm2Map(String[] stcd, LocalDateTime startDatetime,LocalDateTime endDatetime);
    Map<String, Map> searchAllByStcdAndTm2Map2(String[] stcd, LocalDateTime startDatetime);


}
