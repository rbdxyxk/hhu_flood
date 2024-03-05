package cn.hhu.controller;

import cn.hhu.Bean.DamScope;
import cn.hhu.utils.FileUploadUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.util.List;

/**
 * @author Gyk
 */
@Controller
@RequestMapping(path = "/damScope")
public class DamScopeController {
    // 9                         -- 9 + 8650
    // 9 + 9 + 8650              -- 9+9+8650 + 8650
    // 9 + 9 + 9 + 8650 + 8650   -- 9 + 9 + 9 + 8650 + 8650 + 8650
    private int beforeUnitLines = 9;// 数据之前有多少行无用行（不用读取），从这个位置开始读
    private int unitCount = 8650;// 向后读取多少 -- 为txt文件中某一时刻的结点数
    private String txtPos = "d:/idea-workspace/hhu/outPutFileSLH.txt";// DamScope文件位置

    /**
     *  根据选择的时间点读取溃坝范围的txt文件
     * @param timeInterval 时间点
     * @return List[DamScope]
     */
    @RequestMapping(path = "/readTxt/{timeInterval}", method = RequestMethod.POST)
    @ResponseBody
    public String readScope(@PathVariable int timeInterval) {
        System.out.println("timeInterval = " + timeInterval);
        File file = new File(txtPos);

        int start = timeInterval * beforeUnitLines + (timeInterval - 1) * unitCount;
        int end = start + unitCount;
        System.out.println("start = " + start + " end = " + end);

        List<DamScope> list = FileUploadUtil.txt2StringByTimeInterval(file, start, end);
        System.out.println(list.get(0));
        System.out.println(list.get(8650));
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i));
//        }
//        System.out.println("txt2String = " + txt2String);

        return JSONObject.toJSONString(list);
    }


}
