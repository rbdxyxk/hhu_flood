package cn.hhu.Bean;


import lombok.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * @author tlj
 * @description TODO
 * @date 2022/12/25 9:13
 */
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Component
@Data
public class ForecastManualInitParams {
    private List<String> paraForecasts;
//    private Map<String, BigDecimal> rainInfos;
    //@NotNull(message="输入有误,各参数不能为空")
    private BigDecimal P1;
    private BigDecimal P2;
    private BigDecimal P3;
    private BigDecimal P4;
    private BigDecimal P5;

    private BigDecimal P7;
    private BigDecimal P8;
    private BigDecimal P9;
    private BigDecimal P10;

    private BigDecimal riverInfo;
    private BigDecimal rsvrInfo;
    private LocalDate date;
    private Integer hour;
    private String checked;
}
