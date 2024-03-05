package cn.hhu.mapper;

import cn.hhu.Bean.DisResFlood;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface DisResFloodMapper {

    List<DisResFlood> getone();

    List<DisResFlood> gettwo(String id, int startTime, int endTime);

    List<DisResFlood> getthree(String id, int startTime, int endTime, String calRegCD);

}
