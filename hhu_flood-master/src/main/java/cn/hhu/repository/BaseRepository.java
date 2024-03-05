package cn.hhu.repository;

import cn.hhu.utils.DbUtils;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author tlj
 */
public abstract class BaseRepository<T> {
    public Integer InsertAllParams(Connection conn, T t) throws SQLException, IllegalAccessException {
        String sql = DbUtils.getInsertSql(t);
        return DbUtils.update(sql,conn);
    }
}
