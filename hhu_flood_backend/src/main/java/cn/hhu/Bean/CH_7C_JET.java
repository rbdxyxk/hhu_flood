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
@TableName("CH_7C_JET")
 public class CH_7C_JET {
    private Integer ID;
    private Integer I_NUM;//湿地
    private Integer NQZ;//时段总数
    private BigDecimal QZ0;// m^3/s 流量初始值
    private Integer STCD;//测站编码
    private Timestamp TM;//起始时间
}
