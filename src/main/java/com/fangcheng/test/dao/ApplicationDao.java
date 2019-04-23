package com.fangcheng.test.dao;

import com.fangcheng.test.entity.Application;

import java.util.List;

/**
 * @ProjectName: _222
 * @Package: com.fangcheng.test.dao
 * @ClassName: ApplicationDao
 * @Description: java类作用描述
 * @Author: FangCheng
 * @CreateDate: 2019/3/28 16:30
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/3/28 16:30
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface ApplicationDao {
    Application findByApplicationNumber(String applicationNumber);

    List<Application> findAllApplication();
    List<Application> findByUserId(String userId);
    List<Application> findBySchoolYear(String schoolYear);
    List<Application> findByProcessNode(String processNode);
    void save(Application application);

    void  deletByApplicationNumber(String applicationNumber);

}
