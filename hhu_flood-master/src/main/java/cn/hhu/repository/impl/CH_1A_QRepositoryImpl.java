package cn.hhu.repository.impl;

import cn.hhu.Bean.CH_1A_Q;
import cn.hhu.repository.CH_1A_QRepository;
import cn.hhu.utils.DbUtils;

import java.sql.Connection;

/**
 * @author tlj
 */
public class CH_1A_QRepositoryImpl  implements CH_1A_QRepository {
    @Override
    public Integer insert(CH_1A_Q c, Connection conn) throws Exception {
        String sql = DbUtils.getInsertSql(c);
        return DbUtils.update(sql,conn);
    }
}
