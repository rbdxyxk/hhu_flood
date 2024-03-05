package cn.hhu.repository;

import cn.hhu.Bean.CH_1B_Q;

import java.sql.Connection;
/**
 * @author tlj
 */
public interface CH_1B_QRepository {
    public Integer insert(CH_1B_Q c,Connection conn) throws Exception;
}
