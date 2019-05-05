package com.fangcheng.test.controller;

import com.fangcheng.test.entity.*;
import com.fangcheng.test.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.*;


@Controller
@RequestMapping("/")
public class AppController {

	@Autowired
	UserService userService;

	@Autowired
	ApplicationService applicationService;
	
	@Autowired
	TableAuthorService tableAuthorService;

	@Autowired
	TableApprovalService tableApprovalService;
	
	@Autowired
    MessageSource messageSource;

	@Autowired
    PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;
	
	@Autowired
    AuthenticationTrustResolver authenticationTrustResolver;

	@Autowired
	TableClassService tableClassService;

	@Autowired
	UserFamilyService userFamilyService;
	/**
	 * @method  loginPage
	 * @description 登陆控制及页面跳转
	 * @date: 2019/4/16 15:13
	 * @author: Fangcheng
	[]
	 * @return java.lang.String
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		if (isCurrentAuthenticationAnonymous()) {
			return "login";
		} else {
			return "redirect:/main";
		}
	}
	@RequestMapping(value = { "/", "/main" }, method = RequestMethod.GET)
	public String main(ModelMap model) {
		List<Application> applications = applicationService.findByStatusNodes(3);
		List<Application> applications1 = applicationService.findByStatusNodes(4);
		List<Application> applicationslist = applicationService.findAllApplication();
		model.addAttribute("loggedinuser", getUserName());
		model.addAttribute("pending",applications.size());
		model.addAttribute("solved",applications1.size());
		model.addAttribute("count",applicationslist.size());
		return "main";
	}
	/**
	 * @method  userList
	 * @description 前端请求返回到用户列表界面
	 * @date: 2019/4/16 15:14
	 * @author: Fangcheng
	[model, params]
	 * @return java.lang.String
	 */
	@RequestMapping(value = { "/userList" }, method = RequestMethod.GET)
	public String userList(ModelMap model,@Valid String params) {
		model.addAttribute("params", params);
		if(params.equals("getStudentList")){
			model.addAttribute("getStudentList",true);
		}
		if(params.equals("getCounsellorList")){
			model.addAttribute("getCounsellorList",true);
		}
		if(params.equals("getCollegeList")){
			model.addAttribute("getCollegeList",true);
		}
		if(params.equals("getAdminList")){
			model.addAttribute("getAdminList",true);
		}
		return "userList";
	}
	@RequestMapping(value = { "/test" }, method = RequestMethod.GET)
	public String test(ModelMap model) {
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("edit", false);
		model.addAttribute("loggedinuser", getUserName());
		return "counsellor/adadaas";
	}
	/**
	 * @method  userIno
	 * @description 根据前端的请求跳转到用户信息界面
	 * @date: 2019/4/16 15:14
	 * @author: Fangcheng
	[model]
	 * @return java.lang.String
	 */
	@RequestMapping(value = { "/userInfo" }, method = RequestMethod.GET)
	public String userInfo(ModelMap model) {
		try {
			User user = userService.findByUserId(getPrincipal());
			model.addAttribute("userInfo",user);
		}catch (Exception e){
			e.printStackTrace();
		}
		model.addAttribute("edit", true);
		return "user-edit";
	}
	/**
	 * @method  newUser
	 * @description 添加用户
	 * @date: 2019/4/1 19:13
	 * @author: Fangcheng
	[model]
	 * @return java.lang.String
	 */
	@RequestMapping(value = { "/newuser" }, method = RequestMethod.GET)
	public String newUser(ModelMap model) {
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("edit", false);
		model.addAttribute("loggedinuser", getUserName());
		return "admin/registration";
	}
	/**
	 * @method  saveUser
	 * @description 获取页面提交的参数创建新用户
	 * @date: 2019/4/1 19:13
	 * @author: Fangcheng
	[user, result, model]
	 * @return java.lang.String
	 */
	@RequestMapping(value = { "/newuser" }, method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> saveUser(@Valid User user, BindingResult result,
                           ModelMap model,HttpServletResponse response) {
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		Map<String,Object> map = new HashMap<String, Object>();
		if (result.hasErrors()) {
			map.put("error","信息不正确");
			return map;
		}
		if(!userService.isUserIdUnique(user.getUserId())){
			FieldError userIdError =new FieldError("user","userId",messageSource.getMessage("non.unique.userId", new String[]{user.getUserId()}, Locale.getDefault()));
		    result.addError(userIdError);
			map.put("error","学号/工号重复");
			return map;
		}
		try{
			user.setUserName(unicode(user.getUserName()));
			user.setGroupId(unicode(user.getGroupId()));
			user.setUserCollege(unicode(user.getUserCollege()));
			user.setUserMajor(unicode(user.getUserMajor()));
			user.setUserSex(unicode(user.getUserSex()));
			user.setBasePlaceA(unicode(user.getBasePlaceA()));
			user.setBasePlaceP(unicode(user.getBasePlaceP()));
			user.setBasePlaceC(unicode(user.getBasePlaceC()));
			user.setPoliticalOutlook(unicode(user.getPoliticalOutlook()));
			user.setIdType(unicode(user.getIdType()));
			user.setNation(unicode(user.getNation()));
			userService.saveUser(user);
		}catch (Exception e){
			e.printStackTrace();
		}
		model.addAttribute("success", "User " + user.getUserId() + " "+ user.getGroupId() + " registered successfully");
		model.addAttribute("loggedinuser", getUserName());
		map.put("success","添加成功");
		return map;
	}
	@RequestMapping(value = { "/addStudent" }, method = RequestMethod.GET)
	public String addStudent(ModelMap model) {
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("getStudentList",true);
		model.addAttribute("minYear",getYearly()-3);
		model.addAttribute("maxYear",getYearly());
		model.addAttribute("date",getdate());
		return "admin/user-add";
	}
	@RequestMapping(value = { "/addCounsellor" }, method = RequestMethod.GET)
	public String addCounsellor(ModelMap model,HttpServletResponse response) {
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("getCounsellorList",true);
		model.addAttribute("date",getdate());
		return "admin/user-add";
	}
	@RequestMapping(value = { "/addCollege" }, method = RequestMethod.GET)
	public String addCollege(ModelMap model) {
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("getCollegeList",true);
		model.addAttribute("date",getdate());
		return "admin/user-add";
	}
	@RequestMapping(value = { "/addAdmin" }, method = RequestMethod.GET)
	public String addAdmin(ModelMap model) {
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("getAdminList",true);
		model.addAttribute("date",getdate());
		return "admin/user-add";
	}
/*	@RequestMapping(value = { "/edit-user-{userId}" }, method = RequestMethod.GET)
	public String editUser(@PathVariable String userId, ModelMap model) {
		User user = userService.findByUserId(userId);
		model.addAttribute("user", user);
		model.addAttribute("edit", true);
		model.addAttribute("loggedinuser", getUserName());
		return "admin/registration";
	}*/
	/**
	 * @method  updateUser
	 * @description 修改用户的信息
	 * @date: 2019/4/1 19:16
	 * @author: Fangcheng
	[user, result, model, userId]
	 * @return java.lang.String
	 */
	@RequestMapping(value = { "/edit-user" }, method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> updateUser(@RequestBody  String[] value
										 ,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response) {
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		Map<String,Object> map = new HashMap<String, Object>();
		User user = userService.findByUserId(getPrincipal());
		user.setPhoneNumber(value[0]);
		user.setQQ(value[1]);
		user.setUserEmail(value[2]);
		user.setPostalNumber(value[3]);
		user.setPostalAddress(value[4]);
		user.setUserSecurity(value[5]);
		user.setSecurityAnwser(value[6]);
		try{
			userService.updateUserData(user);
		}catch (Exception e){
			e.printStackTrace();
			map.put("error",getPrincipal() + "修改失败");
			return map;
		}
		map.put("resultString",getPrincipal() + "修改成功");
		return map;
	}
	@RequestMapping(value = { "/editRole" }, method = RequestMethod.GET)
	public String editUserRole(@Valid String userId, ModelMap model) {
		User user = userService.findByUserId(userId);
		model.addAttribute("user", user);
		return "edit-Role";
	}
	/**
	 * @method  saveUser
	 * @description 获取页面提交的参数创建新用户
	 * @date: 2019/4/1 19:13
	 * @author: Fangcheng
	[user, result, model]
	 * @return java.lang.String
	 */
	@RequestMapping(value = { "/editRole" }, method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> editRole(@Valid User user, BindingResult result,
									   ModelMap model,HttpServletResponse response) {
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		Map<String,Object> map = new HashMap<String, Object>();
		try{
			userService.updateUserRole(user);
		}catch (Exception e){
			e.printStackTrace();
		}
		map.put("success","修改成功");
		return map;
	}
    /**
     * @method  approval
     * @description 获取前端参数后台对数据进行筛选
     * @date: 2019/4/19 15:12
     * @author: Fangcheng
    [model, params]
     * @return java.lang.String
     */
	@RequestMapping(value = { "/approval" }, method = RequestMethod.GET)
	public String approval(ModelMap model,@Valid String params) {
			model.addAttribute("params", params);
			model.addAttribute("schoolYearly",getSchoolYear());
			System.out.println(getSchoolYear());
		return "approval";
	}
	@RequestMapping(value = { "/applicationRecord" }, method = RequestMethod.GET)
	public String applicationRecord(ModelMap model,@Valid String params) {
		model.addAttribute("params", params);
		model.addAttribute("yearly",getYearly());
		return "applicationRecord";
	}
	@RequestMapping(value = { "/addfamily" }, method = RequestMethod.GET)
	public String newfamily(ModelMap model) {
		model.addAttribute("yearly",getYearly());
		return "student/add-family";
	}
	@RequestMapping(value = { "/addfamily" }, method = RequestMethod.POST)
	public void addfamily(@Valid ModelMap modelMap,UserFamily userFamily,
		HttpServletRequest request,HttpServletResponse response) {
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		List<UserFamily> userFamilyList = userFamilyService.findByUserId(getPrincipal());
		userFamily.setUserId(getPrincipal());
		try {
			userFamilyService.save(userFamily);
		}catch (Exception e){
			e.printStackTrace();
			modelMap.addAttribute("errror", "222");
		}
	}
	@RequestMapping(value = { "/deletefamily" }, method = RequestMethod.GET)
	public void deletefamily(@Valid Integer id,HttpServletRequest request) {
		try {
			userFamilyService.delete(id);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * @method  newApplication
	 * @description 添加新的申请
	 * @date: 2019/4/13 13:03
	 * @author: Fangcheng
	[model]
	 * @return java.lang.String
	 */
    @RequestMapping(value = { "/addapplication" }, method = RequestMethod.GET)
    public String newApplication(ModelMap model) {
    	User user = userService.findByUserId(getPrincipal());
        model.addAttribute("user",user);
        return "student/application";
    }
	/**
	 * @method  newApplication
	 * @description 添加新的申请
	 * @date: 2019/4/13 13:03
	 * @author: Fangcheng
	[model]
	 * @return java.lang.String
	 */
	@RequestMapping(value = { "/viewapplication" }, method = RequestMethod.GET)
	public String viewapplication(ModelMap model,String userId,String applicationNumber) {
		User user = userService.findByUserId(userId);
		Application application = applicationService.findByApplicationNumber(applicationNumber);
		model.addAttribute("user",user);
		model.addAttribute("application",application);
		return "viewApplication";
	}
	@RequestMapping(value = { "/findpassword" }, method = RequestMethod.GET)
	public String findpassword(ModelMap model,String userId) {
		User user = userService.findByUserId(userId);
		model.addAttribute("user",user);
		return "alterpwd";
	}
	@RequestMapping(value = { "/alterpwd" }, method = RequestMethod.GET)
	public String alterpassword(ModelMap model,String userId) {
		User user = userService.findByUserId(userId);
		model.addAttribute("user",user);
		return "findpwd";
	}
	@RequestMapping(value = { "/alterpwd" }, method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> alterpwd(@RequestBody String[] anwser) {
		Map<String,Object> map = new HashMap<String, Object>();
		User user = userService.findByUserId(getPrincipal());
		if(user.getSecurityAnwser().length()>0){
			try {
				if(anwser[1].equals(user.getSecurityAnwser())){
					map.put("success",true);
				}
			}catch (Exception e){
				e.printStackTrace();
			}
			return map;
		}
		user.setUserSecurity(anwser[0]);
		user.setSecurityAnwser(anwser[1]);
		try {
			userService.alterUserSecurityQuestion(user);
		}catch (Exception e){
			e.printStackTrace();
		}
		map.put("success","修改成功");
		return map;
	}
	/**
	 * @method  addApplication
	 * @description 获取贫困申请界面数据，记录该申请
	 * @date: 2019/4/16 15:11
	 * @author: Fangcheng
	[modelMap, applicationNumber, yearlyIncome, populationSize, perCapitaIncome, naturalDisaster, unexpectedAccident, membershipSituation, unemploymentSituation, fundedSituation, otherSituation, address, postalAddress, postNumber, addressee, contactNumber, emergencyContact, emergencyContactNumber, povertyLevel, reasonsForApplication]
	 * @return java.lang.String
	 */
    @RequestMapping(value = { "/addApplication" }, method = RequestMethod.POST)
	public void addApplication(@Valid Application application, BindingResult result,
								 ModelMap model,HttpServletResponse response) {
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		application.setApplicationNumber(getApplicationNumber());
		application.setUserId(getPrincipal());
		application.setSchoolYear(getSchoolYear());
		application.setApprovalStatus("提交申请");
		application.setProcessNode("学生");
		application.setStatusNodes(1);
		TableApproval tableApproval = new TableApproval();
		tableApproval.setApplicationNumber(getApplicationNumber());
		tableApproval.setUserName(getUserName());
		tableApproval.setApprovalStatus("提交申请");
		tableApproval.setProcessNode("学生");
		tableApproval.setRemarks("");
		tableApproval.setTime(getTime());
		tableApproval.setId(getPrincipal()+getSchoolYear()+"01");
		try {
			applicationService.save(application);
			tableApprovalService.save(tableApproval);
		}catch (Exception e){
			e.printStackTrace();
			model.addAttribute("errror", "222");
		}
    }

	/**
	 * @method  alterApplication
	 * @description 修改申请状态，流程审批
	 * @date: 2019/4/21 15:09
	 * @author: Fangcheng
	[applicationNumber]
	 * @return java.lang.String
	 * 状态节点 1 代表学生  2 代表辅导员 3 代表院系  4 代表学工部审批结束 归档
	 */
	@RequestMapping(value = { "/alterApplicationPass" }, method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> alterApplicationPass(@RequestBody String[] applicationNumberList) {
		Map<String,Object> map = new HashMap<String, Object>();
		List<String> listsuccess = new ArrayList<>();
		for(String str:applicationNumberList){
			Application application = applicationService.findByApplicationNumber(str);
			application.setApprovalStatus(getApprovalStatus());
			application.setProcessNode(getGroupId());
			application.setStatusNodes(application.getStatusNodes()+1);
			TableApproval tableApproval = new TableApproval();
			List<TableApproval> tableApprovalList = tableApprovalService.findByApplicationNumber(getPrincipal()+getSchoolYear());
			tableApproval.setApplicationNumber(str);
			tableApproval.setUserName(getUserName());
			tableApproval.setApprovalStatus(getApprovalStatus());
			tableApproval.setProcessNode(getGroupId());
			tableApproval.setRemarks("");
			tableApproval.setTime(getTime());
			tableApproval.setId(str+getSchoolYear()+tableApprovalList.size());
			try {
				applicationService.alterApplication(application);
				tableApprovalService.save(tableApproval);
			}catch (Exception e){
				e.printStackTrace();
			}
		}
	map.put("success","删除成功");
	return map;
	}

	/**
	 * @method  alterApplicationRefuse
	 * @description 申请被驳回，改变状态节点，申请重新开始
	 * @date: 2019/4/23 20:44
	 * @author: Fangcheng
	[applicationNumberList]
	 * @return java.lang.String
	 * 	状态节点 1 代表学生  2 代表辅导员 3 代表院系  4 代表学工部
	 */
	@RequestMapping(value = { "/alterApplicationRefuse" }, method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> alterApplicationRefuse(@RequestBody String[] applicationNumberList) {
		Map<String,Object> map = new HashMap<String, Object>();
		List<String> listsuccess = new ArrayList<>();
		List<String> listerror = new ArrayList<>();
		for(String str:applicationNumberList){
			Application application = applicationService.findByApplicationNumber(str);
			application.setApprovalStatus("驳回");
			application.setProcessNode("学生");//当前审批节点
			application.setStatusNodes(1);
			TableApproval tableApproval = new TableApproval();
			List<TableApproval> tableApprovalList = tableApprovalService.findByApplicationNumber(getPrincipal()+getSchoolYear());
			tableApproval.setApplicationNumber(getApplicationNumber());
			tableApproval.setUserName(getUserName());
			tableApproval.setApprovalStatus("驳回");
			tableApproval.setProcessNode(getGroupId());
			tableApproval.setRemarks("");
			tableApproval.setTime(getTime());
			tableApproval.setId(getPrincipal()+getSchoolYear()+tableApprovalList.size());
			try {
				applicationService.alterApplication(application);
				tableApprovalService.save(tableApproval);
			}catch (Exception e){
				e.printStackTrace();
				listerror.add(str);
			}
			listsuccess.add(str);
		}
		map.put("success",listsuccess);
		map.put("error",listerror);
		return map;
	}


	/**
	 * This method will delete an user by it's UserID value.
	 */
	@RequestMapping(value = { "/delete-user" }, method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> deleteUser(@RequestBody String userId,ModelMap model,
						   HttpServletRequest request,HttpServletResponse response) {
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			userService.deleteUserByUserId(userId);
		}catch (Exception e) {
			e.printStackTrace();
			map.put("resultString",userId + "删除失败");
			return map;
		}
		map.put("resultString",userId + "删除成功");
		return map;
	}
	@RequestMapping(value = { "/remove-user" }, method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> removeUser(@RequestBody String[] userIdList,ModelMap model,HttpServletResponse response) {
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		Map<String,Object> map = new HashMap<String, Object>();
		List<String> listerror = new ArrayList<>();
		List<String> listsuccess = new ArrayList<>();
		for(String userId:userIdList){
			listsuccess.add(userId);
			try {
				userService.deleteUserByUserId(userId);
			}catch (Exception e){
				e.printStackTrace();
				listerror.add(userId);
				listsuccess.remove(userId);
				map.put("error","用户" + listerror + "删除失败");
			}
		}
		map.put("success","用户"+listsuccess+"删除成功");
		return map;
	}
	@RequestMapping(value = { "/delete-application" }, method = RequestMethod.POST)
	public void deleteApplication(@Valid String applicationNumber) {
		try{
			applicationService.deleteApplicationByApplicationNumber(applicationNumber);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * This method will provide UserProfile list to views
	 */
	@ModelAttribute("roles")
	public List<TableAuthor> initializeProfiles() {
		return tableAuthorService.findAll();
	}
	/**
	 * This method handles Access-Denied redirect.
	 */
	@RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
	public String accessDeniedPage(ModelMap model) {
		model.addAttribute("loggedinuser", getUserName());
		return "accessDenied";
	}


	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){    
			//new SecurityContextLogoutHandler().logout(request, response, auth);
			persistentTokenBasedRememberMeServices.logout(request, response, auth);
			SecurityContextHolder.getContext().setAuthentication(null);
		}
		return "redirect:/login?logout";
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
	private String getUserName(){
		String userId = getPrincipal();
		User user = userService.findByUserId(userId);
		return user.getUserName();
	}
	private String getGroupId(){
		String userId = getPrincipal();
		User user = userService.findByUserId(userId);
		return user.getGroupId();
	}
	private String getApprovalStatus(){
		String userId = getPrincipal();
		User user = userService.findByUserId(userId);
		if(user.getGroupId()=="学工部"){
			return "流程结束";
		}
		return "审批通过";
	}
	private String getApplicationNumber(){
		String applicationNumber = getPrincipal() + getSchoolYear();
		return applicationNumber;
	}
	private String subString(String str){
		int index = str.indexOf("\"")+1;
		int end = str.lastIndexOf("\"");
		str = str.substring(index,end);
		return str;
	}
	private String getTime(){
		String time = "";
		Date date = new Date();
		time = date + "";
		return time;
	}
	private String unicode(String str){
		try{
			byte[] strBytes = str.getBytes("ISO-8859-1");
			str = new String(strBytes,"utf-8");//采用utf-8去接string
		}catch (Exception e){
			e.printStackTrace();
		}
		return str;
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
	private Integer getYearly(){
		Calendar date = Calendar.getInstance();
		int year = date.get(Calendar.YEAR);
		int month = date.get(Calendar.MONTH);
		if(month<=8){
			year =  year-1;
		}
		return year;
	}
	private Date getdate() {
		Date date=new java.sql.Date(System.currentTimeMillis());
		System.out.println(date);
		return date;
	}

	/**
	 * This method returns true if users is already authenticated [logged-in], else false.
	 */
	private boolean isCurrentAuthenticationAnonymous() {
	    final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    System.out.println(authentication);
	    return authenticationTrustResolver.isAnonymous(authentication);
	}
}