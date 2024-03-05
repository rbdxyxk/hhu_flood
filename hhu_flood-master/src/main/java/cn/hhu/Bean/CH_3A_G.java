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
@TableName("CH_3A_G")
 public class CH_3A_G {
    private Integer ID;
    private Integer I_NUM;//闸门序号
    private Integer NGO;//时段总数
    private BigDecimal GO0;//闸门开度初始值
    private Integer STCD;//测站编码
    private Timestamp TM;//起始时间
}
