package cn.hhu.Bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author tlj
 */
/**
 * @author tlj
 */
@Data
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Component
@TableName("CH_6A_WL")
 public class CH_6A_WL {
    private Integer ID;
    private Integer I_NUM;//湿地
    private Integer NQQ;//时段总数
    private BigDecimal QQ0;//流量初始值
    private Integer STCD;//测站编码
    private Timestamp TM;//起始时间
}
