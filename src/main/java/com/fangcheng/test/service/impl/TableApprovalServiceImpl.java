package com.fangcheng.test.service.impl;

import com.fangcheng.test.dao.ApplicationDao;
import com.fangcheng.test.dao.TableApprovalDao;
import com.fangcheng.test.entity.Application;
import com.fangcheng.test.entity.TableApproval;
import com.fangcheng.test.service.TableApprovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ProjectName: _222
 * @Package: com.fangcheng.test.service.impl
 * @ClassName: TableApprovalServiceImpl
 * @Description: java类作用描述
 * @Author: FangCheng
 * @CreateDate: 2019/4/18 16:23
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/4/18 16:23
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Service("tableApprovalService")
@Transactional
public class TableApprovalServiceImpl implements TableApprovalService {
    @Autowired
    private TableApprovalDao tableApprovalDao;
    @Autowired
    private ApplicationDao applicationDao;

    @Override
    public List<TableApproval> findAllApproval() {
        return tableApprovalDao.findAllApproval();
    }

    @Override
    public List<TableApproval> findByApplicationNumber(String applicationNumber) {
        return tableApprovalDao.findByApplicationNumber(applicationNumber);
    }

    @Override
    public TableApproval findById(String id) {
        return null;
    }

    @Override
    public void save(TableApproval tableApproval) {
    tableApprovalDao.save(tableApproval);
    }

    @Override
    public void alterApprovalStatus(TableApproval tableApproval) {
        TableApproval entity = tableApprovalDao.findById(tableApproval.getId());
        entity.setProcessNode(tableApproval.getProcessNode());
        entity.setApprovalStatus(tableApproval.getApprovalStatus());
        entity.setRemarks(tableApproval.getRemarks());
    }

    @Override
    public void deleteAllApprovalByUserId(String userId) {
        List<Application> applicationList = applicationDao.findByUserId(userId);
        for(Application application:applicationList){
            List<TableApproval> tableApprovalList = tableApprovalDao.findByApplicationNumber(application.getApplicationNumber());
            for(TableApproval tableApproval:tableApprovalList){
                try{
                    tableApprovalDao.deleteById(tableApproval.getId());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
