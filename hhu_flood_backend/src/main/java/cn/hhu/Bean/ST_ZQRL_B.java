package cn.hhu.Bean;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

//流量水位关系曲线
/**
 * @author tlj
 */
@Data
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Component
@TableName(value = "ST_ZQRL_B",schema = "jsycwater")
 public class ST_ZQRL_B {
    private Integer STCD;
    //启用时间
    private Timestamp BGTM;
    //点序号
    private Double PTNO;
    private Double Z;
    private Double Q;
    //曲线名称
    private String LNNM;
    //备注
    private String comments;
    @TableField(fill=FieldFill.INSERT_UPDATE)
    private Timestamp MODITIME;
}
