package cn.hhu.service.impl.RunoffGenStragey;

import cn.hhu.service.impl.RunoffGenServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author tlj
 * @description TODO
 * @date 2022/11/24 16:15
 */

public interface RunoffGenStrategy {

    Map<String,List<RunoffGenServiceImpl.RunoffGenData>> ComputeRunoffGen(Map<String, Map<LocalDate, BigDecimal>> map, LocalDateTime begin, LocalDateTime end);
}
