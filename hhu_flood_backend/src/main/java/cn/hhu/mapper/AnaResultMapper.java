package cn.hhu.mapper;

import cn.hhu.Bean.Mod_Fruit;
import cn.hhu.Bean.Mod_MidResult;
import cn.hhu.Bean.Para_Calplan;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Gyk
 */
@Mapper
@Repository
public interface AnaResultMapper {

    List<Para_Calplan> findAll();

    List<Para_Calplan> findAutoCal();

    Para_Calplan findById(String id);

    List<Mod_Fruit> findByStatus12(String id, int startTime, int endTime);
    List<Mod_MidResult> findByStatus3(String id, int startTime, int endTime, String calRegCD);

    void updateData(String id, String flowtm, double data1, double data2, int status);

    /**
     * 若flowtm不为空。则删除某一条数据
     */
    void deleteDataBy2(String id,String flowtm);
    void deleteData1(String id);
    void deleteData2(String id);

    void save(String c, String c0, BigDecimal p1, BigDecimal p2, BigDecimal p3, BigDecimal p4, BigDecimal p5, BigDecimal p6, BigDecimal p7, BigDecimal p8, BigDecimal p9, BigDecimal p10, BigDecimal p11, BigDecimal p12, BigDecimal p13, BigDecimal p14, BigDecimal p15, BigDecimal p16, BigDecimal p17, BigDecimal p18);
    void saveAs(String id,String c,String c0_id, String c0,String man,int on,String m_date, BigDecimal p1, BigDecimal p2, BigDecimal p3, BigDecimal p4, BigDecimal p5, BigDecimal p6, BigDecimal p7, BigDecimal p8, BigDecimal p9, BigDecimal p10, BigDecimal p11, BigDecimal p12, BigDecimal p13, BigDecimal p14, BigDecimal p15, BigDecimal p16, BigDecimal p17, BigDecimal p18);
    void setOnline(String c);
    void del(String c,String c0);

    @Insert("insert into forcastflood.PARA_MODST(TM,STINF,WORKER,ORDINF)"+"values(#{TM},'1','Maker','来自于自动运行设置命令')")
    public Integer insert0(String TM);
}
