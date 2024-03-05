package cn.hhu.Bean;


import lombok.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Component
@Data
public class DisResFlood {
    private Float P1;
    private Float P2;
    private Float P3;
    private Float P4;
    private Float P5;

    private Float north;
    private Float south;
    private Float water;
}
