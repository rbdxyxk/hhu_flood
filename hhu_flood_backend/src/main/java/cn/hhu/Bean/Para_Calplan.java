package cn.hhu.Bean;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Gyk
 */
@Component
public class Para_Calplan {

    private String id;
    private String Name;
    private String ParamId;
    private String Start;
    private String CalculateDate;
    private float CalTimeLong;
    private String Worker;
    private Date TS;

    @Override
    public String toString() {
        return "Para_Calplan{" +
                "id='" + id + '\'' +
                ", Name='" + Name + '\'' +
                ", ParamId='" + ParamId + '\'' +
                ", Start='" + Start + '\'' +
                ", CalculateDate='" + CalculateDate + '\'' +
                ", CalTimeLong=" + CalTimeLong +
                ", Worker='" + Worker + '\'' +
                ", TS=" + TS +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getParamId() {
        return ParamId;
    }

    public void setParamId(String paramId) {
        ParamId = paramId;
    }

    public String getStart() {
        return Start;
    }

    public void setStart(String start) {
        Start = start;
    }

    public String getCalculateDate() {
        return CalculateDate;
    }

    public void setCalculateDate(String calculateDate) {
        CalculateDate = calculateDate;
    }

    public float getCalTimeLong() {
        return CalTimeLong;
    }

    public void setCalTimeLong(float calTimeLong) {
        CalTimeLong = calTimeLong;
    }

    public String getWorker() {
        return Worker;
    }

    public void setWorker(String worker) {
        Worker = worker;
    }

    public Date getTS() {
        return TS;
    }

    public void setTS(Date TS) {
        this.TS = TS;
    }
}
