package com.fangcheng.test.controller;

import com.fangcheng.test.entity.Application;
import com.fangcheng.test.entity.TableApproval;
import com.fangcheng.test.entity.TableClass;
import com.fangcheng.test.entity.User;
import com.fangcheng.test.service.ApplicationService;
import com.fangcheng.test.service.TableApprovalService;
import com.fangcheng.test.service.TableClassService;
import com.fangcheng.test.service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/*
 * @ProjectName: _222
 * @Package: com.fangcheng.test.controller
 * @ClassName: OldInformationSelect
 * @Description: java类作用描述
 * @Author: FangCheng
 * @CreateDate: 2019/4/29 11:55
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/4/29 11:55
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */

    @Controller
    @RequestMapping("/")
    public class OldInformationSelect {
        @Autowired
        UserService userService;
        @Autowired
        ApplicationService applicationService;
        @Autowired
        TableClassService tableClassService;
        @Autowired
        TableApprovalService tableApprovalService;

/*
     * @method  getStudentApplicationList
     * @description 获取当前登陆用户历史申请记录
     * @date: 2019/4/17 19:44
     * @author: Fangcheng
    [model, pageStart, pageSize]
     * @return java.lang.String
     */

    @ResponseBody
    @RequestMapping(value = { "/getStudentApplicationListOld" }, method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public String getStudentApplicationListOld(ModelMap model, @Valid Integer pageStart, Integer pageSize, String select,String schoolYear, HttpServletResponse response) {
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        List<Application> applications = applicationService.findByUserId(getPrincipal());
        List<Application> applicationList = new ArrayList<>();
        for(Application application :applications){
            if(application.getStatusNodes().equals(4))
                applicationList.add(application);
        }
        return applicationlistToJson(applications,pageStart,pageSize);
    }
/*
     * @method  getCounsellorApplicationList
     * @description 辅导员查询管理范围内的学生的申请信息
     * @date: 2019/4/17 19:43
     * @author: Fangcheng
    [model, pageStart, pageSize]
     * @return java.lang.String
     */

    @ResponseBody
    @RequestMapping(value = { "/getCounsellorApplicationListOld" }, method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public String getCounsellorApplicationListOld(ModelMap model,@Valid Integer pageStart,Integer pageSize,String select,String schoolYear,HttpServletResponse response) {

        response.setHeader("Content-type", "text/html;charset=UTF-8");
        List<Application> applications = applicationService.findByStatusNodes(1);
        List<Application> applicationList = new ArrayList<>();
        for(Application application :applications){
            try{
                User user = userService.findByUserId(application.getUserId());
                TableClass tableClass = tableClassService.findByClassId(user.getUserClass());
                if(tableClass.getTeacherId().equals(getPrincipal()))
                    applicationList.add(application);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        if(select!=null&&!"".equals(select)){
            return applicationlistToJson(dealWithSelect(dealWithSchoolYear(applications,schoolYear),select,schoolYear),pageStart,pageSize);
        }
        if(schoolYear!=null||!"".equals(schoolYear)){
            return applicationlistToJson(dealWithSchoolYear(applications,schoolYear),pageStart,pageSize);
        }
        return applicationlistToJson(applicationList,pageStart,pageSize);
    }
/*
     * @method  getCollegeApplicationList
     * @description 院系管理员查询本院系下的所有申请 根据管理员所属系统查找
     * @date: 2019/4/17 19:41
     * @author: Fangcheng
    [model, pageStart, pageSize]
     * @return java.lang.String
     */
    @RequestMapping(value = { "/getCollegeApplicationListOld" }, method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getCollegeApplicationListOld(ModelMap model,@Valid Integer pageStart,Integer pageSize,String select,String schoolYear,HttpServletResponse response) {
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        List<Application> applications = applicationService.findByStatusNodes(2);
        List<Application> applicationList = new ArrayList<>();
        User userCollege = userService.findByUserId(getPrincipal());
        for(Application application :applications){
            try {
                User user = userService.findByUserId(application.getUserId());
                if(user.getUserCollege().equals(userCollege.getUserCollege()))
                    applicationList.add(application);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if(select!=null&&!"".equals(select)){
            return applicationlistToJson(dealWithSelect(dealWithSchoolYear(applications,schoolYear),select,schoolYear),pageStart,pageSize);
        }
        if(schoolYear!=null||!"".equals(schoolYear)){
            return applicationlistToJson(dealWithSchoolYear(applications,schoolYear),pageStart,pageSize);
        }
        return applicationlistToJson(applicationList,pageStart,pageSize);
    }
/*
     * @method  getAllApplicationList
     * @description
     * @date: 2019/4/17 19:40
     * @author: Fangcheng
    [model, pageStart, pageSize]
     * @return java.lang.String
     */

    @ResponseBody
    @RequestMapping(value = { "/getAllApplicationListOld" }, method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public String getAllApplicationListOld(ModelMap model, @Valid Integer pageStart, Integer pageSize,String select,String schoolYear, HttpServletResponse response) {
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        List<Application> applications = applicationService.findByStatusNodes(4);
        if(select!=null&&!"".equals(select)){
            return applicationlistToJson(dealWithSelect(dealWithSchoolYear(applications,schoolYear),select,schoolYear),pageStart,pageSize);
        }
        if(schoolYear!=null&&!"".equals(schoolYear)){
            return applicationlistToJson(dealWithSchoolYear(applications,schoolYear),pageStart,pageSize);
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
            System.out.println(application.getSystemAudit());
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
    private List<Application> dealWithSelect(List<Application> applicationList,String select,String schoolyYear){
        List<Application> applicationList1 = new ArrayList<>();
        User user;
        try{
            for(Application application:applicationList){
                if(!"".equals(schoolyYear)&&schoolyYear!=null){
                    if(!schoolyYear.equals(application.getSchoolYear()))
                    {
                        continue;
                    }
                }
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
    private List<Application> dealWithSchoolYear(List<Application> applicationList,String schoolyYear){
            List<Application> applicationList1 = new ArrayList<>();
            try{
                for(Application application:applicationList){
                    if(!"".equals(schoolyYear)&&schoolyYear!=null){
                        if(!schoolyYear.equals(application.getSchoolYear()))
                        {
                            continue;
                        }
                        applicationList1.add(application);
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
            }
            return applicationList1;
        }
        @ResponseBody
        @RequestMapping(value = { "/getTableApproval" }, method = RequestMethod.GET,produces = "application/json;charset=utf-8")
        public String getTableApproval(ModelMap model, @Valid String applicationNumber,HttpServletResponse response) {
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            List<TableApproval> tableApprovalList = tableApprovalService.findByApplicationNumber(applicationNumber);
            Integer pageStart = 1;
            Integer pageSize = 10;
            return approvallistToJson(tableApprovalList,pageStart,pageSize);
        }

        private String approvallistToJson(List<TableApproval> tableApprovalList, Integer pageStart, Integer pageSize) {
            ArrayList arrayList = new ArrayList();
            JSONObject jsonObject1 = new JSONObject();
            int index = (pageStart -1)*pageSize;//开始位置
            int end = 0;
            if(tableApprovalList.size()-index>pageSize){
                end = index+pageSize;
            }else {
                end = tableApprovalList.size();
            }
            jsonObject1.accumulate("code",0);
            jsonObject1.accumulate("msg", "");
            jsonObject1.accumulate("count", tableApprovalList.size());
            TableApproval tableApproval;
            for (int i = index;i < end;i++) {
                JSONObject jsonObject = new JSONObject();
                tableApproval = tableApprovalList.get(i);
                // 向jsonObject添加属性对
                jsonObject.accumulate("applicationNumber", tableApproval.getApplicationNumber());//申请编号
                jsonObject.accumulate("processNode", tableApproval.getProcessNode());//学院
                jsonObject.accumulate("approvalStatus",tableApproval.getApprovalStatus());//专业
                jsonObject.accumulate("userName", tableApproval.getUserName());//姓名
                jsonObject.accumulate("remarks",tableApproval.getRemarks());//
                jsonObject.accumulate("time", tableApproval.getTime());//当前节点
                jsonObject.accumulate("id", tableApproval.getId());//审批人
                arrayList.add(jsonObject);
            }
            jsonObject1.accumulate("data", arrayList);
            return jsonObject1.toString();
        }


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
    private String getGroupId(){
        String userId = getPrincipal();
        User user = userService.findByUserId(userId);
        return user.getGroupId();
    }
    private String getUserName(){
        String userId = getPrincipal();
        User user = userService.findByUserId(userId);
        return user.getUserName();
    }
    private String selectRange(){
        if(getGroupId()=="学生"){
            return "";
        }
        if(getGroupId()=="辅导员"){
            return "getCounsellorApplicationList";
        }
        if(getGroupId()=="院系办公室"){
            return "getCollegeApplicationList";
        }
        if(getGroupId()=="学工部"){
            return "getAllApplicationList";
        }
        return "";
    }
}
