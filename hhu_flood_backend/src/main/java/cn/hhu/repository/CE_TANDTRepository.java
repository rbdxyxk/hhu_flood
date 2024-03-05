package cn.hhu.repository;

import cn.hhu.Bean.CE_TANDT;

import java.sql.Connection;
import java.sql.SQLException;
/**
 * @author tlj
 */
public interface CE_TANDTRepository {
    public Integer insertAll(Connection conn, CE_TANDT ce_tandt) throws SQLException, IllegalAccessException;
}
