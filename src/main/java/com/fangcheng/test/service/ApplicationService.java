package com.fangcheng.test.service;

import com.fangcheng.test.entity.Application;

import java.util.List;

/**
 * @ProjectName: _222
 * @Package: com.fangcheng.test.service
 * @ClassName: ApplicationService
 * @Description: java类作用描述
 * @Author: FangCheng
 * @CreateDate: 2019/4/13 12:28
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/4/13 12:28
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface ApplicationService {
    Application findByApplicationNumber(String applicationNumber);

    void save(Application application);
    void  alterApplication(Application application);
    void deleteApplicationByApplicationNumber(String application);
    void deleteAllApplicationByUserId(String userId);
    void update(Application application);
    List<Application> findAllApplication();
    List<Application> findByUserId(String userId);
    List<Application> findBySchoolYear(String schoolYear);
    List<Application> findByProcessNode(String processNode);
    List<Application> findByStatusNodes(Integer statusNodes);
    boolean isApplicationNumberUnique(String applicationNumber);

}
