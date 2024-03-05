package cn.hhu.controller;



import cn.hhu.Bean.Road;
import cn.hhu.service.CruiseService;
import cn.hhu.utils.FileUploadUtil;
import cn.hhu.utils.FloodUtil;
import cn.hhu.utils.PageBeanUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * @author Gyk
 */
@Controller
@RequestMapping(path = "/cruise")
public class CruiseController {

    @Autowired
    private CruiseService cruiseService;

    @Value("${Hhu.path.upload}")
    private String uploadPath;

    /**
     *  获取的坐标数据存入到txt文件中，数据库存储文件名称。
     */
    @RequestMapping(path = "/addRoad",method = RequestMethod.GET)
    @ResponseBody
    public String addRoad(Road road) throws IOException {

        // 判空
        if (StringUtils.isBlank(road.getInfo_data()) || StringUtils.isBlank(road.getInfo_name())){
            return FloodUtil.getJSONString(1, "保存线路失败");
        }

        // 生成文件名
        String fileName = FloodUtil.generateUUID();
        // 创建文件
        boolean flagCreate = FileUploadUtil.creatTxtFile(uploadPath,fileName);
        //写入数据

        if (flagCreate == true){
            //创建成功，则读入数据
            boolean flagWrite = FileUploadUtil.writeTxtFile(road.getInfo_data());
        }

        //保存数据文件的路径
        road.setInfo_data(FileUploadUtil.getFilenameTemp());

        // 添加路线信息
        int rows = cruiseService.addRoad(road);
        // 处理提示信息
        String msg = null;
        if (rows == 1){
            msg = "保存线路成功";
        }else {
            msg = "保存线路失败";
        }

        return FloodUtil.getJSONString(0, msg);
    }

    /**
     * 分页查询线路信息
     */
    @RequestMapping(path = "/list",method = RequestMethod.POST)
    @ResponseBody
    public String getRoadsList(@RequestParam(name = "curPage",defaultValue = "1") String curPage){

        // 数据类型转换
        int currentPage = Integer.parseInt(curPage);

        // 设置每页显示条数
        int pageSize = 3;
        // 数据总量
        int totalData = cruiseService.findRows(1);

        // 查询数据
        List<Road> roads = cruiseService.findRoads(1, currentPage, pageSize);

        // 构造分页对象
        PageBeanUtil pg = new PageBeanUtil(currentPage,pageSize,totalData);

        // 分页对象存放数据
        pg.setPageData(roads);

        // 返回json格式数据
        return JSON.toJSONString(pg);
    }

}
