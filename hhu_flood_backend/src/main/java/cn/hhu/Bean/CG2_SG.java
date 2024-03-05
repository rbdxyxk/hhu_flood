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
 public class CG2_SG {//ITYPE==8
    private Integer ID;
    private Integer NID;//边界号
    private Integer NSG;//闸门总数
    private BigDecimal SGL;//闸门的宽度
    private BigDecimal ZSS;//闸的过水堰顶高程
    private BigDecimal DHS;//闸的设计水头无控制出流经验公式系数
    private BigDecimal C1S;//无控制出流经验公式的系数
    private BigDecimal C2S;//闸下出流经验公式系数
    private BigDecimal GOSMAX;//出流计算的闸门最大开度
}
