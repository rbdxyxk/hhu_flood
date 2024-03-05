package cn.hhu.Bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author tlj
 */
@Data
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Component
@TableName("CE_TANDT")
 public class CE_TANDT {
    private Integer ID;
    private BigDecimal STIME;// h 模拟时段总数
    private BigDecimal DTIME;// s 时间步长
    private BigDecimal HTOL1;// m 识别完全干旱单元的水深阈值
    private BigDecimal HTOL2;// m 识别完全湿润单元的水深阈值
    private BigDecimal HTOL3;// m 相邻单元底高程差阈值
    private BigDecimal EFX; // x方向粘滞系数
    private BigDecimal EFY; // Y方向粘滞系数
    private BigDecimal THETA; // 底高程修正系数
}
