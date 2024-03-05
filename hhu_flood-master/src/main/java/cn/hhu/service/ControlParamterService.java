package cn.hhu.service;

import cn.hhu.Bean.ST_RSVR_R;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
/**
 * @author tlj
 */
public interface ControlParamterService {
    public List<Date> getSTTM(int STCD);
    public List<ST_RSVR_R> getST_RSVR_R(String id, Date TM1 , Date TM2);
    public Boolean insertAllParameters(String s) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException;
    public String getAllParameterById(Integer id);
}
