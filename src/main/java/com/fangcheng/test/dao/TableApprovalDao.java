package com.fangcheng.test.dao;

import com.fangcheng.test.entity.TableApproval;

import java.util.List;

/**
 * @ProjectName: _222
 * @Package: com.fangcheng.test.dao
 * @ClassName: TableApprovalServiceDao
 * @Description: java类作用描述
 * @Author: FangCheng
 * @CreateDate: 2019/4/18 16:24
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/4/18 16:24
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface TableApprovalDao {
    List<TableApproval> findAllApproval();
    void save(TableApproval tableApproval);
}
