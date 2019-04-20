package com.fangcheng.test.service.impl;

import com.fangcheng.test.dao.TableClassDao;
import com.fangcheng.test.entity.TableClass;
import com.fangcheng.test.service.TableClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ProjectName: _222
 * @Package: com.fangcheng.test.service.impl
 * @ClassName: TableClassServiceImpl
 * @Description: java类作用描述
 * @Author: FangCheng
 * @CreateDate: 2019/4/20 12:30
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/4/20 12:30
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Service("tableClassService")
@Transactional
public class TableClassServiceImpl implements TableClassService {
    @Autowired
    private TableClassDao tableClassDao;

    @Override
    public TableClass findByClassId(String classId) {
        return tableClassDao.findByClassId(classId);
    }

    @Override
    public void saveClass(TableClass tableClass) {
    tableClassDao.save(tableClass);
    }

    @Override
    public void alertClassInfo(TableClass tableClass) {

    }

    @Override
    public void deleteClassByClassId(String classId) {
        tableClassDao.delteByClassId(classId);
    }

    @Override
    public List<TableClass> findAllClass() {
        return tableClassDao.findAllClass();
    }

    @Override
    public List<TableClass> findByTeacherId(String teacherId) {
        return tableClassDao.findByTeacherId(teacherId);
    }

    @Override
    public boolean isClassIdUnique(String classId) {
        TableClass tableClass = findByClassId(classId);
        return ( tableClass == null || tableClass.getClassId() == classId);
    }
}
