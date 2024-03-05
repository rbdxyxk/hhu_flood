package cn.hhu.Bean;

import lombok.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author tlj
 */
@Data
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Component
 public class Output {
    private Integer ID=0;
    private Integer TimeInterval=0;
    private Integer element;
    private BigDecimal X_axis;
    private BigDecimal Y_axis;
    private BigDecimal bed;
    private BigDecimal water;
    private BigDecimal flow1;
    private BigDecimal flow2;
    private BigDecimal TP;
    private BigDecimal TN;
    private BigDecimal COD;
    private BigDecimal BOD;
    private BigDecimal NH3_H;
    private BigDecimal DO;
    private BigDecimal WTMP;
    private BigDecimal PL6;
    private BigDecimal Q;
}
