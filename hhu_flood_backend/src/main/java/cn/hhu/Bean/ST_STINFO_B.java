package cn.hhu.Bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import org.springframework.stereotype.Component;
/**
 * @author tlj
 */
@Data
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Component
@TableName(value = "ST_STINFO_B",schema = "jsycwater")
 public class ST_STINFO_B {
   private Integer STCD;
   //private String STCDT;
   private String STNM;//测站名称
   //private String BNNM;
   //private String STADDR;

}
