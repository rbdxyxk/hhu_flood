package cn.hhu.service;

import cn.hhu.Bean.ForecastManualInitParams;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author tlj
 * @description TODO
 * @date 2022/12/25 19:29
 */

public interface ForecastManualService {
    public ForecastManualInitParams FrmOninit();


    public String backId();

    public  String saveborderPara(ForecastManualInitParams initParams);
}
