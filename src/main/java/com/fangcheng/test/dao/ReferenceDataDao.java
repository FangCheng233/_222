package com.fangcheng.test.dao;

import com.fangcheng.test.entity.ReferenceData;

import java.util.List;

/**
 * @ProjectName: _222
 * @Package: com.fangcheng.test.dao
 * @ClassName: ReferenceDataDao
 * @Description: java类作用描述
 * @Author: FangCheng
 * @CreateDate: 2019/5/13 14:57
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/5/13 14:57
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface ReferenceDataDao {
    ReferenceData findBySchoolYear(String schoolYear);
    List<ReferenceData> findAll();
    void save(ReferenceData referenceData);
}
