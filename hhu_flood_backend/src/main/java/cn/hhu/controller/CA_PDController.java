package cn.hhu.controller;


import cn.hhu.Bean.CA_PD;
import cn.hhu.service.CA_PDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
/**
 * @author tlj
 */
@Controller
@RequestMapping("/CAPD")

public class CA_PDController {
    @Autowired
    CA_PDService ca_pdService;

    @RequestMapping("getAll")
    @ResponseBody
    public List<CA_PD> getAll(){
       return ca_pdService.getAll();
    }
}
