package cn.hhu.controller;

import cn.hhu.service.ControlParamterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author tlj
 */
@Controller
@RequestMapping("/parameters")
public class ContrlParametersController {

    @Autowired
    ControlParamterService cps;
    @PostMapping("addAll")
    @ResponseBody
    public String addAll(@RequestBody String s) {
        try {
            cps.insertAllParameters(s);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "true";

    }
    @GetMapping("CAPD/{ID}")
    @ResponseBody
    public String selectAllById(@PathVariable("ID") Integer ID){
        return cps.getAllParameterById(ID);
    }
}
