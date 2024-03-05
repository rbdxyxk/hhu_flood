package cn.hhu.Bean;

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
 public class CG1_B {
    private Integer ID;
    private Integer IDUM;//边界号
    private Long NOD1;//节点号
    private Long NOD2;//节点号
    private Integer ITYPE;//边界类型
    private Integer ICRV;//过程线编号
}
