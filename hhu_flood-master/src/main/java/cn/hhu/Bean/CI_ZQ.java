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
@TableName("CI_ZQ")
 public class CI_ZQ {
    private Integer ID;
    private  Integer K_NUM;//水位流量关系曲线号
    private Integer I_NUM;//水位流量关系曲线上的点据号
    private BigDecimal HRC;//m 水位值
    private BigDecimal QRC;//m^3/s 单宽流量
    private Integer STCD;//测站编码
    private Timestamp TM;//起始时间
}
