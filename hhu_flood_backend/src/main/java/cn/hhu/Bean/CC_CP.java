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
@TableName("CC_CP")
 public class CC_CP {
    private Integer ID;
    private Integer NST;//模拟时段总数
    private Integer NBC;//给定边界总数
    private Integer IUNITS;//
    private Integer IPRINT;//打印频率
    private Integer NOUT;//指定输出单元边总数
    private Integer IRAIN;//降水文件
    private Integer IQUA;//模拟水质
    private Integer INPP;//源污染文件
    private Integer IBDP;//湖床污染文件
}
