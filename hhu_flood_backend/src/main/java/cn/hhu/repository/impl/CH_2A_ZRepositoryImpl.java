package cn.hhu.repository.impl;

import cn.hhu.Bean.CH_2A_Z;
import cn.hhu.repository.CH_2A_ZRepository;
import cn.hhu.utils.DbUtils;



import java.sql.Connection;

/**
 * @author tlj
 */
public class CH_2A_ZRepositoryImpl implements CH_2A_ZRepository {
    @Override
    public Integer insert(CH_2A_Z c, Connection conn) throws IllegalAccessException, Exception {
        String sql = DbUtils.getInsertSql(c);
        return DbUtils.update(sql,conn);
    }
}
