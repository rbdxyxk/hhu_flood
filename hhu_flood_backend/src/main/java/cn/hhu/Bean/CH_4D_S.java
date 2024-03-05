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
@TableName("CH_4D_S")
 public class CH_4D_S {
    private Integer ID;
    private Integer I_NUM;//闸门序号
    private Integer J_TIME;//时段
    private BigDecimal SH;//m 闸门开度值
    private BigDecimal DTZS;//h  时段长度
}
