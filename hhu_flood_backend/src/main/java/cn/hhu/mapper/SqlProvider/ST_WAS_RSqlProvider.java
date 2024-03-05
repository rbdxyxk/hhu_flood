package cn.hhu.mapper.SqlProvider;

import java.time.LocalDateTime;

/**
 * @author tlj
 * @description TODO
 * @date 2022/11/5 17:47
 */

public class ST_WAS_RSqlProvider implements SelectInfoByStcdsAndTMScopeSqlProvider{
    public String SelectWasInfoByStcdsAndTMScopeSql( String[] stcd, LocalDateTime startDatetime, LocalDateTime endDatetime){
        return  this.SelectInfoByStcdsAndTMScopeSql("st_was_r",stcd, startDatetime, endDatetime,"UPZ","DWZ","TGTQ");
    }
}
