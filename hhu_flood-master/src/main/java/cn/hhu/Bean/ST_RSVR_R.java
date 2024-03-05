package cn.hhu.Bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author tlj
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "ST_RSVR_R",schema = "jsycwater")
  public class ST_RSVR_R {
    private Integer STCD;
    private Date TM;
    private Double RZ;//库上水位
    private Double INQ;//入库流量
    private Double W;//蓄水量
    private Double OTQ;//库下水位
    private Double INQDR;//出库流量
    private Double BLRZ;//库水特征码
    private String RWCHRCD;//库水水势
    private String RWPTN;//入流时段长
    private String MSQMT;//测流方法
}
