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
@TableName("CH_5B_W")
 public class CH_5B_W {
    private Integer ID;
    private Integer I_NUM;//堰顶序号
    private Integer J_TIME;//时段
    private BigDecimal WO;//m 水位
    private BigDecimal DTWO;//h  时段长度
}
