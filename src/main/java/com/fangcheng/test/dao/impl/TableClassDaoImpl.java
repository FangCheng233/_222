package com.fangcheng.test.dao.impl;

import com.fangcheng.test.dao.AbstractDao;
import com.fangcheng.test.dao.TableClassDao;
import com.fangcheng.test.entity.TableClass;
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
 * @Package: com.fangcheng.test.dao.impl
 * @ClassName: TableClassDaoImpl
 * @Description: java类作用描述
 * @Author: FangCheng
 * @CreateDate: 2019/4/20 12:31
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/4/20 12:31
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Repository("tableClassDao")
public class TableClassDaoImpl extends AbstractDao<Integer,TableClass> implements TableClassDao {

    static final Logger logger = LoggerFactory.getLogger(TableClassDao.class);

    @Override
    public TableClass findByClassId(String classId) {
        logger.info("classId: {}",classId);
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("classId", classId));
        TableClass tableClass = (TableClass)crit.uniqueResult();
        return tableClass;
    }

    @Override
    public void save(TableClass tableClass) {
    persist(tableClass);
    }

    @Override
    public void delteByClassId(String classId) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("classId", classId));
        TableClass tableClass = (TableClass)crit.uniqueResult();
        delete(tableClass);
    }

    @Override
    public List<TableClass> findAllClass() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("classId"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<TableClass> tableClassList = (List<TableClass>) criteria.list();
        return tableClassList;
    }

    @Override
    public List<TableClass> findByTeacherId(String teacherId) {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("classId"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<TableClass> tableClassList =  criteria.add(Restrictions.eq("teacherId",teacherId)).list();
        return tableClassList;
    }
}
