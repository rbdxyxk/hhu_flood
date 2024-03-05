package cn.hhu.Bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author tlj
 */
@Data
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Component
@TableName(value = "ST_PPTN_R",schema = "jsycwater")
//降水量
 public class ST_PPTN_R {
    private ST_STINFO_B ST;
    private String STCD;
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="UTC")
    private Date TM;//主键 时间

   @Value("0.0")
    private BigDecimal DRP;//时段降水量
    private BigDecimal INTV;//时间长
    private BigDecimal PDR;//降水历时
   @Value("0.0")
    private BigDecimal DYP=BigDecimal.ZERO;//日降水量
    private String WTH;//天气状况
}
