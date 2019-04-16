package com.fangcheng.test.controller;

import com.fangcheng.test.entity.User;
import com.fangcheng.test.service.ApplicationService;
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
     * @description 条件查询，分页后信息返回用户页面
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
        loop:for (int i = index;i < end;i++) {
            JSONObject jsonObject = new JSONObject();
            user = users.get(i);
            // 向jsonObject添加属性对i
            jsonObject.accumulate("id", ""+(i+1));//学号i
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
}

