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
@TableName("CD_PLINE")
 public class CD_PLINE {
    private Integer ID;
    private Integer NQC;//流量过程先总数
    private Integer NVC;//流速过程线总数
    private Integer NZC;//水位过程线总数
    private Integer NGC;//闸门过程线总数
    private Integer NHC;//水位流量关系曲线总数
    private Integer NQH;//水位流量关系曲线上的点距总数
    private Integer NSC;//上下游边界处的闸门过程线总数
    private Integer NQLC;//湿地支流入流过程线总数
    private Integer NRC;//降雨过程线总数
    private Integer NSPC;//急流过程线总数
    private Integer NPMC;//抽水过程线总数
    private  Integer NWC;

}
