package cn.hhu;

import cn.hhu.Bean.*;
import cn.hhu.mapper.*;
import cn.hhu.service.Bean.*;
import cn.hhu.service.ST_PPTN_RService;
import cn.hhu.service.ST_RSVR_RService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.time.*;
import java.util.*;

@SpringBootTest
class HhuApplicationTests {
    @Autowired
    ST_PPTN_RService sprs;
    @Autowired
    ST_GATE_RMapper st_gate_rMapper;
    @Autowired
    ST_RIVER_RMapper st;

    @Autowired
    OutputMapper outputMapper;

    @Autowired
    ST_ZQRL_BMapper st_zqrl_bMapper;

    @Autowired
    ST_WAS_RMapper st_was_rMapper;
    @Autowired
    CA_PDMapper ca_pdMapper;
    @Autowired
    ST_RSVR_RService st_rsvr_rService;
    @Autowired
    ST_PPTN_RService st_pptn_rService;
    @Test
    public void getCA(){
        List<CA_PD> ca_pd = ca_pdMapper.selectList(null);
        System.out.println(ca_pd);
    }
    @Test
    void contextLoads() {

        System.out.println(outputMapper.selectList(new QueryWrapper<>()));
    }
    @Test
    void  a(){
        Class a = CE_TANDT.class;
        Method[] methods = a.getMethods();
        System.out.println(Arrays.toString(methods));
    }
    @Test
    void b(){
        ST_RIVER_R st_river_r1 = new ST_RIVER_R();
        st_river_r1.setSTCD(44455);
        st.insert(st_river_r1);
    }
    //插入WAS
    @Test void c(){
//        {
//            name:"门楼",
//                    STCD:"41814900"
//        },
//        {
//            name: "平山",
//                    STCD: "41814488"
//        },
//        {
//            name:"留架",
//                    STCD: "41814288"
//        },
//        {
//            name:"王屋",
//                    STCD: "41814100"
//        }
        int STCD = 41814100;
        QueryWrapper<ST_GATE_R> wrapper = new QueryWrapper<>();
        wrapper.select("STCD","TM","GTQ");
        wrapper.eq("STCD",STCD);
        List<ST_GATE_R> l = st_gate_rMapper.selectList(wrapper);
        Random r = new Random();
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        //初始时间
        //LocalDateTime time = LocalDateTime.of(2006, 6, 16, 10, 15, 32);
        //long seconds = time.toEpochSecond(ZoneOffset.of("+8"));

        ST_WAS_R o= new ST_WAS_R();
        ST_GATE_R data=null;
        o.setSTCD(STCD);
        for (int i = 0, n=l.size(); i < n ; i++) {
            data = l.get(i);
            double v1 = r.nextDouble()*11;
            double v2 = r.nextDouble()*11;
//            double q1 = r.nextDouble()*10+3;
//            int hours = r.nextInt(24*15)+24;
//            seconds+=hours*3600;
            double v11 = Double.valueOf(decimalFormat.format(v1));
            double v21 = Double.valueOf(decimalFormat.format(v2));

            o.setTM(data.getTM());
            o.setTGTQ(data.getGTQ());
            o.setDWZ(new BigDecimal(v11));
            o.setUPZ(new BigDecimal(v21));
            st_was_rMapper.insert(o);
            //o.setBGTM();
        }
    }
    //插入数据
    @Test void D(){

        int STCD = 41816401;
        Random r = new Random();
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
//        //初始时间
//
        LocalDateTime time = LocalDateTime.of(2006, 6, 16, 10, 15, 32);
       long seconds = time.toEpochSecond(ZoneOffset.of("+8"));

        ST_ZQRL_B o = new ST_ZQRL_B();
        o.setSTCD(STCD);
        for (int i = 0; i < 200 ; i++) {

            double v = r.nextDouble()*10;
            double q1 = r.nextDouble()*10+3;
            int hours = r.nextInt(24*15)+24;
            seconds+=hours*3600;
            double z = Double.valueOf(decimalFormat.format(v));
            double q = Double.valueOf(decimalFormat.format(q1));
            o.setQ(q);
            o.setZ(z);
            o.setPTNO(Double.valueOf(i));
            o.setBGTM(new Timestamp(seconds*1000));
            st_zqrl_bMapper.insert(o);
            //o.setBGTM();
        }
    }

