package cn.hhu.repository;

import cn.hhu.Bean.CH_1A_Q;

import java.sql.Connection;
/**
 * @author tlj
 */
public interface CH_1A_QRepository {
    public Integer insert(CH_1A_Q c, Connection conn) throws IllegalAccessException, Exception;
}
