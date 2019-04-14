package com.fangcheng.test.dao.impl;

import com.fangcheng.test.dao.AbstractDao;
import com.fangcheng.test.dao.UserDao;
import com.fangcheng.test.entity.User;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @ProjectName: _222
 * @Package: com.fc.test.dao.impl
 * @ClassName: UserDaoImpl
 * @Description: java类作用描述
 * @Author: FangCheng
 * @CreateDate: 2019/3/24 14:49
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/3/24 14:49
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

    static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    public User findByUserId(String userId) {
        logger.info("userID : {}", userId);
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("userId", userId));
        User user = (User)crit.uniqueResult();
        if(user!=null){
            Hibernate.initialize(user.getTableAuthors());
        }
        return user;
    }

//    @Override
//    public User findByUserClass(String userClass) {
//        logger.info("userClass : {}", userId);
//        return null;
//    }

    public List<User> findAllUsers() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("userId"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<User> users = (List<User>) criteria.list();
        return users;
    }

    @Override
    public void save(User user) {
        persist(user);
    }

    @Override
    public void deletByUserId(String userId) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("userId", userId));
        User user = (User)crit.uniqueResult();
        delete(user);
    }
}
