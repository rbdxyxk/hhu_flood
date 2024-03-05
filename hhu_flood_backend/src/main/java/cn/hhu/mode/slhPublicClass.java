package cn.hhu.mode;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class slhPublicClass {
    private static String user;
    private static String password;
    private static String url;
    private static String driver;
    static {
        try (InputStream is = slhPublicClass.class.getClassLoader().getResourceAsStream("jdbc.properties")) {
            Properties properties = new Properties();
            properties.load(is);
            //获取配置信息
            user = properties.getProperty("user");
            password = properties.getProperty("password");
            url = properties.getProperty("url");
            driver = properties.getProperty("driverclass");
            //注册
            Class.forName(driver);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public static Connection getMysqlConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url,user,password);
        }catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }

    public static void main(String[] args) {
        System.out.println(getMysqlConnection());
        double wi=sglLineInsertV("51112101",24.8);
        System.out.println("waterQuantity： "+wi);

    }
    public static double  sglLineInsertV(String STCD ,double  WaterH) //线性插值函数,由水位插值得到蓄量
    {
        String tabNmP = "st_zvarl_b"; //库容曲线表
        String Sqlstr, stdCode;int i ;
        double sglTempMinH1, sglTempMinQ1; double rV = 0.0;
        double  sglTempMinH2, sglTempMinQ2 ;
        ArrayList<Double> sglHandQData0=new ArrayList();
        ArrayList<Double> sglHandQData1=new ArrayList();
        i = 0;
        stdCode = STCD;
        Sqlstr = "Select RZ,W FROM st_zvarl_b WHERE STCD='" + stdCode +
                "' and RZ<=" + (WaterH + 2.5) + " and RZ>=" + (WaterH - 2.5) + " order by RZ asc";//''搜索半径为5米
        try ( Connection connection = getMysqlConnection();
              Statement ps = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                      ResultSet.CONCUR_READ_ONLY);
              //执行查询
              ResultSet rs = ps.executeQuery(Sqlstr);
        ) {
            ResultSetMetaData rsm = rs.getMetaData();
            //计数
            int rowCount=0;
            while (rs.next()){rowCount =rowCount +1;}
            //对数据处理
            if (rowCount > 0)
            {
                rs.absolute(1);
                sglTempMinH1 =rs.getDouble("RZ");// Format(dbrsLZV.Fields("Z").Value, "#.00")
                sglTempMinQ1 =rs.getDouble("W");// Format(dbrsLZV.Fields("V").Value, "#.00")
                if (rowCount == 1)
                {
                    rV = -1;
                    return rV;
                } //''只有一个数据

                for(i=1;i<=rowCount ;i++) //  ''找到差距最小值,即与WaterH最近的值；同时读取数据
                { rs.absolute(i);
                    if (Math.abs(sglTempMinH1 - WaterH) - Math.abs(rs.getDouble("RZ") - WaterH) > 0) {
                        sglTempMinH1 = rs.getDouble("RZ");
                        sglTempMinQ1 = rs.getDouble("W");
                    }
                    sglHandQData0.add(rs.getDouble("RZ"));
                    sglHandQData1.add(rs.getDouble("W"));
                }
                for( i = 0;i<sglHandQData0.size();i++)
                {
                    if (Math.abs(sglHandQData0.get(i) - sglTempMinH1) < 0.001)
                    //''改变最小，将其增加一个绝对大量，认为其本身是绝对大值
                    {
                        sglHandQData0.set(i, sglTempMinH1 + sglHandQData0.get(i));
                        sglHandQData1.set(i, sglTempMinQ1 + sglHandQData1.get(i));
                    }
                }
                sglTempMinH2 =  sglHandQData0.get(0);
                sglTempMinQ2 = sglHandQData1.get(0);

                for (i = 0; i<sglHandQData0.size();i++) //''找到差距次最小值，即与WaterH次近的值；
                {  if (Math.abs(sglTempMinH2 - WaterH) - Math.abs(sglHandQData0.get(i) - WaterH) > 0 )
                {
                    sglTempMinH2 = sglHandQData0.get(i);
                    sglTempMinQ2 = sglHandQData1.get(i);
                }
                }
            }
            else
            {
                rV = -1;
                return rV;
            }
            if (sglTempMinH2 == sglTempMinH1)
            {
                rV = -2;
                return rV;
            }
            // '''线性插值函数
            rV  = sglTempMinQ1 * (sglTempMinH2 - WaterH) / (sglTempMinH2 - sglTempMinH1);
            rV = rV + sglTempMinQ2 * (sglTempMinH1 - WaterH) / (sglTempMinH1 - sglTempMinH2);
            rV = Math.round(rV * 100) / 100.0;

            return rV;

        }catch (Exception e) {
            e.printStackTrace();
            return rV;
        }
    }
    public static double sglLineInsertZI(String STCD, double WaterV) //线性插值函数,由蓄量插值得到水位
    {
        String tabNmP = "st_zvarl_b"; //库容曲线表
        String Sqlstr, stdCode; int i,j=0;int rowCount=0;boolean bTmp=false;
        double sglTempMinH1=0.01, sglTempMinQ1=0.01;
        Double sglTempMinH2=0.01, sglTempMinQ2=0.01; double rZI = 0.0;
        ArrayList<Double> sglHandQData0 = new ArrayList(); ArrayList<Double> sglHandQData1 = new ArrayList();
        i = 0;
        stdCode = STCD;
        Sqlstr = "Select RZ,W FROM st_zvarl_b WHERE STCD='" + stdCode +
                "' and W<=" + (WaterV + 9500) + " and W>=" + (WaterV - 9500) + " order by W asc";//''搜索半径为5米
        //'查询最新蓄量
        try ( Connection connection = getMysqlConnection();
              Statement ps = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                      ResultSet.CONCUR_READ_ONLY);
              //执行查询
              ResultSet rs = ps.executeQuery(Sqlstr);

        )
        {
            ResultSetMetaData rsm = rs.getMetaData();
            //计数
            while (rs.next()) {       rowCount = rowCount + 1;              }
            //对数据处理
            if (rowCount > 0)
            {
                rs.absolute(1);
                sglTempMinH1 = rs.getDouble("RZ");// Format(dbrsLZV.Fields("Z").Value, "#.00")
                sglTempMinQ1 =rs.getDouble("W");// Format(dbrsLZV.Fields("V").Value, "#.00")

                if (rowCount == 1)
                {
                    rZI = -1;
                    return rZI;
                } //''只有一个数据
                for (i = 1; i <= rowCount ; i++) //  ''找到差距最小值,即与WaterV最近的值；同时读取数据
                {
                    rs.absolute(i);
                    if (Math.abs(sglTempMinQ1 - WaterV) - Math.abs(rs.getDouble("W") - WaterV) > 0)
                    {
                        sglTempMinH1 = rs.getDouble("RZ");
                        sglTempMinQ1 = rs.getDouble("W");
                    }
                    sglHandQData0.add(rs.getDouble("RZ"));
                    sglHandQData1.add( rs.getDouble("W"));
                }
                for (i = 0; i < rowCount; i++)
                {
                    //''改变最小，将其增加一个绝对大量，认为其本身是绝对大值
                    if (Math.abs(sglHandQData0.get(i) - sglTempMinH1) < 0.001)
                    {
                        sglHandQData0.set(i, sglTempMinH1 + sglHandQData0.get(i));
                        sglHandQData1.set(i, sglTempMinQ1 + sglHandQData1.get(i));
                    }
                }
                sglTempMinH2 =sglHandQData0.get(0);
                sglTempMinQ2 = sglHandQData1.get(0);
                for (i = 0; i < sglHandQData0.size(); i++) //''找到差距次最小值，即与WaterV次近的值；
                {
                    if (Math.abs(sglTempMinQ2 - WaterV) - Math.abs(sglHandQData1.get(i) - WaterV) > 0)
                    {
                        sglTempMinH2 = sglHandQData0.get(i);
                        sglTempMinQ2 =sglHandQData1.get(i);
                    }
                }
            }
            else
            {
                rZI = -1;
                return rZI;
            }
            if (sglTempMinH2 == sglTempMinH1)
            {
                rZI = -2;
                return rZI;
            }
            // '''线性插值函数
            rZI = sglTempMinH1 * (sglTempMinQ2 - WaterV) / (sglTempMinQ2 - sglTempMinQ1);
            rZI = rZI + sglTempMinH2 * (sglTempMinQ1 - WaterV) / (sglTempMinQ1 - sglTempMinQ2);
            rZI = Math.round(rZI * 100) / 100.0;
            return rZI;
        }catch (Exception e) {
            e.printStackTrace();
            return rZI;
        }
        // MsgBox sglTempMinH2 & "水位中有重叠数据", vbOKOnly

    }

}