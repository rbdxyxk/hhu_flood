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
@TableName("CF_GFANDNF")
 public class CF_GFANDNF {
    private Integer ID;
    private Integer MG;//闸下出流计算方式
    private Integer MGS;//法向通道计算方式
    private Integer MCS;//法向通道计算方式
    private Integer MTS;//单元间底高程差较大时的处理方式
    private Integer LIMTER;//是否使用二阶限制函数
    private Integer BETA;//计算精度
    private BigDecimal PP;//模拟区纬度
    private BigDecimal WSP;//m/s 风速
    private BigDecimal WINC;//° 风向角
//    public CF_GFANDNF(String[] s1,String s2,String s3,Integer ID){
//        this.ID=ID;
//
//    }
}
