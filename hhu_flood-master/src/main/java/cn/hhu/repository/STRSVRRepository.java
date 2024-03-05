package cn.hhu.repository;

import cn.hhu.Bean.ST_RSVR_R;

import java.util.Date;
import java.util.List;

/**
 * @author tlj
 */
public interface STRSVRRepository {
    public List<Date> getSTTM(int STCD);
    public List<ST_RSVR_R> getST_RSVR_R(String id,Date TM1 ,Date TM2);
}