    @Test
    void test2(){
        System.out.println(LocalDate.now());
        System.out.println(LocalDateTime.now());
        System.out.println(LocalTime.now());
        LocalDateTime time = LocalDateTime.of(2006, 6, 16, 10, 15, 32);
        //转时间戳
        long l = time.toInstant(ZoneOffset.of("+8")).toEpochMilli();


        System.out.println(l);
        //时间戳转时间
        Instant instant = Instant.ofEpochMilli(l);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant,ZoneId.systemDefault());
        System.out.println(localDateTime);

        //转换为秒
        long l1 = localDateTime.toEpochSecond(ZoneOffset.of("+8"));
        long epochSecond = instant.getEpochSecond();
        System.out.println(l1+":"+epochSecond);
        System.out.println(new Date());
//        new Date(LocalDateTime.now().toString());


        System.out.println(new java.sql.Date(655464646L));

    }
    @Test
    void test6(){
        System.out.println(sprs.getAllSTCDAndSTNM());

    }
    @Test void test7(){
        List<ST_PPTN_R> rainInfo = st_pptn_rService.getRainInfo("41837100", LocalDateTime.of(2006, 8, 12, 18, 0), LocalDateTime.of(2010, 8, 12, 18, 0));
        List<ST_RSVR_R> rsvrInfo = st_rsvr_rService.getRsvrInfo("41810088", LocalDateTime.of(2006, 8, 12, 18, 0), LocalDateTime.of(2010, 8, 12, 18, 0));
        int n= rainInfo.size();
        int m = rsvrInfo.size();
        System.out.println("开始了");
        List<JSONObject> list =new LinkedList<>();

        int i=0,j= 0;
            for (; (i< n)&&(j<m); ) {
                JSONObject data = new JSONObject();
                ST_PPTN_R temp1=rainInfo.get(i);
                ST_RSVR_R temp2=rsvrInfo.get(j);
                Date tm1=temp1.getTM();
                Date tm2=temp2.getTM();
                if(tm1.compareTo(tm2)==0){

                    data.put("TM",tm1);
                    data.put("DYP",temp1.getDYP());
                    data.put("RZ",temp2.getRZ());
                    data.put("OTQ",temp2.getOTQ());
                    i++;
                    j++;
                }else if(tm1.compareTo(tm2)>0){

                    data.put("TM",tm2);
                    data.put("DYP",null);
                    data.put("RZ",temp2.getRZ());
                    data.put("OTQ",temp2.getOTQ());
                    j++;
                }else {
                    System.out.println(tm1);
                    data.put("TM",tm1);
                    data.put("DYP",temp1.getDYP());
                    data.put("RZ",null);
                    data.put("OTQ",null);
                    i++;
                }
                list.add(data);
            }
            if(i<n){
                for(;i<n;i++){
                    JSONObject data = new JSONObject();

                    data.put("TM",rainInfo.get(i).getTM());
                    data.put("DYP",rainInfo.get(i).getDYP());
                    data.put("RZ",null);
                    data.put("OTQ",null);
                    list.add(data);
                }
            }
            else{
                for(;j<m;j++){

                    JSONObject data = new JSONObject();
                    data.put("TM",rsvrInfo.get(j).getTM());
                    data.put("DYP",null);
                    data.put("RZ",rsvrInfo.get(j).getRZ());
                    data.put("OTQ",rsvrInfo.get(j).getOTQ());
                    i++;
                    list.add(data);
                }
            }
        System.out.println(list);


    }
    @Test
    void test8(){
        System.out.println(" ".hashCode()+":"+" ".hashCode());
        System.out.println(" " == "");
        System.out.println('.'=='.');
        System.out.println(" "+'.');
        byte a= 1;
        System.out.println(new Byte(a));
        System.out.println('a'^'a');
        "aa".charAt(0);
    }



}
