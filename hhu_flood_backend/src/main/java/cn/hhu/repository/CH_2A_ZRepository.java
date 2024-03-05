package cn.hhu.repository;

import cn.hhu.Bean.CH_2A_Z;


import java.sql.Connection;
/**
 * @author tlj
 */
public interface CH_2A_ZRepository {
    public Integer insert(CH_2A_Z c, Connection conn) throws IllegalAccessException, Exception;
}
