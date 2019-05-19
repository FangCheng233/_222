package com.fangcheng.test.dao.impl;

import com.fangcheng.test.dao.AbstractDao;
import com.fangcheng.test.dao.ReferenceDataDao;
import com.fangcheng.test.entity.Areas;
import com.fangcheng.test.entity.ReferenceData;
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
 * @ClassName: ReferenceDataDaoImpl
 * @Description: java类作用描述
 * @Author: FangCheng
 * @CreateDate: 2019/5/13 14:57
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/5/13 14:57
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Repository("referenceDataDao")
public class ReferenceDataDaoImpl  extends AbstractDao<Integer, ReferenceData> implements ReferenceDataDao {
    static final Logger logger = LoggerFactory.getLogger(ReferenceDataDaoImpl.class);
    @Override
    public ReferenceData findBySchoolYear(String schoolYear) {
        logger.info("error : {}", schoolYear);
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("schoolYear", schoolYear));
        ReferenceData referenceData = (ReferenceData) crit.uniqueResult();
        return referenceData;
    }

    @Override
    public List<ReferenceData> findAll() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("schoolYear"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<ReferenceData> referenceDataList = (List<ReferenceData>) criteria.list();
        return referenceDataList;
    }

    @Override
    public void save(ReferenceData referenceData) {
    persist(referenceData);
    }
}
