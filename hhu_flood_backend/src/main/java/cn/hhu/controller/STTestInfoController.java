package cn.hhu.controller;

import cn.hhu.Bean.STTest;
import cn.hhu.service.STTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * @author tlj
 */
@RestController
@RequestMapping("STTest")
public class STTestInfoController {
    @Autowired
    STTestService sts;


    @GetMapping("info/{name}")
    public List<STTest> getByName(@PathVariable String name){
        return sts.getInfosByName(name);
    }
}
