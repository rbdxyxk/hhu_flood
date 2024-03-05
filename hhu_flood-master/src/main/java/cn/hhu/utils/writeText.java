package cn.hhu.utils;

import cn.hhu.Bean.*;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

/**
 * @author tlj
 */

public class writeText {
    /**
     * 写入TXT文件
     */
    @Test
    public  void writeFile() {
        int ID = 0;

        try {
            // 相对路径，如果没有则要建立一个新的output.txt文件
            File writeName = new File("DamBreak_CF_"+ID+".txt");
            // 创建新文件,有同名的文件的话直接覆盖
            writeName.createNewFile();
            try (FileWriter writer = new FileWriter(writeName);
                 BufferedWriter out = new BufferedWriter(writer)
            ) {
                // \r\n即为换行
                out.write(myToString(getSingleObject(CA_PD.class,ID),1)+"\r\n");

                out.write(myToString(getSingleObject(CB_NREAD.class,ID),1)+"\r\n");
                CC_CP cc_cp = getSingleObject(CC_CP.class, ID);
                out.write(myToString(cc_cp,1)+"\r\n");
                CD_PLINE cd_pline = getSingleObject(CD_PLINE.class,ID);
                out.write(myToString(cd_pline,1)+"\r\n");
                out.write(myToString(getSingleObject(CE_TANDT.class,ID),1)+"\r\n");
                //前五行结构单一
                //第六行一个对象分三行写
                CF_GFANDNF cf_gfandnf= getSingleObject(CF_GFANDNF.class, ID);
                out.write("  "+cf_gfandnf.getMG()+"  "+cf_gfandnf.getMGS()+"  "+cf_gfandnf.getMCS()+"  "+cf_gfandnf.getMTS()+"\r\n");
                out.write("  "+cf_gfandnf.getLIMTER()+"  "+cf_gfandnf.getBETA()+"\r\n");
                out.write("  "+cf_gfandnf.getPP()+"  "+cf_gfandnf.getWSP()+"  "+cf_gfandnf.getWINC()+"\r\n");
                out.newLine();
                //打印边界条件
                //如果分多次查询
//                for (int i = 0; i < cc_cp.getNBC(); i++) {
//                    String sql ="SELECT * FROM CG1_B where ID=? AND IDUM = ?";
//                }
                if(cc_cp.getNBC()>0){
                    List<CG1_B> instances = DbUtils.getObjects("SELECT * FROM CG1_B WHERE ID= ?  ORDER BY IDUM", CG1_B.class, ID);
                    for (int i = 0; i < instances.size(); i++) {
                        CG1_B cg1_b = instances.get(i);
                        out.write(myToString(cg1_b,1)+"\r\n");
                        switch (cg1_b.getITYPE()){
                            case 6:
                                CG2_W cg2_w = DbUtils.getMyInstance("SELECT * FROM CG2_W WHERE ID= ?  AND NID = ? ", CG2_W.class, ID, cg1_b.getIDUM());
                                out.write(myToString(cg2_w,2)+"\r\n");
                                break;
                            case 7:
                                CG2_G cg2_g = DbUtils.getMyInstance("SELECT * FROM CG2_G WHERE ID= ?  AND NID = ? ", CG2_G.class, ID, cg1_b.getIDUM());
                                out.write(myToString(cg2_g,2)+"\r\n");
                                break;
                            case 8:
                                CG2_SG cg2_sg = DbUtils.getMyInstance("SELECT * FROM CG2_SG WHERE ID= ?  AND NID = ? ", CG2_SG.class, ID, cg1_b.getIDUM());
                                out.write(myToString(cg2_sg,2)+"\r\n");
                                break;
                            case 9:
                                CG2_L cg2_l = DbUtils.getMyInstance("SELECT * FROM CG2_L WHERE ID= ?  AND NID = ? ", CG2_L.class, ID, cg1_b.getIDUM());
                                out.write(myToString(cg2_l,2)+"\r\n");
                                break;
                            case 12:
                                CG2_BANDC cg2_bandc = DbUtils.getMyInstance("SELECT * FROM CG2_BANDC WHERE ID= ?  AND NID = ? ", CG2_BANDC.class, ID, cg1_b.getIDUM());
                                out.write(myToString(cg2_bandc,2)+"\r\n");
                                break;
                            case 15:
                                CG2_CW cg2_cw = DbUtils.getMyInstance("SELECT * FROM CG2_CW WHERE ID= ?  AND NID = ? ", CG2_CW.class, ID, cg1_b.getIDUM());
                                out.write(myToString(cg2_cw,2)+"\r\n");
                                break;
                            case 16:
                                CG2_CL cg2_cl = DbUtils.getMyInstance("SELECT * FROM CG2_CL WHERE ID= ?  AND NID = ? ", CG2_CL.class, ID, cg1_b.getIDUM());
                                out.write(myToString(cg2_cl,2)+"\r\n");
                                break;
                            default:
                                break;
                        }
                    }
                }

                if(cd_pline.getNQC()>0){
                    out.newLine();
                    lineData(out, CH_1A_Q.class,CH_1B_Q.class,ID,"NQT");
                    //List<CH_1A_Q> objects = DbUtils.getObjects("SELECT * FROM CH_1A_Q WHERE ID= ?  ORDER BY I_NUM", CH_1A_Q.class, ID);
                    //for (int i = 0; i < objects.size(); i++) {
                    //    CH_1A_Q ch_1A_q = objects.get(i);
                    //    out.write(myToString(ch_1A_q,2)+"\r\n");
                    //    for (int j = 0; j < ch_1A_q.getNQT(); j++) {
                    //        List<CH_1B_Q> objects1 = DbUtils.getObjects("SELECT * FROM CH_1B_Q WHERE ID= " + ID + " AND I_NUM = " + ch_1A_q.getI_NUM() + " ORDER BY J_TIME", CH_1B_Q.class);
                    //        for(CH_1B_Q ch_1B_q:objects1){
                    //            out.write(myToString(ch_1B_q,3)+"\r\n");
                    //        }
                    //    }
                    //}
                }

                if(cd_pline.getNZC()>0){
                    out.newLine();
                    //List<CH_2A_Z> objects = DbUtils.getObjects("SELECT * FROM CH_2A_Z WHERE ID= ?  ORDER BY I_NUM", CH_2A_Z.class, ID);
                    //for (int i = 0; i < objects.size(); i++) {
                    //    CH_2A_Z ch_2A_z = objects.get(i);
                    //    out.write(myToString(ch_2A_z,2)+"\r\n");
                    //
                    //        List<CH_2B_Z> objects1 = DbUtils.getObjects("SELECT * FROM CH_2B_Z WHERE ID= " + ID + " AND I_NUM = " + ch_2A_z.getI_NUM() + " ORDER BY J_TIME", CH_2B_Z.class);
                    //        for(CH_2B_Z ch_2B_z:objects1){
                    //            out.write(myToString(ch_2B_z,3)+"\r\n");
                    //        }
                    //
                    //}
                    lineData(out,CH_2A_Z.class,CH_2B_Z.class,ID,"NZT");
                }
                //闸门过程线
                if(cd_pline.getNGC()>0){
                    out.newLine();
                    lineData(out,CH_3A_G.class,CH_3B_G.class,ID,"NGT");
                }
                //上下游边界处的
                if(cd_pline.getNSC()>0){
                    out.newLine();
                    lineData(out,CH_4A_S.class, CH_4B_S.class,ID,"NZS");
                    lineData(out,CH_4C_S.class,CH_4D_S.class,ID,"NZS1");
                }
                //上下游边界处的堰

                if(cd_pline.getNWC()!=null&&cd_pline.getNWC()>0){
                    out.newLine();
                    lineData(out,CH_6A_WL.class,CH_6B_WL.class,ID,"NWO");

                }

                //湿地支流边界
                if(cd_pline.getNQLC()>0){
                    out.newLine();
                    lineData(out,CH_6A_WL.class,CH_6B_WL.class,ID,"NQQ");
                }

                //急流边界
                if(cd_pline.getNSPC()>0){
                    out.newLine();
                    lineData(out,CH_7A_JET.class,CH_7B_JET.class,ID,"NZQ");
                    lineData(out,CH_7C_JET.class,CH_7D_JET.class,ID,"NQZ");
                }

                out.newLine();
                {
                    String sql ="SELECT * FROM CJ_VAL0 WHERE ID= ?  ORDER BY I_NUM";
                    List<CJ_VAL0> objects = DbUtils.getObjects(sql, CJ_VAL0.class, ID);
                    int i =0;
                    for(CJ_VAL0 cjVal0:objects){
                        out.write("   "+cjVal0.getZ1());
                        if(i++==9){
                            out.newLine();
                            i=0;
                        }
                    }
                    i=0;
                    for(CJ_VAL0 cjVal0:objects){
                        out.write("    "+cjVal0.getU1());
                        if(i++==9){
                            out.newLine();
                            i=0;
                        }
                    }
                    i=0;
                    for(CJ_VAL0 cjVal0:objects){
                        out.write("   "+cjVal0.getV1());
                        if(i++==9){
                            out.newLine();
                            i=0;
                        }
                    }
                }

                out.flush(); // 把缓存区内容压入文件
            }

        } catch (IOException | IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }


    public  static <T> T getSingleObject( Class<T> clazz,int ID) throws IllegalAccessException {

        String sql= "SELECT * FROM "+clazz.getSimpleName()+" where ID=?";
        T t = (T)DbUtils.getMyInstance(sql, clazz,ID);
        return t ;
    }
    public static <T> String myToString(T t,int first) throws IllegalAccessException {
        Class clazz = t.getClass();
        Field[] fields = clazz.getDeclaredFields();
        String result="   ";
        for (int i = first; i < fields.length ; i++) {
            fields[i].setAccessible(true);
            Object temp = fields[i].get(t);
            if(temp!=null){
                result+=temp;
                result+="   ";
            }

        }
        return  result;
    }
    /*
    * 用于处理过程线数据
    * */
    public static <T> void  lineData(BufferedWriter out, Class<T> clazz,Class cl , int  ID,String name) throws IllegalAccessException, IOException, NoSuchFieldException {
        String typeName=clazz.getTypeName();
        System.out.println(typeName);
        List<T> objects = DbUtils.getObjects("SELECT * FROM "+clazz.getSimpleName()+" WHERE ID= ?  ORDER BY I_NUM", clazz, ID);
        for (int i = 0; i < objects.size(); i++) {
            T t = objects.get(i);

            out.write(myToString(t,2)+"\r\n");
            Field field = clazz.getDeclaredField(name);
            field.setAccessible(true);
            //获取数量
            Integer num = (Integer)field.get(t);
            List objects1 = DbUtils.getObjects("SELECT * FROM "+ cl.getSimpleName()+" WHERE ID= " + ID + " AND I_NUM = " + num + " ORDER BY J_TIME", cl);
                for(Object obj:objects1){
                    System.out.println(obj);
                    out.write(myToString(obj,3)+"\r\n");
                }

        }
    }
    /*
    测试一下查询效率
    */
    @Test
    public  void cajun() throws IllegalAccessException {

        long l3 =System.currentTimeMillis();

        List<CG1_B> instances = DbUtils.getObjects("SELECT * FROM CG1_B WHERE ID= ?  ORDER BY IDUM", CG1_B.class, 0);
        for (int i = 0; i < instances.size(); i++) {
            System.out.println(myToString(instances.get(i),1));
        }
        long l1 = System.currentTimeMillis();
        System.out.println(l1-l3);
    }

    /*
    *
    * 随机NZC个数生成随机数据
    *
    * */

    @Test
    public void randomWrite(){
        int ID = 0;
        int NZC = 2 ;//getRandom(8,3);
        try {
            // 相对路径，如果没有则要建立一个新的output.txt文件
            File writeName = new File("DamBreak_CF_R"+ID+".txt");
            // 创建新文件,有同名的文件的话直接覆盖
            writeName.createNewFile();
            try (FileWriter writer = new FileWriter(writeName);
                 BufferedWriter out = new BufferedWriter(writer)
            ) {
                // \r\n即为换行
                out.write(myToString(getSingleObject(CA_PD.class,ID),1)+"\r\n");

                out.write(myToString(getSingleObject(CB_NREAD.class,ID),1)+"\r\n");
                CC_CP cc_cp = getSingleObject(CC_CP.class, ID);
                out.write(myToString(cc_cp,1)+"\r\n");
                CD_PLINE cd_pline = getSingleObject(CD_PLINE.class,ID);
                cd_pline.setNZC(NZC);
                out.write(myToString(cd_pline,1)+"\r\n");
                CE_TANDT ce_tandt = getSingleObject(CE_TANDT.class, ID);
                ce_tandt.setSTIME(new BigDecimal(5.0).setScale(4,BigDecimal.ROUND_HALF_UP));
                out.write(myToString(ce_tandt,1)+"\r\n");
                //前五行结构单一
                //第六行一个对象分三行写
                CF_GFANDNF cf_gfandnf= getSingleObject(CF_GFANDNF.class, ID);
                out.write("  "+cf_gfandnf.getMG()+"  "+cf_gfandnf.getMGS()+"  "+cf_gfandnf.getMCS()+"  "+cf_gfandnf.getMTS()+"\r\n");
                out.write("  "+cf_gfandnf.getLIMTER()+"  "+cf_gfandnf.getBETA()+"\r\n");
                out.write("  "+cf_gfandnf.getPP()+"  "+cf_gfandnf.getWSP()+"  "+cf_gfandnf.getWINC()+"\r\n");
                out.newLine();
                //打印边界条件
                //如果分多次查询
//                for (int i = 0; i < cc_cp.getNBC(); i++) {
//                    String sql ="SELECT * FROM CG1_B where ID=? AND IDUM = ?";
//                }
                if(cc_cp.getNBC()>0){
                    List<CG1_B> instances = DbUtils.getObjects("SELECT * FROM CG1_B WHERE ID= ?  ORDER BY IDUM", CG1_B.class, ID);
                    for (int i = 0; i < instances.size(); i++) {
                        CG1_B cg1_b = instances.get(i);
                        out.write(myToString(cg1_b,1)+"\r\n");
                        switch (cg1_b.getITYPE()){
                            case 6:
                                CG2_W cg2_w = DbUtils.getMyInstance("SELECT * FROM CG2_W WHERE ID= ?  AND NID = ? ", CG2_W.class, ID, cg1_b.getIDUM());
                                out.write(myToString(cg2_w,2)+"\r\n");
                                break;
                            case 7:
                                CG2_G cg2_g = DbUtils.getMyInstance("SELECT * FROM CG2_G WHERE ID= ?  AND NID = ? ", CG2_G.class, ID, cg1_b.getIDUM());
                                out.write(myToString(cg2_g,2)+"\r\n");
                                break;
                            case 8:
                                CG2_SG cg2_sg = DbUtils.getMyInstance("SELECT * FROM CG2_SG WHERE ID= ?  AND NID = ? ", CG2_SG.class, ID, cg1_b.getIDUM());
                                out.write(myToString(cg2_sg,2)+"\r\n");
                                break;
                            case 9:
                                CG2_L cg2_l = DbUtils.getMyInstance("SELECT * FROM CG2_L WHERE ID= ?  AND NID = ? ", CG2_L.class, ID, cg1_b.getIDUM());
                                out.write(myToString(cg2_l,2)+"\r\n");
                                break;
                            case 12:
                                CG2_BANDC cg2_bandc = DbUtils.getMyInstance("SELECT * FROM CG2_BANDC WHERE ID= ?  AND NID = ? ", CG2_BANDC.class, ID, cg1_b.getIDUM());
                                out.write(myToString(cg2_bandc,2)+"\r\n");
                                break;
                            case 15:
                                CG2_CW cg2_cw = DbUtils.getMyInstance("SELECT * FROM CG2_CW WHERE ID= ?  AND NID = ? ", CG2_CW.class, ID, cg1_b.getIDUM());
                                out.write(myToString(cg2_cw,2)+"\r\n");
                                break;
                            case 16:
                                CG2_CL cg2_cl = DbUtils.getMyInstance("SELECT * FROM CG2_CL WHERE ID= ?  AND NID = ? ", CG2_CL.class, ID, cg1_b.getIDUM());
                                out.write(myToString(cg2_cl,2)+"\r\n");
                                break;
                            default:
                                break;
                        }
                    }
                }

                if(cd_pline.getNQC()>0){
                    out.newLine();
                    lineData(out,CH_1A_Q.class,CH_1B_Q.class,ID,"NQT");
                    //List<CH_1A_Q> objects = DbUtils.getObjects("SELECT * FROM CH_1A_Q WHERE ID= ?  ORDER BY I_NUM", CH_1A_Q.class, ID);
                    //for (int i = 0; i < objects.size(); i++) {
                    //    CH_1A_Q ch_1A_q = objects.get(i);
                    //    out.write(myToString(ch_1A_q,2)+"\r\n");
                    //    for (int j = 0; j < ch_1A_q.getNQT(); j++) {
                    //        List<CH_1B_Q> objects1 = DbUtils.getObjects("SELECT * FROM CH_1B_Q WHERE ID= " + ID + " AND I_NUM = " + ch_1A_q.getI_NUM() + " ORDER BY J_TIME", CH_1B_Q.class);
                    //        for(CH_1B_Q ch_1B_q:objects1){
                    //            out.write(myToString(ch_1B_q,3)+"\r\n");
                    //        }
                    //    }
                    //}
                }

                if(cd_pline.getNZC()>0){
                    out.newLine();
                    writeRandom(cd_pline.getNZC(),out);
                    out.newLine();
                }
                //闸门过程线
                if(cd_pline.getNGC()>0){
                    out.newLine();
                    lineData(out,CH_3A_G.class,CH_3B_G.class,ID,"NGT");
                }
                //上下游边界处的
                if(cd_pline.getNSC()>0){
                    out.newLine();
                    lineData(out,CH_4A_S.class,CH_4B_S.class,ID,"NZS");
                    lineData(out,CH_4C_S.class,CH_4D_S.class,ID,"NZS1");
                }
                //上下游边界处的堰

                if(cd_pline.getNWC()!=null&&cd_pline.getNWC()>0){
                    out.newLine();
                    lineData(out,CH_6A_WL.class,CH_6B_WL.class,ID,"NWO");

                }

                //湿地支流边界
                if(cd_pline.getNQLC()>0){
                    out.newLine();
                    lineData(out,CH_6A_WL.class,CH_6B_WL.class,ID,"NQQ");
                }

                //急流边界
                if(cd_pline.getNSPC()>0){
                    out.newLine();
                    lineData(out,CH_7A_JET.class,CH_7B_JET.class,ID,"NZQ");
                    lineData(out,CH_7C_JET.class,CH_7D_JET.class,ID,"NQZ");
                }

                out.newLine();
                {
                    String sql ="SELECT * FROM CJ_VAL0 WHERE ID= ?  ORDER BY I_NUM";
                    List<CJ_VAL0> objects = DbUtils.getObjects(sql, CJ_VAL0.class, ID);
                    int i =0;
                    for(CJ_VAL0 cjVal0:objects){
                        out.write("   "+cjVal0.getZ1());
                        if(i++==9){
                            out.newLine();
                            i=0;
                        }
                    }
                    i=0;
                    for(CJ_VAL0 cjVal0:objects){
                        out.write("    "+cjVal0.getU1());
                        if(i++==9){
                            out.newLine();
                            i=0;
                        }
                    }
                    i=0;
                    for(CJ_VAL0 cjVal0:objects){
                        out.write("   "+cjVal0.getV1());
                        if(i++==9){
                            out.newLine();
                            i=0;
                        }
                    }
                }

                out.flush(); // 把缓存区内容压入文件
            }

        } catch (IOException | IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /*
    *打印随机数
    * @author tlj
    *
    * */
    public void writeRandom(int NZC, BufferedWriter out) throws IOException {

        Random r = new Random();
        //Map<String,Object> map = new HashMap<>();
        //设置初始时间
        Double DTZT00=r.nextDouble()*9.0+1.0;

        for (int i = 1; i <= NZC; i++) {
            //设置时段总数1~10
            int NZT=r.nextInt(9)+1;
            System.out.println(DTZT00);
            //保留三位小数
            //BigDecimal ZT0 = new BigDecimal(String.format("%.3f",ZT00));
            //BigDecimal ZTj =new BigDecimal(String.format("%.3f",ZT00));
            //out.newLine();
           //out.write("   "+NZT+"   "+ZT0);
           //out.newLine();
           //out.write(String.format("% 4S% 4S",ZT0,1.000));
            //out.write("   "+ZT0+"   "+1.000);
            BigDecimal DTZT0 = new BigDecimal(DTZT00);
            out.newLine();
            BigDecimal ZT0 = getResult(DTZT0);
            out.write("   "+NZT+"   "+ ZT0);
            out.newLine();
            out.write("   "+getResult(DTZT0)+"   "+ 1.000);
            for (int j = 2; j <= NZT ; j++) {

                //BigDecimal temp = new BigDecimal((NZT+1-j)*2);
                //Double factor=ZTj.divide(temp,3).doubleValue();
                DTZT0= DTZT0.add(new BigDecimal(1.0));
                BigDecimal ZTj=getResult(DTZT0);

                 //ZTj = ZTj.subtract(ZTj.divide(temp,3)).add(new BigDecimal(r.nextDouble()*factor*2-factor)).setScale(3,BigDecimal.ROUND_HALF_UP);
                out.newLine();
                out.write("   "+ZTj+"   "+1.000);

            }
            DTZT00=Double.valueOf(DTZT0.add(new BigDecimal(1.0*(15-NZT))).toString());
        }
    }
    /*
    * 获取随机数
    * @Param max 最大值
    * @Param low 最小值
    *
    * */
    public static int getRandom(int max,int low){
        Random r = new Random();
        return r.nextInt(max-low)+low;
    }

    @Test
    public void rTest(){
        Random r = new Random();

        for (int i = 0; i < 5; i++) {
            int random = r.nextInt(19)+1;
            System.out.println(random);
        }
    
     
    
    }
    @Test
    public void testr2(){
        int v = (int)(Math.random() * 100);
        System.out.println(v);

    }
    @Test
    public void testr3() throws InterruptedException {
        for (int i = 0; i < 10 ; i++) {
            Long random = System.currentTimeMillis();
            System.out.println(random%100);
            //Thread.sleep((int)(Math.random() * 10));
        }

    }
    //y = 0.0021x2 - 0.6745x + 73.562
    public  BigDecimal getResult(BigDecimal DTZT){
        return new BigDecimal(0.0021).multiply(DTZT.pow(2)).add(DTZT.multiply(new BigDecimal(-0.6754))).add(new BigDecimal(73.526)).setScale(3,BigDecimal.ROUND_HALF_UP);
    }


    //y = 0.0021x2 - 0.6745x + 73.562

    @Test
    public void Test001(){
        BigDecimal DTZT =new BigDecimal(50.0);
        BigDecimal b1 = new BigDecimal(0.002).multiply(DTZT.pow(2)).add(DTZT.multiply(new BigDecimal(-0.1846))).add(new BigDecimal(73.625)).setScale(3,BigDecimal.ROUND_HALF_UP);
        System.out.println(b1);
    }

}
