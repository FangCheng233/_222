package com.fangcheng.test.controller;

import com.fangcheng.test.entity.Application;
import com.fangcheng.test.entity.TableApproval;
import com.fangcheng.test.entity.TableAuthor;
import com.fangcheng.test.entity.User;
import com.fangcheng.test.service.ApplicationService;
import com.fangcheng.test.service.TableApprovalService;
import com.fangcheng.test.service.TableAuthorService;
import com.fangcheng.test.service.UserService;
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
import java.util.List;
import java.util.Locale;


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
		model.addAttribute("loggedinuser", getUserName());
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
	@RequestMapping(value = { "/approval" }, method = RequestMethod.GET)
	public String approval(ModelMap model,@Valid String params) {
			model.addAttribute("params", params);
		return "approval";
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
        model.addAttribute("edit", false);
        model.addAttribute("loggedinuser", getUserName());
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
	public String addApplication(@Valid  ModelMap modelMap,  BindingResult result,@RequestParam String applicationNumber,
	@RequestParam Integer yearlyIncome, @RequestParam Integer populationSize, @RequestParam Integer perCapitaIncome,
	@RequestParam String naturalDisaster, @RequestParam String unexpectedAccident, @RequestParam String membershipSituation,
	@RequestParam String unemploymentSituation, @RequestParam String fundedSituation, @RequestParam String otherSituation,
	@RequestParam String address, @RequestParam String postalAddress, @RequestParam Integer postNumber,
	@RequestParam String addressee, @RequestParam String contactNumber, @RequestParam String emergencyContact,
	@RequestParam String emergencyContactNumber, @RequestParam String povertyLevel, @RequestParam String reasonsForApplication,
	@RequestParam String liabilities,@RequestParam String _csrf) {
    	Application application = new Application();
		application.setApplicationNumber(applicationNumber);
		application.setUserId(getPrincipal());
		application.setSchoolYear("asdasdfs");
		application.setPovertyLevel(povertyLevel);
		application.setYearlyIncome(yearlyIncome);
		application.setPopulationSize(populationSize);
		application.setPerCapitaIncome(perCapitaIncome);
		application.setLiabilities(liabilities);
		application.setNaturalDisaster(naturalDisaster);
		application.setUnexpectedAccident(unexpectedAccident);
		application.setMembershipSituation(membershipSituation);
		application.setUnemploymentSituation(unemploymentSituation);
		application.setFundedSituation(fundedSituation);
		application.setOtherSituation(otherSituation);
		application.setAddress(address);
		application.setPostalAddress(postalAddress);
		application.setPostalCode(postNumber);
		application.setAddressee(addressee);
		application.setContactNumber(contactNumber);
		application.setEmeergencyContact(emergencyContact);
		application.setEmeergencyContactNumber(emergencyContactNumber);
		application.setReasonsForApplication(reasonsForApplication);
		application.setApprovalStatus("adas");
		application.setProcessNode("sdad");
		if(!applicationService.isApplicationNumberUnique(applicationNumber)){
			FieldError userIdError =new FieldError("application","applicationNumber",messageSource.getMessage("non.unique.applicationNumber", new String[]{applicationNumber}, Locale.getDefault()));
			result.addError(userIdError);
			return "student/application";
		}
		/*applicationService.save(application);*/
		TableApproval tableApproval = new TableApproval();
		tableApproval.setApplicationNumber("asdaada");
		tableApproval.setUserName("sadad");
		tableApproval.setApprovalStatus("asdaada");
		tableApproval.setProcessNode("asdaada");
		tableApproval.setRemarks("asdaada");
		System.out.println(tableApproval);
		tableApprovalService.save(tableApproval);
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
	 * This method will delete an user by it's UserID value.
	 */
	@RequestMapping(value = { "/delete-user" }, method = RequestMethod.GET)
	public String deleteUser(@Valid String userId) {
		userService.deleteUserByUserId(userId);
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
}