package cn.hhu.Bean;

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
 public class CG2_BANDC {//ITYPE==12
    private  Integer ID;
    private  Integer NID;//边界号
    private BigDecimal HIN;//m 桥涵内部高度或者涵洞直径
    private BigDecimal WB; // 方形涵洞的宽度或桥梁跨度
    private BigDecimal CLN; //m 桥、涵长度
    private BigDecimal BDE; //m 桥、涵底高程
    private BigDecimal ENR; //厚度
    private BigDecimal WAN;// 桥、涵的翼墙角度
    private Integer IBI;//形状指标
    private Integer INW;//翼墙指标
    private Integer NB;//桥、涵总数
    private BigDecimal CRN;//桥、涵的曼宁糙率系数
}
