package com.liuss.model.controller.sys;

import com.liuss.model.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RoleController extends BaseController {
    @RequestMapping("/rolemanage")
    public String roleManage(){
        return "sys/rolemanage";
    }
}
