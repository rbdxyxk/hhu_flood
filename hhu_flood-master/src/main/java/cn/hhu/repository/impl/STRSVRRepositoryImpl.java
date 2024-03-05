package cn.hhu.repository.impl;

import cn.hhu.Bean.ST_RSVR_R;
import cn.hhu.repository.STRSVRRepository;
import cn.hhu.utils.DbUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author tlj
 */
public class STRSVRRepositoryImpl implements STRSVRRepository {
    @Override
    public List<Date> getSTTM(int STCD)  {
        String sql = "SELECT TM FROM st_rsvr_r WHERE STCD = ? order by TM";

        Connection conn=null;
        PreparedStatement ps =null;
        ResultSet rs=null;
        List<Date> l= new ArrayList<>();
        try {
            conn= DbUtils.getMysqlConnection();
            Statement s = conn.createStatement();
            s.execute("USE jsycwater");
            DbUtils.close(null,s,null);
            ps=conn.prepareStatement(sql);


                ps.setObject( 1, STCD);//填充数据

            rs=ps.executeQuery();//执行结果
            //Class c=Weather.class;
            ResultSetMetaData rsmd = rs.getMetaData();//获取元数据
            Date t =null;
            while (rs.next()){
                //System.out.println(rs.getObject(1));
                //System.out.println(rsmd.getColumnCount());
                t= rs.getTimestamp(1);


                // System.out.println(rsmd.getColumnLabel(1));
                l.add(t);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(rs,ps,conn);
        }

        return l;


    }

    @Override
    public List<ST_RSVR_R> getST_RSVR_R(String id, Date TM1, Date TM2) {
        String sql = "SELECT * FROM st_rsvr_r WHERE STCD = ? AND TM between ? AND ?";

        Connection conn=null;
        PreparedStatement ps =null;
        ResultSet rs=null;
        List<ST_RSVR_R> l= new ArrayList<>();
        try {
            conn= DbUtils.getMysqlConnection();
            Statement s = conn.createStatement();
            s.execute("USE jsycwater");
            DbUtils.close(null,s,null);
            ps=conn.prepareStatement(sql);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");


            ps.setObject( 1, id);//填充数据
            ps.setObject(2,sdf.format(TM1));
            ps.setObject(3,sdf.format(TM2));
            rs=ps.executeQuery();//执行结果
            //Class c=Weather.class;
            ResultSetMetaData rsmd = rs.getMetaData();//获取元数据
            ST_RSVR_R st_rsvr_r =null;
            while (rs.next()){
                st_rsvr_r = new ST_RSVR_R();
                for(int i =1 ; i<=rsmd.getColumnCount();i++){
                    String columnName = rsmd.getColumnName(i);//列名
                    Object columnObject = rs.getObject(i);//数据
                    if(columnObject!=null) {
                        Field field = ST_RSVR_R.class.getDeclaredField(columnName);
                        field.setAccessible(true);
                        field.set(st_rsvr_r, columnObject);
                    }


                }

                l.add(st_rsvr_r);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(rs,ps,conn);
        }

        return l;
    }

    @Test
    public void test1(){
        System.out.println(getSTTM(41813388));
    }
}
