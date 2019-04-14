package com.fangcheng.test.service;

import com.fangcheng.test.entity.User;

import java.util.List;

/**
 * @ProjectName: _222
 * @Package: com.fc.test.service
 * @ClassName: UserService
 * @Description: java类作用描述
 * @Author: FangCheng
 * @CreateDate: 2019/3/24 14:49
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/3/24 14:49
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface UserService {
    User findByUserId(String userId);

    void saveUser(User user);

    void  alterUserPassword(User user);

    void updateUserData(User user);

    void alterUserSecurityQuestion(User user);
    void deleteUserByUserId(String userId);

    List<User> findAllUsers();
    boolean isUserIdUnique(String userId);
//    boolean isUserClassUnique(String userClass);
}
