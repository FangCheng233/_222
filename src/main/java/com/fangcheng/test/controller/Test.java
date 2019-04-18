package com.fangcheng.test.controller;

import com.fangcheng.test.entity.Application;
import com.fangcheng.test.service.ApplicationService;
import com.fangcheng.test.service.UserService;
import com.mysql.cj.util.TestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.fangcheng.test.entity.User;
import com.fangcheng.test.service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/")
@SessionAttributes("roles")
public class Test {
    @Autowired
    UserService userService;
    @Autowired
    ApplicationService applicationService;
//    public static Test test;
    public static void main(String[] args) {
        Application application = new Application();
        Test test = new Test();
        test.getid();
    }
    public void saveApp(Application application){
        application.setApplicationNumber("sasdada");
        application.setUserId("12345");
        application.setSchoolYear("asda");
        application.setPovertyLevel("adsad");
        application.setYearlyIncome(123);
        application.setPopulationSize(4);
        application.setPerCapitaIncome(1);
        application.setSchoolYear("asdad");
        application.setAddress("asda");
        application.setPostalAddress("asdada");
        application.setAddressee("asdad");
        application.setContactNumber("SSSS");
        application.setEmeergencyContact("asdada");
        application.setEmeergencyContactNumber("SSSS");
        application.setReasonsForApplication("asdada");
        System.out.println(application);
//        applicationService.save(application);
    }
    public void get(){
       applicationService.findByApplicationNumber("asdadad");
        System.out.println(applicationService.findByApplicationNumber("asdadad"));
    }
    public void getid(){
        userService.findAllUsers();
        System.out.println(userService.findAllUsers());
    }
}
