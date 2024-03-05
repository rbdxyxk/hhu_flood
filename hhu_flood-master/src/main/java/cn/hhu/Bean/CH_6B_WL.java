package cn.hhu.Bean;

import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("CH_6B_WL")
 public class CH_6B_WL {
    private Integer ID;
    private Integer I_NUM;//湿地序号
    private Integer J_TIME;//时段
    private BigDecimal QQ;//m^3/s 流量
    private BigDecimal DTQQ;//h  时段长度
}
