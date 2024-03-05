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
@TableName("CH_7B_JET")
 public class CH_7B_JET {
    private Integer Id;
    private Integer I_NUM;//急流序号
    private Integer J_TIME;//时段
    private BigDecimal ZQ;//m 水位
    private BigDecimal DTZQ;//h  时段长度
}
