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
 public class CG2_G {//ITYPE==7
    private Integer ID;
    private Integer NID;//边界号
    private Integer NGG;//闸门的个数
    private BigDecimal GL;//闸门的宽度
    private BigDecimal ZG;//闸的过水堰顶高程
    private BigDecimal DH;//闸的设计水头无控制出流经验
    private BigDecimal C1G;//无控制出流经验公式的系数
    private BigDecimal C2G;//闸下出流经验公式系数
    private BigDecimal GOMAX;//出流计算的闸门最大开度
}
