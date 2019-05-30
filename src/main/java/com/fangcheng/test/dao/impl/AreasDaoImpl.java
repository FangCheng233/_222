package com.fangcheng.test.dao.impl;

import com.fangcheng.test.dao.AbstractDao;
import com.fangcheng.test.dao.AreasDao;
import com.fangcheng.test.entity.Areas;
import com.fangcheng.test.entity.User;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

/**
 * @ProjectName: _222
 * @Package: com.fangcheng.test.dao.impl
 * @ClassName: AreasDaoImpl
 * @Description: java类作用描述
 * @Author: FangCheng
 * @CreateDate: 2019/5/11 13:11
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/5/11 13:11
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Repository("areasDao")
public class AreasDaoImpl extends AbstractDao<Integer, Areas>  implements AreasDao {
    static final Logger logger = LoggerFactory.getLogger(AreasDaoImpl.class);
    @Override
    public Areas findById(BigInteger id) {
        logger.info("error : {}", id);
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("id", id));
        Areas areas = (Areas) crit.uniqueResult();
        return areas;
    }
    @Override
    public List<Areas> findByName(String provinceName, String cityName, String countyName) {
        logger.info("error : {}", provinceName+cityName+countyName);
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("provinceName", provinceName)).add(Restrictions.eq("cityName",cityName)).add(Restrictions.eq("countyName",countyName));
        List<Areas> areas = crit.list();
        return areas;
    }
    @Override
    public List<Areas> findAllProvince() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("provinceName"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<Areas> areas = (List<Areas>) criteria.list();
        return areas;
    }

    @Override
    public List<Areas> findAllCity(String provinceName) {//根据省名找下辖市
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("provinceName"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<Areas> areasList =  criteria.add(Restrictions.eq("provinceName",provinceName)).list();
        return areasList;
    }

    @Override
    public List<Areas> findAllCounty(String cityName) {//根据市名找县名
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("cityName"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<Areas> areasList =  criteria.add(Restrictions.eq("cityName",cityName)).list();
        return areasList;
    }

    @Override
    public List<Areas> findAllTown(String countryName) {//找县
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("countryName"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<Areas> areasList =  criteria.add(Restrictions.eq("countryName",countryName)).list();
        return areasList;
    }

    @Override
    public List<Areas> findAll() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<Areas> areas = (List<Areas>) criteria.list();
        return areas;
    }
    @Override
    public void deleteById(String id) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("id", id));
        Areas areas = (Areas) crit.uniqueResult();
        delete(areas);
    }
}
