package cn.hhu.utils;

import cn.hhu.repository.test;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author tlj
 */
public class DbUtils {
    //获取jdbc连接
    public static Connection getSqlServerConnection() throws SQLException, ClassNotFoundException, IOException {
        InputStream is = test.class.getClassLoader().getResourceAsStream("SLHSQMDB.jdbc.properties");
        Properties props = new Properties();
        props.load(is);
        String user = props.getProperty("user");
        String password = props.getProperty("password");
        String url = props.getProperty("url");
        String driverClass = props.getProperty("driverclass");
        Class.forName(driverClass);
        Connection conn = DriverManager.getConnection(url,user,password);
        return  conn;
    }
    //获取jdbc连接
    public static Connection getSqlServerConnection1() throws SQLException, ClassNotFoundException, IOException {
        InputStream is = test.class.getClassLoader().getResourceAsStream("jsycwater.jdbc.properties");
        Properties props = new Properties();
        props.load(is);
        String user = props.getProperty("user");
        String password = props.getProperty("password");
        String url = props.getProperty("url");
        String driverClass = props.getProperty("driverclass");
        Class.forName(driverClass);
        Connection conn = DriverManager.getConnection(url,user,password);
        return  conn;
    }

    //获取jdbc连接
    public static Connection getMysqlConnection() throws SQLException, ClassNotFoundException, IOException {
        InputStream is = test.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties props = new Properties();
        props.load(is);
        String user = props.getProperty("user");
        String password = props.getProperty("password");
        String url = props.getProperty("url");
        String driverClass = props.getProperty("driverclass");
        Class.forName(driverClass);
        Connection conn = DriverManager.getConnection(url,user,password);
        return  conn;
    }


