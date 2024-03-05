package cn.hhu.repository;

import cn.hhu.Bean.CF_GFANDNF;

import java.sql.Connection;
import java.sql.SQLException;
/**
 * @author tlj
 */
public interface CF_GFANDNFRepository {
    public Integer insertAll(Connection conn, CF_GFANDNF cf_gfandnf) throws SQLException, IllegalAccessException;
}
