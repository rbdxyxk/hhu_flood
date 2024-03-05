package cn.hhu.Bean;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class Mod_MidResult {

    private String Id;
    private int PDNUM;
    private String FLOWTM;
    private BigDecimal Q;
    private float P;

    @Override
    public String toString() {
        return "Mod_MidResult{" +
                "Id='" + Id + '\'' +
                ", PDNUM=" + PDNUM +
                ", FLOWTM='" + FLOWTM + '\'' +
                ", Q=" + Q +
                ", P=" + P +
                '}';
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public int getPDNUM() {
        return PDNUM;
    }

    public void setPDNUM(int PDNUM) {
        this.PDNUM = PDNUM;
    }

    public String getFLOWTM() {
        return FLOWTM;
    }

    public void setFLOWTM(String FLOWTM) {
        this.FLOWTM = FLOWTM;
    }

    public BigDecimal getQ() {
        return Q;
    }

    public void setQ(BigDecimal q) {
        Q = q;
    }

    public float getP() {
        return P;
    }

    public void setP(float p) {
        P = p;
    }
}
