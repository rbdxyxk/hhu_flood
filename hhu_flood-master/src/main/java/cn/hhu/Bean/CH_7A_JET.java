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
@TableName("CH_7A_JET")
 public class CH_7A_JET {
    private Integer ID;
    private Integer I_NUM;//急流
    private Integer NZQ;//水位过程时段总数
    private BigDecimal ZQ0;//水位过程初始值
    private Integer STCD;//测站编码
    private Timestamp TM;//起始时间
}
