package com.fangcheng.test.dao.impl;

import com.fangcheng.test.dao.AbstractDao;
import com.fangcheng.test.dao.TableApprovalDao;
import com.fangcheng.test.entity.TableApproval;
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
 * @ClassName: TableApprovalDaoImpl
 * @Description: java类作用描述
 * @Author: FangCheng
 * @CreateDate: 2019/4/18 16:35
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/4/18 16:35
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Repository
public class TableApprovalDaoImpl extends AbstractDao<Integer, TableApproval> implements TableApprovalDao {
    static final Logger logger = LoggerFactory.getLogger(TableApprovalDao.class);

    @Override
    public List<TableApproval> findAllApproval() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("applicationNumber"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<TableApproval> tableApprovals = (List<TableApproval>) criteria.list();
        return tableApprovals;
        /*return null;*/
    }

    @Override
    public List<TableApproval> findByApplicationNumber(String applicationNumber) {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("applicationNumber"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<TableApproval> tableApprovalList =  criteria.add(Restrictions.eq("applicationNumber",applicationNumber)).list();
        return tableApprovalList;
    }

    @Override
    public void save(TableApproval tableApproval) {
        persist(tableApproval);
    }
}
