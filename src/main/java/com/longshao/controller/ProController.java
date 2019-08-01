package com.longshao.controller;

import com.longshao.Test.DicomToJpg;
import com.longshao.dto.Result;
import com.longshao.service.ProService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/pro")
public class ProController {
    @Autowired
    private ProService proService;

    @RequestMapping(value = "login", produces = "application/json;charset:utf-8")
    @ResponseBody
    public Result login(@RequestBody Map map, HttpSession session) {/*String name,String password*/
        Result result = new Result();
        String name = map.get("name").toString();
        String password = map.get("password").toString();
        List list = null;
        try {
            proService.login(name, password, result,session);
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setContent("登录失败，失败原因" + e.getMessage());
        }
        return result;
    }
    @ResponseBody
    @RequestMapping("register")
    public Result register(String name, String password){
        Result result = new Result();
        try {
            proService.addUser(name,password,result);
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setContent("注册失败，失败原因" + e.getMessage());
        }
        return result;
    }

    @RequestMapping("dicomToJpg")
    public void getJpg(HttpSession session){
        ServletContext servletContext = session.getServletContext();
        String path = servletContext.getRealPath("/");
//        DicomToJpg.localConverse(path,"201907010932040012ABD.DCM");
        DicomToJpg.diskConverse("E:\\test\\201907010932040012ABD.DCM");
    }
}
