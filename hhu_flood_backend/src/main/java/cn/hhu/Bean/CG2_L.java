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
 public class CG2_L {//ITYPE==9
    private Integer ID;
    private Integer NID;//边界号
    private BigDecimal CLVE;//堤底溢流的流量系数
    private BigDecimal ELVE;//m 堤底高程
}
