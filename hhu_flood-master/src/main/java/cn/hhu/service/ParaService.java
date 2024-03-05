package cn.hhu.service;

import cn.hhu.Bean.ParaPlanInitParams;

public interface ParaService {

    public ParaPlanInitParams PsOninit();
    public ParaPlanInitParams SelectValue(String c0,String c);
    public void save(ParaPlanInitParams initParams);
    public void saveAs(ParaPlanInitParams initParams);
    public void setOnline(ParaPlanInitParams initParams);
    public void del(ParaPlanInitParams initParams);
}
