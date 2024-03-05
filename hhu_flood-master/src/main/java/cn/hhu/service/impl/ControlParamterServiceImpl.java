package cn.hhu.service.impl;

import cn.hhu.Bean.*;
import cn.hhu.mapper.*;
import cn.hhu.repository.STRSVRRepository;
import cn.hhu.repository.impl.STRSVRRepositoryImpl;
import cn.hhu.service.ControlParamterService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
/**
 * @author tlj
 */
public class ControlParamterServiceImpl implements ControlParamterService {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    @Autowired
    CA_PDMapper ca_pdMapper;
    @Autowired
    Gson gson ;
    @Autowired
    CB_NREADMapper cb_nreadMapper;
    @Autowired
    CC_CPMapper cc_cpMapper;
    @Autowired
    CD_PLINEMapper cd_plineMapper;
    @Autowired
    CE_TANDTMapper ce_tandtMapper;
    @Autowired
    CF_GFANDNFMapper cf_gfandnfMapper;
    @Autowired
    ST_RIVER_RMapper st_river_rMapper;
    @Autowired
    CH_1A_QMapper ch_1A_qMapper;
    @Autowired
    CH_2A_ZMapper ch_2A_zMapper;
    @Autowired
    CH_1B_QMapper ch_1B_qMapper;
    @Autowired
    CH_2B_ZMapper ch_2B_zMapper;
    @Autowired
    CH_3A_GMapper ch_3A_gMapper;
    @Autowired
    CH_3B_GMapper ch_3B_gMapper;
    @Autowired
    ST_GATE_RMapper st_gate_rMapper;
    @Autowired
    CI_ZQMapper ci_zqMapper;
    @Autowired
    ST_ZQRL_BMapper st_zqrl_bMapper;
    @Autowired
    CH_7A_JETMapper ch_7A_jetMapper;
    @Autowired
    CH_7B_JETMapper ch_7B_jetMapper;
    @Autowired
    CH_7C_JETMapper ch_7C_jetMapper;
    @Autowired
    CH_7D_JETMapper ch_7D_jetMapper;
    @Autowired
    ST_WAS_RMapper st_was_rMapper;
    @Autowired
    CH_5A_WMapper ch_5A_wMapper;
    @Autowired
    CH_5B_WMapper ch_5B_wMapper;

    //时段长度
    double hours=0;

    @Autowired
            CH_6A_WLMapper ch_6A_wlMapper;
    @Autowired
            CH_6B_WLMapper ch_6B_wlMapper;
    @Autowired
            CH_4A_SMapper ch_4A_sMapper;
    @Autowired
            CH_4B_SMapper ch_4B_sMapper;
    @Autowired
            CH_4C_SMapper ch_4C_sMapper;
    @Autowired
            CH_4D_SMapper ch_4D_sMapper;
    STRSVRRepository st = new STRSVRRepositoryImpl();
    @Override
    public List<Date> getSTTM(int STCD){
        return st.getSTTM(STCD);
    }

    @Override
    public List<ST_RSVR_R> getST_RSVR_R(String id, Date TM1, Date TM2) {
        return st.getST_RSVR_R(id,TM1,TM2);
    }

    private <T> Integer insertEntity(JsonObject jo,int id, String name, BaseMapper<T> mapper,Class<T> clazz) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        T t=gson.fromJson(jo.get(name),clazz);
        Method setId = clazz.getMethod("setID", Integer.class);
        setId.invoke(t,id);

        return mapper.insert(t);
    }

    @Transactional(rollbackFor = Exception.class,readOnly = false)
    @Override
    public Boolean insertAllParameters(String s) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
//        Integer maxId = ca_pdMapper.getMaxId();
        // 个json转成json对象
        JsonObject jo = JsonParser.parseString(s).getAsJsonObject();
        System.out.println(jo);
