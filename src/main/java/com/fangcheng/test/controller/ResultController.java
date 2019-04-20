package com.fangcheng.test.controller;

import com.fangcheng.test.entity.Application;
import com.fangcheng.test.entity.TableClass;
import com.fangcheng.test.entity.User;
import com.fangcheng.test.service.ApplicationService;
import com.fangcheng.test.service.TableClassService;
import com.fangcheng.test.service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.xml.transform.Result;
import java.util.ArrayList;
import java.util.List;


/**
 * @ProjectName: _222
 * @Package: com.fangcheng.test.controller
 * @ClassName: ResultController
 * @Description: java类作用描述
 * @Author: FangCheng
 * @CreateDate: 2019/4/8 9:54
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/4/8 9:54
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class ResultController {
    @Autowired
    UserService userService;
    @Autowired
    ApplicationService applicationService;
    @Autowired
    TableClassService tableClassService;


    /**
     * @method  getStudentInfo
     * @description 获取用户的信息
     * @date: 2019/4/9 16:53
     * @author: Fangcheng
    [model]
     * @return java.lang.String
     */
    @ResponseBody
    @RequestMapping(value = { "/getStudentList" }, method = RequestMethod.GET,produces = "application/json")
    public String getStudentInfo(ModelMap model,@Valid Integer pageStart,Integer pageSize) {
        List<User> users = userService.findAllUsers();
        List<User> users1 = new ArrayList<>();
        for(User user :users){
            if(user.getGroupId().equals("1"))
            users1.add(user);
        }
        return listToJson(users1,pageStart,pageSize);
    }
    /**
     * @method  getCounsellorList
     * @description 获取辅导员用户的信息
     * @date: 2019/4/9 16:53
     * @author: Fangcheng
    [model]
     * @return java.lang.String
     */
    @ResponseBody
    @RequestMapping(value = { "/getCounsellorList" }, method = RequestMethod.GET,produces = "application/json")
    public String getCounsellorList(ModelMap model,@Valid Integer pageStart,Integer pageSize) {
        List<User> users = userService.findAllUsers();
        List<User> users1 = new ArrayList<>();
        for(User user :users){
            if(user.getGroupId().equals("2"))
                users1.add(user);
        }
        return listToJson(users1,pageStart,pageSize);
    }
    /**
     * @method  getCollegeList
     * @description 获取院系级用户的信息
     * @date: 2019/4/9 16:53
     * @author: Fangcheng
    [model]
     * @return java.lang.String
     */
    @ResponseBody
    @RequestMapping(value = { "/getCollegeList" }, method = RequestMethod.GET,produces = "application/json")
    public String getCollegeList(ModelMap model,@Valid Integer pageStart,Integer pageSize) {
        List<User> users = userService.findAllUsers();
        List<User> users1 = new ArrayList<>();
        for(User user :users){
            if(user.getGroupId().equals("3"))
                users1.add(user);
        }
        return listToJson(users1,pageStart,pageSize);
    }
    /**
     * @method  getAdminList
     * @description 获取管理员级别用户的列表
     * @date: 2019/4/9 16:53
     * @author: Fangcheng
    [model]
     * @return java.lang.String
     */
    @ResponseBody
    @RequestMapping(value = { "/getAdminList" }, method = RequestMethod.GET,produces = "application/json")
    public String getAdminList(ModelMap model,@Valid Integer pageStart,Integer pageSize) {
        List<User> users = userService.findAllUsers();
        List<User> users1 = new ArrayList<>();
        for(User user :users){
            if(user.getGroupId().equals("4"))
                users1.add(user);
        }
        return listToJson(users1,pageStart,pageSize);
    }
    /**
     * @method  listToJson
     * @description 条件查询，分页后将用户信息返回用户列表userList页面
     * @date: 2019/4/14 16:05
     * @author: Fangcheng
    [users, pageStart, pageSize]
     * @return java.lang.String
     */
    private static String listToJson(List<User> users,Integer pageStart,Integer pageSize) {
        ArrayList arrayList = new ArrayList();
        JSONObject jsonObject1 = new JSONObject();
        int index = (pageStart -1)*pageSize;//开始位置
        int end = 0;
        if(users.size()-index>pageSize){
            end = index+pageSize;
        }else {
            end = users.size();
        }
        jsonObject1.accumulate("code",0);
        jsonObject1.accumulate("msg", "");
        jsonObject1.accumulate("count", users.size());
        User user;
        for (int i = index;i < end;i++) {
            JSONObject jsonObject = new JSONObject();
            user = users.get(i);
            // 向jsonObject添加属性对i
/*            jsonObject.accumulate("id", ""+(i+1));//学号i*/
            jsonObject.accumulate("userId", user.getUserId());//学号
            jsonObject.accumulate("userName", user.getUserName());//用户名
            jsonObject.accumulate("groupId",user.getGroupId());//教师/学生/院系/管理员
            jsonObject.accumulate("userSex", user.getUserSex());//性别
            jsonObject.accumulate("userClass", user.getUserClass());//班级
            jsonObject.accumulate("userCollege", user.getUserCollege());//学院
            jsonObject.accumulate("userMajor", user.getUserMajor());//专业
            jsonObject.accumulate("userEmail", user.getUserEmail());//邮箱
            jsonObject.accumulate("userGrade", user.getUserGrade());//年级
            arrayList.add(jsonObject);
        }
        jsonObject1.accumulate("data", arrayList);
        return jsonObject1.toString();
    }

