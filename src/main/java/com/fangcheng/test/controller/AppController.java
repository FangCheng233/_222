package com.fangcheng.test.controller;

import com.fangcheng.test.entity.Application;
import com.fangcheng.test.entity.TableAuthor;
import com.fangcheng.test.entity.User;
import com.fangcheng.test.service.ApplicationService;
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
import org.springframework.ui.Model;
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
    MessageSource messageSource;

	@Autowired
    PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;
	
	@Autowired
    AuthenticationTrustResolver authenticationTrustResolver;


	/**
	 * @method  listUsers
	 * @description 返回所有的用户信息
	 * @date: 2019/4/1 19:14
	 * @author: Fangcheng
	[model]
	 * @return java.lang.String
	 */
	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	public String listUsers(ModelMap model) {
	    List<User> users = userService.findAllUsers();
		model.addAttribute("users", users);
		model.addAttribute("loggedinuser", getPrincipal());
		return "main";
	}
	//获取用户的信息
	@RequestMapping(value = { "/userInfo" }, method = RequestMethod.GET)
	public String userInfo(ModelMap model,@Valid String params) {
		model.addAttribute("params", params);
		return "userList";
	}
	@RequestMapping(value = { "/application" }, method = RequestMethod.GET)
	public String application(ModelMap model) {
		model.addAttribute("loggedinuser", getPrincipal());
		return "student/application";
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
		model.addAttribute("loggedinuser", getPrincipal());
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
		model.addAttribute("loggedinuser", getPrincipal());
		//return "success";
		return "registrationsuccess";
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
        Application application = new Application();
        model.addAttribute("application", application);
        model.addAttribute("edit", false);
        model.addAttribute("loggedinuser", getPrincipal());
        return "student/application";
    }
    /**
     * @method  saveUser
     * @description 获取页面提交的参数创建新用户
     * @date: 2019/4/1 19:13
     * @author: Fangcheng
    [user, result, model]
     * @return java.lang.String
     */
    public static void main(String[]args){
    	AppController appController = new AppController();

	}

    @RequestMapping(value = { "/addapplication" }, method = RequestMethod.POST)
	public String addApplication(Model model,
								@RequestParam String yearlyIncome,
								@RequestParam String populationSize,
								@RequestParam String perCapitaIncome,
								@RequestParam String naturalDisaster,
								@RequestParam String unexpectedAccident,
								@RequestParam String membershipSituation,
								@RequestParam String unemploymentSituation,
								@RequestParam String fundedSituation,
								@RequestParam String otherSituation,
								@RequestParam String address,
								@RequestParam String postalAddress,
								@RequestParam String postNumber,
								@RequestParam String addressee,
								@RequestParam String contactNumber,
								@RequestParam String emergencyContact,
								@RequestParam String emergencyContactNumber,
								@RequestParam String povertyLevel,
								@RequestParam String reasonsForApplication,
											   BindingResult result) {

//		System.out.println("shop.name="+yearlyIncome+" contact="+populationSize+" phone="+povertyLevel+povertyLevel+address);
		Application application = applicationService.findByApplicationNumber("asdadad");
        return "registrationsuccess";
    }


	@RequestMapping(value = { "/edit-user-{userId}" }, method = RequestMethod.GET)
	public String editUser(@PathVariable String userId, ModelMap model) {
		User user = userService.findByUserId(userId);
		model.addAttribute("user", user);
		model.addAttribute("edit", true);
		model.addAttribute("loggedinuser", getPrincipal());
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
		System.out.println(user);
		userService.updateUserData(user);
		model.addAttribute("success", "User " + user.getUserId()  + " updated successfully");
		model.addAttribute("loggedinuser", getPrincipal());
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
		model.addAttribute("loggedinuser", getPrincipal());
		return "accessDenied";
	}

	/**
	 * This method handles login GET requests.
	 * If users is already logged-in and tries to goto login page again, will be redirected to list page.
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		if (isCurrentAuthenticationAnonymous()) {
			return "login";
	    } else {
	    	return "redirect:/list";
	    }
	}

	/**
	 * This method handles logout requests.
	 * Toggle the handlers if you are RememberMe functionality is useless in your app.
	 */
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
	 * This method returns the principal[user-name] of logged-in user.
	 */
	private String getPrincipal(){
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails)principal).getUsername();
		} else {
			userName = principal.toString();
		}
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