package cn.hhu.Bean;

/**
 * @author Gyk
 */
public class DamScope {

    private int unitId;
    private double x;
    private double y;
    private double bedElev;
    private double waterElev;

    @Override
    public String toString() {
        return "DamScope{" +
                "unitId=" + unitId +
                ", x=" + x +
                ", y=" + y +
                ", bedElev=" + bedElev +
                ", waterElev=" + waterElev +
                '}';
    }

    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getBedElev() {
        return bedElev;
    }

    public void setBedElev(double bedElev) {
        this.bedElev = bedElev;
    }

    public double getWaterElev() {
        return waterElev;
    }

    public void setWaterElev(double waterElev) {
        this.waterElev = waterElev;
    }
}
