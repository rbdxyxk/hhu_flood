package cn.hhu.repository;

import cn.hhu.Bean.CJ_VAL0;
import cn.hhu.utils.DbUtils;
import cn.hhu.utils.readText;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author tlj
 */
public class ControllerDataRepository {
    @Test
    public void  myInsert(){
        Map<String, Object> map = readText.readControlData();
        Connection conn=null;
        PreparedStatement ps =null;

        try {
            List<CJ_VAL0> l = (List<CJ_VAL0>) map.get("List");
            conn= DbUtils.getMysqlConnection();
            //关闭自动连jie
            conn.setAutoCommit(false);
            String sql = "insert into CJ_VAL0 values (?,?,?,?,?)";
            insertList(l,sql,conn);

            map.remove("List");
            for(Object value:map.values()){
                String sql1=DbUtils.getInsertSql(value);
                ps=conn.prepareStatement(sql1);
                ps.execute();
                ps.close();
            }

            conn.commit();
        } catch (Exception throwables) {

            throwables.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            DbUtils.close(null,ps,conn);
        }
    }
    public static <T> void insertList(List<T> l,String sql,Connection conn)  {


        PreparedStatement  ps= null;

        try {
            ps = conn.prepareStatement(sql);
            for(int i =1;i<(l.size()+1);i++) {
                T t = l.get(i - 1);
                Class clazz = t.getClass();
                Field[] fields = clazz.getDeclaredFields();
                for (int j = 0; j < fields.length; j++) {
                    fields[j].setAccessible(true);
                    ps.setObject(j + 1, fields[j].get(t));
                }

                ps.addBatch();
                //每四百条数据插入一次
                if (i % 400 == 0) {
                    ps.executeBatch();
                    ps.clearBatch();
                }
            }
            ps.executeBatch();
            ps.clearBatch();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }finally {
            DbUtils.close(null,ps,null);
        }
    }




}
