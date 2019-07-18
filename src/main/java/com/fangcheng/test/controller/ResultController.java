package com.fangcheng.test.controller;

import com.fangcheng.test.entity.*;
import com.fangcheng.test.service.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.math.BigInteger;
import java.util.*;


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
/*@SessionAttributes("roles")*/
public class ResultController {
    @Autowired
    UserService userService;
    @Autowired
    ApplicationService applicationService;
    @Autowired
    TableClassService tableClassService;
    @Autowired
    UserFamilyService userFamilyService;
    @Autowired
    AreasService areasService;
    @Autowired
    ReferenceDataService referenceDataService;

    /**
     * @method  getStudentInfo
     * @description 获取用户的信息
     * @date: 2019/4/9 16:53
     * @author: Fangcheng
    [model]
     * @return java.lang.String
     */
    @ResponseBody
    @RequestMapping(value = { "/getStudentList" }, method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public String getStudentInfo(ModelMap model,@Valid Integer pageStart,Integer pageSize,String select, HttpServletResponse response) {
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        List<User> users = userService.findAllUsers();
        List<User> users1 = new ArrayList<>();
        for(User user :users){
            try{
                if(user.getGroupId().equals("学生"))
                    users1.add(user);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        if(select!=null&&!"".equals(select)){
            return listToJson(dealWithSelectUser(users1,select),pageStart,pageSize);
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
    @RequestMapping(value = { "/getCounsellorList" }, method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public String getCounsellorList(ModelMap model,@Valid Integer pageStart,Integer pageSize,String select, HttpServletResponse response) {
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        List<User> users = userService.findAllUsers();
        List<User> users1 = new ArrayList<>();
        for(User user :users){
            try {
                if(user.getGroupId().equals("辅导员"))
                    users1.add(user);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if(select!=null&&!"".equals(select)){
            return listToJson(dealWithSelectUser(users1,select),pageStart,pageSize);
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
    @RequestMapping(value = { "/getCollegeList" }, method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public String getCollegeList(ModelMap model,@Valid Integer pageStart,Integer pageSize,String select,HttpServletResponse response) {
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        List<User> users = userService.findAllUsers();
        List<User> users1 = new ArrayList<>();
        for(User user :users){
            try {
                if(user.getGroupId().equals("院系办公室"))
                    users1.add(user);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if(select!=null&&!"".equals(select)){
            return listToJson(dealWithSelectUser(users1,select),pageStart,pageSize);
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
    @RequestMapping(value = { "/getAdminList" }, method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public String getAdminList(ModelMap model,@Valid Integer pageStart,Integer pageSize,String select,HttpServletResponse response) {
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        List<User> users = userService.findAllUsers();
        List<User> users1 = new ArrayList<>();
        for(User user :users){
            try {
                if(user.getGroupId().equals("学工部"))
                    users1.add(user);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if(select!=null&&!"".equals(select)){
            return listToJson(dealWithSelectUser(users1,select),pageStart,pageSize);
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
            jsonObject.accumulate("preAuditRemarks",user.getPreAuditRemarks());//贫困信息
            jsonObject.accumulate("waiverAmount",user.getWaiverAmount());//
            jsonObject.accumulate("userEmail", user.getUserEmail());//邮箱
            jsonObject.accumulate("userGrade", user.getUserGrade());//年级
            arrayList.add(jsonObject);
        }
        jsonObject1.accumulate("data", arrayList);
        return jsonObject1.toString();
    }

   /**
     * @method  getStudentApplicationList
     * @description 获取当前登陆用户历史申请记录
     * @date: 2019/4/17 19:44
     * @author: Fangcheng
    [model, pageStart, pageSize]
     * @return java.lang.String
     */
    @ResponseBody
    @RequestMapping(value = { "/getStudentApplicationList" }, method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public String getStudentApplicationList(ModelMap model,@Valid Integer pageStart,Integer pageSize,String select,HttpServletResponse response) {
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        List<Application> applications = applicationService.findByUserId(getPrincipal());
        List<Application> applicationList = new ArrayList<>();
        for(Application application :applications){
            if(application.getStatusNodes().equals(4)){
                applicationList.add(application);
            }
        }
        return applicationlistToJson(applications,pageStart,pageSize);
    }
    /**
     * @method  getStudentApplication
     * @description 学生历史申请记录
     * @date: 2019/5/29 15:38
     * @author: Fangcheng
    [model, pageStart, pageSize, response]
     * @return java.lang.String
     */
    @ResponseBody
    @RequestMapping(value = { "/getStudentApplication" }, method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public String getStudentApplication(ModelMap model,@Valid Integer pageStart,Integer pageSize,HttpServletResponse response) {
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        List<Application> applicationList = new ArrayList<>();
        try{
            Application application = applicationService.findByApplicationNumber(getPrincipal()+getSchoolYear());
            if(application!=null){
                applicationList.add(application);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return applicationlistToJson(applicationList,pageStart,pageSize);
    }
    /**
     * @method  getCounsellorApplicationList
     * @description 辅导员查询管理范围内的学生的申请信息
     * @date: 2019/4/17 19:43
     * @author: Fangcheng
    [model, pageStart, pageSize]
     * @return java.lang.String
     */
    @ResponseBody
    @RequestMapping(value = { "/getCounsellorApplicationList" }, method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public String getCounsellorApplicationList(ModelMap model,@Valid Integer pageStart,Integer pageSize,String select,HttpServletResponse response) {
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        List<Application> applications = applicationService.findByStatusNodes(1);
        List<Application> applicationList = new ArrayList<>();
        for(Application application :applications){
            try{
                User user = userService.findByUserId(application.getUserId());
                TableClass tableClass = tableClassService.findByClassId(user.getUserClass());
                if(tableClass.getTeacherId().equals(getPrincipal())&&(application.getSchoolYear().equals(getSchoolYear()))){
                    String ss = getSchoolYear();
                    applicationList.add(application);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        if(select!=null&&!"".equals(select)){
            return applicationlistToJson(dealWithSelect(applications,select),pageStart,pageSize);
        }
        return applicationlistToJson(applicationList,pageStart,pageSize);
    }
    /**
     * @method  getCollegeApplicationList
     * @description 院系管理员查询本院系下的所有申请 根据管理员所属系统查找
     * @date: 2019/4/17 19:41
     * @author: Fangcheng
    [model, pageStart, pageSize]
     * @return java.lang.String
     */
    @ResponseBody
    @RequestMapping(value = { "/getCollegeApplicationList" }, method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public String getCollegeApplicationList(ModelMap model,@Valid Integer pageStart,Integer pageSize,String select,HttpServletResponse response) {
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        List<Application> applications = applicationService.findByStatusNodes(2);
        List<Application> applicationList = new ArrayList<>();
        User userCollege = userService.findByUserId(getPrincipal());
        for(Application application :applications){
            try {
                User user = userService.findByUserId(application.getUserId());
                if(user.getUserCollege().equals(userCollege.getUserCollege())&&(application.getSchoolYear().equals(getSchoolYear())))
                    applicationList.add(application);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if(select!=null&&!"".equals(select)){
            return applicationlistToJson(dealWithSelect(applications,select),pageStart,pageSize);
        }
        return applicationlistToJson(applicationList,pageStart,pageSize);
    }
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
    public String getAllApplicationList(ModelMap model, @Valid Integer pageStart, Integer pageSize,String select,HttpServletResponse response) {
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        List<Application> applications = applicationService.findByStatusNodes(3);
        List<Application> applicationList = new ArrayList<>();
        for(Application application :applications){
            try {
                if(application.getSchoolYear().equals(getSchoolYear()))
                    applicationList.add(application);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if(select!=null&&!"".equals(select)){
            return applicationlistToJson(dealWithSelect(applications,select),pageStart,pageSize);
        }
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
            jsonObject.accumulate("schoolYear",application.getSchoolYear());//学年
            jsonObject.accumulate("userGrade", user.getUserGrade());//年级
            jsonObject.accumulate("povertyLevel", application.getPovertyLevel());//困难级别
            jsonObject.accumulate("systemAudit",application.getSystemAudit());
            jsonObject.accumulate("systemValue",application.getSystemValue());
            jsonObject.accumulate("remarks",application.getRemarks());
            jsonObject.accumulate("userId", application.getUserId());//学号
            jsonObject.accumulate("userName", user.getUserName());//姓名
            jsonObject.accumulate("reasonsForApplication",application.getReasonsForApplication());//申请理由
            jsonObject.accumulate("processNode", application.getProcessNode());//当前节点
            jsonObject.accumulate("teacherName", tableClass.getTeacherName());//审批人
            jsonObject.accumulate("approvalStatus",application.getApprovalStatus());//审批状态
            arrayList.add(jsonObject);
        }
        jsonObject1.accumulate("data", arrayList);
        return jsonObject1.toString();
    }
    @ResponseBody
    @RequestMapping(value = { "/getUserFamily" }, method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public String getUserFamily(ModelMap model,@Valid HttpServletResponse response) {
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        List<UserFamily> userFamilyList = userFamilyService.findByUserId(getPrincipal());
        return userFamilylistToJson(userFamilyList);
    }
    @ResponseBody
    @RequestMapping(value = { "/viewUserFamily" }, method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public String viewUserFamily(ModelMap model,@Valid HttpServletResponse response,String userId){
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        List<UserFamily> userFamilyList = userFamilyService.findByUserId(userId);
        return userFamilylistToJson(userFamilyList);
    }
    private String userFamilylistToJson(List<UserFamily> userFamilyList) {
        ArrayList arrayList = new ArrayList();
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.accumulate("code",0);
        jsonObject1.accumulate("msg", "");
        jsonObject1.accumulate("count", userFamilyList.size());
        for (UserFamily userFamily:userFamilyList) {
            JSONObject jsonObject = new JSONObject();
            // 向jsonObject添加属性对
            jsonObject.accumulate("id", userFamily.getId());//编号
            jsonObject.accumulate("userName", userFamily.getUserName());//姓名
            jsonObject.accumulate("userAge",userFamily.getUserAge());//年龄
            jsonObject.accumulate("occupation", userFamily.getOccupation());//职业
            jsonObject.accumulate("relationship",userFamily.getRelationship());//关系
            jsonObject.accumulate("health", userFamily.getHealth());//健康状况
            jsonObject.accumulate("annualIncome", userFamily.getAnnualIncome());//年收入
            jsonObject.accumulate("workUnit", userFamily.getWorkUnit());//工作单位
            jsonObject.accumulate("phoneNumber", userFamily.getPhoneNumber());//电话
            arrayList.add(jsonObject);
        }
        jsonObject1.accumulate("data", arrayList);
        return jsonObject1.toString();
    }
    @ResponseBody
    @RequestMapping(value = { "/getAreas" }, method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public String getAreas(ModelMap model,@Valid Integer pageStart, Integer pageSize,String select, HttpServletResponse response){
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        List<Areas> areasList = areasService.findAll();
        if(select!=null&&!"".equals(select)){
            try{
                List<Areas> areasList1 = areasService.findAllCity(select);
                List<Areas> areasList2 = areasService.findAllCounty(select);
                List<Areas> areasList3 = areasService.findAllTown(select);
                if(areasList1!=null){
                    return areaslistToJson(areasList1,pageStart,pageSize);
                }else if(areasList2!=null){
                    return areaslistToJson(areasList2,pageStart,pageSize);
                }else if(areasList3!=null){
                    return areaslistToJson(areasList3,pageStart,pageSize);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return areaslistToJson(areasList,pageStart,pageSize);
    }
    private String areaslistToJson(List<Areas> areasList,Integer pageStart,Integer pageSize) {
        ArrayList arrayList = new ArrayList();
        JSONObject jsonObject1 = new JSONObject();
        int index = (pageStart -1)*pageSize;//开始位置
        int end = 0;
        if(areasList.size()-index>pageSize){
            end = index+pageSize;
        }else {
            end = areasList.size();
        }
        jsonObject1.accumulate("code",0);
        jsonObject1.accumulate("msg", "");
        jsonObject1.accumulate("count", areasList.size());
        Areas areas;
        for (int i = index;i < end;i++) {
            JSONObject jsonObject = new JSONObject();
            areas = areasList.get(i);
            // 向jsonObject添加属性对
            jsonObject.accumulate("id", areas.getId());//编号
            jsonObject.accumulate("provinceName", areas.getProvinceName());//省名
            jsonObject.accumulate("provinceId",areas.getProvinceId());//省
            jsonObject.accumulate("cityName", areas.getCityName());//市
            jsonObject.accumulate("cityId",areas.getCityId());//市
            jsonObject.accumulate("countyName", areas.getCountyName());//县
            jsonObject.accumulate("countyId", areas.getCountyId());//县
            jsonObject.accumulate("poorerAreas", areas.getPoorerAreas());//是否贫困地区
            arrayList.add(jsonObject);
        }
        jsonObject1.accumulate("data", arrayList);
        return jsonObject1.toString();
    }
    @ResponseBody
    @RequestMapping(value = { "/getReferenceData" }, method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public String getReferenceData(ModelMap model,@Valid Integer pageStart, Integer pageSize,HttpServletResponse response){
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        List<ReferenceData> referenceDataList = referenceDataService.findAll();
        return reflistToJson(referenceDataList);
    }
    private String reflistToJson(List<ReferenceData> referenceDataList) {
        ArrayList arrayList = new ArrayList();
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.accumulate("code",0);
        jsonObject1.accumulate("msg", "");
        jsonObject1.accumulate("count", referenceDataList.size());
        for (ReferenceData referenceData:referenceDataList) {
            JSONObject jsonObject = new JSONObject();
            // 向jsonObject添加属性对
            jsonObject.accumulate("schoolYear", referenceData.getSchoolYear());//电话
            jsonObject.accumulate("livingExpenses", referenceData.getLivingExpenses());//编号
            jsonObject.accumulate("tuition1", referenceData.getTuition1());//姓名
            jsonObject.accumulate("tuition2",referenceData.getTuition2());//年龄
            jsonObject.accumulate("tuition3", referenceData.getTuition3());//职业
            jsonObject.accumulate("tuition4",referenceData.getTuition4());//关系
            jsonObject.accumulate("tuition5", referenceData.getTuition5());//健康状况
            jsonObject.accumulate("tuition6", referenceData.getTuition6());//年收入
            jsonObject.accumulate("tuition7", referenceData.getTuition7());//工作单位
            jsonObject.accumulate("tuition8", referenceData.getTuition8());//电话
            jsonObject.accumulate("tuition9", referenceData.getTuition9());//电话
            jsonObject.accumulate("tuition10", referenceData.getTuition10());//电话
            jsonObject.accumulate("tuition11", referenceData.getTuition11());//电话
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
    private String getSchoolYear(){
        String schoolYear = "";
        Calendar date = Calendar.getInstance();
        int year = date.get(Calendar.YEAR);
        int month = date.get(Calendar.MONTH);
        if(8<=month){
            schoolYear =  year + "-" +(year+1);
        }
        if(month<=6){
            schoolYear = (year-1) + "-" + year;
        }
        return schoolYear;
    }
    private List<Application> dealWithSelect(List<Application> applicationList,String select){
        List<Application> applicationList1 = new ArrayList<>();
        User user;
        try{
            for(Application application:applicationList){
                if(application.getUserId().equals(select)){
                    applicationList1.add(application);
                }
                if(userService.findByUserId(application.getUserId()).getUserCollege().equals(select)){
                    applicationList1.add(application);
                }
                if(userService.findByUserId(application.getUserId()).getUserMajor().equals(select)){
                    applicationList1.add(application);
                }
                if(userService.findByUserId(application.getUserId()).getUserClass().equals(select)){
                    applicationList1.add(application);
                }
                if(userService.findByUserId(application.getUserId()).getUserName().equals(select)){
                    applicationList1.add(application);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return applicationList1;
    }
    private List<User> dealWithSelectUser(List<User> userList,String select){
        List<User> userList1 = new ArrayList<>();
        try{
            for(User user:userList){
                if(user.getUserId().equals(select)){
                    userList1.add(user);
                }
                if(user.getUserName().equals(select)){
                    userList1.add(user);
                }
                if(user.getUserClass().equals(select)){
                    userList1.add(user);
                }
                if(user.getUserMajor().equals(select)){
                    userList1.add(user);
                }
                if(user.getUserCollege().equals(select)){
                    userList1.add(user);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return userList1;
    }
    }
