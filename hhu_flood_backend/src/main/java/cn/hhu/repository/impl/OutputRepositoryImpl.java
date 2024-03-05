package cn.hhu.repository.impl;

import cn.hhu.Bean.Output;
import cn.hhu.Bean.QinflowData;
import cn.hhu.repository.OutputRepository;
import cn.hhu.utils.DbUtils;
import cn.hhu.utils.readText;

import org.testng.annotations.Test;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author tlj
 */
public class OutputRepositoryImpl implements OutputRepository {
    //批量插入代码
    public void insertOutput(Map<String, List> map) {

        Connection conn=null;
        PreparedStatement ps =null;
        List<Output> l= map.get("Output");
        List<QinflowData> l1 = map.get("Qinflow");
        String sql = "insert into output(ID,TimeInterval,element,X_axis,Y_axis,bed,water,flow1,flow2,TP,TN,COD,BOD,NH3_H,DO) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            conn= DbUtils.getMysqlConnection();
            conn.setAutoCommit(false);//关闭自动连接
            ps = conn.prepareStatement(sql);
            for(int i =1;i<(l.size()+1);i++){
                Output output = l.get(i-1);
                //            Class clazz = output.getClass();
                //            //循环设置参数
                //            for(int j =0 ; j<16 ; j++){
                //
                //            }
                //设置参数
                ps.setInt(1,output.getID());
                ps.setInt(2,output.getTimeInterval());
                ps.setInt(3,output.getElement());
                ps.setBigDecimal(4,output.getX_axis());
                ps.setBigDecimal(5,output.getY_axis());
                ps.setBigDecimal(6,output.getBed());
                ps.setBigDecimal(7,output.getWater());
                ps.setBigDecimal(8,output.getFlow1());
                ps.setBigDecimal(9,output.getFlow2());
                ps.setBigDecimal(10,output.getTP());
                ps.setBigDecimal(11,output.getTN());
                ps.setBigDecimal(12,output.getCOD());
                ps.setBigDecimal(13,output.getBOD());
                ps.setBigDecimal(14,output.getNH3_H());
                ps.setBigDecimal(15,output.getDO());

                //攒sql
                ps.addBatch();
                //每四百条数据插入一次
                if(i%400==0){
                    ps.executeBatch();
                    ps.clearBatch();
                }




            }
            ps.executeBatch();
            ps.clearBatch();
            ps.close();
            for(QinflowData qinflowData : l1){
                sql= DbUtils.getInsertSql(qinflowData);
                ps=conn.prepareStatement(sql);
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



    @Test
    public  void myInsertTest(){

        insertOutput(readText.readFile());

    }

    @Override
    public Output getOutputByIntervalAndElement(int id, int timeInterval, int element){
        String sql = "SELECT bed,water,flow1,flow2 FROM output WHERE ID=? AND TimeInterval=? AND element=?";
        return DbUtils.getMyInstance(sql,Output.class,id,timeInterval,element);

    }

    @Override
    public List<Output> getOutputByInterval(){
        String sql = "SELECT * FROM output WHERE ID=? AND TimeInterval = ?";
        return DbUtils.getObjects(sql,Output.class,0,1);
    }

    @Override
    public int getMaxTimeInterval(int id){
        String sql = "SELECT MAX(TimeInterval) FROM output where ID = "+Integer.toString(id);
        int max = -1;
        try(Connection conn=DbUtils.getMysqlConnection();
            PreparedStatement ps=conn.prepareStatement(sql);


        ){
            //ps.setInt(1,id);

            try(ResultSet rs = ps.executeQuery(sql);){

                while(rs.next()){
                    max=rs.getInt(1);
                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println( max);
        return max;
    }

    @Override
    public List<Output> getOutputsByTimeIntervalAndElement(int id , int timeInterval , int element1 , int element2 ){
        String sql=" select Id,element,bed,water,flow1 from output where id=? AND TimeInterval=? And element>=? AND element<=?";
        return  DbUtils.getObjects(sql, Output.class, id, timeInterval, element1, element2);
        //System.out.println(l);
    }


    @Override
    public List<Output> getOutputsByTimeIntervalAndElementArray(int id , int timeInterval , int[] elements ){
        String sql=" select Id,element,bed,water,flow1 from output where id=? AND TimeInterval=? And element=?";
        List<Output> outputs = new LinkedList<>();
        try(Connection conn=DbUtils.getMysqlConnection();
            PreparedStatement ps=conn.prepareStatement(sql);){
            for (int i = 0; i < elements.length; i++) {
                ps.setObject(1,id);
                ps.setInt(2,timeInterval);
                ps.setInt(3,elements[i]);
                System.out.println(ps);
                try(ResultSet rs = ps.executeQuery();){


                    if(rs.next()) {

                        ResultSetMetaData rsmd = rs.getMetaData();//获取元数据
                        Output output = new Output();
                        for (int j = 0; j < rsmd.getColumnCount(); j++) {
                            String columnName = rsmd.getColumnName(j+1);
                            Object columnValue = rs.getObject(j+1);
                            if(columnValue!=null){
                                Field fileld = output.getClass().getDeclaredField(columnName);
                                fileld.setAccessible(true);
                                fileld.set(output,columnValue);
                            }
                        }
                        outputs.add(output);
                    }

                }


            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return outputs;
    }

    @Override
    public List<Output> getOutputsByTimeIntervalAndElementArray(int id , int timeInterval , List<Integer> elements ){
        String sql=" select Id,element,bed,water,flow1 from output where id=? AND TimeInterval=? And element=?";
        List<Output> outputs = new LinkedList<>();
        try(Connection conn=DbUtils.getMysqlConnection();
            PreparedStatement ps=conn.prepareStatement(sql);){
            for (int i = 0 , length = elements.size(); i < length; i++) {
                ps.setObject(1,id);
                ps.setInt(2,timeInterval);
                ps.setInt(3,elements.get(i));
                //System.out.println(ps);
                try(ResultSet rs = ps.executeQuery();){


                    if(rs.next()) {

                        ResultSetMetaData rsmd = rs.getMetaData();//获取元数据
                        Output output = new Output();
                        for (int j = 0; j < rsmd.getColumnCount(); j++) {
                            String columnName = rsmd.getColumnName(j+1);
                            Object columnValue = rs.getObject(j+1);
                            if(columnValue!=null){
                                Field fileld = output.getClass().getDeclaredField(columnName);
                                fileld.setAccessible(true);
                                fileld.set(output,columnValue);
                            }
                        }
                        outputs.add(output);
                    }

                }


            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return outputs;
    }


    @Test
    public void  getMaxTimeInterval(){
        String sql = "SELECT MAX(TimeInterval) FROM output where id=? ";
        int max = -1;
        try(Connection conn=DbUtils.getMysqlConnection();
            PreparedStatement ps=conn.prepareStatement(sql);


        ){
            ps.setInt(1,0);

            try(ResultSet rs = ps.executeQuery(sql);){
                while(rs.next()){
                    max=rs.getInt(1);
                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        //PreparedStatement ps=null;
        //Connection conn=null;
        //ResultSet rs=null;
        //    try{
        //        conn=DbUtils.getMysqlConnection();
        //           ps =conn.prepareStatement(sql);
        //        //ps.setObject(1,Integer.toString(0));
        //        //String rsq = ((JDBC4PreparedStatement)ps).asSql();
        //         rs = ps.executeQuery(sql);
        //        while(rs.next()){
        //                        max=rs.getInt(1);
        //                  }
        //    }catch(Exception e){
        //        e.printStackTrace();
        //    }finally{
        //        DbUtils.close(rs,ps,conn);
        //    }

            System.out.println( max);

    }
}
