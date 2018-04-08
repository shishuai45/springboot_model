package com.liuss.model.controller.sys;

import com.liuss.model.controller.BaseController;
import com.liuss.model.entity.sys.Module;
import com.liuss.model.service.sys.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ModuleController extends BaseController {
    @Autowired
    private ModuleService moduleService;
    @RequestMapping("/modulemanage")
    public String moduleManage(){
        return "sys/modulemanage";
    }
    @RequestMapping("/getmodules")
    @ResponseBody
    public Map<String,Object> getModule(@RequestParam(value = "start")int start,@RequestParam(value = "limit")int limit){
        Map<String,Object> result=new HashMap<>();
        result.put("success",true);
        result.put("totalCount",moduleService.findModulesCount());
        result.put("data",moduleService.findModulesByStartAndCount(start,limit));
        return result;
    }
    @RequestMapping("/saveModule")
    @ResponseBody
    public Map<String,Object>saveModule(Module module){
        Map<String,Object>result=new HashMap<>();
        try {
            int r= moduleService.saveModule(module);
            if(r>0){
                result.put("success",true);
                result.put("msg","保存成功！");
            }
            else {
                result.put("success",false);
                result.put("msg","保存失败！");
            }
        }
        catch (Exception e){
            e.printStackTrace();
            result.put("success",false);
            result.put("msg","保存过程发生异常，保存失败！");
        }
        return result;
    }
    @RequestMapping("/deleteModule")
    @ResponseBody
    public Map<String,Object> deleteModule(@RequestParam("ids")String ids) {
        Map<String,Object>result=new HashMap<>();
        try {
            String[]idarr=ids.split(",");
            for (int i=0;i<idarr.length;i++){
                int id=Integer.parseInt(idarr[i]);
                moduleService.deleteModule(id);
            }
            result.put("success",true);
            result.put("msg","删除成功！");
        }
        catch (Exception e){
            e.printStackTrace();
            result.put("success",false);
            result.put("msg","删除过程发生异常，删除失败！");
        }
        return result;
    }
}
