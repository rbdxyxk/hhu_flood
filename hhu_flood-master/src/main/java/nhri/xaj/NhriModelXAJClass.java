package nhri.xaj;

public class NhriModelXAJClass {

    private double WUM = -1, WLM = -1, WDM = -1;    //上层土壤张力水容量、下层土壤张力水容量、深层土壤张力水容量，单位mm；模型参数
    private double sm = -1, C = -1, B = -1; //sm表层土自由水蓄水容量， 单位mm;C深层散发系数，B流域蓄水容量分布曲线指数；无单位；模型参数
    private double A = -1, datT = -1;   //A计算流域面积，单位平方公里；datT计算时段长，单位小时;模型参数
    private double WU = -1, WL = -1, WD = -1;   //上层土湿、下层土湿、深层土湿，单位mm,需给定初值、计算过程变化量
    private double WM, W;   //总张力水容量（土壤含水能力）、总张力水蓄量（土壤含水量） 单位mm
    //private double pe, ek;//pe净雨量(剔除蒸散发)、ek折算后的蒸发量;单位mm
    private double WMM; //WMM流域点最大蓄水容量，单位mm，计算得出，不变化
    private double div; //给定常数，净雨量计算单元,单位mm
    private double iia, inn, ifc, imf, ef, ib;  //用于人工干预降雨，iia,inn,ef为计算系数；imf,ib折算系数，ifc人工干预降雨量，单位mm
    private int nd; //净雨量计算单元数,无单位
    private double E, EU, EL, ED;   //土壤总蒸发量、上层蒸发量、下层蒸发量、深层蒸发量，单位mm；待计算值
    private double r, irs;  //r总产流深，irs人工干预净雨量，单位mm
    private double[] rd, ped, pedf; //均为数组，rd为每单元净雨量产流深，ped为单元净雨量，pedf人工干预单元净雨量；单位mm
    //divThreeWater 函数所用到的独有变量
    private double fr, s;   //fr日模计算结果， s为自由水蓄水量，单位mm
    private double ct;  //单位转换，得到立方米每秒，流域面积（平方公里）/(时间*3.6)，即:产流深（mm）*0.001*流域面积（平方公里）*1000*1000/(时段（小时）*60*60）=产流深*流域面积/(3.6*时段)；
    private double IM = -1, ki = -1, kg = -1, ex = -1, cg = -1, ci = -1;    //IM不透水面积比例，ki壤中流出流系数，kg地下水出流系数，ex流域自由水分别曲线指数，cg地下水消退系数，ci壤中流消退系数；无单位，均为模型参数
    private double MS;  //  MS表层土点最大自由水蓄水容量
    private double cs;int L;    //坡面汇流系数、坡面汇流滞后时段数（计算时长为单元）
    private double rs, ri, rg;  //地表径流，壤中流，地下径流，单位mm
    private double qqs, qqi, qqg;   //qqs地表径流汇流量，qqi壤中流汇流量，qqg地下径流汇流量；均为坡面汇流；单位m3/s
    //
    private double K = -1, x = -1;  //K河段稳定流汇流时间(槽蓄系数，单位小时)，河道汇流的马斯经根汇流系数（流量比重因子，一般在0.1至0.4），-0.1，模型参数
    private double C0, C1, C2;  //河道演算系数

    /// WUM上层土壤张力水容量
    public double getMod_WUM() {   return WUM;  }
    public void setMod_WUM(double WUM)    {   this.WUM = WUM; }

    /// WLM下层土壤张力水容量

    public double getMod_WLM()   { return WLM; }
    public void setMod_WLM(double WLM)    {this.WLM = WLM; }

    /// WDM深层土壤张力水容量
    public double getMod_WDM()    {return WDM; }
    public void setMod_WDM(double WDM)    {this.WDM = WDM; }

    /// sm表层土自由水蓄水容量
    public double getMod_sm()    {  return sm; }
    public void setMod_sm(double sm)    {this.sm = sm; }

    /// C深层散发系数
    public double getMod_C()    {   return C; }
    public void setMod_C(double C)    { this.C = C; }

    /// A计算流域面积，单位平方公里
    public double getMod_A()    {    return A; }
    public void setMod_A(double A)    { this.A = A; }

    /// datT计算时段长，单位小时
    public double getMod_datT()    {return datT; }
    public void setMod_datT(double datT)    { this.datT = datT; }

    /// B流域蓄水容量分布曲线指数
    public double getMod_B()    {   return B; }
    public void setMod_B(double B)    {   this.B = B; }

    /// IM不透水面积比例
    public double getMod_IM()    {  return IM; }
    public void setMod_IM(double IM)    {this.IM = IM; }

