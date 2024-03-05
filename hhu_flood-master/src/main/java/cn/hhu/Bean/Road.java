package cn.hhu.Bean;

import org.springframework.stereotype.Component;

/**
 * @author Gyk
 */
@Component
public class Road {

    private int id;//线路主键id
    private int uid;//创建人的id
    private String info_name;//线路名称
    private String info_data;//线路数据。存放值为文件的路径
    private int expired;//是否有效；0-有效；1-无效

    @Override
    public String toString() {
        return "Road{" +
                "id=" + id +
                ", uid=" + uid +
                ", info_name='" + info_name + '\'' +
                ", info_data='" + info_data + '\'' +
                ", expired=" + expired +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getInfo_name() {
        return info_name;
    }

    public void setInfo_name(String info_name) {
        this.info_name = info_name;
    }

    public String getInfo_data() {
        return info_data;
    }

    public void setInfo_data(String info_data) {
        this.info_data = info_data;
    }

    public int getExpired() {
        return expired;
    }

    public void setExpired(int expired) {
        this.expired = expired;
    }
}
