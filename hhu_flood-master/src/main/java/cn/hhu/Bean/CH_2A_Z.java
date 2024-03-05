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
@TableName("CH_2A_Z")
 public class CH_2A_Z {
    private Integer ID;
    private Integer I_NUM;//水位过程线序号
    private Integer NZT;//时段总数
    private BigDecimal ZT0;//初始水位
    private Integer STCD;//测站编码
    private Timestamp TM;//起始时间
}
