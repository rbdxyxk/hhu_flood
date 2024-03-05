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
@TableName("CH_5A_W")
 public class CH_5A_W {
    private Integer ID;
    private Integer I_NUM;//堰顶
    private Integer NWO;//时段总数
    private BigDecimal WO0;//水位过程初始值
    private Integer STCD;//测站编码
    private Timestamp TM;//起始时间
}
