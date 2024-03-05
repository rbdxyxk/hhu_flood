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
@TableName(value = "ST_WAS_R",schema = "jsycwater")
 public class ST_WAS_R {
    private  Integer STCD;
    private Timestamp TM;
    //闸上水位
    private BigDecimal UPZ;
    //闸下水位
    private BigDecimal DWZ;
    //过闸流量
    private BigDecimal TGTQ;
}
