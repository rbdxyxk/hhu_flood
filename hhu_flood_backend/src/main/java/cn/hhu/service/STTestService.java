package cn.hhu.service;

import cn.hhu.Bean.STTest;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author tlj
 */
public interface STTestService extends IService<STTest> {

//     default List<STTest> getInfosByName(String name){
//         System.out.println("是否集成"+(getBaseMapper() instanceof STTestMapper));
//         return  getBaseMapper().selectList(new QueryWrapper<STTest>().eq("STNM",name));
//    }
List<STTest> getInfosByName(String name);
}
