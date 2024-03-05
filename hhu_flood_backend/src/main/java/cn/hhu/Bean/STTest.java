package cn.hhu.Bean;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;


/**
 * @author tlj
 */
@Data
@EqualsAndHashCode
@ToString
@Component
//@TableName(value = "STTest",schema = "waterFlood")
 public class STTest {
    private String STCD;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd hh")
    private Date TM;//主键 时间
    private String STNM;//测站名称
    private BigDecimal Z=BigDecimal.ZERO;//水位
    private BigDecimal Q=BigDecimal.ZERO;;//流量
    private BigDecimal DRP=new BigDecimal("0.00");//时段降水量
}
