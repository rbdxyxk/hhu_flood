package cn.hhu.service;

import cn.hhu.Bean.ST_PUMP_R;
import cn.hhu.Bean.ST_RSVR_R;
import cn.hhu.Bean.ST_STINFO_B;

import java.time.LocalDateTime;
import java.util.List;

public interface ST_PUMPService {

    public List<ST_STINFO_B> getAllSTCDAndSTNM();


    public List<ST_PUMP_R> getDataByStcdAndDate(String STCD, LocalDateTime startDateTime, LocalDateTime endDateTime);
}
