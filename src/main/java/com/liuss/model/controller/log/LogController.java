package com.liuss.model.controller.log;

import com.liuss.model.controller.BaseController;
import com.liuss.model.model.log.Mod_LoginLog;
import com.liuss.model.service.log.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Map<String,Object> getloginlog(@RequestParam("page")int page,@RequestParam("start")int start,@RequestParam("limit")int limit){
        Map<String,Object>result=new HashMap<>();
        result.put("success",true);
        result.put("data",logService.getLoginLogByIndexAndSize(start,limit));
        result.put("totalCount",logService.getLoginLogCount());
        return result;
    }
}
