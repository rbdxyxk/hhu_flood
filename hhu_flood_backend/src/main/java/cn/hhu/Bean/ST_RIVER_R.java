package cn.hhu.Bean;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

//河流情况
/**
 * @author tlj
 */
@Data
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Component
@TableName(value = "ST_RIVER_R",schema = "jsycwater")
 public class ST_RIVER_R {
//    private ST_STINFO_B ST;
   private  Integer STCD;
    private Timestamp TM;
    private BigDecimal Z=BigDecimal.ZERO;//水位
    private BigDecimal Q=BigDecimal.ZERO;;//流量
    private BigDecimal XSA;
    private BigDecimal XSAVV;//平均流速
    private BigDecimal XSMXV;//最大流速
    private String FLWCHRCD;
    private String WPTN;
    private String MSQMT;
    private String MSAMT;
    private String MSVMT;
}
