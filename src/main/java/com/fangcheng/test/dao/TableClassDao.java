package com.fangcheng.test.dao;

import com.fangcheng.test.entity.TableClass;

import java.util.List;

/**
 * @ProjectName: _222
 * @Package: com.fangcheng.test.dao
 * @ClassName: TableClassDao
 * @Description: java类作用描述
 * @Author: FangCheng
 * @CreateDate: 2019/4/20 12:31
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/4/20 12:31
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface TableClassDao {
    TableClass findByClassId(String classId);
    void  save(TableClass tableClass);
    void delteByClassId(String classId);
    List<TableClass> findAllClass();
    List<TableClass> findByTeacherId(String teacherId);
}
