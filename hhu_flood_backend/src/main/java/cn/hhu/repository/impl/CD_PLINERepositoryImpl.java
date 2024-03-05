package cn.hhu.repository.impl;

import cn.hhu.Bean.CD_PLINE;
import cn.hhu.repository.BaseRepository;
import cn.hhu.repository.CD_PLINERepository;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author tlj
 */
public class CD_PLINERepositoryImpl extends BaseRepository<CD_PLINE> implements CD_PLINERepository {
    @Override
    public Integer insertAll(Connection conn, CD_PLINE cd_pline) throws SQLException, IllegalAccessException {
        return InsertAllParams(conn,cd_pline);
    }
}
