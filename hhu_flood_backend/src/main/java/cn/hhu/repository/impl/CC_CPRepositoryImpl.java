package cn.hhu.repository.impl;

import cn.hhu.Bean.CC_CP;
import cn.hhu.repository.BaseRepository;
import cn.hhu.repository.CC_CPRepository;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author tlj
 */
public class CC_CPRepositoryImpl extends BaseRepository<CC_CP> implements CC_CPRepository {
    @Override
    public Integer insertAll(Connection conn, CC_CP cc_cp) throws SQLException, IllegalAccessException {
        return InsertAllParams(conn,cc_cp);
    }
}
