package cn.hhu.repository.impl;

import cn.hhu.Bean.CE_TANDT;
import cn.hhu.repository.BaseRepository;
import cn.hhu.repository.CE_TANDTRepository;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author tlj
 */
public class CE_TANDTRepositoryImpl extends BaseRepository<CE_TANDT> implements CE_TANDTRepository {
    @Override
    public Integer insertAll(Connection conn, CE_TANDT ce_tandt) throws SQLException, IllegalAccessException {
        return InsertAllParams(conn, ce_tandt);
    }
}
