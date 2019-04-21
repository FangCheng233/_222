package com.fangcheng.test.service;

import com.fangcheng.test.entity.TableApproval;

import java.util.List;

/**
 * @ProjectName: _222
 * @Package: com.fangcheng.test.service
 * @ClassName: TableApprovalService
 * @Description: java类作用描述
 * @Author: FangCheng
 * @CreateDate: 2019/4/18 16:20
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/4/18 16:20
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface TableApprovalService {
    List<TableApproval> findAllApproval();
    List<TableApproval> findByApplicationNumber(String applicationNumber);
    void save(TableApproval tableApproval);
}
