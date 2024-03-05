package cn.hhu.mapper.SqlProvider;

import org.apache.ibatis.jdbc.SQL;

import java.time.LocalDateTime;
import java.util.Collections;

/**
 * @author tlj
 * @description TODO
 * @date 2022/10/21 16:12
 */

public class RsvrSqlProvider implements  QueryStcdInfo{
    public String queryRsvRBySTCDAndTm(String[] stcd, LocalDateTime startDatetime, LocalDateTime endDatetime){
        return new SQL(){{
            SELECT("STCD","TM","IFNULL(INQ,0.0) as inq","IFNULL(W,0.0) as w","IFNULL(OTQ,0.0) as otq");
            FROM("jsycwater.ST_RSVR_R");
            WHERE("tm between #{startDatetime} AND  #{endDatetime}");
            //判断 stcd
            if(stcd.length>0){
                StringBuilder condition= new StringBuilder("STCD IN (");
                for(String st : stcd){
                    condition.append(st+",");
                }
                condition.deleteCharAt(condition.length()-1);
                condition.append(")");
                WHERE(condition.toString());
            }
            //ORDER_BY("Stcd","tm");


        }}.toString();
    }
}
