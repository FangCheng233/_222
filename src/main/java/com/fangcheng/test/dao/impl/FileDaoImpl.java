package com.fangcheng.test.dao.impl;

import com.fangcheng.test.dao.AbstractDao;
import com.fangcheng.test.dao.FileDao;
import com.fangcheng.test.entity.File;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * @ProjectName: _222
 * @Package: com.fangcheng.test.dao.impl
 * @ClassName: FileDaoImpl
 * @Description: java类作用描述
 * @Author: FangCheng
 * @CreateDate: 2019/5/17 16:18
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/5/17 16:18
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Repository("fileDao")
public class FileDaoImpl extends AbstractDao<Integer, File> implements FileDao {
    static final Logger logger = LoggerFactory.getLogger(FileDao.class);

    @Override
    public File findById(String id) {
        logger.info("id : {}", id);
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("id", id));
        File file = (File) crit.uniqueResult();
        return file;
    }

    @Override
    public void save(File file) {
        persist(file);
    }

    @Override
    public void deletById(String id) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("id", id));
        File file = (File) crit.uniqueResult();
        delete(file);
    }
}
