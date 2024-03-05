package cn.hhu.Bean;

import lombok.*;

import java.math.BigDecimal;

/**
 * @author tlj
 */
@Data
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
 public class QinflowData {
    private Integer ID;
    private Integer TimeInterval;
    private BigDecimal Qinflow;
}
