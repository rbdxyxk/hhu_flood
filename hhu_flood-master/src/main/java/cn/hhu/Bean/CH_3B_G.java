package cn.hhu.Bean;

import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("CH_3B_G")
 public class CH_3B_G {
    private Integer ID;
    private Integer I_NUM;//闸门序号
    private Integer J_TIME;//时段
    private BigDecimal GO;//m 闸门开度值
    private BigDecimal DTGO;//h  时段长度
}
