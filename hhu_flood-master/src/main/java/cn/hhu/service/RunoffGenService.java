package cn.hhu.service;

import cn.hhu.service.impl.RunoffGenServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author tlj
 * @description TODO 产流量计算
 * @date 2022/11/24 16:12
 */

public interface RunoffGenService {
    public Map<String,List<RunoffGenServiceImpl.RunoffGenData>> getAllRunoffGenData(String[] stcd, LocalDateTime startDate, LocalDateTime endDate);
}
