package com.fangcheng.test.dao;

import com.fangcheng.test.entity.UserFamily;

import java.util.List;

/**
 * @ProjectName: _222
 * @Package: com.fangcheng.test.dao
 * @ClassName: UserFamilyDao
 * @Description: java类作用描述
 * @Author: FangCheng
 * @CreateDate: 2019/4/21 13:43
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/4/21 13:43
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface UserFamilyDao {
    List<UserFamily> findByUserId(String userId);
    List<UserFamily> findAll();
    void save(UserFamily userFamily);
    void delete(String id);
}