    /// ki壤中流出流系数
    public double getMod_ki()    {  return ki; }
    public void setMod_ki(double ki)    {this.ki = ki; }


    /// kg地下水出流系数
    public double getMod_kg()    {  return kg; }
    public void setMod_kg(double kg)    {this.kg = kg; }

    /// ex流域自由水分别曲线指数
    public double getMod_ex()    {  return ex; }
    public void setMod_ex(double ex)    {this.ex = ex; }

    /// ci壤中流消退系数
    public double getMod_ci()    {  return ci; }
    public void setMod_ci(double ci)    {this.ci = ci; }

    /// cg地下水消退系数
    public double getMod_cg()    {  return cg; }
    public void setMod_cg(double cg)    {this.cg = cg; }

    /// cs坡面汇流系数
    public double getMod_cs()    {  return cs; }
    public void setMod_cs(double cs)    {this.cs = cs; }

    /// L坡面汇流时段数
    public int getMod_L()    {  return L; }
    public void setMod_L(int L)    {this.L = L; }


    /// 河道汇流的马斯经根汇流系数
    public double getMod_x()    {  return x; }
    public void setMod_x(double x)    {this.x = x; }
    public double getMod_K()    {  return K; }
    public void setMod_K(double K)    {this.K = K; }


    /// WU上层土湿,单位mm,计算过程变化量
    public double getMod_WU()    {  return WU; }
    public void setMod_WU(double WU)    {this.WU = WU; }

    /// WL下层土湿
    public double getMod_WL()    {  return WL; }
    public void setMod_WL(double WL)    {this.WL = WL; }


    ///WD 深层土湿
    public double getMod_WD()    {  return WD; }
    public void setMod_WD(double WD)    {this.WD = WD; }

    public double getMod_dayFR()    {  return fr; }
    public void setMod_dayFR(double fr)    {this.fr = fr; }

    public double getMod_dayS()    {  return s; }
    public void setMod_dayS(double s)    {this.s = s; }

    public  String ComponentID() {  return "XinAnJiang_Model"; }
    public  String ComponentDescription() {  return "The model is single cycle.so you must give although time"; }
    public  String ModelID() {  return "Model of XinAnjiang";  }
    public  String ModelDescription() {  return "model for soil library"; }

