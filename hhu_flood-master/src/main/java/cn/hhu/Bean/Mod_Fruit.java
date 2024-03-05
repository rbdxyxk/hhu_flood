package cn.hhu.Bean;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

@Component
public class Mod_Fruit {

    private String Id;
    private int PDNUM;
    private String FLOWTM;
    private BigDecimal Q;
    private BigDecimal W;
    private BigDecimal ZI;
    private BigDecimal V;
    private Date TS;

    @Override
    public String toString() {
        return "Mod_Fruit{" +
                "Id='" + Id + '\'' +
                ", PDNUM=" + PDNUM +
                ", FLOWTM='" + FLOWTM + '\'' +
                ", Q=" + Q +
                ", W=" + W +
                ", ZI=" + ZI +
                ", V=" + V +
                ", TS=" + TS +
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

    public BigDecimal getW() {
        return W;
    }

    public void setW(BigDecimal w) {
        W = w;
    }

    public BigDecimal getZI() {
        return ZI;
    }

    public void setZI(BigDecimal ZI) {
        this.ZI = ZI;
    }

    public BigDecimal getV() {
        return V;
    }

    public void setV(BigDecimal v) {
        V = v;
    }

    public Date getTS() {
        return TS;
    }

    public void setTS(Date TS) {
        this.TS = TS;
    }
}
