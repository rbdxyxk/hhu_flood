package cn.hhu.repository.impl;

import cn.hhu.Bean.CF_GFANDNF;
import cn.hhu.repository.BaseRepository;
import cn.hhu.repository.CF_GFANDNFRepository;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author tlj
 */
public class CF_GFANDNFRepositoryImpl extends BaseRepository<CF_GFANDNF> implements CF_GFANDNFRepository {
    @Override
    public Integer insertAll(Connection conn, CF_GFANDNF cf_gfandnf) throws SQLException, IllegalAccessException {
        return  InsertAllParams(conn,cf_gfandnf);
    }
}