//        ObjectMapper objectMapper = new ObjectMapper();
        CA_PD ca_pd = gson.fromJson(jo.get("CA_PD"), CA_PD.class);
        if(ca_pd.getId()!=null){
            System.out.println("进入删除了");
            deleteAllData(ca_pd.getId());
        }
        ca_pdMapper.insert(ca_pd);
        //System.out.println(ca_pd);
        Integer id = ca_pd.getId();

        CB_NREAD cb_nread = gson.fromJson(jo.get("CB_NREAD"), CB_NREAD.class);

        cb_nread.setID(id);
        cb_nreadMapper.insert(cb_nread);
        CC_CP cc_cp = gson.fromJson(jo.get("CC_CP"), CC_CP.class);

        cc_cp.setID(id);
        cc_cpMapper.insert(cc_cp);
        CD_PLINE cd_pline = gson.fromJson(jo.get("CD_PLINE"), CD_PLINE.class);
        cd_pline.setID(id);
        cd_plineMapper.insert(cd_pline);
        CE_TANDT ce_tandt = gson.fromJson(jo.get("CE_TANDT"), CE_TANDT.class);
        ce_tandt.setID(id);
        ce_tandtMapper.insert(ce_tandt);
            insertEntity(jo,id,"CF_GFANDNF",cf_gfandnfMapper, CF_GFANDNF.class);
        Integer nst = cc_cp.getNST();
        hours = ce_tandt.getDTIME().multiply(new BigDecimal(nst)).doubleValue();
        JsonObject checked = jo.getAsJsonObject("Checked");
        //System.out.println(checked);
//        JsonArray asJsonArray = checked.getAsJsonObject("NQC").getAsJsonArray("Checked");
//        System.out.println(asJsonArray);
//        System.out.println(asJsonArray.size());
        //插入流量过程线
        insertLineData(checked.getAsJsonObject("NQC"),id,ch_1A_qMapper, CH_1A_Q.class,ch_1B_qMapper,CH_1B_Q.class,ST_RIVER_R.class.getMethod("getQ"));
        //插入水位过程线Z
        insertLineData(checked.getAsJsonObject("NQC"),id,ch_2A_zMapper,CH_2A_Z.class,ch_2B_zMapper,CH_2B_Z.class,ST_RIVER_R.class.getMethod("getZ"));
        //插入闸门数据
        data2Line(
                getBaseData(st_gate_rMapper,checked.getAsJsonObject("NGC")),
                ch_3A_gMapper,ch_3B_gMapper,id,CH_3A_G.class,CH_3B_G.class,
                ST_GATE_R.class.getDeclaredMethod("getGTOPHGT"),
                ST_GATE_R.class.getDeclaredMethod("getSTCD"),
                ST_GATE_R.class.getDeclaredMethod("getTM"));
        //插入水位流量关系曲线
        List<List<ST_ZQRL_B>> baseData = getBaseData(st_zqrl_bMapper, checked.getAsJsonObject("NHC"), "BGTM");
        insertZQ(baseData,id);
        //湿地过程线
        data2Line(
                getBaseData(st_gate_rMapper,checked.getAsJsonObject("NQLC")),
                ch_6A_wlMapper,ch_6B_wlMapper,id,CH_6A_WL.class,CH_6B_WL.class,
                ST_GATE_R.class.getDeclaredMethod("getGTQ"),
                ST_GATE_R.class.getDeclaredMethod("getSTCD"),
                ST_GATE_R.class.getDeclaredMethod("getTM")
        );
        //急流过程线
        //水位
        data2Line(
                getBaseData(st_was_rMapper,checked.getAsJsonObject("NSPC")),
                ch_7A_jetMapper,ch_7B_jetMapper,id,CH_7A_JET.class,CH_7B_JET.class,
                ST_WAS_R.class.getDeclaredMethod("getUPZ"),
                ST_WAS_R.class.getDeclaredMethod("getSTCD"),
                ST_WAS_R.class.getDeclaredMethod("getTM")
        );
        //流量
        data2Line(
                getBaseData(st_gate_rMapper,checked.getAsJsonObject("NSPC")),
                ch_7C_jetMapper,ch_7D_jetMapper,id,CH_7C_JET.class,CH_7D_JET.class,
                ST_GATE_R.class.getDeclaredMethod("getGTQ"),
                ST_GATE_R.class.getDeclaredMethod("getSTCD"),
                ST_GATE_R.class.getDeclaredMethod("getTM")
        );
        //边界处的闸
        //水位
        data2Line(
                getBaseData(st_was_rMapper,checked.getAsJsonObject("NSPC")),
                ch_4A_sMapper,ch_4B_sMapper,id,CH_4A_S.class, CH_4B_S.class,
                ST_WAS_R.class.getDeclaredMethod("getUPZ"),
                ST_WAS_R.class.getDeclaredMethod("getSTCD"),
                ST_WAS_R.class.getDeclaredMethod("getTM")
        );
        //流量
        data2Line(
                getBaseData(st_gate_rMapper,checked.getAsJsonObject("NSPC")),
                ch_4C_sMapper,ch_4D_sMapper,id,CH_4C_S.class,CH_4D_S.class,
                ST_GATE_R.class.getDeclaredMethod("getGTQ"),
                ST_GATE_R.class.getDeclaredMethod("getSTCD"),
                ST_GATE_R.class.getDeclaredMethod("getTM")
        );

