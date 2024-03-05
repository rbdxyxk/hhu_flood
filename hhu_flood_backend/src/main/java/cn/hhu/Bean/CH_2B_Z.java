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
@TableName("CH_2B_Z")
 public class CH_2B_Z {
    private Integer ID;
    private Integer I_NUM;//水位过程线
    private Integer J_TIME;//时段
    private BigDecimal ZT;//m 水位
    private BigDecimal DTZT;// h 时段长度
}
