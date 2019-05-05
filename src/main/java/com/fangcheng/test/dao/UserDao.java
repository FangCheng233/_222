package com.fangcheng.test.dao;

import com.fangcheng.test.entity.User;

import java.util.List;

/**
 * @ProjectName: _222
 * @Package: com.fc.test.dao
 * @ClassName: UserDao
 * @Description: java类作用描述
 * @Author: FangCheng
 * @CreateDate: 2019/3/24 14:48
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/3/24 14:48
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface UserDao {
    User findByUserId(String userId);

    void save(User user);

    void  deletByUserId(String userId);

    List<User> findAllUsers();
}
