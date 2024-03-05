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
 public class CJ_VAL0 {
    private Integer ID;
    private Integer I_NUM;//模拟区内单元
    private BigDecimal  Z1;//m 初始水位
    private BigDecimal U1;//m/s x方向流速分量
    private BigDecimal V1;//m/s Y方向流速分量
}
