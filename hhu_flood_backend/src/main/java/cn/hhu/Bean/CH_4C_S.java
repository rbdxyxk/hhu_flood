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
@TableName("CH_4C_S")
 public class CH_4C_S {
    private Integer ID;
    private Integer I_NUM;//闸门
    private Integer NZS1;//时段总数
    private BigDecimal SH0;//闸门开度初始值
    private Integer STCD;//测站编码
    private Timestamp TM;//起始时间
}
