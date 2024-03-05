package cn.hhu.Bean;

import lombok.*;
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
 public class CG2_CL {//ITYPE==16
    private Integer ID;
    private Integer NID;
    private BigDecimal HINL;
    private BigDecimal WBL;
    private BigDecimal CLNL;
    private BigDecimal BDEL;
    private BigDecimal ENRL;
    private BigDecimal WANL;
    private BigDecimal IBIL;
    private BigDecimal INWL;
    private BigDecimal NBL;
    private BigDecimal CRNL;
    private BigDecimal ELEVL;
    private BigDecimal CCl;
}
