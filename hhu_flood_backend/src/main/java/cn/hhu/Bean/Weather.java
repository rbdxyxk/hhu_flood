package cn.hhu.Bean;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

/**
 * @author tlj
 */
@Data
@EqualsAndHashCode
@ToString
 public class Weather {
    private  String Id;
    private String CalRegCD;//计算区域
    private Date ModifiedDate;
    private Double RainHour;
    private Double RainFirst;
    private Double RainSecond;
    private Double RainThird;
    private Double Eva;//蒸发量
    private Double W;//土湿
    private Double FlowDeep;//产水基流
}
