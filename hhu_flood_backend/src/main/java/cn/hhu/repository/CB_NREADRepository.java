package cn.hhu.repository;

import cn.hhu.Bean.CB_NREAD;

import java.sql.Connection;
import java.sql.SQLException;
/**
 * @author tlj
 */
public interface CB_NREADRepository {
    public Integer insertAll(Connection conn, CB_NREAD cb_nread) throws SQLException, IllegalAccessException;
}
