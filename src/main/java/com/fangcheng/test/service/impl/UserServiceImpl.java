package com.fangcheng.test.service.impl;

import com.fangcheng.test.dao.UserDao;
import com.fangcheng.test.entity.TableAuthor;
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
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //将从查询到的User对象返回
    public User findByUserId(String userId){
        return userDao.findByUserId(userId);
    }
//    public User findByUserClass(String userClass){
//        return dao.findByUserClass(userClass)
//    }
    //添加用户
    public void saveUser(User user){
        //对密码做加密处理
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.save(user);
    }
    public void updateUserData(User user){
        User entity = userDao.findByUserId(user.getUserId());
        if(entity!=null) {
            entity.setUserId(user.getUserId());
            entity.setBirthDate(user.getBirthDate());
            entity.setPhoneNumber(user.getPhoneNumber());
            entity.setUserEmail(user.getUserEmail());
            entity.setQQ(user.getQQ());
            entity.setPostalAddress(user.getPostalAddress());
            entity.setTableAuthors(user.getTableAuthors());
            entity.setUserSecurity(user.getUserSecurity());
            entity.setSecurityAnwser(user.getSecurityAnwser());
        }
    }
    public void updateUserRole(User user){
        User entity = userDao.findByUserId(user.getUserId());
        Integer authorId=0;
        if(entity!=null) {
            for (TableAuthor tableAuthor:user.getTableAuthors()){
                authorId = tableAuthor.getAuthorId();
            }
            switch(authorId){
                case 1:
                    entity.setGroupId("学生");break;
                case 2:
                    entity.setGroupId("辅导员");break;
                case 3:
                    entity.setGroupId("院系办公室");break;
                case 4:
                    entity.setGroupId("学工部");break;
            }
            entity.setTableAuthors(user.getTableAuthors());
        }
    }
    //修改用户密码
    public void alterUserPassword(User user){
        User entity = userDao.findByUserId(user.getUserId());
        if(entity!=null){
            //密码不相等的情况下修改
            if (!user.getPassword().equals(entity.getPassword()));
                entity.setPassword(passwordEncoder.encode(user.getPassword()));
        }
    }
    //修改密保信息
    public void alterUserSecurityQuestion(User user){
        User entity = userDao.findByUserId(user.getUserId());
        if(entity!=null){

        }
    }
    //删除用户
    public void deleteUserByUserId(String userId){
        userDao.deletByUserId(userId);

    }
    //查找所有用户
    public List<User> findAllUsers(){
        return userDao.findAllUsers();
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
