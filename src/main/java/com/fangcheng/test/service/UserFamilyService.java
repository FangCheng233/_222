package com.fangcheng.test.service;

import com.fangcheng.test.entity.UserFamily;

import java.util.List;

/**
 * @ProjectName: _222
 * @Package: com.fangcheng.test.service
 * @ClassName: UserFamilyService
 * @Description: java类作用描述
 * @Author: FangCheng
 * @CreateDate: 2019/4/21 13:42
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/4/21 13:42
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface UserFamilyService {
   List<UserFamily> findByUserId(String userId);
   List<UserFamily> findAll();
   void save(UserFamily userFamily);
   void delete(String id);
   void deleteByUserId(String userId);
}

