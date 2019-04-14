package com.fangcheng.test.service.impl;

import com.fangcheng.test.dao.UserDao;
import com.fangcheng.test.entity.User;
import com.fangcheng.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ProjectName: _222
 * @Package: com.fc.test.service.impl
 * @ClassName: UserServiceImpl
 * @Description: java类作用描述
 * @Author: FangCheng
 * @CreateDate: 2019/3/24 14:50
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/3/24 14:50
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao dao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //将从查询到的User对象返回
    public User findByUserId(String userId){
        return dao.findByUserId(userId);
    }
//    public User findByUserClass(String userClass){
//        return dao.findByUserClass(userClass)
//    }
    //添加用户
    public void saveUser(User user){
        //对密码做加密处理
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        dao.save(user);
    }
    public void updateUserData(User user){
        User entity = dao.findByUserId(user.getUserId());
        if(entity!=null) {
            entity.setUserId(user.getUserId());
            if (user.getGroupId()!=null){//年级信息
                entity.setGroupId(user.getGroupId());
            }
            if (user.getUserName()!=null){//姓名
                entity.setUserName(user.getUserName());
            }
            if (user.getUserMajor()!=null){//专业
                entity.setUserMajor(user.getUserMajor());
            }
            if (user.getUserCollege()!=null){//学院
                entity.setUserCollege(user.getUserCollege());
            }
            if (user.getUserClass()!=null){//班级
                entity.setUserClass(user.getUserClass());
            }
            entity.setUserSex(user.getUserSex());
            entity.setBirthDate(user.getBirthDate());
            entity.setPhoneNumber(user.getPhoneNumber());
            entity.setIdNumber(user.getIdNumber());
            entity.setNativePlace(user.getNativePlace());
            entity.setBasePlace(user.getBasePlace());
        }
    }
    //修改用户密码
    public void alterUserPassword(User user){
        User entity = dao.findByUserId(user.getUserId());
        if(entity!=null){
            //密码不相等的情况下修改
            if (!user.getPassword().equals(entity.getPassword()));
                entity.setPassword(passwordEncoder.encode(user.getPassword()));
        }
    }
    //修改密保信息
    public void alterUserSecurityQuestion(User user){
        User entity = dao.findByUserId(user.getUserId());
        if(entity!=null){
            //密码不相等的情况下修改
            if (!user.getUserSecurity().equals(entity.getUserSecurity())){
                entity.setUserSecurity(user.getUserSecurity());
            }
            if (!user.getSecurityAnwser().equals(entity.getSecurityAnwser())){
                entity.setSecurityAnwser(user.getSecurityAnwser());
            }
        }
    }
    //删除用户
    public void deleteUserByUserId(String userId){
        dao.deletByUserId(userId);
    }
    //查找所有用户
    public List<User> findAllUsers(){
        return dao.findAllUsers();
    }
    public boolean isUserIdUnique(String userId){
        User user = findByUserId(userId);
        return ( user == null || user.getUserId() == userId);
    }

//    @Override
//    public boolean isUserClassUnique(String userClass) {
//        User user = findByUserClass(userClass);
//        return false;
//    }
}