    public String InitPara()
    {
        // IM = 1001;//
        // 蓄水能力
        WM = WUM + WLM + WDM;// W = WU + WL + WD;
        WMM = WM * (1 + B) / (1 - IM);
        div = 5;//土层厚的北方该值应大些
        MS = sm * (1 + ex);
        iia = (float)0.4; inn = (float)0.5; ifc = 10; ef = (float)0.05; imf = (float)0.5; ib = 0.6;
        ct = (float)(A / (3.6 * datT));//单位转换系数
        calMuskiC(datT);//计算马斯京根系数C0、C1、C2
        return "XAJmodelUsedInSLH20140402";
    }
    // 计算函数，P雨量，EP蒸发（均值）
    public double[] EvFlowCal(double P, double EP)
    {
        //值参变量 w,wu,wl,wd,wm,wum,wlm,wdm,PE,ek,C,b,WMM,div,iia,inn,ifc,imf,ef,ib,但是w,wu,wl,wd,wm,wum,wlm,wdm要求返回值
        //形参变量 nd,e,eu,el,ed,r,rd,ped,pedf,irs
        //PE净雨量(剔除蒸散发) 单位 mm
        double a, PE, peds, rri, fi;
        int ii;
        double minn;
        double WUe, WDe, WLe;
        double[] PeWULD = new double[4];
        W = WU + WL + WD;

        //EP折算后的水面蒸发量
        if (WU + P >= EP) {
            EU = EP;
            EL = 0;
            ED = 0;
        } else {
            EU = WU + P;       //上层土湿蓄量蒸干后，再蒸发下层，似乎不合理
            if (WL >= C * WLM) {
                EL = (EP - EU) * WL / WLM;
                ED = 0;
            } else if (WL >= C * (EP - EU)) {
                EL = C * (EP - EU);
                ED = 0;
            } else {
                EL = WL; ED = C * (EP - WU) - EL;
            }
        }
        E = EU + EL + ED;   //时段总蒸发量
        PE = P - E;    //去掉土壤蒸发的时段净雨量

        minn = 0.0001F;

        //div下渗能力？ PE净雨量
        if (PE <= div) {
            nd = 1;
            ped = new double[nd + 1];
            pedf = new double[nd + 1];
            ped[1] = PE;
        } else {
            nd = (int)(PE / div + 1);   //下渗过程次数nd
            ped = new double[nd + 1];
            pedf = new double[nd + 1];
            for (ii = 1; ii <= nd - 1; ii++) {
                ped[ii] = div;
            }
            ped[nd] = PE - (nd - 1) * div + minn;
        }
        rd = new double[nd + 1];

        if (PE > 0) {

            //采用单抛物线来考虑 土壤蓄水容量空间分布 的不均匀性的新安江模型，流域蓄水容量分布曲线
            a = (float)(WMM * (1.0 - Math.pow((1 - W / (WM + 0.0001)), (1.0 / (1.0 + B)))));
            //b流域蓄水容量分布曲线指数，wm张力水容量  a = WMM * (1 - (1 - w / (wm + 0.0001)) ^ (1 / (1 + b)));
            r = 0;
            peds = 0;   // r为产流深
            for (ii = 1; ii <= nd; ii++) {
                a = a + ped[ii];
                peds = peds + ped[ii];
                rri = r;
                r = peds - WM + W;     //  净雨量-张力容量+蓄量=产流量，w为总张力水蓄量（土壤含水量）单位mm
                if (a < WMM) {
                    r = (float)(r + WM * Math.pow((1.0 - a / WMM), (1.0 + B)));
                }
                rd[ii] = r - rri;   // 每单位容量下的产流深
            }

            fi = (float)((iia * Math.pow((WM - W + 0.0001), inn) + ifc) * (ef + 1));  //iia=0.4 系数,inn=0.5,ifc=10,ef=0.05

            if (PE >= fi) {
                irs = (PE - fi) * (1.0 - r / PE) * imf; //imf=0,这样计算？什么时候给非0值呢？ 考虑人工干预降雨时，不为0
            } else {
                irs = (float)((PE - fi / (ef + 1.0) * (1.0 - Math.pow((1.0 - PE / fi), (ef + 1.0)))) * (1.0 - r / PE) * imf);
            }

            for (ii = 1; ii <= nd; ii++) {
                pedf[ii] = irs / nd * ib; //ib=0,why? 为0，表示不考虑人工干预降雨
            }

        }
        //计算时段末的土湿

        WUe = 0; WDe = 0; WLe = 0;
        if (PE <= 0) {
            r = 0;
            if (WU + P >= EP) {
                WUe = WU + P - E;
                WLe = WL; WDe = WD; //上层土湿量+净雨量能满足蒸发，下层、深层土湿不变
            } else if (WL - EL > 0) {
                WUe = 0;
                WLe = WL - EL;
                WDe = WD; //下层土湿量大于下层蒸发量，上层土湿为0，深层土湿不变
            } else if (WD - ED > 0) {
                WUe = 0;
                WLe = 0;
                WDe = WD - ED;
            }
        } else {
            WUe = WU + PE - r;
            WLe = WL;

            WDe = WD;  //R为产流量
            if (WUe > WUM) {
                WLe = WL + WUe - WUM;
                WUe = WUM;

                if (WLe > WLM) {
                    WDe = WD + WLe - WLM;
                    WLe = WLM;
                    WUe = WUM;
                } else {
                    WDe = WD;
                }
            }
        }

        WU = WUe;
        WD = WDe;
        WL = WLe;
        PeWULD[0] = PE; PeWULD[1] = WU; PeWULD[2] = WL; PeWULD[2] = WD;
        return PeWULD;
    }
    /// 分水源出流及坡面汇流计算
    /// </summary>
    /// <param name="pe"></param>
    public double[] divThreeWater(double pe)//分水源出流及坡面汇流计算
    {
        //值参变量 pe,rd,ped,pedf,nd, fr,s,ct,IM,ki,kg,ex,cg,ci,ib,sm,MS
        //形参变量 qqg,qqi,qqs,rs,ri,rg
        //参数未使用 irs,iia,inn,ifc,imf,ef

        double rb, rr, kgd, kid, td, XX, au, ff;
        double[] qqThree = new double[3];//三层产流
        int ii;
        if (pe <= 0) {
            rs = 0; rg = s * kg * fr; ri = s * ki * fr;//此处用日模计算出的s、fr
            s = s * (1 - kg - ki);
            qqg = qqg * cg + rg * (1 - cg) * ct;
            qqi = qqi * ci + ri * (1 - ci) * ct;
            qqs = rs * ct;// 坡面汇流
            qqThree[0] = qqg; qqThree[1] = qqi; qqThree[2] = qqs;
            return qqThree;
        }
        rb = IM * pe;
        kid = (double)((1 - Math.pow((1.0 - (kg + ki)), (1.0 / nd))) / (kg + ki));
        kgd = kid * kg;
        kid = kid * ki;
        rs = 0; ri = 0; rg = 0; //地表径流      '壤中流       '地下径流
        for (ii = 1; ii <= nd; ii++) {          //产流量rd 由yield计算得出
            td = rd[ii] - IM * ped[ii];   //   '产流量-净雨量， IM为不透水面积占全流域面积的比例
            XX = fr;
            fr = td / ped[ii];   //'产流面积
            s = XX * s / fr;  //'s为自由水蓄水量， sm为自由水蓄水容量
            if (s >= sm)// goto C2;
            {
                rr = (ped[ii] + pedf[ii] / fr * ib + s - sm) * fr;
            } else {
                //      '自由水容量曲线，MS表层土点最大自由水蓄水量
                au = (double)(MS * (1.0 - Math.pow((1.0 - s / sm), (1.0 / (1.0 + ex)))));
                //'与自由水蓄水量s相应的纵坐标AU
                ff = au + ped[ii] + pedf[ii] / fr * ib;
                //ff临时变量,pedf为外加净降雨（或人工干预净降雨）
                if (ff < MS)// goto c3;
                {
                    ff = (float)(Math.pow((1.0 - (ped[ii] + pedf[ii] / fr * ib + au) / MS), (1.0 + ex)));
                    rr = (ped[ii] + pedf[ii] / fr * ib - sm + s + sm * ff) * fr;// '地面径流量
                } else {
                    rr = (ped[ii] + pedf[ii] / fr * ib + s - sm) * fr;
                }
            }
            rs = rr + rs;
            s = ped[ii] + pedf[ii] / fr * ib - rr / fr + s;// 's自由水蓄量
            rg = s * kgd * fr + rg; //  'kgd自由水对地下水的出流系数
            ri = s * kid * fr + ri;//      'kid自由水对壤中流的出流系数
            s = s * (1 - kid - kgd);
        }
        rs = rs + rb;
        qqg = qqg * cg + rg * (1.0 - cg) * ct;// '地下水汇流计算，ct为单位转换系数,cg为消退系数
        qqi = qqi * ci + ri * (1.0 - ci) * ct;// '壤中流汇流计算
        qqs = rs * ct;
        qqThree[0] = qqg; qqThree[1] = qqi; qqThree[2] = qqs;
        return qqThree;
    }

