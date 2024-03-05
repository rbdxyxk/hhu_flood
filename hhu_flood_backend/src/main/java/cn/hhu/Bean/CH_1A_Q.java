package cn.hhu.Bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

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
@TableName("CH_1A_Q")
 public class CH_1A_Q {
    private Integer ID;
    private Integer I_NUM;//流量过程线序号
    private Integer NQT;//时段总数
    private BigDecimal QT0;//初始流量
    private Integer STCD;//测站编码
    private Timestamp TM;//起始时间
}
