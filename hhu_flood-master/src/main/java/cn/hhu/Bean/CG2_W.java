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
 public class CG2_W {//ITYPE==6
    private Integer ID;
    private Integer NID;//边界号
    private BigDecimal CWR;//堰的缺口和堰长之比
    private BigDecimal CWC1;//堰顶高程
    private BigDecimal CWC2;//堰底高程
    private BigDecimal WLNG;//堰的长度
}
