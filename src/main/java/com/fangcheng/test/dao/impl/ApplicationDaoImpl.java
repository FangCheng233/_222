package com.fangcheng.test.dao.impl;
import com.fangcheng.test.dao.AbstractDao;
import com.fangcheng.test.dao.ApplicationDao;
import com.fangcheng.test.entity.Application;
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
 * @ClassName: ApplicationDaoImpl
 * @Description: java类作用描述
 * @Author: FangCheng
 * @CreateDate: 2019/3/28 16:30
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/3/28 16:30
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Repository("applicationDao")
public class ApplicationDaoImpl extends AbstractDao<Integer, Application> implements ApplicationDao{
    static final Logger logger = LoggerFactory.getLogger(ApplicationDaoImpl.class);

    public Application findByApplicationNumber(String applicationNumber) {
        logger.info("applicationNumber : {}", applicationNumber);
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("applicationNumber", applicationNumber));
        Application application = (Application) crit.uniqueResult();
        return application;
    }

    public List<Application> findAllApplication() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("applicationNumber"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<Application> applications = (List<Application>) criteria.list();
        return applications;
    }

    @Override
    public List<Application> findByUserId(String userId) {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("userId"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<Application> tableClassList =  criteria.add(Restrictions.eq("userId",userId)).list();
        return tableClassList;
    }

    @Override
    public List<Application> findBySchoolYear(String schoolYear) {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("schoolYear"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<Application> tableClassList =  criteria.add(Restrictions.eq("schoolYear",schoolYear)).list();
        return tableClassList;
    }

    @Override
    public void save(Application application) {
        persist(application);
    }

    @Override
    public void deletByApplicationNumber(String applicationNumber) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("applicationNumber", applicationNumber));
        Application application = (Application) crit.uniqueResult();
        delete(application);
    }
}
