package cn.hhu.repository;

import cn.hhu.Bean.CA_PD;

import java.sql.Connection;
import java.sql.SQLException;
/**
 * @author tlj
 */
public interface CA_PDRepository {
    public Integer getMaxId();
    public Integer insertAll(Connection conn, CA_PD ca_pd) throws SQLException, IllegalAccessException;
}
