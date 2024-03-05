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
@TableName("CH_7D_JET")
 public class CH_7D_JET {
    private Integer ID;
    private Integer I_NUM;//急流序号
    private Integer J_TIME;//时段
    private BigDecimal QZ;//m^3/s 流量
    private BigDecimal DTQZ;//h  时段长度
}
