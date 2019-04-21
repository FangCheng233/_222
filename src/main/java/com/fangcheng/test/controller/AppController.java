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
@SessionAttributes("roles")
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
		model.addAttribute("loggedinuser", getUserName());
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
		return "userList";
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
	public String userIno(ModelMap model) {
		return "userInfo";
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
	public String saveUser(@Valid User user, BindingResult result,
                           ModelMap model) {
		if (result.hasErrors()) {
			return "admin/registration";
		}
		if(!userService.isUserIdUnique(user.getUserId())){
			FieldError userIdError =new FieldError("user","userId",messageSource.getMessage("non.unique.userId", new String[]{user.getUserId()}, Locale.getDefault()));
		    result.addError(userIdError);
			return "admin/registration";
		}
		//调用业务层处理
		userService.saveUser(user);
		model.addAttribute("success", "User " + user.getUserId() + " "+ user.getGroupId() + " registered successfully");
		model.addAttribute("loggedinuser", getUserName());
		//return "success";
		return "registrationsuccess";
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
		return "approval";
	}
	@RequestMapping(value = { "/addfamily" }, method = RequestMethod.GET)
	public String newfamily(ModelMap model) {
		return "student/add-family";
	}
	@RequestMapping(value = { "/addfamily" }, method = RequestMethod.POST)
	public String addfamily(@Valid ModelMap modelMap,
		@RequestParam String userName, @RequestParam Integer userAge, @RequestParam String userSex,
		@RequestParam Integer annualIncome, @RequestParam String health, @RequestParam String occupation,
		@RequestParam String phoneNumber,@RequestParam String workUnit, @RequestParam String relationShip,
		HttpServletRequest request,HttpServletResponse response) {
		UserFamily userFamily = new UserFamily();
		List<UserFamily> userFamilyList = userFamilyService.findByUserId(getPrincipal());
		userFamily.setId(getPrincipal()+""+userFamilyList.size());
		userFamily.setUserId(getPrincipal());
		userFamily.setUserName(unicode(userName));
		userFamily.setUserAge(userAge);
		userFamily.setUserSex(unicode(userSex));
		userFamily.setAnnualIncome(annualIncome);
		userFamily.setHealth(unicode(health));
		userFamily.setOccupation(unicode(occupation));
		userFamily.setPhoneNumber(phoneNumber);
		userFamily.setWorkUnit(unicode(workUnit));
		userFamily.setRelationship(unicode(relationShip));
		try {
			userFamilyService.save(userFamily);
		}catch (Exception e){
			e.printStackTrace();
			modelMap.addAttribute("errror", "222");
			return "student/application";
		}
		return "registrationsuccess";
	}

	@RequestMapping(value = { "/deletefamily" }, method = RequestMethod.GET)
	public String deletefamily(@Valid String id,HttpServletRequest request) {
		try {
			userFamilyService.delete(id);
		}catch (Exception e){
			e.printStackTrace();
			return  "";
		}
		return "redirect:/userInfo";
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
        return "student/application";
    }

	/**
	 * @method  addApplication
	 * @description 获取贫困申请界面数据，记录该申请
	 * @date: 2019/4/16 15:11
	 * @author: Fangcheng
	[modelMap, applicationNumber, yearlyIncome, populationSize, perCapitaIncome, naturalDisaster, unexpectedAccident, membershipSituation, unemploymentSituation, fundedSituation, otherSituation, address, postalAddress, postNumber, addressee, contactNumber, emergencyContact, emergencyContactNumber, povertyLevel, reasonsForApplication]
	 * @return java.lang.String
	 */
    @RequestMapping(value = { "/addapplication" }, method = RequestMethod.POST)
	public String addApplication(@Valid ModelMap modelMap,
	@RequestParam Integer yearlyIncome, @RequestParam Integer populationSize, @RequestParam Integer perCapitaIncome,
	@RequestParam String naturalDisaster, @RequestParam String unexpectedAccident, @RequestParam String membershipSituation,
	@RequestParam String unemploymentSituation, @RequestParam String fundedSituation, @RequestParam String otherSituation,
	@RequestParam String address, @RequestParam String postalAddress, @RequestParam Integer postNumber,
	@RequestParam String addressee, @RequestParam String contactNumber, @RequestParam String emergencyContact,
	@RequestParam String emergencyContactNumber, @RequestParam String povertyLevel, @RequestParam String reasonsForApplication,
	@RequestParam String liabilities , HttpServletRequest request,HttpServletResponse response) {
    	Application application = new Application();
    	List<TableApproval> tableApprovalList = tableApprovalService.findByApplicationNumber(getPrincipal()+getSchoolYear());
		application.setApplicationNumber(getPrincipal()+getSchoolYear()+tableApprovalList.size());
		application.setUserId(getPrincipal());
		application.setSchoolYear(getSchoolYear());
		application.setPovertyLevel(unicode(povertyLevel));
		application.setYearlyIncome(yearlyIncome);
		application.setPopulationSize(populationSize);
		application.setPerCapitaIncome(perCapitaIncome);
		application.setLiabilities(unicode(liabilities));
		application.setNaturalDisaster(unicode(naturalDisaster));
		application.setUnexpectedAccident(unicode(unexpectedAccident));
		application.setMembershipSituation(unicode(membershipSituation));
		application.setUnemploymentSituation(unicode(unemploymentSituation));
		application.setFundedSituation(unicode(fundedSituation));
		application.setOtherSituation(unicode(otherSituation));
		application.setAddress(unicode(address));
		application.setPostalAddress(unicode(postalAddress));
		application.setPostalCode(postNumber);
		application.setAddressee(unicode(addressee));
		application.setContactNumber(contactNumber);
		application.setEmeergencyContact(unicode(emergencyContact));
		application.setEmeergencyContactNumber(emergencyContactNumber);
		application.setReasonsForApplication(unicode(reasonsForApplication));
		application.setApprovalStatus("提交申请");
		application.setProcessNode("学生申请");
		TableApproval tableApproval = new TableApproval();
		tableApproval.setApplicationNumber(getPrincipal()+getSchoolYear());
		tableApproval.setUserName(getUserName());
		tableApproval.setApprovalStatus("提交申请");
		tableApproval.setProcessNode("学生");
		tableApproval.setRemarks("");
		tableApproval.setId(getPrincipal()+getSchoolYear()+"01");
		try {
			applicationService.save(application);
			tableApprovalService.save(tableApproval);
		}catch (Exception e){
			e.printStackTrace();
			modelMap.addAttribute("errror", "222");
			return "student/application";
		}
		return "registrationsuccess";
    }

	@RequestMapping(value = { "/edit-user-{userId}" }, method = RequestMethod.GET)
	public String editUser(@PathVariable String userId, ModelMap model) {
		User user = userService.findByUserId(userId);
		model.addAttribute("user", user);
		model.addAttribute("edit", true);
		model.addAttribute("loggedinuser", getUserName());
		return "admin/registration";
	}

	/**
	 * @method  updateUser
	 * @description 修改用户的信息
	 * @date: 2019/4/1 19:16
	 * @author: Fangcheng
	[user, result, model, userId]
	 * @return java.lang.String
	 */
	@RequestMapping(value = { "/edit-user-{userId}" }, method = RequestMethod.POST)
	public String updateUser(@Valid User user, BindingResult result,
                             ModelMap model, @PathVariable String userId) {
		if (result.hasErrors()) {
			return "registration";
		}
		userService.updateUserData(user);
		model.addAttribute("success", "User " + user.getUserId()  + " updated successfully");
		model.addAttribute("loggedinuser", getUserName());
		return "registrationsuccess";
	}
	/**
	 * @method  alterApplication
	 * @description 修改申请状态，流程审批
	 * @date: 2019/4/21 15:09
	 * @author: Fangcheng
	[applicationNumber]
	 * @return java.lang.String
	 */
	@RequestMapping(value = { "/alterApplication" }, method = RequestMethod.POST)
	@ResponseBody
	public String alterApplication(@RequestParam List<String> applicationNumber) {

		return "";
	}


	/**
	 * This method will delete an user by it's UserID value.
	 */
	@RequestMapping(value = { "/delete-user" }, method = RequestMethod.GET)
	public String deleteUser(@Valid String userId) {
		userService.deleteUserByUserId(userId);
		return "redirect:/userInfo";
	}
	@RequestMapping(value = { "/delete-application" }, method = RequestMethod.GET)
	public String deleteApplication(@Valid String applicationNumber) {
		try{
			applicationService.deleteApplicationByApplicationNumber(applicationNumber);
		}catch (Exception e){
			e.printStackTrace();
			return "redirect:/userInfo";
		}
		return "redirect:/userInfo";
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
		String userName = null;
		User user = userService.findByUserId(userId);
		userName = user.getUserName();
		return userName;
	}
	/**
	 * This method returns true if users is already authenticated [logged-in], else false.
	 */
	private boolean isCurrentAuthenticationAnonymous() {
	    final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    return authenticationTrustResolver.isAnonymous(authentication);
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
}