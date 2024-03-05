package cn.hhu.service.impl;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import cn.hhu.Bean.*;
import cn.hhu.repository.*;
import cn.hhu.repository.impl.*;
import cn.hhu.service.ControlDataService;
import cn.hhu.utils.DbUtils;
import cn.hhu.utils.getObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author tlj
 */
public class ControlDataServiceImpl  implements ControlDataService {
    private CA_PDRepository ca_pdRepository = new CA_PDRepositoryImpl();
    private CB_NREADRepository cb_nreadRepository = new CB_NREADRepositoryImpl();
    private CC_CPRepository cc_cpRepository =new CC_CPRepositoryImpl();
    private CD_PLINERepository cd_plineRepository = new CD_PLINERepositoryImpl();
    private CE_TANDTRepository ce_plineRepository = new CE_TANDTRepositoryImpl();
    private CF_GFANDNFRepository cf_gfandnfRepository =new CF_GFANDNFRepositoryImpl();
    private CH_1A_QRepository ch_1A_qRepository =new CH_1A_QRepositoryImpl();
    private  CH_1B_QRepository ch_1B_qRepository = new CH_1B_QRepositoryImpl();
    private  CH_2A_ZRepository ch_2A_zRepository = (CH_2A_ZRepository) new CH_2A_ZRepositoryImpl();
    private CH_2B_ZRepository ch_2B_zRepository = (CH_2B_ZRepository) new CH_2B_ZRepositoryImpl();
    STRSVRRepositoryImpl strsvrRepository = new STRSVRRepositoryImpl();
    private void insertBYST_RSVR_R(List<ST_RSVR_R> l, Connection conn, Integer id, Integer iNum) throws Exception {
        CH_1A_Q ch_1A_q = new CH_1A_Q(id, iNum, l.size() ,
                l.get(0).getINQ()!=null?BigDecimal.valueOf(l.get(0).getINQ()):null,null,null);

        //ch_1A_q插入数据库
        ch_1A_qRepository.insert(ch_1A_q,conn);
//        for(ST_RSVR_R st : l){
//            CH_1B_Q ch_1B_q = new CH_1B_Q();
//        }
        //得到CH_1B_Q插入数据库
        for (int i = 0 ,n = l.size(); i < n; i++) {
            CH_1B_Q ch_1B_q = new CH_1B_Q(id,iNum,i,null,null);
            ST_RSVR_R st = l.get(i);
            if(st.getINQDR()!=null){
                ch_1B_q.setDTQT(BigDecimal.valueOf(st.getINQDR()));
            }
            if(st.getOTQ()!=null){
                ch_1B_q.setQT(BigDecimal.valueOf(st.getOTQ()));
            }
            ch_1B_qRepository.insert(ch_1B_q,conn);
        }

    }
    //插入到具体数据库的方法
    private void insertZ(List<ST_RSVR_R> l,Connection conn,Integer id,Integer iNum) throws Exception {

        Double d;
        CH_2A_Z ch_2A_z = new CH_2A_Z(id,iNum,l.size(),
                (d=l.get(0).getRZ())!=null?BigDecimal.valueOf(d):null,null,null);

        //ch_2A_Z插入数据库
        ch_2A_zRepository.insert(ch_2A_z,conn);

//        for(ST_RSVR_R st : l){
//            CH_1B_Q ch_1B_q = new CH_1B_Q();
//        }
        //得到CH_1B_Q插入数据库
        for (int i = 0 ,n = l.size(); i < n; i++) {
            CH_2B_Z data = new CH_2B_Z(id,iNum,i,null,null);
            ST_RSVR_R st = l.get(i);
            if(st.getBLRZ()!=null){
                data.setZT(BigDecimal.valueOf(st.getBLRZ()));
            }
            if(st.getINQDR()!=null){
                data.setDTZT(BigDecimal.valueOf(st.getINQDR()));
            }
            ch_2B_zRepository.insert(data,conn);
        }

    }
    //插入过程线数据的泛型方法
    private <T> void  insertData(HttpServletRequest req, Connection conn,Integer id,String a[],String name,T t) throws Exception {
        //获取站点选择数据
        //String[] a= req.getParameterValues("checked[]");
        Gson gson = new Gson();
        DateFormat fmt =new SimpleDateFormat("\"yyyy-MM-dd HH\"");

        for (int i = 0; i <a.length ; i++) {
            if("1".equals(a[i])){
                //String o = req.getParameter("checked"+i);
                String o = req.getParameter(name+i);
                JsonObject returnData = new JsonParser().parse(o).getAsJsonObject();
                String ID = returnData.get("ID").toString();
                System.out.println(ID);
                Date TM1 =fmt.parse(returnData.get("TM1").toString());
                Date TM2 = fmt.parse(returnData.get("TM2").toString());
                Method[] methods = t.getClass().getMethods();
                for(Method m : methods){
                    int num = m.getParameterCount();
                    Class<?>[] parameterTypes = m.getParameterTypes();
                    System.out.println(m+"----"+ Arrays.toString(parameterTypes));
                    if(num==3&&parameterTypes[0].equals(String.class)&&parameterTypes[1].equals(Date.class)&&parameterTypes[2].equals(Date.class)){
                        List<ST_RSVR_R> l = (List<ST_RSVR_R>) m.invoke(t,ID, TM1, TM2);
                        System.out.println(l);
                        insertZ(l,conn,id,i);
                        break;
                    }
                }
//                List<ST_RSVR_R> list = repository.getST_RSVR_R(ID, TM1, TM2);
//                insertBYST_RSVR_R(list,conn,id,i);
            }
        }

    }

