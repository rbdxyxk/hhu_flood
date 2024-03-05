package cn.hhu.repository;/*
author:tlj
*/

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author tlj
 */
public class test {
    @Test
    public void jdbcTest() throws IOException, ClassNotFoundException, SQLException {
        InputStream is = test.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties props = new Properties();
        props.load(is);
        String user = props.getProperty("user");
        String password = props.getProperty("password");
        String url = props.getProperty("url");
        String driverClass = props.getProperty("driverclass");
        Class.forName(driverClass);
        Connection conn = DriverManager.getConnection(url,user,password);
        System.out.println(conn);


    }
    @Test
    public void sqlserverTest() throws IOException, ClassNotFoundException, SQLException {
        InputStream is = test.class.getClassLoader().getResourceAsStream("jsycwater.jdbc.properties");
        Properties props = new Properties();
        props.load(is);
        String user = props.getProperty("user");
        String password = props.getProperty("password");
        String url = props.getProperty("url");
        String driverClass = props.getProperty("driverclass");
        Class.forName(driverClass);
        Connection conn = DriverManager.getConnection(url,user,password);
        String sql  = "use jsycwater SELECT a.STCD,STNM,b.TM,Z,Q,DRP \n" +
                "from st_stinfo_b a,dbo.ST_RIVER_R b,dbo.ST_PPTN_R c\n" +
                "where a.STCD=b.STCD and b.STCD=c.STCD and b.TM =c.TM and a.STNM=?\n" +
                "order by b.TM";
        PreparedStatement ps =conn.prepareStatement(sql);
        ps.setObject(1,"ww");
        ps.executeQuery();

        System.out.println(conn);


    }

}
