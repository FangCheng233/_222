package com.fangcheng.test.controller;

import com.fangcheng.test.entity.Application;
import com.fangcheng.test.entity.TableApproval;
import com.fangcheng.test.service.ApplicationService;
import com.fangcheng.test.service.UserService;
import com.mysql.cj.util.TestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.fangcheng.test.entity.User;
import com.fangcheng.test.service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
/**
 * @ProjectName: _222
 * @Package: com.fc.test
 * @ClassName: Test
 * @Description: java类作用描述
 * @Author: FangCheng
 * @CreateDate: 2019/3/24 14:28
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/3/24 14:28
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Controller
@RequestMapping("/test")
public class Test {
    @Autowired
    UserService userService;
    @Autowired
    ApplicationService applicationService;

//    public static Test test;
    public static void main(String[] args) {
        Application application = new Application();
        Test test = new Test();
    }

    @RequestMapping(value = { "/testEntity" }, method = RequestMethod.POST)
    public String testEntity(@Valid Application application, BindingResult result,
                           ModelMap model,HttpServletResponse response) {
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        //调用业务层处理
        //return "success";
        System.out.println(application);
        return "registrationsuccess";
    }

    @RequestMapping(value="/fixLeader1", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String fixLeader() {
        return "OK";
    }
    @RequestMapping(value = { "/fixLeader" }, method = RequestMethod.POST)
    public void name(@Valid ModelMap modelMap,
                          @RequestParam String id,
                          HttpServletRequest request, HttpServletResponse response) {
    }
    //得到当前用户所有菜单
    @RequestMapping("/treeMenu")
    @ResponseBody
    public List<User> treeMenu(){
        return  userService.findAllUsers();
    }
}