//        //堰
        data2Line(
                getBaseData(st_was_rMapper,checked.getAsJsonObject("NWC")),
                ch_5A_wMapper,ch_5B_wMapper,id,CH_5A_W.class,CH_5B_W.class,
                ST_WAS_R.class.getDeclaredMethod("getUPZ"),
                ST_WAS_R.class.getDeclaredMethod("getSTCD"),
                ST_WAS_R.class.getDeclaredMethod("getTM")
        );
        //写入text
        writeFile(id);

        return true;
    }

    @Override
    public String getAllParameterById(Integer id) {
        CA_PD ca_pd = ca_pdMapper.selectById(id);
        CB_NREAD cb_nread = cb_nreadMapper.selectById(id);
        CC_CP cc_cp = cc_cpMapper.selectById(id);
         CD_PLINE cd_pline = cd_plineMapper.selectById(id);
        CE_TANDT ce_tandt =ce_tandtMapper.selectById(id);
        CF_GFANDNF cf_gfandnf = cf_gfandnfMapper.selectById(id);
        //System.out.println(getCheckInfo(ch_1A_qMapper,id));;
        Map<String,Object> m = new HashMap<>();
        Map<String,Object> m1 = new HashMap<>();
        Map<String,Map> checked = new HashMap<>();
        checked.put("NQC",getCheckInfo(ch_1A_qMapper,id));
        checked.put("NZC",getCheckInfo(ch_2A_zMapper,id));
        checked.put("NGC",getCheckInfo(ch_3A_gMapper,id));
        checked.put("NSC",getCheckInfo(ch_4A_sMapper,id));
        checked.put("NWC",getCheckInfo(ch_5A_wMapper,id));
        checked.put("NQLC",getCheckInfo(ch_6A_wlMapper,id));
        checked.put("NSPC",getCheckInfo(ch_7A_jetMapper,id));
        //对水位流量关系曲线进行读取
        checked.put("NHC",getZQCheckInfo(ci_zqMapper,id));
        m1.put("ca_pd",ca_pd);
        m1.put("cb_nread",cb_nread);
        m1.put("cc_cp",cc_cp);
        m.put("Form1",m1);
        m.put("ce_tandt",ce_tandt);
        m.put("cf_gfandnf",cf_gfandnf);
        m.put("cd_pline",cd_pline);
        m.put("checked",checked);
        String s = gson.toJson(m);
        System.out.println(s);

        return s;
    }
    private<T> Map<String,List> getCheckInfo(BaseMapper<T> mapper,int id) {
        QueryWrapper<T> wrapper = new QueryWrapper<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


        wrapper.select("STCD","TM");
        wrapper.eq("ID",id);
        List<String> stcdL = new ArrayList<>();
        List<String> tmL = new ArrayList<>();
        mapper.selectList(wrapper).forEach(t->{
            System.out.println(t.getClass());

            try {
                 stcdL.add( t.getClass().getMethod("getSTCD").invoke(t).toString());
                 tmL.add(formatter.format(t.getClass().getMethod("getTM").invoke(t)) );
            } catch (Exception e) {
                e.printStackTrace();
            }



        });
        HashMap<String, List> map = new HashMap<>();
        map.put("date",tmL);
        map.put("Checked",stcdL);

        return map;
    }
    //水位流量关系曲线特殊处理
    private<T> Map<String,List> getZQCheckInfo(BaseMapper<T> mapper,int id) {
        QueryWrapper<T> wrapper = new QueryWrapper<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


        wrapper.select("STCD","TM");
        wrapper.eq("ID",id);
        wrapper.eq("I_NUM",0);
        List<String> stcdL = new ArrayList<>();
        List<String> tmL = new ArrayList<>();
        mapper.selectList(wrapper).forEach(t->{
            System.out.println(t.getClass());

            try {
                stcdL.add( t.getClass().getMethod("getSTCD").invoke(t).toString());
                tmL.add(formatter.format(t.getClass().getMethod("getTM").invoke(t)) );
            } catch (Exception e) {
                e.printStackTrace();
            }



        });
        HashMap<String, List> map = new HashMap<>();
        map.put("date",tmL);
        map.put("Checked",stcdL);

        return map;
    }


    private<T,K> void insertLineData(JsonObject jo, int id, BaseMapper<T> mapper, Class<T> clazz1,BaseMapper<K> mapper2, Class<K> clazz2, Method method ) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        //获取有参构造方法
        Constructor<T> c1=null;
        Constructor<K> c2=null;
        for(Constructor c : clazz1.getConstructors()){
            if(c.getParameters().length>0){
                c1=c;
                break;
            }
        }
        for(Constructor c : clazz2.getConstructors()){
            if(c.getParameters().length>0){
                c2=c;
                break;
            }
        }
        river2Line(jo,id,mapper,c1,mapper2,c2,method);
    }
    //从河流数据中得到过程线数据
