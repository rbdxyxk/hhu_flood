package cn.hhu.repository.impl;

import cn.hhu.Bean.CB_NREAD;
import cn.hhu.repository.BaseRepository;
import cn.hhu.repository.CB_NREADRepository;

import java.sql.Connection;
import java.sql.SQLException;

 public class CB_NREADRepositoryImpl extends BaseRepository implements CB_NREADRepository {
    @Override
    public Integer insertAll(Connection conn, CB_NREAD cb_nread) throws SQLException, IllegalAccessException {
        return InsertAllParams(conn,cb_nread);
    }
}
