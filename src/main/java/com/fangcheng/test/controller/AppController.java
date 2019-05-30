package com.fangcheng.test.controller;

import com.fangcheng.test.entity.*;
import com.fangcheng.test.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
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
	@Autowired
	ReferenceDataService referenceDataService;
	@Autowired
	AreasService areasService;
	@Autowired
    FileService fileService;
	@Autowired
	private PasswordEncoder passwordEncoder;
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
	/**
	 * @method  loginPage
	 * @description 登陆控制及页面跳转
	 * @date: 2019/4/16 15:13
	 * @author: Fangcheng
	[]
	 * @return java.lang.String
	 */
	@RequestMapping(value = "/verify", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,String> verify(@RequestBody  String[] value) {
		Map<String,String> map = new HashMap<>();
		User user = userService.findByUserId(getPrincipal());
		if (user.getPassword().equals(passwordEncoder.encode(value[0]))) {
			map.put("success","验证通过");
			return map;
		} else {
			map.put("error","密码错误");
			return map;
		}
	}
	/**
	 * @method  logoutPage
	 * @description 登出操作。跳转至首页
	 * @date: 2019/5/29 15:26
	 * @author: Fangcheng
	[request, response]
	 * @return java.lang.String
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
	@RequestMapping(value="/logoutVerify", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,String> logoutVerify (HttpServletRequest request, HttpServletResponse response){
		Map<String,String> map = new HashMap<>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){
			//new SecurityContextLogoutHandler().logout(request, response, auth);
			persistentTokenBasedRememberMeServices.logout(request, response, auth);
			SecurityContextHolder.getContext().setAuthentication(null);
		}
		return map;
	}
	@RequestMapping(value = { "/", "/main" }, method = RequestMethod.GET)
	public String main(ModelMap model) {
		List<Application> applications = applicationService.findByStatusNodes(3);
		List<Application> applications1 = applicationService.findByStatusNodes(4);
		List<Application> applicationslist = applicationService.findAllApplication();
		User user = userService.findByUserId(getPrincipal());
		model.addAttribute("loggedinuser", getUserName());
		model.addAttribute("user", user);
		model.addAttribute("pending",applications.size());//计算该用户待审批量
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
			user.setPassword(user.getPassword());
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
    @ResponseBody
	public Map<String,Object> addfamily(@Valid ModelMap modelMap,UserFamily userFamily,
		HttpServletRequest request,HttpServletResponse response) {
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		Map<String,Object> map = new HashMap<String, Object>();
		userFamily.setUserId(getPrincipal());
		userFamily.setId(getPrincipal()+getTime());
		userFamily.setUserName(unicode(userFamily.getUserName()));
		userFamily.setRelationship(unicode(userFamily.getRelationship()));
		userFamily.setWorkUnit(unicode(userFamily.getWorkUnit()));
		userFamily.setOccupation(unicode(userFamily.getOccupation()));
		userFamily.setHealth(unicode(userFamily.getHealth()));
		userFamily.setUserSex(unicode(userFamily.getUserSex()));
		try {
			userFamilyService.save(userFamily);
			map.put("success","添加成功");
		}catch (Exception e){
			e.printStackTrace();
            map.put("error","添加失败");
		}
		return map;
	}
	@RequestMapping(value = { "/deletefamily" }, method = RequestMethod.POST)
	@ResponseBody
	public void deletefamily(@RequestBody String id,HttpServletRequest request) {
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
	/**
	 * @method  findpassword
	 * @description 跳转至验证密保问题的界面
	 * @date: 2019/5/5 11:10
	 * @author: Fangcheng
	[model, userId]
	 * @return java.lang.String
	 */
	@RequestMapping(value = { "/getUserSecurity" }, method = RequestMethod.GET)
	public String getUserSecurity(ModelMap model,String userId) {
		User user = userService.findByUserId(userId);
		model.addAttribute("user",user);
		return "userSecurity";
	}

	@RequestMapping(value = { "/getApprovalList" }, method = RequestMethod.GET)
	public String getApprovalList(ModelMap model,String applicationNumber) {
		model.addAttribute("applicationNumber",applicationNumber);
		return "approvalprocess";
	}
    @RequestMapping(value = { "/findUser" }, method = RequestMethod.GET)
    @ResponseBody
    public Map<String,String> findUser(ModelMap model,String userId) {
        Map<String,String> map = new HashMap<String, String>();
        try {
            User user = userService.findByUserId(userId);
            if(user==null){
                map.put("error","该用户不存在，请检查学号/工号是否正确");
                return map;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        map.put("success","true");
        return map;
    }
    @RequestMapping(value = { "/alterpwd" }, method = RequestMethod.GET)
    public String alterpassword(ModelMap model,String userId) {
        User user = userService.findByUserId(userId);
        model.addAttribute("user",user);
        return "alterpwd";
    }
	@RequestMapping(value = { "/alterPassword" }, method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> alterPassword(@RequestBody String[] anwser) {
		Map<String,Object> map = new HashMap<String, Object>();
		User user = userService.findByUserId(anwser[1]);
		try {
		    user.setPassword(anwser[0]);
		    userService.alterUserPassword(user);
		    map.put("success","修改成功");
		    return map;
		}catch (Exception e){
		    e.printStackTrace();
		    map.put("error","密码修改失败");
		}
		return map;
	}
	@RequestMapping(value = { "/compareAnwser" }, method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> alterpwd(@RequestBody String[] anwser) {
		Map<String,Object> map = new HashMap<String, Object>();
		User user = userService.findByUserId(anwser[2]);
		if(user.getSecurityAnwser().length()>0){
			try {
				if(anwser[1].equals(user.getSecurityAnwser())){
					map.put("success",true);
					return map;
				}
				map.put("error","答案错误");
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		return map;
	}
    /**
     * @method  findApplication
     * @description 检查该用户本年度是否有提交记录，避免重复提交
     * @date: 2019/5/10 0:38
     * @author: Fangcheng
    [model]
     * @return java.util.Map<java.lang.String,java.lang.String>
     */
    @RequestMapping(value = { "/findApplication" }, method = RequestMethod.GET)
    @ResponseBody
    public Map<String,String> findApplication(ModelMap model) {
        Map<String,String> map = new HashMap<String, String>();
        List<Areas> areasList = areasService.findByName("陕西省","渭南市","华县");
        try {
            List<Application> applicationList = applicationService.findByUserId(getPrincipal());
            if(applicationList!=null){
                for(Application application:applicationList){
                    if(application.getSchoolYear().equals(getSchoolYear()));
                    map.put("error","已提交该年度申请，请勿重复提交");
                    return map;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        map.put("success","true");
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
	@ResponseBody
	public Map<String,Object> addApplication(@Valid Application application, BindingResult result,
								 ModelMap model,HttpServletResponse response) {
		Map<String,Object> map = new HashMap<String, Object>();
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		application.setApplicationNumber(getApplicationNumber());
		application.setLiabilities(unicode(application.getLiabilities()));
		application.setAddressee(unicode(application.getAddressee()));
		application.setAddress(unicode(application.getAddress()));
		application.setOtherSituation(unicode(application.getOtherSituation()));
		application.setPovertyLevel(unicode(application.getPovertyLevel()));
		application.setUnexpectedAccident(unicode(application.getUnexpectedAccident()));
		application.setFundedSituation(unicode(application.getFundedSituation()));
		application.setMembershipSituation(unicode(application.getMembershipSituation()));
		application.setUnemploymentSituation(unicode(application.getUnemploymentSituation()));
		application.setPostalAddress(unicode(application.getPostalAddress()));
		application.setNaturalDisaster(unicode(application.getNaturalDisaster()));
		application.setReasonsForApplication(unicode(application.getReasonsForApplication()));
		application.setEmergencyContact(unicode(application.getEmergencyContact()));
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
			dealWithResult(application);
			map.put("success","提交成功");
		}catch (Exception e){
			e.printStackTrace();
			tableApprovalService.deleteAllApprovalByApplication(application.getApplicationNumber());
			applicationService.deleteApplicationByApplicationNumber(application.getApplicationNumber());
			map.put("error", "222");
		}
		return map;
    }
	/**
	 * @method  upload
	 * @description 上传压缩文件
	 * @date: 2019/5/17 23:32
	 * @author: Fangcheng
	[multipartFile, request]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 */
	@RequestMapping(value = { "/upload" }, method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> upload(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest request) {
		Map<String,String> map = new HashMap<>();
		if(!multipartFile.isEmpty()){
			String contextPath = request.getContextPath();//"/SpringMvcFileUpload"
			String servletPath = request.getServletPath();//"/gotoAction"
			String scheme = request.getScheme();//
			String url = getSchoolYear()+"\\"+getUserCollege()+"\\"+getPrincipal();
			String storePath= "C:\\Users\\FangCheng\\Downloads\\SpringMVCHibernateWithSpringSecurityExample\\_222\\src\\main\\webapp\\static\\data\\"+url;//存放我们上传的文件路径
//           String fileName = multipartFile.getOriginalFilename();
			String fileName = getApplicationNumber()+".zip";
			java.io.File filepath = new java.io.File(storePath, fileName);
			if (!filepath.getParentFile().exists()) {
				filepath.getParentFile().mkdirs();//如果目录不存在，创建目录
			}
			try {
				multipartFile.transferTo(new java.io.File(storePath+ File.separator+fileName));//把文件写入目标文件地址
                //判断文件是否存在
                com.fangcheng.test.entity.File file1 = fileService.findById(getApplicationNumber());
                if(file1==null){
					com.fangcheng.test.entity.File file = new com.fangcheng.test.entity.File();
                	file.setId(getApplicationNumber());
                	file.setSuffix(".zip");
                	file.setName(getApplicationNumber()+".zip");
                	file.setLastModifyTime(getTime());
                    file.setVisitUrl(url.replace(	"\\","/"));
                    file.setCreatTime(getTime());
                    file.setUserId(getPrincipal());
                    fileService.save(file);
                    map.put("success","文件上传成功");
                }else {
                	file1.setCreatTime(getTime());
                	file1.setLastModifyTime(getTime());
                	fileService.update(file1);
                	map.put("success","文件更新成功");
				}

			} catch (Exception e) {
				e.printStackTrace();
				map.put("error","文件上传失败");
				return map;
			}
		}
		return map;
	}
	@RequestMapping(value = { "/download" }, method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> download(@RequestBody String[] applicationNumberList) {
		Map<String,Object> map = new HashMap<String, Object>();
		List<String> listsuccess = new ArrayList<>();
		for(String str:applicationNumberList){
			try{
				com.fangcheng.test.entity.File file = fileService.findById(str);
				map.put("success",file.getVisitUrl()+ "/" +file.getName());
				return map;
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		map.put("error","该用户未上传文件");
		return map;
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
	map.put("success","成功");
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
			tableApproval.setRemarks(applicationNumberList[1]);
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
			tableApprovalService.deleteAllApprovalByUserId(userId);
			applicationService.deleteAllApplicationByUserId(userId);
			userFamilyService.deleteByUserId(userId);
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
				tableApprovalService.deleteAllApprovalByUserId(userId);
				applicationService.deleteAllApplicationByUserId(userId);
				userFamilyService.deleteByUserId(userId);
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
	@ResponseBody
	public void deleteApplication(@RequestBody String applicationNumber) {
		try{
			tableApprovalService.deleteAllApprovalByApplication(applicationNumber);
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
	@RequestMapping(value = "/getReference", method = RequestMethod.GET)
	public String getReference(ModelMap model) {
		return "admin/referencedata";
	}
    @RequestMapping(value = "/getPoorerAreas", method = RequestMethod.GET)
    public String getPoorerAreas(ModelMap model) {
        return "admin/Areas";
    }
    @RequestMapping(value = "/verifyInfo", method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Integer> verifyInfo(ModelMap model) {
	    Map<String,Integer> map = new HashMap<>();
	    User user = userService.findByUserId(getPrincipal());
	    if (null==user.getUserSecurity()){
	        map.put("error",1);
	        return map;
        }
        map.put("success",0);
        return map;
    }


	public void dealWithResult(Application application){
		//获取该申请人的家庭成员信息
		List<UserFamily> userFamilyList = new ArrayList<>();
		Map<String,Integer> map = new HashMap<>();
		try {
			userFamilyList = userFamilyService.findByUserId(application.getUserId());
		}catch (Exception e){
			e.printStackTrace();
		}
		try {
			if(userFamilyList.size()>0){
				//60岁以上
				Integer sixtyAge = 0;
				//学生数量
				Integer student = 0;
				//70岁以上
				Integer seventyAge = 0;
				//总学费支出
				int totalTuition = 0;
				//家庭成员年总收入
				int totalIncome = 0;
				for(UserFamily userFamily:userFamilyList){
					//计算60-70岁成员数量，
					if(userFamily.getUserAge()>=60&&userFamily.getUserAge()<70){
						sixtyAge++;
					}
					//计算70岁以上的成员数量
					if(userFamily.getUserAge()>=70){
						seventyAge++;
					}
					//计算家庭成员中是否有学龄成员计算年度学费支出
					if(userFamily.getOccupation().equals("学生")){
						totalTuition+=userFamily.getTuition();
						totalIncome+=userFamily.getAnnualIncome();//学生若有收入也应计算
						student++;
					}else {
						totalIncome+=userFamily.getAnnualIncome();
					}
					//家庭成员中若有伤残应为其赋分值
					if(userFamily.getHealth().equals("轻度残疾")){
						map.put("health",1);
					}else if(userFamily.getHealth().equals("重度残疾")){
						map.put("health",3);
					}
				}
				map.put("sixtyOld",sixtyAge);
				map.put("seventyAge",seventyAge);
				//学费
				map.put("totalTuition",totalTuition);
				//收入
				map.put("totalIncome",totalIncome);
				map.put("student",student);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		map.put("lonely",0);
		//处理出生地
		User user = userService.findByUserId(application.getUserId());
		//获取用户地址，寻找对应县信息
		try {
			List<Areas> areas = areasService.findByName(user.getBasePlaceP(),user.getBasePlaceC(),user.getBasePlaceA());
			for(Areas areas1:areas){
				if(areas1.getPoorerAreas().equals(1)){
					map.put("isPoor",1);
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		map.put("isPoor",0);
		//处理花费
		Integer systemValue = 0;
		String remarks = "";
		Integer yearlyIncome = application.getYearlyIncome();
		Integer guarantee = application.getGuarantee();
		ReferenceData referenceData = referenceDataService.findBySchoolYear(application.getSchoolYear());
		Integer xuefei = 0;
		//判断院系
		if(user.getUserCollege().equals("信息工程学院")){
			xuefei = referenceData.getTuition1();
		}else if (user.getUserCollege().equals("外国语言系")){
			xuefei = referenceData.getTuition2();
		}else if (user.getUserCollege().equals("经济系")){
			xuefei = referenceData.getTuition3();
		}else if (user.getUserCollege().equals("艺术与设计系")){
			xuefei = referenceData.getTuition4();
		}else if (user.getUserCollege().equals("管理系")){
			xuefei = referenceData.getTuition5();
		}else if (user.getUserCollege().equals("飞行器")){
			xuefei = referenceData.getTuition6();
		}
		try {
			//计算家庭成员
			if (map.get("health")!=0){
				systemValue+=map.get("health").intValue();
				remarks = remarks + "家庭有残疾人员,";
			}
			if(map.get("sixtyOld")!=0){
				remarks = remarks + "家有60-70岁老人"+map.get("sixtyOld").intValue()+"人,";
			}
			if(map.get("seventyAge")!=0){
				remarks = remarks +"70岁以上老人"+map.get("seventyAge").intValue()+"人,";
			}
			//计算家庭总收入
			if(yearlyIncome<10000){
				systemValue +=3;
			}else if(yearlyIncome<=20000&&yearlyIncome>10000){
				systemValue+=2;
			}else if (yearlyIncome<30000&&yearlyIncome>20000){
				systemValue+=1;
			}
			//计算家庭月人均收入
			if((yearlyIncome/application.getPopulationSize())<=500){
				systemValue+=5;
			}else if ((yearlyIncome/application.getPopulationSize())>500&&(yearlyIncome/application.getPopulationSize())<1000){
				systemValue+=3;
			}else if((yearlyIncome/application.getPopulationSize())>1000&&(yearlyIncome/application.getPopulationSize())<1500){
				systemValue+=1;
			}
			//计算保障金
			if(guarantee<referenceData.getLivingExpenses()){//生源地保障金大于学校
				systemValue+=2;
			}
			//计算负债情况
			if(application.getLiabilities().equals("2万以下")){
				systemValue+=1;
			}else if (application.getLiabilities().equals("2-5万")){
				systemValue+=2;
			}else if (application.getLiabilities().equals("5万以上")){
				systemValue+=3;
			}
			//计算生活饮食支出占比
		switch (application.getPercentage()){
			case "1": systemValue+=1;break;
			case "2": systemValue+=2;break;
			case "3": systemValue+=3;break;
			case "4": systemValue+=4;break;
			default:break;
		}
			//计算大病医疗支出
			//九三
			if(yearlyIncome<(map.get("totalTuition")+xuefei)){
				remarks = remarks + "学费支出大于收入";
			}
			//计算贫困地区
			if(map.get("isPoor").equals(1)){
				//评分加1
				systemValue+=2;
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		if(systemValue>17){
			application.setSystemAudit("特别贫困");
		}else {
			application.setSystemAudit("一般贫困");
		}
		//判断持有证明
		if(application.getProve().equals("特困证")||application.getProve().equals("社会扶助证")||
				application.getProve().equals("最低生活保障证")||application.getProve().equals("建档立卡")||
				application.getProve().equals("孤残学生")||application.getProve().equals("烈士子女")){
			application.setSystemAudit("特别贫困");
		}
		application.setSystemValue(systemValue);
		application.setRemarks(remarks);
		applicationService.update(application);
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
	private String getUserCollege(){
		User user = userService.findByUserId(getPrincipal());
		String userCollege = user.getUserCollege();
		return userCollege;
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
		return date;
	}

	/**
	 * This method returns true if users is already authenticated [logged-in], else false.
	 */
	private boolean isCurrentAuthenticationAnonymous() {
	    final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    return authenticationTrustResolver.isAnonymous(authentication);
	}
	private Integer getSize(){
		Integer size = 0;
		//
		//判断用户级别
		if(getGroupId().equals("辅导员")){
			//获取
		}else if(getGroupId().equals("院系办公室")){

		}else if(getGroupId().equals("学工部")){

		}
//		List<Application> applicationList = applicationService.
		return size;
	}
}