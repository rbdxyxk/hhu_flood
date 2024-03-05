package cn.hhu.repository.impl;

import cn.hhu.Bean.CA_PD;
import cn.hhu.repository.BaseRepository;
import cn.hhu.repository.CA_PDRepository;
import cn.hhu.utils.DbUtils;
import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author tlj
 */
 public class CA_PDRepositoryImpl extends BaseRepository<CA_PD> implements CA_PDRepository {

    @Override
    public Integer getMaxId() {
        PreparedStatement ps =null;
        Connection conn=null;
        ResultSet rs= null;
        Integer result = 0;
        String sql="SELECT MAX(Id) FROM CA_PD";

        try {
            conn= DbUtils.getMysqlConnection();
            ps = conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()){
                result=rs.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(rs,ps,conn);
        }
        return  result;
    }


    @Override
    public Integer insertAll(Connection conn, CA_PD ca_pd) throws SQLException, IllegalAccessException {
        return InsertAllParams(conn,ca_pd);
    }

    @Test
    public void test(){
        System.out.println(getMaxId());
    }
}
