package com.liuss.model.controller.sys;

import com.liuss.model.controller.BaseController;
import com.liuss.model.entity.sys.Menu;
import com.liuss.model.service.sys.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class MenuController extends BaseController {
    @Autowired
    private MenuService menuService;
    @RequestMapping("/menumanage")
    public String menuManage(){
        return "sys/menumanage";
    }
    @RequestMapping("/getMenus")
    @ResponseBody
    public Map<String,Object>getMenus(@RequestParam("moduleid")int moduleId,@RequestParam("start")int start,@RequestParam("limit")int limit)
    {
        Map<String,Object> result=new HashMap<>();
        result.put("success",true);
        result.put("totalCount",menuService.findMenuCountByModuleId(moduleId));
        result.put("data",menuService.findMenusByModuleId(moduleId,start,limit));
        return result;
    }
    @RequestMapping("/saveMenu")
    @ResponseBody
    public Map<String,Object>saveMenu(Menu menu){
        Map<String,Object>result=new HashMap<>();
        try {
            int r=menuService.saveMenu(menu);
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
    @RequestMapping("/deleteMenu")
    @ResponseBody
    public Map<String,Object> deleteModule(@RequestParam("ids")String ids) {
        Map<String,Object>result=new HashMap<>();
        try {
            String[]idarr=ids.split(",");
            for (int i=0;i<idarr.length;i++){
                int id=Integer.parseInt(idarr[i]);
                menuService.deleteMenu(id);
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
