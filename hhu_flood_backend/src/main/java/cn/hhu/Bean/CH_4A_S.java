package cn.hhu.Bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author tlj
 */
@Data
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Component
@TableName("CH_4A_S")
 public class CH_4A_S {
    private Integer ID;
    private Integer I_NUM;//闸
    private Integer NZS;//时段总数
    private BigDecimal ZS0;//水位初始值
    private Integer STCD;//测站编码
    private Timestamp TM;//起始时间
}