/*
    *//**
     * @method  getStudentApplicationList
     * @description 描述一下方法的作用
     * @date: 2019/4/17 19:44
     * @author: Fangcheng
    [model, pageStart, pageSize]
     * @return java.lang.String
     *//*
    @ResponseBody
    @RequestMapping(value = { "/getStudentApplicationList" }, method = RequestMethod.GET,produces = "application/json")
    public String getStudentApplicationList(ModelMap model,@Valid Integer pageStart,Integer pageSize) {
        List<User> users = userService.findAllUsers();
        List<User> users1 = new ArrayList<>();
        for(User user :users){
            if(user.getGroupId().equals("4"))
                users1.add(user);
        }
        return listToJson(users1,pageStart,pageSize);
    }
    *//**
     * @method  getCounsellorApplicationList
     * @description 描述一下方法的作用
     * @date: 2019/4/17 19:43
     * @author: Fangcheng
    [model, pageStart, pageSize]
     * @return java.lang.String
     *//*
    @ResponseBody
    @RequestMapping(value = { "/getCounsellorApplicationList" }, method = RequestMethod.GET,produces = "application/json")
    public String getCounsellorApplicationList(ModelMap model,@Valid Integer pageStart,Integer pageSize) {
        List<Application> applications = applicationService.findAllApplication();
        List<Application> applicationList = new ArrayList<>();
        for(Application application :applications){
            User user = userService.findByUserId(application.getUserId());
            if(user.getUse().equals("4"))
                applicationList.add(application);
        }
        return applicationlistToJson(applicationList,pageStart,pageSize);
    }*/
    /**
     * @method  getCollegeApplicationList
     * @description 描述一下方法的作用
     * @date: 2019/4/17 19:41
     * @author: Fangcheng
    [model, pageStart, pageSize]
     * @return java.lang.String
     */
