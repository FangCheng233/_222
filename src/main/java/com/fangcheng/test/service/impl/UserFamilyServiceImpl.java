package com.fangcheng.test.service.impl;

import com.fangcheng.test.dao.UserFamilyDao;
import com.fangcheng.test.entity.UserFamily;
import com.fangcheng.test.service.UserFamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ProjectName: _222
 * @Package: com.fangcheng.test.service.impl
 * @ClassName: UserFamilyServiceImpl
 * @Description: java类作用描述
 * @Author: FangCheng
 * @CreateDate: 2019/4/21 13:42
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/4/21 13:42
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Service("userFamilyService")
@Transactional
public class UserFamilyServiceImpl implements UserFamilyService {
    @Autowired
    private UserFamilyDao userFamilyDao;

    @Override
    public List<UserFamily> findByUserId(String userId) {
        return userFamilyDao.findByUserId(userId);
    }

    @Override
    public List<UserFamily> findAll() {
        return userFamilyDao.findAll();
    }

    @Override
    public void save(UserFamily userFamily) {
        userFamilyDao.save(userFamily);
    }

    @Override
    public void delete(String id) {
        userFamilyDao.delete(id);
    }

    @Override
    public void deleteByUserId(String userId) {
        List<UserFamily> userFamilyList = userFamilyDao.findByUserId(userId);{
            for (UserFamily userFamily:userFamilyList){
                try {
                    userFamilyDao.delete(userFamily.getId());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