//    @Transactional(propagation = Propagation.NOT_SUPPORTED)
//    @DS("jsycwater")
    private <T,K> void river2Line(JsonObject jo, int id, BaseMapper<T> mapper, Constructor<T> constructor,BaseMapper<K> mapper2, Constructor<K> constructor2, Method method) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        JsonArray checked = jo.getAsJsonArray("Checked");
        JsonArray date = jo.getAsJsonArray("date");
        //JsonElement je =  date.get(0);

//        System.out.println(je);
//        System.out.println(je.getAsJsonArray().get(0));
        for(int i=0,n=checked.size();i<n;i++){
            int STCD = checked.get(i).getAsInt();
//            JsonArray dateArray = date.get(i).getAsJsonArray();
            String begin = date.get(i).getAsString();
            LocalDateTime beginDateTime = LocalDateTime.parse(begin, dtf);
            LocalDateTime endTime =getEndTime(beginDateTime,hours);

            System.out.println(beginDateTime+":"+endTime);
            QueryWrapper<ST_RIVER_R> wrapper=new QueryWrapper<>();
            wrapper.eq("STCD",STCD);
            wrapper.between("TM",beginDateTime,endTime);
            wrapper.orderByAsc("TM");
            //获取河道数据
            List<ST_RIVER_R> objects = st_river_rMapper.selectList(wrapper);


            BigDecimal d0;
            if(objects.size()<1){
                System.out.println("无数据");
                return;
            }
            ST_RIVER_R st_river_r=objects.get(0);
            T t = constructor.newInstance(id,i,objects.size()-1,
                    (d0= (BigDecimal) method.invoke(st_river_r))!=null? d0:null,STCD,Timestamp.valueOf(begin));
            mapper.insert(t);
            K k=null;
            //插入过程线
            for (int j = 1,length=objects.size(); j < length; j++) {
                //获取毫秒数
                Long tm1 = st_river_r.getTM().getTime();
                st_river_r= objects.get(j);
                Long tm2 = st_river_r.getTM().getTime();
                k = constructor2.newInstance(id,i,j-1
                        ,(d0= (BigDecimal) method.invoke(st_river_r))!=null? d0:null,
                        new BigDecimal((tm2-tm1)/3600000));//时段长度 一个小时由3600000毫秒
                //执行插入操作
                mapper2.insert(k);

            }
        }
    }
    private <T> List<List<T>> getBaseData(BaseMapper<T> mapper,JsonObject jo){
        JsonArray checked = jo.getAsJsonArray("Checked");
        JsonArray date = jo.getAsJsonArray("date");
        List<List<T>> re = new ArrayList<>();
        List<T> list;
        for(int i=0,n=checked.size();i<n;i++){

            int STCD = checked.get(i).getAsInt();
            String begin = date.get(i).getAsString();
            LocalDateTime beginDateTime = LocalDateTime.parse(begin, dtf);
            LocalDateTime endTime =getEndTime(beginDateTime,hours);
            QueryWrapper<T> wrapper=new QueryWrapper<>();
            wrapper.eq("STCD",STCD);
            wrapper.between("TM",beginDateTime,endTime);
            wrapper.orderByAsc("TM");
            list = mapper.selectList(wrapper);

            re.add(list);
        }
        return  re;
    }
    private <T> List<List<T>> getBaseData(BaseMapper<T> mapper,JsonObject jo,String TM){
        JsonArray checked = jo.getAsJsonArray("Checked");
        JsonArray date = jo.getAsJsonArray("date");
        List<List<T>> re = new ArrayList<>();
        List<T> list;
        for(int i=0,n=checked.size();i<n;i++){

            int STCD = checked.get(i).getAsInt();
            String begin = date.get(i).getAsString();
            LocalDateTime beginDateTime = LocalDateTime.parse(begin, dtf);
            LocalDateTime endTime =getEndTime(beginDateTime,hours);
            QueryWrapper<T> wrapper=new QueryWrapper<>();
            wrapper.eq("STCD",STCD);
            wrapper.between(TM,beginDateTime,endTime);
            wrapper.orderByAsc(TM);
            list = mapper.selectList(wrapper);

            re.add(list);
        }
        return  re;
    }
    private <T,K,W> void data2Line(List<List<T>> l,BaseMapper<K> kMapper,BaseMapper<W> wMapper,int id,Class<K> kClass,Class<W> wClass,Method getData,Method getSTCD, Method getTM) throws InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        //获取有参构造方法
        Constructor<K> c1=null;
        Constructor<W> c2=null;
        for(Constructor c : kClass.getConstructors()){
            if(c.getParameters().length>0){
                c1=c;
                break;
            }
        }
        for(Constructor c : wClass.getConstructors()){
            if(c.getParameters().length>0){
                c2=c;
                break;
            }
        }
        List<T> list = null;
        //实列化

        for (int i = 0 ,n = l.size(); i < n; i++) {
            list= l.get(i);
            int length= list.size();
            if(length==0){
                continue;
            }
            T t = list.get(0);
            K k = c1.newInstance(id, i, length-1, getData.invoke(t),getSTCD.invoke(t),getTM.invoke(t));

            kMapper.insert(k);
            for (int j = 1  ; j < length; j++) {
                Method getTm=t.getClass().getMethod("getTM");
                Long tm1= ((Timestamp)getTm.invoke(t)).getTime();
                T temp = list.get(j);
                Long tm2= ((Timestamp)getTm.invoke(temp)).getTime();
                W w = c2.newInstance(id,i,j-1,getData.invoke(t),
                        new BigDecimal((tm2-tm1)/3600000));//时段长度 一个小时由3600000毫秒
                wMapper.insert(w);
                t=temp;

            }
        }
    }
    //插入水位流量关系曲线
    private void  insertZQ(List<List<ST_ZQRL_B>> l ,int id ){
        CI_ZQ ci_zq = new CI_ZQ();
        ci_zq.setID(id);

        for (int i = 0 ,n = l.size(); i < n ; i++) {
            ci_zq.setK_NUM(i);
            List<ST_ZQRL_B> list = l.get(i);
            for (int j = 0,length = list.size(); j < length; j++) {
                ci_zq.setI_NUM(j);
                ST_ZQRL_B st_zqrl_b = list.get(j);
                ci_zq.setSTCD(st_zqrl_b.getSTCD());
                ci_zq.setTM(st_zqrl_b.getBGTM());
                //水位
                ci_zq.setHRC(new BigDecimal(st_zqrl_b.getZ()));
                //流量
                ci_zq.setQRC(new BigDecimal(st_zqrl_b.getQ()));
                ci_zqMapper.insert(ci_zq);

            }
        }
    }
    //用于获取结束时间
    private LocalDateTime getEndTime(LocalDateTime beginDateTime,double hours){
        long seconds = beginDateTime.toEpochSecond(ZoneOffset.of("+8"));
        seconds+=hours*3600;
        return  LocalDateTime.ofEpochSecond(seconds,0,ZoneOffset.ofHours(8));
    }
    //通过ID删除所有数据，简单向
    private void deleteAllData(int id){
        ca_pdMapper.deleteById(id);
        cb_nreadMapper.deleteById(id);
        cc_cpMapper.deleteById(id);
        cd_plineMapper.deleteById(id);
        ce_tandtMapper.deleteById(id);
        cf_gfandnfMapper.deleteById(id);
        ch_1A_qMapper.deleteById(id);
        ch_1B_qMapper.deleteById(id);
        ch_2B_zMapper.deleteById(id);
        ch_2A_zMapper.deleteById(id);
        ch_3A_gMapper.deleteById(id);
        ch_3B_gMapper.deleteById(id);
        ch_4A_sMapper.deleteById(id);
        ch_4B_sMapper.deleteById(id);
        ch_4C_sMapper.deleteById(id);
        ch_4D_sMapper.deleteById(id);
        ch_5A_wMapper.deleteById(id);
        ch_5B_wMapper.deleteById(id);
        ch_6A_wlMapper.deleteById(id);
        ch_6B_wlMapper.deleteById(id);
        ch_7A_jetMapper.deleteById(id);
        ch_7B_jetMapper.deleteById(id);
        ch_7C_jetMapper.deleteById(id);
        ch_7D_jetMapper.deleteById(id);
        ci_zqMapper.deleteById(id);

    }
    private <T> void deleteByID(BaseMapper<T> mapper,int id){
        QueryWrapper<T> wrapper = new QueryWrapper<>();
        wrapper.eq("ID",id);
        mapper.delete(wrapper);
    }

    public static void main(String[] args) {
//        Collections.sort(new ArrayList(), new Comparator<Object>() {
//            @Override
//            public int compare(Object o1, Object o2) {
//                return 0;
//            }
//        });
    }

    //写入text
    public  void writeFile(int ID) {


        try {
            // 相对路径，如果没有则要建立一个新的output.txt文件
            File writeName = new File("DamBreak_CF_"+ID+".txt");
            // 创建新文件,有同名的文件的话直接覆盖
            writeName.createNewFile();
            try (FileWriter writer = new FileWriter(writeName);
                 BufferedWriter out = new BufferedWriter(writer)
            ) {
                // \r\n即为换行
                out.write(myToString(ca_pdMapper.selectById(ID),1)+"\r\n");

                out.write(myToString(cb_nreadMapper.selectById(ID),1)+"\r\n");
                CC_CP cc_cp = cc_cpMapper.selectById(ID);
                out.write(myToString(cc_cp,1)+"\r\n");
                CD_PLINE cd_pline = cd_plineMapper.selectById(ID);
                out.write(myToString(cd_pline,1)+"\r\n");
                out.write(myToString(cd_plineMapper.selectById(ID),1)+"\r\n");
                //前五行结构单一

                //第六行一个对象分三行写
                CF_GFANDNF cf_gfandnf= cf_gfandnfMapper.selectById(ID);
                out.write("  "+cf_gfandnf.getMG()+"  "+cf_gfandnf.getMGS()+"  "+cf_gfandnf.getMCS()+"  "+cf_gfandnf.getMTS()+"\r\n");
                out.write("  "+cf_gfandnf.getLIMTER()+"  "+cf_gfandnf.getBETA()+"\r\n");
                out.write("  "+cf_gfandnf.getPP()+"  "+cf_gfandnf.getWSP()+"  "+cf_gfandnf.getWINC()+"\r\n");
                out.newLine();
                //打印边界条件
                //如果分多次查询
//                for (int i = 0; i < cc_cp.getNBC(); i++) {
//                    String sql ="SELECT * FROM CG1_B where ID=? AND IDUM = ?";
//                }
                //打印边界条件
//                if(cc_cp.getNBC()>0){
//                    List<CG1_B> instances = DbUtils.getObjects("SELECT * FROM CG1_B WHERE ID= ?  ORDER BY IDUM", CG1_B.class, ID);
//                    for (int i = 0; i < instances.size(); i++) {
//                        CG1_B cg1_b = instances.get(i);
//                        out.write(myToString(cg1_b,1)+"\r\n");
//                        switch (cg1_b.getITYPE()){
//                            case 6:
//                                CG2_W cg2_w = DbUtils.getMyInstance("SELECT * FROM CG2_W WHERE ID= ?  AND NID = ? ", CG2_W.class, ID, cg1_b.getIDUM());
//                                out.write(myToString(cg2_w,2)+"\r\n");
//                                break;
//                            case 7:
//                                CG2_G cg2_g = DbUtils.getMyInstance("SELECT * FROM CG2_G WHERE ID= ?  AND NID = ? ", CG2_G.class, ID, cg1_b.getIDUM());
//                                out.write(myToString(cg2_g,2)+"\r\n");
//                                break;
//                            case 8:
//                                CG2_SG cg2_sg = DbUtils.getMyInstance("SELECT * FROM CG2_SG WHERE ID= ?  AND NID = ? ", CG2_SG.class, ID, cg1_b.getIDUM());
//                                out.write(myToString(cg2_sg,2)+"\r\n");
//                                break;
//                            case 9:
//                                CG2_L cg2_l = DbUtils.getMyInstance("SELECT * FROM CG2_L WHERE ID= ?  AND NID = ? ", CG2_L.class, ID, cg1_b.getIDUM());
//                                out.write(myToString(cg2_l,2)+"\r\n");
//                                break;
//                            case 12:
//                                CG2_BANDC cg2_bandc = DbUtils.getMyInstance("SELECT * FROM CG2_BANDC WHERE ID= ?  AND NID = ? ", CG2_BANDC.class, ID, cg1_b.getIDUM());
//                                out.write(myToString(cg2_bandc,2)+"\r\n");
//                                break;
//                            case 15:
//                                CG2_CW cg2_cw = DbUtils.getMyInstance("SELECT * FROM CG2_CW WHERE ID= ?  AND NID = ? ", CG2_CW.class, ID, cg1_b.getIDUM());
//                                out.write(myToString(cg2_cw,2)+"\r\n");
//                                break;
//                            case 16:
//                                CG2_CL cg2_cl = DbUtils.getMyInstance("SELECT * FROM CG2_CL WHERE ID= ?  AND NID = ? ", CG2_CL.class, ID, cg1_b.getIDUM());
//                                out.write(myToString(cg2_cl,2)+"\r\n");
//                                break;
//                            default:
//                                break;
//                        }
//                    }
//                }
                //流量过程线
                if(cd_pline.getNQC()>0){
                    out.newLine();
                    lineData(out,ch_1A_qMapper,ch_1B_qMapper,ID,"NQT");
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
                    lineData(out,ch_2A_zMapper,ch_2B_zMapper,ID,"NZT");
                }
                //闸门过程线
                if(cd_pline.getNGC()>0){
                    out.newLine();
                    lineData(out,ch_3A_gMapper,ch_1B_qMapper,ID,"NGO");
                }
                //上下游边界处的
                if(cd_pline.getNSC()>0){
                    out.newLine();
                    lineData(out,ch_4A_sMapper,ch_4B_sMapper,ID,"NZS");
                    lineData(out,ch_4C_sMapper,ch_4D_sMapper,ID,"NZS1");
                }
                //上下游边界处的堰

                if(cd_pline.getNWC()!=null&&cd_pline.getNWC()>0){
                    out.newLine();
                    lineData(out,ch_5A_wMapper,ch_5B_wMapper,ID,"NWO");

                }

                //湿地支流边界
                if(cd_pline.getNQLC()>0){
                    out.newLine();
                    lineData(out,ch_6A_wlMapper,ch_6B_wlMapper,ID,"NQQ");
                }

                //急流边界
                if(cd_pline.getNSPC()>0){
                    out.newLine();
                    lineData(out,ch_7A_jetMapper,ch_7B_jetMapper,ID,"NZQ");
                    lineData(out,ch_7C_jetMapper,ch_7D_jetMapper,ID,"NQZ");
                }

                out.newLine();
                //地形数据
//                {
//                    String sql ="SELECT * FROM CJ_VAL0 WHERE ID= ?  ORDER BY I_NUM";
//                    List<CJ_VAL0> objects = DbUtils.getObjects(sql, CJ_VAL0.class, ID);
//                    int i =0;
//                    for(CJ_VAL0 cjVal0:objects){
//                        out.write("   "+cjVal0.getZ1());
//                        if(i++==9){
//                            out.newLine();
//                            i=0;
//                        }
//                    }
//                    i=0;
//                    for(CJ_VAL0 cjVal0:objects){
//                        out.write("    "+cjVal0.getU1());
//                        if(i++==9){
//                            out.newLine();
//                            i=0;
//                        }
//                    }
//                    i=0;
//                    for(CJ_VAL0 cjVal0:objects){
//                        out.write("   "+cjVal0.getV1());
//                        if(i++==9){
//                            out.newLine();
//                            i=0;
//                        }
//                    }
//                }

                out.flush(); // 把缓存区内容压入文件
            }

        } catch (IOException | IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
    private <T> String myToString(T t,int first) throws IllegalAccessException {
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
    private <T> String myToString(T t,int first,int exclude) throws IllegalAccessException {
        Class clazz = t.getClass();
        Field[] fields = clazz.getDeclaredFields();
        String result="   ";
        for (int i = first; i < (fields.length-exclude) ; i++) {
            fields[i].setAccessible(true);
            Object temp = fields[i].get(t);
            if(temp!=null){
                result+=temp;
                result+="   ";
            }

        }
        return  result;
    }
    private <T,K> void  lineData(BufferedWriter out, BaseMapper<T> tMapper,BaseMapper<K> kMapper, int  ID, String name) throws IllegalAccessException, IOException, NoSuchFieldException {
        QueryWrapper<K> kWrapper ;
        QueryWrapper<T> tWrapper = new QueryWrapper<>();
        tWrapper.eq("ID",ID);
        tWrapper.orderByAsc("I_NUM");
        List<T> list = tMapper.selectList(tWrapper);
        for (int i = 0 ,n1 = list.size(); i < n1 ; i++) {
            T t = list.get(i);
            //输出过程线
            out.write(myToString(t,2,2)+"\r\n");

            System.out.println("类型"+":"+t.getClass());
            Field field = t.getClass().getDeclaredField(name);
            field.setAccessible(true);
            //获取数量
            Integer num = (Integer)field.get(t);
            kWrapper = new QueryWrapper<>();
            kWrapper.eq("ID",ID);
            kWrapper.eq("I_NUM",i);
            kWrapper.orderByAsc("J_TIME");

            List<K> listK=kMapper.selectList(kWrapper);
            for (K k : listK){
                //输出到txt文件中
                out.write(myToString(k,3)+"\r\n");
            }
        }
    }
    private <T,K> void  lineData(BufferedWriter out, BaseMapper<T> tMapper,BaseMapper<K> kMapper, int  ID, String name,String ...args) throws IllegalAccessException, IOException, NoSuchFieldException {
        QueryWrapper<K> kWrapper ;
        QueryWrapper<T> tWrapper = new QueryWrapper<>();
        tWrapper.eq("ID",ID);
        tWrapper.orderByAsc("I_NUM");
        List<T> list = tMapper.selectList(tWrapper);
        for (int i = 0 ,n1 = list.size(); i < n1 ; i++) {
            T t = list.get(i);
            //输出过程线
            out.write(myToString(t,2)+"\r\n");

            System.out.println("类型"+":"+t.getClass());
            Field field = t.getClass().getDeclaredField(name);
            field.setAccessible(true);
            //获取数量
            Integer num = (Integer)field.get(t);
            kWrapper = new QueryWrapper<>();
            kWrapper.eq("ID",ID);
            kWrapper.eq("I_NUM",i);
            kWrapper.orderByAsc("J_TIME").select(args);

            List<K> listK=kMapper.selectList(kWrapper);
            for (K k : listK){
                //输出到txt文件中
                out.write(myToString(k,3)+"\r\n");
            }
        }
    }
    //不打印STCD和TM
    private <T,K> void  lineData2(BufferedWriter out, BaseMapper<T> tMapper,BaseMapper<K> kMapper, int  ID, String name ) throws IllegalAccessException, IOException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
        QueryWrapper<K> kWrapper ;
        QueryWrapper<T> tWrapper = new QueryWrapper<>();
        tWrapper.eq("ID",ID);
        tWrapper.orderByAsc("I_NUM");
        List<T> list = tMapper.selectList(tWrapper);
        if(list.size()<0) return;
        T t1 = list.get(0);
        Class tClass = t1.getClass();
//        Method setSTCD = kClass.getMethod("setSTCD",Integer.class);
//        Method setTM     = kClass.getMethod("setTM",Timestamp.class);
//        Field stcd = tClass.getField("STCD");
//        stcd.setAccessible(true);
//        Field tm = tClass.getField("TM");
//        tm.setAccessible(true);



        for (int i = 0 ,n1 = list.size(); i < n1 ; i++) {
            T t = list.get(i);

//            setSTCD.invoke(t,new Integer(null));
//            setTM.invoke(t,null);
//            stcd.set(t,null);
//            tm.set(t,null);
            //输出过程线
            out.write(myToString(t,2)+"\r\n");

            System.out.println("类型"+":"+t.getClass());
            Field field = t.getClass().getDeclaredField(name);
            field.setAccessible(true);
            //获取数量
            Integer num = (Integer)field.get(t);
            kWrapper = new QueryWrapper<>();
            kWrapper.eq("ID",ID);
            kWrapper.eq("I_NUM",i);
            kWrapper.orderByAsc("J_TIME");

            List<K> listK=kMapper.selectList(kWrapper);




                for (K k : listK){
                    //输出到txt文件中

                    out.write(myToString(k,3)+"\r\n");
                }

        }
    }

}
