package cn.hhu.service;

import cn.hhu.Bean.AutoCalInitParams;
import cn.hhu.Bean.ForecastManualInitParams;

public interface AutoCalService {
    public AutoCalInitParams AutoOninit();
    public String backId();

    public  String saveborderPara(AutoCalInitParams initParams);
}