    public static void main(String[] args) {

        Method[] methods = Object.class.getMethods();
        for(Method m : methods){
            int num = m.getParameterCount();
            Class<?>[] parameterTypes = m.getParameterTypes();
            System.out.println(num+"----"+ Arrays.toString(parameterTypes));
            if(num>0){
                System.out.println(parameterTypes[0].equals(long.class));
            }

        }
    }


    private void insertQData(HttpServletRequest req, Connection conn,Integer id) throws Exception {
        //获取站点选择数据
        String[] a= req.getParameterValues("checked[]");
        Gson gson = new Gson();
        DateFormat fmt =new SimpleDateFormat("\"yyyy-MM-dd HH\"");
        STRSVRRepositoryImpl repository = new STRSVRRepositoryImpl();
        for (int i = 0; i <a.length ; i++) {
            if("1".equals(a[i])){
                String o = req.getParameter("checked"+i);
                JsonObject returnData = new JsonParser().parse(o).getAsJsonObject();
                String ID = returnData.get("ID").toString();
                System.out.println(ID);
                Date TM1 =fmt.parse(returnData.get("TM1").toString());
                Date TM2 = fmt.parse(returnData.get("TM2").toString());
                List<ST_RSVR_R> list = repository.getST_RSVR_R(ID, TM1, TM2);
                insertBYST_RSVR_R(list,conn,id,i);
            }
        }

    }


    @Override
    public int dataSet(HttpServletRequest request)  {
        Connection conn = null;
        int result = 0;

        try {
            conn= DbUtils.getMysqlConnection();
            conn.setAutoCommit(false);
            Integer id=ca_pdRepository.getMaxId()+1;
            CD_PLINE cd = getObjectUtils.getObjectFromRequest(request, CD_PLINE.class);
            cd.setID(id);
            CA_PD ca = getObjectUtils.getObjectFromRequest(request, CA_PD.class);
            ca.setId(id);
            CC_CP cc = getObjectUtils.getObjectFromRequest(request, CC_CP.class);
            cc.setID(id);
            CB_NREAD cb= getObjectUtils.getObjectFromRequest(request, CB_NREAD.class);
            cb.setID(id);
            CE_TANDT ce = getObjectUtils.getObjectFromRequest(request, CE_TANDT.class);
            ce.setID(id);
            CF_GFANDNF cf = getObjectUtils.getObjectFromRequest(request, CF_GFANDNF.class);
            cf.setID(id);
            System.out.println(ca);
            System.out.println(cb);
            System.out.println(cc);
            System.out.println(cd);
            System.out.println(ce);
            System.out.println(cf);
            result+=ca_pdRepository.insertAll(conn,ca);
            result+=cb_nreadRepository.insertAll(conn,cb);
            result+=cc_cpRepository.insertAll(conn,cc);
            result+=cd_plineRepository.insertAll(conn,cd);
            result+=ce_plineRepository.insertAll(conn,ce);
            result+=cf_gfandnfRepository.insertAll(conn,cf);
            //插入流量过程线
            insertQData(request,conn,id);
            insertData(request,conn,id,request.getParameterValues("checkedZ[]"),"checkedZ",strsvrRepository);
            conn.commit();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException throwables) {

                throwables.printStackTrace();
                return -1;
            }
            e.printStackTrace();
            return -1;
        } finally {
            DbUtils.close(null,null,conn);
        }


        return result;
    }
}
