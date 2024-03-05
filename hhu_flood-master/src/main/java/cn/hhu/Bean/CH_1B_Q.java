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
@TableName("CH_1B_Q")
 public class CH_1B_Q {
    private Integer ID;
    private Integer I_NUM;//流量过程线
    private Integer J_TIME;//时段
    private BigDecimal QT;// m^3 /s 流量
    private BigDecimal DTQT;// h 时段长度
}
