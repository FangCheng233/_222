package com.fangcheng.test.service;

import com.fangcheng.test.entity.ReferenceData;

import java.util.List;


/**
 * @ProjectName: _222
 * @Package: com.fangcheng.test.service
 * @ClassName: ReferenceDataService
 * @Description: java类作用描述
 * @Author: FangCheng
 * @CreateDate: 2019/5/13 14:55
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/5/13 14:55
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface ReferenceDataService {
    ReferenceData findBySchoolYear(String schoolYear);
    List<ReferenceData> findAll();
    void updata(ReferenceData referenceData);
    void save(ReferenceData referenceData);
}
