package cn.hhu.repository.impl;

import cn.hhu.Bean.CH_1B_Q;
import cn.hhu.repository.CH_1B_QRepository;
import cn.hhu.utils.DbUtils;

import java.sql.Connection;

/**
 * @author tlj
 */
public class CH_1B_QRepositoryImpl implements CH_1B_QRepository {


    @Override
    public Integer insert(CH_1B_Q c, Connection conn) throws IllegalAccessException {
        String sql = DbUtils.getInsertSql(c);
        return DbUtils.update(sql,conn);
    }
}
