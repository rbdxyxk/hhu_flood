package cn.hhu.service;



import cn.hhu.Bean.Road;
import cn.hhu.mapper.CruiseMapper;
import cn.hhu.utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

/**
 * @author Gyk
 */
@Service
public class CruiseService {

    @Autowired
    private CruiseMapper cruiseMapper;

    public int addRoad(Road road){

        if (road == null){
            throw new IllegalArgumentException("参数为空");
        }

        // 补充信息
        road.setUid(1);
        road.setExpired(0);

        return cruiseMapper.insertRoad(road);
    }

    public List<Road> findRoads(int uid, int offset, int limit){

        List<Road> roads = cruiseMapper.selectRoads(uid, (offset - 1) * limit, limit);
        if (roads != null){
            for (Road road : roads) {
                // 读取txt文件中的坐标数据
                System.out.println(road.getInfo_data());
                String info_data = FileUploadUtil.txt2String(new File(road.getInfo_data()));
                road.setInfo_data(info_data);
            }
        }

        return roads;
    }

    public int findRows(int uid){
        return cruiseMapper.selectRoadRows(uid);
    }

}
