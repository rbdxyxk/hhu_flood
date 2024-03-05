package cn.hhu.repository;



import cn.hhu.Bean.CH_2B_Z;

import java.sql.Connection;
/**
 * @author tlj
 */
public interface CH_2B_ZRepository {
    public Integer insert(CH_2B_Z c, Connection conn) throws IllegalAccessException, Exception;
}
