package com.liuss.model.controller.sys;

import com.liuss.model.controller.BaseController;
import com.liuss.model.entity.sys.Role;
import com.liuss.model.service.sys.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class RoleController extends BaseController {
    @Autowired
    private RoleService roleService;
    @RequestMapping("/rolemanage")
    public String roleManage(){
        return "sys/rolemanage";
    }
    @RequestMapping("/getRoles")
    @ResponseBody
    public Map<String,Object>getRoles(@RequestParam("start")int start,@RequestParam("limit")int limit){
        Map<String,Object> result=new HashMap<>();
        result.put("success",true);
        result.put("totalCount",roleService.findRoleCount());
        result.put("data",roleService.findRolesByStartAndCount(start,limit));
        return result;
    }
    @RequestMapping("/saveRole")
    @ResponseBody
    public Map<String,Object>saveRole(Role role){
        Map<String,Object>result=new HashMap<>();
        try {
            int r=roleService.saveRole(role);
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
    @RequestMapping("/deleteRole")
    @ResponseBody
    public Map<String,Object> deleteModule(@RequestParam("ids")String ids) {
        Map<String,Object>result=new HashMap<>();
        try {
            String[]idarr=ids.split(",");
            for (int i=0;i<idarr.length;i++){
                int id=Integer.parseInt(idarr[i]);
                roleService.deleteRole(id);
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
