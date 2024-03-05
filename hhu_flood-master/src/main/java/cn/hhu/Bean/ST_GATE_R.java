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
@TableName(value = "ST_GATE_R",schema = "jsycwater")
 public class ST_GATE_R {
    private Integer STCD;
    private Timestamp TM;
    private BigDecimal GTOPHGT;
    private BigDecimal MSQMT;
    private BigDecimal  GTQ;
}
