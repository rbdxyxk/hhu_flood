package cn.hhu.repository;

import cn.hhu.Bean.CD_PLINE;

import java.sql.Connection;
import java.sql.SQLException;
/**
 * @author tlj
 */
public interface CD_PLINERepository {
    public Integer insertAll(Connection conn, CD_PLINE cd_pline) throws SQLException, IllegalAccessException;
}
