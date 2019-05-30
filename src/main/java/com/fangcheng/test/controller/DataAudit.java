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
import java.math.BigInteger;
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
            map.put("error","修改失败");
        }
        return map;
    }
    @RequestMapping(value = { "/setAreas" }, method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> setAreas(@RequestBody BigInteger[] anwser) {
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            for(BigInteger integer :anwser){
                areasService.updata(integer,1);
            }
            map.put("success","修改成功");
            return map;
        }catch (Exception e){
            e.printStackTrace();
            map.put("error","密码修改失败");
        }
        return map;
    }
    @RequestMapping(value = { "/cancelAreas" }, method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> cancelAreas(@RequestBody BigInteger[] anwser) {
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            for(BigInteger integer :anwser){
                areasService.updata(integer,0);
            }
            map.put("success","修改成功");
            return map;
        }catch (Exception e){
            e.printStackTrace();
            map.put("error","修改失败");
        }
        return map;
    }
}
