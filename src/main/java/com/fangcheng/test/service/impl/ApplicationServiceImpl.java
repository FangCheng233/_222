package com.fangcheng.test.service.impl;

import com.fangcheng.test.dao.ApplicationDao;
import com.fangcheng.test.entity.Application;
import com.fangcheng.test.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ProjectName: _222
 * @Package: com.fangcheng.test.service.impl
 * @ClassName: ApplicationServiceImpl
 * @Description: java类作用描述
 * @Author: FangCheng
 * @CreateDate: 2019/4/13 12:29
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/4/13 12:29
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Service("applicationService")
@Transactional
public class ApplicationServiceImpl implements ApplicationService {
    @Autowired
    private ApplicationDao applicationDao;

    @Override
    public Application findByApplicationNumber(String applicationNumber) {
        return applicationDao.findByApplicationNumber(applicationNumber);
    }

    @Override
    public void save(Application application) {
        applicationDao.save(application);
    }

    @Override
    public void alterApplication(Application application) {
        Application entity = applicationDao.findByApplicationNumber(application.getApplicationNumber());
        if(entity!=null) {}

    }

    @Override
    public void deleteApplicationByApplicationNumber(String applicationNumber) {
        applicationDao.deletByApplicationNumber(applicationNumber);
    }

    @Override
    public List<Application> findAllApplication() {
        return applicationDao.findAllApplication();
    }

    @Override
    public List<Application> findByUserId(String userId) {
        return applicationDao.findByUserId(userId);
    }

    @Override
    public List<Application> findBySchoolYear(String schoolYear) {
        return applicationDao.findBySchoolYear(schoolYear);
    }

    @Override
    public boolean isApplicationNumberUnique(String applicationNumber) {
        Application application = findByApplicationNumber(applicationNumber);
        return ( application == null || application.getApplicationNumber() == applicationNumber);
    }

}
