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

    public void update(Application application){
        Application entity = applicationDao.findByApplicationNumber(application.getApplicationNumber());
        //系统评级
        entity.setSystemAudit(application.getSystemAudit());
        //系统评分
        entity.setSystemValue(application.getSystemValue());
        //备注
        entity.setRemarks(application.getRemarks());
    }
    @Override
    public void alterApplication(Application application) {
        Application entity = applicationDao.findByApplicationNumber(application.getApplicationNumber());
        if(entity!=null) {
            entity.setProcessNode(application.getProcessNode());
            entity.setApprovalStatus(application.getApprovalStatus());
            entity.setStatusNodes(application.getStatusNodes());
        }

    }

    @Override
    public void deleteApplicationByApplicationNumber(String applicationNumber) {
        applicationDao.deleteByApplicationNumber(applicationNumber);
    }
    @Override
    public void deleteAllApplicationByUserId(String userId) {
        List<Application> applicationList = applicationDao.findByUserId(userId);
        for(Application application:applicationList){
            try{
                applicationDao.deleteByApplicationNumber(application.getApplicationNumber());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
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
    public List<Application> findByProcessNode(String processNode) {
        return applicationDao.findByProcessNode(processNode);
    }

    @Override
    public List<Application> findByStatusNodes(Integer statusNodes) {
        return applicationDao.findByStatusNodes(statusNodes);
    }

    @Override
    public boolean isApplicationNumberUnique(String applicationNumber) {
        Application application = findByApplicationNumber(applicationNumber);
        return ( application == null || application.getApplicationNumber() == applicationNumber);
    }

}
