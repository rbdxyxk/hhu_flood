package cn.hhu.controller;

import cn.hhu.Bean.Output;
import cn.hhu.service.ShowOutputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;
/**
 * @author tlj
 */
@RestController
@RequestMapping("line")
public class getDataLineController {
    private static final Log log = LogFactory.getLog(getDataLineController.class);

    @Autowired
    ShowOutputService showOutputService;

    @RequestMapping("data2")
    //获取数据
    public List<Output> getData2(@RequestParam int TimeInterval,
                           @RequestParam String positions,
                           @RequestParam double startX,
                           @RequestParam double startY,
                           @RequestParam double endX,
                           @RequestParam double endY){
        List<Output> outputs = showOutputService.getOutputs(0, TimeInterval, positions, startX, startY, endX, endY,100,18);
        log.warn(outputs);
        return outputs;
    }

    @GetMapping("getMaxInterval")
    public Integer getMaxInterval(){
        return showOutputService.getMaxInterval(0);

    }

    @PostMapping("oneOutput")
    public Output oneOutput(@RequestParam int TimeInterval,
                          @RequestParam String positions,
                          @RequestParam double x,
                          @RequestParam double y){
        return showOutputService.getOutputByLongitudeAndLatitude(TimeInterval,positions, x ,  y);

    }
}