    public static void close(ResultSet rs, Statement st, Connection conn){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(st!=null){
            try {
                st.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    //查询jsycwater
    public static  <T> List<T> getInstances(String sql,Class<T> clazz,Object... args){
        Connection conn=null;
        PreparedStatement ps =null;
        ResultSet rs=null;
        List<T> l= new ArrayList<>();
        try {
            conn= DbUtils.getSqlServerConnection1();
            ps=conn.prepareStatement(sql);

            for(int i=0;i<args.length;i++)
                ps.setObject(i+1, args[i]);//填充数据

            rs=ps.executeQuery();//执行结果
            //Class c=Weather.class;
            ResultSetMetaData rsmd = rs.getMetaData();//获取元数据
           T t =null;
            while (rs.next()){
                //System.out.println(rsmd.getColumnCount());
                t=clazz.newInstance();

                for(int i =1 ; i<=rsmd.getColumnCount();i++){
                    String columnName = rsmd.getColumnName(i);//列名
                    Object columnObject = rs.getObject(i);//数据
                    if(columnObject!=null) {
                        Field field = clazz.getDeclaredField(columnName);

                        // System.out.println(field.getType());
                        field.setAccessible(true);
//                    if(field.getType()==Float.class){
//                        System.out.println("true");
//                        field.setFloat(weather,new Float(rs.getFloat(i)));
//                    }else {

                        field.set(t, columnObject);
                    }
                    // }

                }
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

    //查询mysql waterflood
    public static  <T> List<T> getObjects(String sql,Class<T> clazz,Object... args){
        Connection conn=null;
        PreparedStatement ps =null;
        ResultSet rs=null;
        List<T> l= new ArrayList<>();
        try {
            conn= DbUtils.getMysqlConnection();
            ps=conn.prepareStatement(sql);

            for(int i=0;i<args.length;i++) {
                ps.setObject(i + 1, args[i]);//填充数据
            }
            rs=ps.executeQuery();//执行结果
            //Class c=Weather.class;
            ResultSetMetaData rsmd = rs.getMetaData();//获取元数据
            T t =null;
            while (rs.next()){
                //System.out.println(rsmd.getColumnCount());
                t=clazz.newInstance();

                for(int i =1 ; i<=rsmd.getColumnCount();i++){
                    String columnName = rsmd.getColumnName(i);//列名
                    Object columnObject = rs.getObject(i);//数据
                    if(columnObject!=null) {
                        Field field = clazz.getDeclaredField(columnName);

                        // System.out.println(field.getType());
                        field.setAccessible(true);
//                    if(field.getType()==Float.class){
//                        System.out.println("true");
//                        field.setFloat(weather,new Float(rs.getFloat(i)));
//                    }else {

                        field.set(t, columnObject);
                    }
                    // }

                }
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
    //查询mysql waterflood
    public static  <T> T getMyInstance(String sql,Class<T> clazz,Object... args){
        Connection conn=null;
        PreparedStatement ps =null;
        ResultSet rs=null;
        T t =null;

        try {
            conn= DbUtils.getMysqlConnection();
            ps=conn.prepareStatement(sql);

            for(int i=0;i<args.length;i++){
                ps.setObject(i+1, args[i]);//填充数据
            }


            rs=ps.executeQuery();//执行结果
            //Class c=Weather.class;
            ResultSetMetaData rsmd = rs.getMetaData();//获取元数据

            if (rs.next()){
                //System.out.println(rsmd.getColumnCount());
                t=clazz.newInstance();

                for(int i =1 ; i<=rsmd.getColumnCount();i++){
                    String columnName = rsmd.getColumnName(i);//列名
                    Object columnObject = rs.getObject(i);//数据
                    if(columnObject!=null) {
                        Field field = clazz.getDeclaredField(columnName);

                        // System.out.println(field.getType());
                        field.setAccessible(true);
//                    if(field.getType()==Float.class){
//                        System.out.println("true");
//                        field.setFloat(weather,new Float(rs.getFloat(i)));
//                    }else {

                        field.set(t, columnObject);
                    }
                    // }

                }
                // System.out.println(rsmd.getColumnLabel(1));



            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(rs,ps,conn);
        }

        return t;
    }

    public static int update(String sql,Connection conn,Object...Objects)  {
        PreparedStatement ps = null;
        int result = - 1;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < Objects.length; i++) {
                ps.setObject(i+1,Objects[i]);
            }
            result=ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw(new RuntimeException(throwables));
        } finally {
            close(null,ps,null);
        }

        return result;
    }
    public static  String getInsertSql(Object value) throws IllegalAccessException {

        Class clazz=value.getClass();
        String name = clazz.getSimpleName();
        Field[] fields = clazz.getDeclaredFields();
        String sql1 ="insert into "+name+"(";
        String sql11=" values (";
        for (Field field:fields){
            field.setAccessible(true);
            if(field.get(value)!=null) {
                sql1 = sql1 + field.getName() + ",";
                sql11 = sql11 + "'" + field.get(value) + "'" + ",";
            }
        }
        StringBuilder sql2 =new StringBuilder(sql1);
        StringBuilder sql22 =new StringBuilder(sql11);
        sql2.setCharAt(sql2.length()-1,')');
        sql22.setCharAt(sql22.length()-1,')');
        System.out.println(sql2.toString()+sql22.toString());
        return sql2.toString()+sql22.toString();
    }


    @Test
    public void test()  {
        String path="web\\Exe\\RSFVMWQltl.exe";
        String[] envp = {"calfileNM1.txt"};
        BufferedReader br = null;
        Process process =null;
        try{
             process = Runtime.getRuntime().exec(" cmd /c E: && cd E:\\Fortran\\ForResearch &&  E:\\IdeaProject\\Cesium-test\\out\\artifacts\\Cesium_test_Web_exploded\\Exe\\RSFVMWQltl.exe");
            //转换流
            InputStreamReader isr = new InputStreamReader(process.getErrorStream());
            //缓冲流用于打印错误信息
             br = new BufferedReader(isr);
            String line =null;
            int i=0;
            while((line=br.readLine())!=null){
                System.out.println(i+++":"+line);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if (process != null){
                process.destroy();
            }
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }


}
