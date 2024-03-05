package cn.hhu.Bean;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @author tlj
 * @description TODO
 * @date 2022/10/20 15:01
 */
@Data
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Component
@TableName(value = "ST_ZVARL_B",schema = "jsycwater")
//库（湖）容曲线表
public class ST_ZVARL_B {
    private String STCD;
    private LocalDateTime MSTM;//实测时间
    private Integer PTNO;//点序号
    private BigDecimal RZ;//库水位
    private BigDecimal W;//属水量
    private Integer WSFA;//水面面积
    @TableField(fill= FieldFill.INSERT_UPDATE)
    private Timestamp MODITIME;
}
