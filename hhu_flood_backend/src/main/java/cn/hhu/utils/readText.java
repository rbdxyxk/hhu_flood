package cn.hhu.utils;

import cn.hhu.Bean.*;
import org.junit.Test;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author tlj
 */
public class readText {
    @Test
    public  void  myTest(){
        Map<String, Object> map = readText.readControlData();
        map.remove("List");
        System.out.println(map.size());
        for(Map.Entry<String,Object> entry:map.entrySet()){
            Object value = entry.getValue();
            Class clazz = value.getClass();
            System.out.println(clazz.getSimpleName());
        }
    }

    //读控制数据
   // @Test
    public  static Map<String,Object> readControlData(){
        int element=1600;
        String pathname = "web\\static\\DamBreak_CF.txt";
        Map<String,Object> map = new HashMap<>();
        try (FileInputStream is= new FileInputStream(new File(pathname));
             InputStreamReader isr=new InputStreamReader(is,"GBK");
             BufferedReader br = new BufferedReader(isr) ;// 建立一个对象，它把文件内容转成计算机能读懂的语言
        ) {
            String line;
            
            int i=0;
            int ID = 0;
            //项目说明
            map.put(CA_PD.class.getSimpleName(),new CA_PD(ID,br.readLine()));
            map.put(CB_NREAD.class.getSimpleName(),getObject(CB_NREAD.class,myTrim(br.readLine()),ID));
            map.put(CC_CP.class.getSimpleName(),getObject(CC_CP.class,myTrim(br.readLine()),ID));
            map.put(CD_PLINE.class.getSimpleName(),getObject(CD_PLINE.class,myTrim(br.readLine()),ID));
            map.put(CE_TANDT.class.getSimpleName(),getObject(CE_TANDT.class,myTrim(br.readLine()),ID));
            //这个类比较特殊 ，输出占了三行
            map.put(CF_GFANDNF.class.getSimpleName(),getObject(CF_GFANDNF.class, TArraysUtils.myConcatArray(myTrim(br.readLine()),myTrim(br.readLine()),myTrim(br.readLine())),ID));
//            line=br.readLine();
//            System.out.println(myTrim(line).length);
            line=br.readLine();
            CC_CP cc_cp= (CC_CP) map.get(CC_CP.class.getSimpleName());
            if((line = br.readLine()) != null&&myTrim(line).length!=0){


                CG1_B cg1_b=null;
                //边界条件NBC组数据
                System.out.println(cc_cp);
                for (int j = 1; j <= cc_cp.getNBC(); j++) {//IDUM为边界号从1开始
                        //System.out.println(j+line);
                        cg1_b=getObject(CG1_B.class,myTrim(line),ID);
                        map.put(CG1_B.class.getSimpleName()+"_"+j,cg1_b);
                        int IDUM=cg1_b.getIDUM();
                    //以下省略一些必要的判断以后补上
                    //判断IType的类型
                        switch (cg1_b.getITYPE()){
                            case 6:
                                map.put(CG2_W.class.getSimpleName()+"_"+IDUM,getObject(CG2_W.class,myTrim(br.readLine()),ID,IDUM));
                                break;
                            case 7:
                                map.put(CG2_G.class.getSimpleName()+"_"+IDUM,getObject(CG2_G.class,myTrim(br.readLine()),ID,IDUM));
                                break;
                            case 8:
                                map.put(CG2_SG.class.getSimpleName()+"_"+IDUM,getObject(CG2_SG.class,myTrim(br.readLine()),ID,IDUM));
                                break;
                            case 9:
                                //当前模型执行
                                //System.out.println(IDUM);
                                map.put(CG2_L.class.getSimpleName()+"_"+IDUM,getObject(CG2_L.class,myTrim(br.readLine()),ID,IDUM));
                                break;
                            case 12:
                                map.put(CG2_BANDC.class.getSimpleName()+"_"+IDUM,getObject(CG2_BANDC.class,myTrim(br.readLine()),ID,IDUM));
                                break;
                            case 15:
                                map.put(CG2_CW.class.getSimpleName()+"_"+IDUM,getObject(CG2_CW.class,myTrim(br.readLine()),ID,IDUM));
                                break;
                            case 16:
                                map.put(CG2_CL.class.getSimpleName()+"_"+IDUM,getObject(CG2_CL.class,myTrim(br.readLine()),ID,IDUM));
                                break;
                            default:
                                break;
                        }

                        line=br.readLine();

                }

            }
            CD_PLINE cd_pline = (CD_PLINE) map.get(CD_PLINE.class.getSimpleName());
            if(cd_pline.getNQC()!=0){//当NQC!=0要读入NQC条数据
                while (line!=null&&myTrim(line).length==0){//去除空白
                    line=br.readLine();
                }
                System.out.println(line);
                for (int j = 1; j<=cd_pline.getNQC(); j++) {//流量过程线
                    CH_1A_Q ch_1a_q = getObject(CH_1A_Q.class, myTrim(line), ID, j);
                    map.put(CH_1A_Q.class.getSimpleName()+"_"+j,ch_1a_q);
                    for (int k = 0; k < ch_1a_q.getNQT(); k++) {
                        map.put(CH_1B_Q.class.getSimpleName()+"_"+j+"_"+(k+1),getObject(CH_1B_Q.class,myTrim(br.readLine()),ID,j,(k+1)));
                    }
                    line=br.readLine();
                }
            }

            //System.out.println(cd_pline);
            if(cd_pline.getNZC()!=0){//当NZC!=0要读入NZC条数据
                while (line!=null&&myTrim(line).length==0){//去除空白
                    line=br.readLine();
                }
                for (int j = 1; j<=cd_pline.getNZC(); j++) {//流量过程线
                    CH_2A_Z ch_2a_z = getObject(CH_2A_Z.class, myTrim(line), ID, j);
                    map.put(CH_2A_Z.class.getSimpleName()+"_"+j,ch_2a_z);
                    for (int k = 0; k < ch_2a_z.getNZT(); k++) {
                        map.put(CH_2B_Z.class.getSimpleName()+"_"+j+"_"+(k+1),getObject(CH_2B_Z.class,myTrim(br.readLine()),ID,j,(k+1)));
                    }
                    line=br.readLine();
                }
            }
            //闸门过程线总数
            if(cd_pline.getNGC()!=0){//当NQC!=0要读入NQC条数据
                while (line!=null&&myTrim(line).length==0){//去除空白
                    line=br.readLine();
                }
                System.out.println(line);
                for (int j = 1; j<=cd_pline.getNGC(); j++) {//流量过程线
                    CH_3A_G ch = getObject(CH_3A_G.class, myTrim(line), ID, j);
                    map.put(ch.getClass().getSimpleName()+"_"+j,ch);
                    for (int k = 0; k < ch.getNGO(); k++) {
                        map.put(CH_3B_G.class.getSimpleName()+"_"+j+"_"+(k+1),getObject(CH_3B_G.class,myTrim(br.readLine()),ID,j,(k+1)));
                    }
                    line=br.readLine();
                }
            }
            //NSC：上下游边界处的闸门过程线总数
            if(cd_pline.getNSC()!=0){
                while (line!=null&&myTrim(line).length==0){//去除空白
                    line=br.readLine();
                }
                System.out.println(line);
                for (int j = 1; j<=cd_pline.getNSC(); j++) {//流量过程线
                    CH_4A_S ch = getObject(CH_4A_S.class, myTrim(line), ID, j);
                    map.put(ch.getClass().getSimpleName()+"_"+j,ch);
                    for (int k = 0; k < ch.getNZS(); k++) {
                        map.put(CH_4B_S.class.getSimpleName()+"_"+j+"_"+(k+1),getObject(CH_4B_S.class,myTrim(br.readLine()),ID,j,(k+1)));
                    }
                    line=br.readLine();
                    while (line!=null&&myTrim(line).length==0){//去除空白
                        line=br.readLine();
                    }
                    CH_4C_S ch1 = getObject(CH_4C_S.class, myTrim(line), ID, j);
                    map.put(ch1.getClass().getSimpleName()+"_"+j,ch1);
                    for (int k = 0; k < ch1.getNZS1(); k++) {
                        map.put(CH_4D_S.class.getSimpleName()+"_"+j+"_"+(k+1),getObject(CH_4D_S.class,myTrim(br.readLine()),ID,j,(k+1)));
                    }
                    line=br.readLine();
                }
            }

            //5 上下游边界处的堰
//            if(cd_pline.getNWC()!=0){//当NQC!=0要读入NQC条数据
//                while (line!=null&&myTrim(line).length==0){//去除空白
//                    line=br.readLine();
//                }
//                System.out.println(line);
//                for (int j = 1; j<=cd_pline.getNGC(); j++) {//流量过程线
//                    CH_3A_G ch = getObject(CH_3A_G.class, myTrim(line), ID, j);
//                    map.put(ch.getClass().getSimpleName()+"_"+j,ch);
//                    for (int k = 0; k < ch.getNGO(); k++) {
//                        map.put(CH_3B_G.class.getSimpleName()+"_"+j+"_"+(k+1),getObject(CH_3B_G.class,myTrim(br.readLine()),ID,j,(k+1)));
//                    }
//                    line=br.readLine();
//                }
//            }


            // 6 湿地支流边界
            if(cd_pline.getNQLC()!=0){//当NQC!=0要读入NQC条数据
                while (line!=null&&myTrim(line).length==0){//去除空白
                    line=br.readLine();
                }
                System.out.println(line);
                for (int j = 1; j<=cd_pline.getNQLC(); j++) {//流量过程线
                    CH_6A_WL ch = getObject(CH_6A_WL.class, myTrim(line), ID, j);
                    map.put(ch.getClass().getSimpleName()+"_"+j,ch);
                    for (int k = 0; k < ch.getNQQ(); k++) {
                        map.put(CH_6B_WL.class.getSimpleName()+"_"+j+"_"+(k+1),getObject(CH_6B_WL.class,myTrim(br.readLine()),ID,j,(k+1)));
                    }
                    line=br.readLine();
                }
            }

            //NSPC：急流边界总数
            if(cd_pline.getNSPC()!=0){
                while (line!=null&&myTrim(line).length==0){//去除空白
                    line=br.readLine();
                }
                System.out.println(line);
                for (int j = 1; j<=cd_pline.getNSPC(); j++) {//流量过程线
                    CH_7A_JET ch = getObject(CH_7A_JET.class, myTrim(line), ID, j);
                    map.put(ch.getClass().getSimpleName()+"_"+j,ch);
                    for (int k = 0; k < ch.getNZQ(); k++) {
                        map.put(CH_7A_JET.class.getSimpleName()+"_"+j+"_"+(k+1),getObject(CH_7A_JET.class,myTrim(br.readLine()),ID,j,(k+1)));
                    }
                    line=br.readLine();
                    while (line!=null&&myTrim(line).length==0){//去除空白
                        line=br.readLine();
                    }
                    CH_7C_JET ch1 = getObject(CH_7C_JET.class, myTrim(line), ID, j);
                    map.put(ch1.getClass().getSimpleName()+"_"+j,ch1);
                    for (int k = 0; k < ch1.getNQZ(); k++) {
                        map.put(CH_7D_JET.class.getSimpleName()+"_"+j+"_"+(k+1),getObject(CH_7D_JET.class,myTrim(br.readLine()),ID,j,(k+1)));
                    }
                    line=br.readLine();
                }
            }

            //以下省略一些内容
            //....
            if(cd_pline.getNHC()>0){
                while (line!=null&&myTrim(line).length==0){//去除空白
                    line=br.readLine();
                }
                for (int j = 0; j < cd_pline.getNHC() ; j++) {
                    map.put(CI_ZQ.class.getSimpleName()+"_"+j,getObject(CI_ZQ.class,myTrim(line),ID,(j+1)));
                    line=br.readLine();

                }
            }

            //对初值数据进行处理
            if(element>0){
                while (line!=null&&myTrim(line).length==0){//去除空白
                    line=br.readLine();
                }
               List<CJ_VAL0> l=new LinkedList<>();
                int j =0;
                while (j<element){
                   String[] sArr= myTrim(line);
                    for (int k = 0; k < sArr.length; k++) {
                        l.add(j,new CJ_VAL0(ID,(j+1),new BigDecimal(sArr[k]),null,null));
                        j++;
                    }
                    line=br.readLine();
                }
                j=0;
                while (j<element){
                    String[] sArr= myTrim(line);
                    for (int k = 0; k < sArr.length; k++) {
                        l.get(j).setU1(new BigDecimal(sArr[k]));
                        j++;
                    }
                    line=br.readLine();
                }
                j=0;
                while (j<element){
                    String[] sArr= myTrim(line);
                    for (int k = 0; k < sArr.length; k++) {
                        l.get(j).setV1(new BigDecimal(sArr[k]));
                        j++;
                    }
                    line=br.readLine();
                }
                map.put("List",l);
                System.out.println(l.size());
           }
            if(cc_cp.getNOUT()!=0){
                while (line!=null&&myTrim(line).length==0){//去除空白
                    line=br.readLine();
                }
                for (int j = 0; j < cc_cp.getNOUT() ; j++) {
                    map.put(CK_ZOUT.class.getSimpleName()+"_"+j,getObject(CK_ZOUT.class,myTrim(line),ID));
                    line=br.readLine();

                }
            }







            while ((line = br.readLine()) != null) {
                // 一次读入一行数据

                System.out.println(i+++Arrays.toString(myTrim(line)));
                //System.out.println(i+++line);
            }
        } catch (IOException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(map);

    return map;
    }

    //对象包装方法 目前缺陷：传参只能按固定格式
    public static  <T> T getObject(Class<T> clazz,String[] s,Object...args) throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        T t = clazz.newInstance();
        Field[] fields = clazz.getDeclaredFields();
        int i=0;
        for (i = 0; i <args.length; i++) {
            fields[i].setAccessible(true);
            fields[i].set(t,args[i]);
        }
        for (int j = 0; j < s.length&&i<fields.length; j++) {
            fields[i].setAccessible(true);
            Class type = fields[i].getType();
//            if(type==Boolean.class){
//                if("1".equals(s[j]))
//                fields[i].set(t,true);
//                else if("0".equals(s[j]))
//                    fields[i].set(t,false);
//                i++;
//                break;
//            }
            Constructor con = type.getConstructor(String.class);
            Object o = con.newInstance(s[j]);
            fields[i].set(t,o);
            i++;
        }
        return (t);
    }
    //解决过程线数据导入代码重复问题
    public static String getPLine(String line,BufferedReader br,Integer num,Class clazz1,Map<String,Object> map,Integer ID) throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {// num为数量

        while (line!=null&&myTrim(line).length==0){//去除空白
            line=br.readLine();
        }
        for (int j = 0; j < num ; j++) {
            map.put(clazz1.getSimpleName()+"_"+j,getObject(clazz1,myTrim(line),ID,(j+1)));
            line=br.readLine();

        }
        return line;
    }















//    读输出数控

