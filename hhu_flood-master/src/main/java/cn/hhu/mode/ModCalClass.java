package cn.hhu.mode;

import nhri.xaj.*;

import java.io.InputStream;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
//
public class ModCalClass {
    private static String user;
    private static String password;
    private static String url,url2;
    private static String driver;
    static {
        try (InputStream is = slhPublicClass.class.getClassLoader().getResourceAsStream("jdbc.properties")) {
            Properties properties = new Properties();
            properties.load(is);
            //获取配置信息
            user = properties.getProperty("user");
            password = properties.getProperty("password");
            url = properties.getProperty("url");
            driver =properties.getProperty("driverclass");
            //加载驱动，注册
            Class.forName(driver);
            //获取模型专用数据库
            InputStream is2 = slhPublicClass.class.getClassLoader().getResourceAsStream("floodDB.properties") ;
            Properties properties2 = new Properties();
            properties2.load(is2);
            url2 = properties2.getProperty("floodDB.url");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    /// 模型计算中间成果，净雨量，上下深土湿，壤中、地下、地表产流，流域汇流
    /// </summary>
    double[][] Pe = new double[5][120];
    double[][] WU = new double[5][120];
    double[][] WL = new double[5][120]; double[][] WD = new double[5][120];
    double[][] qqQQG = new double[5][120];double[][] qqQQI = new double[5][120];  double[][] qqQQS = new double[5][120];
    double[][] qqThreeSum = new double[5][120];
    double[][] RegBQ = new double[5][120];
    double[] autoP = new double[5]; double[][] autoWandQ = new double[5][4]; double autoDGZFlow = 0.0, autoZI=0.0,autoV=0.0;///自动计算时读取的雨量、土湿及流量

    /// 入库流量、累计水量
    double[] ResvQ = new double[120]; double[] ResvW = new double[120];
    int cNum = 0; double cdatT = 0.0; double[][] origP = new double[5][120];//oriP为每一计算输入的小时降雨量
    Date modBegTM=new Date();
    String Id, paraId,paraName;
    double ZI = 0.0, V = 0.0, ResFlow = 0.0;//水库水位、水库蓄量、水库出流
    //
    public String getCal_Id()    {   return Id; }
    public void setCal_Id(String Id)    { this.Id = Id; }
    //
    public String getCal_ParaId()    {   return paraId; }
    public void setCal_ParaId(String paraId)    { this.paraId = paraId; }

    public String getCal_ParaName()    {   return paraName; }

    public void setCal_ParaName(String paraName)    { this.paraName = paraName; }
///连接实时雨水情数据库，集成时需重新考虑
    public static Connection getMysqlConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url,user,password);//遥测数据-实时雨水情数据库

        }catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }
    //连接模型专用数据库
    public static Connection getMysqlConnFLDB(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url2,user,password);//遥测数据-实时雨水情数据库

        }catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }
    private double[][]  readPara(char  online)
    {
        double[][] paraCaL = new double[5][29];
        String tabNmQ = "PARA_FORtemp"; String tabNmW = "PARA_Weather"; String tabNmP = "PARA_CALPLAN"; String tabNmR = "PARA_HY"; int i;
        String Sqlstr,SqlTmp,SqlTmp1,SqlTmp2,Sqlstr0;
        //执行查询
        Sqlstr0="select * from PARA_FORECAST where ParaName='"+paraName+"'";
        try (
                Connection connection = getMysqlConnFLDB();
                Statement ps1 = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);
        ) {
            ResultSet pid = ps1.executeQuery(Sqlstr0);
             if (pid.next()){
                this.paraId=pid.getString("ParamId");
            }
            pid.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (online=='1')
        { Sqlstr = "select * from PARA_FORECAST where IsOnLine='1' order by CalRegCD asc";}
        else
        { Sqlstr = "select * from PARA_FORECAST where ParamId='" + paraId + "' order by CalRegCD asc";}

        SqlTmp = "select * from para_weather where Id='" + Id + "' order by CalRegCD asc";
        SqlTmp1=  "select * from para_calplan where Id='" + Id + "'";
        SqlTmp2= "select * from para_uphydst where Id='" + Id + "' ";
        try (
                         Connection connection = getMysqlConnFLDB();
                         Statement ps = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                                 ResultSet.CONCUR_READ_ONLY);
             )
        //执行查询
        {
            int rowCount=0;
            ResultSet gDt = ps.executeQuery(Sqlstr);
            while (gDt.next()){rowCount =rowCount +1;}
                if (rowCount > 0)
                    for (i = 1; i <= rowCount; i++) {
                        gDt.absolute(i);
                        paraCaL[i-1][0] =gDt.getDouble("A");//计算面积
                        paraCaL[i-1][1] =gDt.getDouble("DATT");//计算时间步长
                        paraCaL[i-1][2] =gDt.getDouble("B");//蓄水容量分布指数
                        paraCaL[i-1][3] =gDt.getDouble("EX");//自由水分布曲线指数
                        paraCaL[i-1][4] =gDt.getDouble("IM");//不透水面积
                        paraCaL[i-1][5] =gDt.getDouble("KI");//壤中流出流系数
                        paraCaL[i-1][6] =gDt.getDouble("KG");//地下水出流系数
                        paraCaL[i-1][7] =gDt.getDouble("CI");//壤中流消退系数
                        paraCaL[i-1][8] =gDt.getDouble("CG");//地下水消退系数
                        paraCaL[i-1][9] =gDt.getDouble("CS");//汇流系数
                        paraCaL[i-1][10] =gDt.getDouble("L");//汇流系数
                        paraCaL[i-1][11] =gDt.getDouble("X");//汇流系数
                        paraCaL[i-1][12] =gDt.getDouble("SM");//表层土自由水蓄水容量
                        paraCaL[i-1][13] =gDt.getDouble("WUM");//上层土壤张力水容量
                        paraCaL[i-1][14] =gDt.getDouble("WLM");//下层土壤张力水容量
                        paraCaL[i-1][15] =gDt.getDouble("WDM"); //深层土壤张力水容量
                        paraCaL[i-1][16] =gDt.getDouble("C");//深层散发系数
                        paraCaL[i-1][28] =gDt.getDouble("K");//槽蓄系数
                    }
//                paraId = gDt.getString("ParamId");

                rowCount=0;
                ResultSet rs = ps.executeQuery(SqlTmp);
                while (rs.next()){rowCount =rowCount +1;}
                System.out.println(rowCount);
                System.out.println("0000000000000000");
                if (rowCount > 0)
                    for (i = 1; i <=rowCount; i++) {
                        rs.absolute(i) ;
                        paraCaL[i-1][17] =rs.getDouble("RainHour");//小时雨量
                        paraCaL[i-1][18] =rs.getDouble("RainFirst");//
                        paraCaL[i-1][19] =rs.getDouble("RainSecond");
                        paraCaL[i-1][20] =rs.getDouble("RainThird");
                        paraCaL[i-1][21] =rs.getDouble("Eva");//蒸发
                        paraCaL[i-1][22] =rs.getDouble("W");//土湿总体状况
                        paraCaL[i-1][23] =paraCaL[i-1][22] *0.3;
                        paraCaL[i-1][24] =paraCaL[i-1][22] *0.3;//分成三层
                        paraCaL[i-1][22]=paraCaL[i-1][22]*0.4;
                        paraCaL[i-1][25] =rs.getDouble("FlowDeep");//流域基流,河网汇流初值
                    }
                //gDt.Tables[tabNmW].Clear();
                if (online == '1')//is online model,使用实时计算的雨量、土湿及基流
                {
                    for (i = 0; i < 5; i++) {
                        paraCaL[i][17] =autoP[i];//小时雨量
                        paraCaL[i][18] =autoP[i];//RainFirst
                        paraCaL[i][19] =autoP[i];//RainSecond
                        paraCaL[i][20] =autoP[i];//RainThird
                        paraCaL[i][21] =3 / 24;//蒸发 0.125;每天3mm
                        paraCaL[i][22] =autoWandQ[i][0];// 三层土湿均使用读取的数据
                        paraCaL[i][23] =autoWandQ[i][1];
                        paraCaL[i][24] =autoWandQ[i][2];
                        paraCaL[i][25] =autoWandQ[i][3];//流域基流,河网汇流初值
                    }
                }
                //读取计算时长,小时
                rowCount=0;
                ResultSet rs1 = ps.executeQuery(SqlTmp1);
                while (rs1.next()){rowCount =rowCount +1;}
                System.out.println(rowCount);
                System.out.println("1111111111111111");
                if (rowCount > 0) {rs1.absolute(1);
                    paraCaL[0][26] =rs1.getDouble("CalTimeLong");
                    modBegTM = rs1.getDate("Start");
                }//

                if (online == '1') {
                    paraCaL[0][26] =1;
                }//is online run.计算时长，小时单位

                //读取上游水闸泄流
                rowCount=0;
                ResultSet rs2 = ps.executeQuery(SqlTmp2);

                while (rs2.next()){rowCount =rowCount +1;}
                System.out.println(rowCount);
                System.out.println("22222222222222222222");
                if (rowCount > 0) {
                    rs2.absolute(1);
                    paraCaL[0][27] = rs2.getDouble("FlowFirst");
                    ZI = rs2.getDouble("ZI");
                    V = rs2.getDouble("V");
                    ResFlow = rs2.getDouble("ResFlow");
                }//
                gDt.close();
                rs.close() ;
                rs1.close();// gDt.Tables[tabNmP].Clear();
                 rs2.close();

                if (online == '1') {
                    paraCaL[0][27] =autoDGZFlow;
                    if (autoZI > 0) {
                        ZI = autoZI;
                        V = autoV;
                    }
                }//is online run.读取参数更新时间,大官庄流量


        }
        catch (Exception e)
        {
            e.printStackTrace();
            paraCaL[0][ 0] = 0.01;
            return paraCaL;
        }
        return paraCaL;
    }
    public String CalClickBegin(char online) throws ParseException {
        double[][] paraCaL; String infMod = "";
        if (online == '1')//is online model,
        { String tmp=initAutoCalPara(Id); }
        paraCaL = readPara(online);
        if (paraCaL[0][0] < 0.02) { return "no full para"; }
        infMod= calMoadel(paraCaL);
        if (infMod == "计算时段数太多(计算时长/时段长)，终止计算。计算的时段数应小于120个!") { return infMod; }
        infMod = infMod + writeData(modBegTM, cNum, cdatT, online);
        return infMod;

    }
    public String autoCalBegin(char online, double[][] pAndQ) throws ParseException//该函数可以删除。该函数用于自动预报计算，但在calMoadel已处理自动预报、人工干预预报
    {
        double[][] paraCaL = new double[5][28]; String infMod = "";

        paraCaL = readPara( online);
        if (paraCaL[0][0] < 0.02) { return "no full para"; }
        for (int i = 0; i < 5; i++)
        {
            paraCaL[i][17] = pAndQ[i][4];//降雨

        }
        for (int i = 0; i < 5; i++)
        {for (int j=22;j<26;j++)
        {
            paraCaL[i][j] = pAndQ[i][j-22];//土湿和流域基流
        }
            paraCaL[i][26] = 1;//计算时长，小时单位
        }
        paraCaL[0][27] = pAndQ[0][5];//大官庄泄流
        infMod = calMoadel(paraCaL);
        infMod = infMod + writeData(modBegTM, cNum, cdatT,'1');
        return infMod;
    }
    private String calMoadel(double[][] paraCaL)
    {
        NhriModelXAJClass[] SLHReg = new NhriModelXAJClass[5];double[][] RegRainEV=new double[5][5];
        double  calLongT = 0.0;//计算时长,小时单位
        double[] kQ = new double[10];
        double DaGuanZhuangQ = 0;
        cdatT = 1001;//给个极大的初值
        for (int i = 0; i < 5; i++)
        {
            SLHReg[i] = new NhriModelXAJClass();
            SLHReg[i].setMod_A(paraCaL[i][0]); SLHReg[i].setMod_datT(paraCaL[i][1]);
            SLHReg[i].setMod_B(paraCaL[i][2]); SLHReg[i].setMod_ex(paraCaL[i][3]);
            SLHReg[i].setMod_IM(paraCaL[i][4]); SLHReg[i].setMod_ki(paraCaL[i][5]);
            SLHReg[i].setMod_kg(paraCaL[i][6]); SLHReg[i].setMod_ci(paraCaL[i][7]);
            SLHReg[i].setMod_cg(paraCaL[i][8]); SLHReg[i].setMod_cs(paraCaL[i][9]);
            SLHReg[i].setMod_L((int)paraCaL[i][10]); SLHReg[i].setMod_x(paraCaL[i][11]);
            SLHReg[i].setMod_sm(paraCaL[i][12]); SLHReg[i].setMod_WUM(paraCaL[i][13]);
            SLHReg[i].setMod_WLM(paraCaL[i][14]); SLHReg[i].setMod_WDM(paraCaL[i][15]);
            SLHReg[i].setMod_C( paraCaL[i][16]); SLHReg[i].setMod_K(paraCaL[i][28]);
            //未来气象参数,小时雨量、未来第一、第二、第三天雨量、蒸发 /// 人工干预预报输入
            ///paraCaL[i, 17]当前降雨，此处为一固定值，若有未来降雨应为一系列值
            RegRainEV[i][0] = paraCaL[i][17]; RegRainEV[i][1] = paraCaL[i][18];
            RegRainEV[i][2] = paraCaL[i][19]; RegRainEV[i][3] = paraCaL[i][20];
            RegRainEV[i][4] = paraCaL[i][21];
            //当前三层土湿状况/// 人工干预预报输入
            SLHReg[i].setMod_WU(paraCaL[i][22]*0.4);//20偏湿
            SLHReg[i].setMod_WL(paraCaL[i][23] * 0.3);
            SLHReg[i].setMod_WD(paraCaL[i][24] * 0.3);
            ///日模型计算结果
            ///沂沭泗流域datamx数据库 表dastly  dastly0 中有该值
            SLHReg[i].setMod_dayFR( 0.3);
            SLHReg[i].setMod_dayS(5.3);
            if (cdatT > SLHReg[i].getMod_datT()) { cdatT = SLHReg[i].getMod_datT(); }//找到最小的时段长
        }
        //paraCaL[i, 25] //Qb用基流
        calLongT = paraCaL[0][26];/// 计算时长。人工干预预报输入；自动预报为1小时
        DaGuanZhuangQ = paraCaL[0][27];                         /// 大官庄泄流

        cNum = (int)(calLongT / cdatT) ;//计算的时段数，计算时长/时段长
        int tmpJ = 0;
        if (cNum > 120) { return "计算时段数太多(计算时长/时段长)，终止计算。计算的时段数应小于120个!"; }
        for (int i = 0; i < cNum; i++)
        {
            for (int j = 0; j < 5; j++)
            {
                double[] PeWULD = new double[4]; double[] qqThree = new double[3]; double Qb = 0.0; double Qt = 0.0;
                SLHReg[j].InitPara();
                double inP = (i == 0 ? RegRainEV[j][0] : 0) * cdatT;//每一计算时段雨量,如计算时段为0.5小时、2小时等,此处仅是第一个时段有雨量，其它均为0
                PeWULD = SLHReg[j].EvFlowCal(inP, RegRainEV[j][4]);//计算净雨量 函数参数(雨量,蒸发)
                Pe[j][i] = PeWULD[0]; WU[j][i] = PeWULD[1]; WL[j][i] = PeWULD[2]; WD[j][i] = PeWULD[3];

                qqThree = SLHReg[j].divThreeWater(PeWULD[0]);  //计算三层产流量
                qqQQG[j][i] = qqThree[0]; qqQQI[j][i] = qqThree[1]; qqQQS[j][i] = qqThree[2];
                qqThreeSum[j][i]=qqThree[0]+qqThree[1]+qqThree[2];

                if (i > SLHReg[j].getMod_L())
                {
                    Qb = qqThreeSum[j][i - 1]; Qt = qqThreeSum[j][i - SLHReg[j].getMod_L()];
                    RegBQ[j][i] = SLHReg[j].riverNetFlow(Qb, Qt);//计算流域单元(区域)汇流量
                }
                else
                { RegBQ[j][i] = SLHReg[j].riverNetFlow(paraCaL[j][25], qqThreeSum[j][i]); } //Qb用基流 paraCaL[j, 25]
                tmpJ = j;
                origP[j][i] = inP;//每一计算时段雨量,
            }
            ///调用马斯京根入库流量演算; 0临沭分区\\1石门分区\\2大兴镇分区\\3张疃分区\\4库区周边分区
            ///每个河段断面基流 DaGuanZhuangQ 逐渐递减

            kQ[0] = RegBQ[0][i] * 0.2 + RegBQ[1][i] * 0.2 + RegBQ[2][i] * 0 + RegBQ[3][i] * 0 + RegBQ[4][i] * 0 + DaGuanZhuangQ*0.9;
            kQ[1] = RegBQ[0][i] * 0.2 + RegBQ[1][i] * 0.2 + RegBQ[2][i] * 0 + RegBQ[3][i] * 0 + RegBQ[4][i] * 0 + DaGuanZhuangQ * 0.8;
            kQ[2] = RegBQ[0][i] * 0.2 + RegBQ[1][i] * 0.3 + RegBQ[2][i] * 0 + RegBQ[3][i] * 0.1 + RegBQ[4][i] * 0 + DaGuanZhuangQ * 0.8;
            kQ[3] = RegBQ[0][i] * 0.1 + RegBQ[1][i] * 0.2 + RegBQ[2][i] * 0.1 + RegBQ[3][i] * 0.1 + RegBQ[4][i] * 0 + DaGuanZhuangQ * 0.7;
            kQ[4] = RegBQ[0][i] * 0.1 + RegBQ[1][i] * 0.1 + RegBQ[2][i] * 0.1 + RegBQ[3][i] * 0.2 + RegBQ[4][i] * 0 + DaGuanZhuangQ * 0.7;
            kQ[5] = RegBQ[0][i] * 0.08 + RegBQ[1][i] * 0 + RegBQ[2][i] * 0.2 + RegBQ[3][i] * 0.3 + RegBQ[4][i] * 0 + DaGuanZhuangQ * 0.6;
            kQ[6] = RegBQ[0][i] * 0.08 + RegBQ[1][i] * 0 + RegBQ[2][i] * 0.3 + RegBQ[3][i] * 0.2 + RegBQ[4][i] * 4 + DaGuanZhuangQ * 0.6;
            kQ[7] = RegBQ[0][i] * 0.04 + RegBQ[1][i] * 0 + RegBQ[2][i] * 0.3 + RegBQ[3][i] * 0.1 + RegBQ[4][i] * 6 + DaGuanZhuangQ * 0.6;

            SLHReg[0].MuskingumRiverFlow(8, DaGuanZhuangQ, kQ);
            ResvQ[i] = kQ[7];//计算成果入库流量
        }
        return "calculating is ok. ";
    }
    //writeData函数参数：CalbgT 计算开始时间；cdatT 计算时段; online 是否自动运行
    private String writeData(Date CalbgT, int cNum, double cdatT, char online) throws ParseException {   //写入数据库
        int inNum = 0; int iTmp = 0; String sqlSTR;
//        //转换时间格式为数据库内部类型
//        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date now = new Date();
//        String format = sf.format(now);
//        Date parse = sf.parse(format);
//        System.out.println("1:"+now);
//        System.out.println("2:"+format);

        String  CalRegCD; int PDNUM; Date FLOWTM=new Date(); double tP,tPE, tWU, tWL, tWD, QQG, QQI, QQS, Q; Date TS=new Date();
        String tabNmW = "MOD_MIDRESULT"; String tabNmFRUIT = "MOD_FRUIT";
        String Sqlstr,SqlTmp,SqlTmp1,SqlTmp2,SqlTmp3;
        //执行查询
        Sqlstr = "select * from MOD_MIDRESULT where Id='" + Id  + "' order by PDNUM desc";
        SqlTmp = "select * from MOD_FRUIT where Id='" + Id + "' order by PDNUM desc";//读取计算成果的水库水位;
        SqlTmp1=  "";
        SqlTmp2= "";

        try ( Connection connection = getMysqlConnFLDB();
              Statement ps = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                      ResultSet.CONCUR_READ_ONLY);
              //执行查询


        ) {
           for (int i = 0; i < cNum; i++)
            {
                ResultSet gDt = ps.executeQuery(Sqlstr);
                long cdatTmp= (long) Math.round(10*cdatT);//将小时数处理成长整型
                cdatTmp=CalbgT.getTime()/1000+(i+1) * cdatTmp*6*60;//getTime是毫秒，换成秒。上面一行乘了10，所以这行少乘10
                PDNUM = i; FLOWTM.setTime(cdatTmp*1000);
                if (online == '1') ///is online model
                {
                    int rowCount=0;
                    while (gDt.next()){rowCount =rowCount +1;}
                    if (rowCount > 0)
                    { gDt.absolute(1);
                        PDNUM = gDt.getInt("PDNUM") + 1;
                        cdatTmp= (long) Math.round(10*cdatT);
                        cdatTmp=gDt.getDate("FLOWTM").getTime()/1000+ cdatTmp*6*60;
                        FLOWTM.setTime(cdatTmp*1000);
                    }
                    //else { PDNUM = 0; FLOWTM = CalbgT.AddHours((i + 1) * cdatT); }
                    gDt.close();
                }
                //读取计算成果的水库水位
                ResultSet rs = ps.executeQuery(SqlTmp);
                int rowCount=0;
                while (rs.next()){rowCount =rowCount +1;}
                if (rowCount > 0)
                {rs.absolute(1);
                    ZI =rs.getDouble("ZI");
                    V = rs.getDouble("V");
                }
                rs.close() ;

                for (int j = 0; j < 5; j++)
                {

                    CalRegCD = "SLHRES000" + (j + 1);
                    tP = origP[j][i];
                    tPE = Pe[j][i];
                    tWU = WU[j][i];
                    tWL = WL[j][i];
                    tWD = WD[j][i];
                    QQG = qqQQG[j][i];
                    QQI = qqQQI[j][i];
                    QQS = qqQQS[j][i];
                    Q = RegBQ[j][i]; TS.getTime() ;
                    //转换
                    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String format = sf.format(FLOWTM);
                    String format1 = sf.format(TS);
                    sqlSTR = "insert into MOD_MIDRESULT(Id,CalRegCD,PDNUM,FLOWTM,P,PE,WU,WL,WD,QQG,QQI,QQS,Q,TS) " +
                            " values('" + Id + "','" + CalRegCD + "'," + PDNUM + ",'" + format + "'," +tP+ ","+ tPE + "," + tWU + "," +
                            tWL + "," + tWD + "," + QQG + "," + QQI + "," + QQS + "," + Q + ",'" + format1 + "')";
                    ps.execute(sqlSTR);
                   // sCmd.CommandText = sqlSTR;
                   // iTmp = sCmd.ExecuteNonQuery();
                    inNum = inNum + iTmp;
                }
                FLOWTM=new Date() ;
                cdatTmp= (long) Math.round(10*i *cdatT);
                FLOWTM.setTime( (FLOWTM.getTime()/1000+ cdatTmp*6*60)*1000); TS.getTime() ;

                V = V + (ResvQ[i] - ResFlow) * cdatT * 0.36;//水库蓄量=当前蓄量+（入库流量-出库流量）*时间
                ZI = slhPublicClass.sglLineInsertZI("51112101", V);
                SimpleDateFormat sf0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String format0 = sf0.format(FLOWTM);
                String format2 = sf0.format(TS);
                sqlSTR = "insert into MOD_FRUIT(Id,PDNUM,FLOWTM,Q,W,ZI,V,TS) " +
                        " values('" + Id + "'," + PDNUM + ",'" + format0 + "'," + ResvQ[i] + "," + ResvQ[i] * cdatT * 0.36 + "," +ZI+ ","+V+ ",'" + format2 + "')";
                ps.execute(sqlSTR);
                inNum = inNum + iTmp;
            }
            if (online == '1')
            {
                SimpleDateFormat sf11 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                String format0 = sf0.format(FLOWTM);
                String format21 = sf11.format(TS.getTime());
                sqlSTR = "update  PARA_CALPLAN set CalTimeLong=CalTimeLong+1,TS='" + format21  + "' where Id='" + Id + "' ";
                ps.execute(sqlSTR);
                inNum = inNum + iTmp;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        //this.Response.Write("<script>alert(\"计算完成、结果已保存。\");;</script>");
        return "the number of written record is "+inNum;
        // this.Response.Write("<script>alert( " + inNum + ");;</script>");
    }
    private String initAutoCalPara(String arcId)
    {
        String tabNmPP = "ST_PPTN_R"; String tabNmUP = "PARA_UPHYDST";
        String tabNmW = "MOD_MIDRESULT"; String mdTime = ""; String sqlStr1 = "";
        int i; String sbTM = ""; String seTM = ""; String CalRegCD = ""; int rTmp=0, infectRow=0;
        double tmpW;
        Double p1 = 0.0, p2 = 0.0, p3 = 0.0, p4 = 0.0, p5 = 0.0;
        Date datTmp=new Date() ;
        ///读取实时测量雨量数据
        SimpleDateFormat sf0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format00 = sf0.format(datTmp);
        seTM=format00;
//        seTM = datTmp.toString();
        datTmp.setTime(1000*(datTmp.getTime()/1000- 60*60)) ;//向前移一小时
        sbTM = datTmp.toString() ;



       String Sqlstr = "select STCD,TM,DRP  from ST_PPTN_R where STCD in('51131350','51131300','51112500','51112000','51112101') " +
                " and TM between('" + sbTM + "') and ('" + seTM + "') and INTV<0.9 order by STCD,TM asc";
        //上游站来水//大官庄闸
        String SqlTmp = "select STCD,TM,Z,Q from ST_RIVER_R where STCD='51112000' and TM='" + seTM + "' order by STCD asc";
        //水库水位
        String SqlTmp1 = "select STCD,TM,RZ from ST_RSVR_R where STCD='51112101' and TM='" + seTM + "' order by TM desc";
        ////土湿
        String SqlTmp2 = "select * from MOD_MIDRESULT where Id='" + Id + "' and " +
                " PDNUM in(select max(PDNUM) from MOD_MIDRESULT where Id='" + Id + "') order by CalRegCD asc";
        try ( Connection connection = getMysqlConnection();
              Statement ps = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                      ResultSet.CONCUR_UPDATABLE);
              //执行查询
//                ResultSet gDt = ps.executeQuery(Sqlstr);
//              ResultSet rs = ps.executeQuery(SqlTmp);
//              ResultSet rs1 = ps.executeQuery(SqlTmp1);
              //连接模型专用数据库
              Connection connection2 = getMysqlConnFLDB();
              Statement ps2 = connection2.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                      ResultSet.CONCUR_UPDATABLE);
//              ResultSet rs2 = ps.executeQuery(SqlTmp2);
        ) {
            ResultSet gDt = ps.executeQuery(Sqlstr);
            int rowCount=0;
            while (gDt.next()){rowCount =rowCount +1;}
        if (rowCount > 0)
        {
            for (i = 1; i <= rowCount; i++)
            {
                gDt.absolute(i) ;
                switch (gDt.getString("STCD").trim() )
                {
                    case "51131350":
                        p1 = p1 + gDt.getDouble("DRP");
                        break;
                    case "51131300":
                        p2 = p2 + gDt.getDouble("DRP");
                        break;
                    case "51112500":
                        p3 = p3 + gDt.getDouble("DRP");
                        break;
                    case "51112000":
                        p4 = p4 + gDt.getDouble("DRP");
                        break;
                    case "51112101":
                        p5 = p5 +gDt.getDouble("DRP");
                        break;
                }
            }
            autoP[0] = p1; autoP[1] = p2; autoP[2] = p3; autoP[3] = p4; autoP[4] = p5;
        }
            gDt.close();
        //上游站来水//大官庄闸
            ResultSet rs = ps.executeQuery(SqlTmp);
            rowCount=0;
            while (rs.next()){rowCount =rowCount +1;}
        if (rowCount > 0)
        {rs.absolute(1);
            if (rs.getDouble("Q") > 0)
            { autoDGZFlow = rs.getDouble("Q"); }
            else
            { autoDGZFlow = 0.0; }
        }
        rs.close() ;

        //水库水位
            ResultSet rs1 = ps.executeQuery(SqlTmp1);
            rowCount=0;
            while (rs1.next()){rowCount =rowCount +1;}
        if (rowCount > 0)
        { rs1.absolute(1);
            autoZI  =rs1.getDouble("RZ");
            autoV = slhPublicClass.sglLineInsertV("51112101", autoZI);
        }
        rs1.close();

        ////土湿
            ResultSet rs2 = ps.executeQuery(SqlTmp2);
            rowCount=0;
            while (rs2.next()){rowCount =rowCount +1;}

        if (rowCount > 0)
        {
            for (i = 0; i < 5; i++)
            {rs2.absolute(i+1) ;
                autoWandQ[i][0] = rs2.getDouble("WU");//up土湿
                autoWandQ[i][1] =rs2.getDouble("WL");//Low土湿
                autoWandQ[i][2] = rs2.getDouble("WD");//Deep土湿

                autoWandQ[i][3] = rs2.getDouble("Q");//流域基流
            }
        }
        //保存各读取的数据
            SimpleDateFormat sf01 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            datTmp=new Date() ;
            String format01 = sf01.format(datTmp);
            mdTime = format01;
//            seTM=format00;
//            datTmp=new Date() ;
//            mdTime = datTmp.toString();

        for ( i = 0; i < 5; i++)
        {
            CalRegCD = "SLHRES000" + (i + 1);
            tmpW = autoWandQ[i][0] + autoWandQ[i][1] + autoWandQ[i][2];
            sqlStr1 = "insert into PARA_WEATHER(Id,CalRegCD,ModifiedDate,RainHour,RainFirst,RainSecond,RainThird,Eva,W,FlowDeep" +
                    ") values( '"
                    + arcId + "','" + CalRegCD + "','" + mdTime + "'," + autoP[i] + "," + autoP[i] * 24 + "," + autoP[i] * 24 + ","
                    + autoP[i] * 24 + "," + 0.125 + "," + tmpW + "," + autoWandQ[i][3] + ")";
            //one day's Eva is 3mm,then one hour'Eva is 0.125
            try
            {
                ps.execute(sqlStr1);
                infectRow = infectRow + rTmp;
            }
            catch  (Exception e){
                e.printStackTrace();
            }
        }
        sqlStr1 = "insert into PARA_UPHYDST(Id,STCD,STNM,Distance,ModifiedDate,FlowHour,FlowFirst,FlowSec,FlowThird,NOTE)" +
                " values( '" + arcId + "','51234567','大官庄',70,'" + mdTime + "'," + autoDGZFlow + "," + autoDGZFlow + "," + autoDGZFlow + "," + autoDGZFlow + ",'The Q of three days is same')";
            ps.execute(sqlStr1);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  String.valueOf(rTmp);

    }
}
