package cn.hhu.mapper.SqlProvider;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author tlj
 * @description TODO
 * @date 2022/11/5 17:22
 */

public interface SelectInfoByStcdsAndTMScopeSqlProvider {
    default String SelectInfoByStcdsAndTMScopeSql(String table, String[] stcd, LocalDateTime startDatetime, LocalDateTime endDatetime, String ...args){
        return new SQL(){{
            StringBuilder colums = new StringBuilder("STCD,TM");

            for(String arg : args){
                colums.append(" ,IFNULL(").append(arg).append(",0.0) as ").append(arg);
            }
            SELECT(colums.toString());
            FROM("jsycwater."+table);
            WHERE("tm between #{startDatetime} AND  #{endDatetime}");
            //判断 stcd
            if(stcd.length>0){
                StringBuilder condition= new StringBuilder("STCD IN (");
                for(String st : stcd){
                    condition.append(st).append(",");
                }
                condition.deleteCharAt(condition.length()-1);
                condition.append(")");
                WHERE(condition.toString());
            }
            //ORDER_BY("Stcd","tm");


        }}.toString();
    }
}
