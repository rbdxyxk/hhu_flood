package cn.hhu.mapper.SqlProvider;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author tlj
 * @description TODO
 * @date 2022/10/26 13:29
 */

public interface QueryStcdInfo {
    default String queryAllStcdInfo( String table, String likeCondition){
        return new SQL(){
            {

                SELECT("a.STCD","a.STNM");
                FROM("jsycwater.st_stbprp_b a","("+new SQL(){
                    {
                        SELECT_DISTINCT("STCD");
                        FROM("jsycwater.${table}");
                        if(likeCondition!=null&&!"".equals(likeCondition)){
                            WHERE("STCD like "+likeCondition);
                        }
                    }
                }.toString()+")b");
                WHERE("a.STCD=b.STCD");
            }
        }.toString();
    }
}
