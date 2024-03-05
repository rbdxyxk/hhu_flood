package cn.hhu.Bean;

import lombok.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Component
@Data
public class ParaPlanInitParams {
    private List<String> paraForecasts;
    private List<String> part;
    //    private Map<String, BigDecimal> rainInfos;
    //@NotNull(message="输入有误,各参数不能为空")
    private BigDecimal p1;
    private BigDecimal p2;
    private BigDecimal p3;
    private BigDecimal p4;
    private BigDecimal p5;

    private BigDecimal p6;
    private BigDecimal p7;
    private BigDecimal p8;
    private BigDecimal p9;
    private BigDecimal p10;

    private BigDecimal p11;
    private BigDecimal p12;
    private BigDecimal p13;
    private BigDecimal p14;
    private BigDecimal p15;
    private BigDecimal p16;
    private BigDecimal p17;
    private BigDecimal p18;


    private BigDecimal riverInfo;
    private BigDecimal rsvrInfo;
    private LocalDate date;
    private Integer hour;
    private String checked;
    private String checked0;
}
