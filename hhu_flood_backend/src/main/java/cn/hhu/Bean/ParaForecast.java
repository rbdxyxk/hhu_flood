package cn.hhu.Bean;

import lombok.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author tlj
 * @description TODO
 * @date 2022/12/25 21:32
 */
@Data
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ParaForecast {
    private String paramId;
    private String paraName;
    private String calRegNM;
    private String pMakeP;
    private String  isOnLine;

    private BigDecimal A;
    private BigDecimal DATT;
    private BigDecimal B;
    private BigDecimal EX;
    private BigDecimal IM;
    private BigDecimal KI;
    private BigDecimal KG;
    private BigDecimal CI;
    private BigDecimal CG;
    private BigDecimal CS;
    private BigDecimal L;
    private BigDecimal X;
    private BigDecimal K;
    private BigDecimal SM;
    private BigDecimal WUM;
    private BigDecimal WLM;
    private BigDecimal WDM;
    private BigDecimal C;

//    ,B,,IM,KI,KG,CI,CG,CS,L,X,K,SM,WUM,WLM,WDM,C
}
