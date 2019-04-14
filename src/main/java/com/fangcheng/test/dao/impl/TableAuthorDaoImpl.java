package com.fangcheng.test.dao.impl;

import com.fangcheng.test.dao.AbstractDao;
import com.fangcheng.test.dao.TableAuthorDao;
import com.fangcheng.test.entity.TableAuthor;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ProjectName: _222
 * @Package: com.fangcheng.test.dao.impl
 * @ClassName: TableAuthorDaoImpl
 * @Description: java类作用描述
 * @Author: FangCheng
 * @CreateDate: 2019/3/24 17:48
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/3/24 17:48
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Repository("tableAuthorDao")
public class TableAuthorDaoImpl extends AbstractDao<Integer,TableAuthor> implements TableAuthorDao {

    public TableAuthor findByAuthorId(int authorId){
        return getByKey(authorId);
    }

    public TableAuthor findByAuthorType(String authorType) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("authorType", authorType));
        return (TableAuthor) crit.uniqueResult();
    }

    @Override
    public List<TableAuthor> findAll() {
        Criteria crit = createEntityCriteria();
        crit.addOrder(Order.asc("authorType"));
        return (List<TableAuthor>)crit.list();
    }
}