/*    @ResponseBody
    @RequestMapping(value = { "/getCollegeApplicationList" }, method = RequestMethod.GET,produces = "application/json")
    public String getCollegeApplicationList(ModelMap model,@Valid Integer pageStart,Integer pageSize) {
        List<Application> applications = applicationService.findAllApplication();
        List<Application> applicationList = new ArrayList<>();
        User userCollege = userService.findByUserId(getPrincipal());
        for(Application application :applications){
            User user = userService.findByUserId(application.getUserId());
            if(user.getUserCollege().equals(userCollege.getUserCollege()))
                applicationList.add(application);
        }
        return applicationlistToJson(applicationList,pageStart,pageSize);
    }*/
    /**
     * @method  getAllApplicationList
     * @description
     * @date: 2019/4/17 19:40
     * @author: Fangcheng
    [model, pageStart, pageSize]
     * @return java.lang.String
     */
    @ResponseBody
    @RequestMapping(value = { "/getAllApplicationList" }, method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public String getAllApplicationList(ModelMap model, @Valid Integer pageStart, Integer pageSize, HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        try {
            request.setCharacterEncoding("UTF-8");
        }catch (Exception e){
            e.printStackTrace();
        }

        List<Application> applications = applicationService.findAllApplication();
/*        List<Application> applications1 = new ArrayList<>();
        for(Application application :applications){
            if(application.getGroupId().equals("4"))
                users1.add(user);
        }*/
        return applicationlistToJson(applications,pageStart,pageSize);
    }
    private String applicationlistToJson(List<Application> applications,Integer pageStart,Integer pageSize) {
        ArrayList arrayList = new ArrayList();
        JSONObject jsonObject1 = new JSONObject();
        int index = (pageStart -1)*pageSize;//开始位置
        int end = 0;
        if(applications.size()-index>pageSize){
            end = index+pageSize;
        }else {
            end = applications.size();
        }
        jsonObject1.accumulate("code",0);
        jsonObject1.accumulate("msg", "");
        jsonObject1.accumulate("count", applications.size());
        Application application;
        for (int i = index;i < end;i++) {
            JSONObject jsonObject = new JSONObject();
            application = applications.get(i);
            User user = userService.findByUserId(application.getUserId());
            TableClass tableClass = tableClassService.findByClassId(user.getUserClass());
            // 向jsonObject添加属性对
            jsonObject.accumulate("applicationNumber", application.getApplicationNumber());//申请编号
            jsonObject.accumulate("userCollege", user.getUserCollege());//学院
            jsonObject.accumulate("userMajor",user.getUserMajor());//专业
            jsonObject.accumulate("userClass", user.getUserClass());//班级
            jsonObject.accumulate("userGrade", user.getUserGrade());//年级
            jsonObject.accumulate("povertyLevel", application.getPovertyLevel());//困难级别
            jsonObject.accumulate("userId", application.getUserId());//学号
            jsonObject.accumulate("userName", user.getUserName());//姓名
            jsonObject.accumulate("processNode", application.getProcessNode());//当前节点
            jsonObject.accumulate("teacherName", tableClass.getTeacherName());//审批人
            jsonObject.accumulate("approvalStatus",application.getApprovalStatus());//审批状态
            arrayList.add(jsonObject);
        }
        jsonObject1.accumulate("data", arrayList);
        return jsonObject1.toString();
    }
    /**
     * @method  getPrincipal
     * @description 返回当前用户的信息
     * @date: 2019/4/14 18:32
     * @author: Fangcheng
    []
     * @return java.lang.String
     */
    private String getPrincipal(){
        String userId = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userId = ((UserDetails)principal).getUsername();
        } else {
            userId = principal.toString();

        }
        return userId;
    }
    @RequestMapping(value = { "/test" }, method = RequestMethod.GET)
    public String test(ModelMap model) {
        return "counsellor/adadaas";
    }

        @RequestMapping(value = "/createShop", method = RequestMethod.POST)

        public @ResponseBody String createShop(@RequestBody String name, String contact,
                                 String phone,
                                 String twitter,
                                String address) {
            System.out.println("shop.name=" + name + " contact=" + contact + " phone=" + phone + twitter + address);
            return "registrationsuccess";
        }
    /**
     * 衬衫秒杀
     */
    @RequestMapping(value = "/killApple", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public String killOrder(HttpServletRequest request, @RequestBody TableClass tableClass){
    return "ss";
    }
    }