    /// <summary>
    /// 河网汇流计算
    /// </summary>
    /// <param name="cs">河网水流消退系数</param>
    /// <param name="Qb">滞后时间对应的流量，以计算时段为单元</param>
    /// <param name="Qt">需计算的时段流量=qqg+qqi+qqs</param>
    public double riverNetFlow( double Qb, double Qt)//cs河网水流消退系数，L滞后时间，以计算时段为单元，应为计算时段的整数倍；tNum需计算的时段；均无单位
    {
        double Q = -1;//坡面汇流总流量=qqs+qqi+qqg

        // QT[tNum - L] = qqs + qqi + qqg;
        // Q[tNum] = cs * Q[tNum - 1] + (1 - cs) * QT[tNum - L];
        Q = cs * Qb + (1 - cs) * Qt;
        return Q;
    }

    /// <summary>
    /// 马斯经根分段连续演算河道汇流
    /// </summary>
    /// <param name="rNum">河段数</param>
    /// <param name="rQ">河网汇流总流量</param>
    /// <param name="kQ">初始流量,数组从1开始</param>
    public void MuskingumRiverFlow(int rNum, double rQ,double[] kQ)//每个计算单元分别演算,河道汇流
    {
        double q1, q2, q3; int jj, lm;
        // double[] kQ= new double[10];
        //若分了一次rNum为2，kQ有两个kQ[0]、kQ[1]，直接为出口断面时rNum为0，rNum为河段数
        lm = rNum;//河段数rNum可由s/(3.6*V*t)取整确定，s为单元面积出口到预报断面的距离（km），V为河段平均流速(m/s)，t为计算时段长(h)
        if (lm == 1){
            kQ[0] = rQ;
            return ;}  // goto C1;
        for (jj = 1; jj < lm; jj++) {
            q1 = rQ;
            q2 = kQ[jj - 1];
            q3 = kQ[jj];
            kQ[jj - 1] = rQ;
            rQ = C0 * q1 + C1 * q2 + C2 * q3;
        }
        kQ[lm - 1] = rQ;
        return ;
        //  C1: kQ[0] = rQ;//
    }

    private void calMuskiC(double tt)//tt计算时段长，单位小时
    {
//        K = tt+0.1;
        C0 = (float)((0.5 * tt - K * x) / (K - K * x + 0.5 * tt));
        C1 = (float)((K * x + 0.5 * tt) / (K - K * x + 0.5 * tt));
        C2 = (float)((K - K * x - 0.5 * tt) / (K - K * x + 0.5 * tt));
    }
}