    public  static  Map<String,List> readFile() {
        Map<String,List>  map = new HashMap();
        List<Output> l =new ArrayList<>();
        List<QinflowData> l1 =new ArrayList<>();
        Integer ID=0;
        String pathname = "web/static/outPutFile_2.txt"; // 绝对路径或相对路径都可以，写入文件时演示相对路径,读取以上路径的input.txt文件
        //防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw;
        //不关闭文件会导致资源的泄露，读写文件都同理
        //Java7的try-with-resources可以优雅关闭文件，异常时自动关闭文件；详细解读https://stackoverflow.com/a/12665271
        try (FileReader reader = new FileReader(pathname);
             BufferedReader br = new BufferedReader(reader) // 建立一个对象，它把文件内容转成计算机能读懂的语言
        ) {
            String line;

            int i = 1 ;
                Integer TimeInterval=0;
                BigDecimal Qinflow = null;

            while((line = br.readLine()) != null) {
                if(i==4){
                    System.out.println(i+line);
                    char[] c = line.toCharArray();
                    int j =0;
                    while(!Character.isDigit(c[j])){
                        j++;
                    }
                    while (Character.isDigit(c[j])){
                        int tempt=c[j]-'0';
                        TimeInterval=TimeInterval*10+tempt;
                        j++;
                    }
                    while(!Character.isDigit(c[j])){
                        j++;
                    }
                    String s="";
                    while (Character.isDigit(c[j])||c[j]=='.'){
                        s+=c[j];
                        j++;
                    }
                    Qinflow = new BigDecimal(s);
                    System.out.println(TimeInterval+"|||"+Qinflow);

                }
                if(8<i&&i<1609){
                    l.add(String2Output(TimeInterval,myTrim(line),ID));//调用静态方法将数据为对象

                }else if(i==1609){
                    i=1;
                    l1.add(new QinflowData(ID,TimeInterval,Qinflow));
                     TimeInterval=0;//重新赋予初值
                     Qinflow = null;
                }
                i++;

                // 一次读入一行数据



            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(l.size());
        map.put("Output",l);
        map.put("Qinflow",l1);
        return map;

    }

    //去空白转为字符串数组
    public static  String[] myTrim(String s){
        List<String> l = new ArrayList<>();

        for( int i= 0 ; i<s.length() ; i++){
            String tempt ="";
            while(i<s.length()&&s.charAt(i)!=' '){

                tempt=tempt+s.charAt(i);
                if(s.charAt(i)=='.'){
                    if((i+1)>=s.length()||s.charAt(i+1)==' ')
                        tempt+="0";
                    else if(".".equals(tempt)){

                        tempt="0"+tempt;
                    }
                }
                i++;
            }

            if(!tempt.equals("")) {
                l.add(tempt);
            }
        }

        return  l.toArray(new String[l.size()]);
    }

    public static Output String2Output(Integer TimeInterval,String[] s,Integer ID) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class clazz = Output.class;
        Output op= (Output) clazz.newInstance();
        //设置固定数据
        op.setTimeInterval(TimeInterval);
        op.setID(ID);
        Field[] flies = clazz.getDeclaredFields();
        int j = 0;
        //将String[]转为对象属性值
         for(int i = 2 ; i<flies.length&&j<s.length; i++){
           flies[i].setAccessible(true);
            Class type = flies[i].getType();
//            getTypeObject value = (Object)s[j++];
            Constructor con = type.getConstructor(String.class);
            Object o = con.newInstance(s[j++]);
           flies[i].set(op,o);
        }
        return op;
    }


}
