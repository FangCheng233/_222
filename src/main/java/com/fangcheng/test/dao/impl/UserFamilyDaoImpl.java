package com.fangcheng.test.dao.impl;

import com.fangcheng.test.dao.AbstractDao;
import com.fangcheng.test.dao.UserFamilyDao;
import com.fangcheng.test.entity.UserFamily;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ProjectName: _222
 * @Package: com.fangcheng.test.dao.impl
 * @ClassName: UserFamilyDaoImpl
 * @Description: java类作用描述
 * @Author: FangCheng
 * @CreateDate: 2019/4/21 13:43
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/4/21 13:43
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Repository("userFamilyDao")
public class UserFamilyDaoImpl extends AbstractDao<Integer,UserFamily>implements UserFamilyDao {
    static final Logger logger = LoggerFactory.getLogger(UserFamilyDaoImpl.class);

    @Override
    public List<UserFamily> findByUserId(String userId) {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("userId"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<UserFamily> userFamilyList =  criteria.add(Restrictions.eq("userId",userId)).list();
        return userFamilyList;
    }

    @Override
    public List<UserFamily> findAll() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("userId"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<UserFamily> userFamilyList = (List<UserFamily>) criteria.list();
        return userFamilyList;
    }
    @Override
    public void save(UserFamily userFamily) {
        persist(userFamily);
    }

    @Override
    public void delete(String id) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("id", id));
        UserFamily uniqueResult = (UserFamily)crit.uniqueResult();
        delete(uniqueResult);
    }
}
