package com.fangcheng.test.controller;

import com.fangcheng.test.entity.*;
import com.fangcheng.test.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @ProjectName: _222
 * @Package: com.fangcheng.test.controller
 * @ClassName: DataAudit
 * @Description: java类作用描述
 * @Author: FangCheng
 * @CreateDate: 2019/5/9 14:00
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/5/9 14:00
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Controller
@RequestMapping("/")
public class DataAudit {
    @Autowired
    UserService userService;
    @Autowired
    AreasService areasService;
    @Autowired
    UserFamilyService userFamilyService;
    @Autowired
    ReferenceDataService referenceDataService;
    @Autowired
    ApplicationService applicationService;
    //对学生提交的数据进行审批
    public static void dealWithData(Application application){
        DataAudit dataAudit = new DataAudit();
        //计算学生家庭成员相关权重比例
        dataAudit.family(application);
        //计算学生生源所在地是否属于贫困地区
        dataAudit.birthPlace(application);
        //计算学生个人日常消费情况
        dataAudit.cost(application);

        //将返回的结果写入到学生申请系统批注一栏
        //根据等级在页面显示不同的颜色区
        //处理返回结果
        dataAudit.dealWithResult(application,dataAudit.family(application),dataAudit.birthPlace(application),dataAudit.cost(application));
    }
    public Map<String,Integer> family(Application application){
        //获取该申请人的家庭成员信息
        List<UserFamily> userFamilyList = new ArrayList<>();
        Map<String,Integer> map = new HashMap<>();
        try {
            userFamilyList = userFamilyService.findByUserId(application.getUserId());
        }catch (Exception e){
            e.printStackTrace();
        }
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

    return map;
    }

    public Map<String,Integer> birthPlace(Application application){
        User user = userService.findByUserId(application.getUserId());
        Map<String,Integer> map = new HashMap<>();
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
        return map;
    }
    public Map<String,Integer> cost(Application application){
        Map<String,Integer> map = new HashMap<>();
        return map;
    }
    public void dealWithResult(Application application,Map<String,Integer> map1,Map<String,Integer> map2,Map<String,Integer> map3){
        Integer systemValue = 0;
        String remarks = "";
        User user = userService.findByUserId(application.getUserId());
        Integer yearlyIncome = application.getYearlyIncome();
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
            if (map1.get("health")!=null){
                systemValue+=map1.get("health").intValue();
                remarks = remarks + "家庭有残疾人员,";
            }
            remarks = remarks + "家有60-70岁老人"+map1.get("sixtyOld")+"人，"+"70岁以上老人"+map1.get("seventyAge").intValue()+"人,";
            //计算家庭总收入
            if(yearlyIncome<10000){
                systemValue +=3;
            }else if(yearlyIncome<=20000&&yearlyIncome>10000){
                systemValue+=2;
            }else if (yearlyIncome<30000&&yearlyIncome>20000){
                systemValue+=1;
            }
            if((yearlyIncome/application.getPopulationSize())<=500){
                systemValue+=5;
            }else if ((yearlyIncome/application.getPopulationSize())>500&&(yearlyIncome/application.getPopulationSize())<1000){
                systemValue+=3;
            }else if((yearlyIncome/application.getPopulationSize())>1000&&(yearlyIncome/application.getPopulationSize())<1500){
                systemValue+=1;
            }
            //计算保障金
            //计算负债情况
            if(application.getLiabilities().equals("2万以下")){
                systemValue+=1;
            }else if (application.getLiabilities().equals("2-5万")){
                systemValue+=2;
            }else if (application.getLiabilities().equals("5万以上")){
                systemValue+=3;
            }
            //学费
            if(yearlyIncome<(map1.get("totalTuition")+xuefei)){
                remarks = remarks + "学费支出大于收入";
            }
/*            map.put("totalTuition", 家庭totalTuition);
            //收入
            map.put("totalIncome",totalIncome);
            map.put("student",student);*/
            //计算贫困地区
            if(map1.get("isPoor").equals(1)){
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
        if(application.getProve().equals("特困证")||application.getProve().equals("社会扶助证")||
                application.getProve().equals("最低生活保障证")||application.getProve().equals("建档立卡")||
                application.getProve().equals("孤残学生")||application.getProve().equals("烈士子女")){
            application.setSystemAudit("特别贫困");
        }
        application.setSystemValue(systemValue);
        application.setRemarks(remarks);
        applicationService.update(application);
    }
    @RequestMapping(value = { "/setReference" }, method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> setReference(@RequestBody String[] anwser) {
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            //使用反射修改对应的参考数据
            ReferenceData referenceData = referenceDataService.findBySchoolYear(anwser[0]);
            Class<ReferenceData> referenceDataClass = ReferenceData.class;
            Field field = referenceDataClass.getDeclaredField(anwser[1]);
            field.setAccessible(true);
            field.set(referenceData,Integer.parseInt(anwser[2]));
            referenceDataService.updata(referenceData);
            map.put("success","修改成功");
            return map;
        }catch (Exception e){
            e.printStackTrace();
            map.put("error","密码修改失败");
        }
        return map;
    }


}
