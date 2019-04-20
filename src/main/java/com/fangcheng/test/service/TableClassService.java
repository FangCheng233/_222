package com.fangcheng.test.service;

import com.fangcheng.test.entity.TableClass;

import javax.persistence.Table;
import java.util.List;

/**
 * @ProjectName: _222
 * @Package: com.fangcheng.test.service
 * @ClassName: TableClassService
 * @Description: java类作用描述
 * @Author: FangCheng
 * @CreateDate: 2019/4/20 12:30
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/4/20 12:30
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface TableClassService {
    TableClass findByClassId(String classId);

    void saveClass(TableClass tableClass);

    void alertClassInfo(TableClass tableClass);

    void deleteClassByClassId(String classId);

    List<TableClass> findAllClass();
    List<TableClass> findByTeacherId(String teacherId);
    boolean isClassIdUnique(String classId);
}
