package cn.hhu.mapper;



import cn.hhu.Bean.Road;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Gyk
 */
@Mapper
public interface CruiseMapper {

    int insertRoad(Road road);

    List<Road> selectRoads(int uid, int offset, int limit);

    int selectRoadRows(int uid);

}
