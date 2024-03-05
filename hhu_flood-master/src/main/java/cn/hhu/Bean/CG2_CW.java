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
 public class CG2_CW {//ITYPE==16
    private Integer ID;
    private Integer NID;
    private Integer CWR;
    private BigDecimal CWC1;
    private BigDecimal CWC2;
    private BigDecimal WLNG;
}
