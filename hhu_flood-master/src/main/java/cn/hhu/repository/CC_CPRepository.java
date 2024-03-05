package cn.hhu.repository;

import cn.hhu.Bean.CC_CP;

import java.sql.Connection;
import java.sql.SQLException;
/**
 * @author tlj
 */
public interface CC_CPRepository {
    public Integer insertAll(Connection conn, CC_CP cc_cp) throws SQLException, IllegalAccessException;
}
