package cn.hhu.service;

import cn.hhu.Bean.ST_RIVER_R;
import cn.hhu.Bean.ST_STINFO_B;
import cn.hhu.Bean.ST_WAS_R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author tlj
 * @description TODO
 * @date 2022/11/4 16:38
 */

public interface DischargeService extends FloodPreventionKit<ST_WAS_R> {
    List<ST_STINFO_B> getAllSTCDAndSTNM();

    public List<ST_WAS_R> getDataByStcdAndDate(String STCD, LocalDateTime startDateTime, LocalDateTime endDateTime);

}
