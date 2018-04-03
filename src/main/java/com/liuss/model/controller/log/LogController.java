package com.liuss.model.controller.log;

import com.liuss.model.controller.BaseController;
import com.liuss.model.model.log.Mod_LoginLog;
import com.liuss.model.service.log.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
public class LogController extends BaseController {
    @Autowired
    private LogService logService;
    @RequestMapping("/loginlogview")
    public String loginlogView(){
        return "log/loginlogview";
    }
    @RequestMapping("/getloginlog")
    @ResponseBody
    public Map<String,Object> getloginlog(@RequestParam("page")int page, @RequestParam("start")int start, @RequestParam("limit")int limit,
                                          @RequestParam(value = "username",required = false)String username,
                                          @RequestParam(value = "loginstarttime",required = false)Date loginstarttime,
                                          @RequestParam(value = "loginendtime",required = false)Date loginendtime){
        Map<String,Object>result=new HashMap<>();
        result.put("success",true);
        result.put("data",logService.getLoginLogByIndexAndSize(start,limit,username,loginstarttime,loginendtime));
        result.put("totalCount",logService.getLoginLogCount(username,loginstarttime,loginendtime));
        return result;
    }
}
