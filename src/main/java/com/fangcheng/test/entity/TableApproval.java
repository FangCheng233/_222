package com.fangcheng.test.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @ProjectName: _222
 * @Package: com.fangcheng.test.entity
 * @ClassName: TableApproval
 * @Description: java类作用描述
 * @Author: FangCheng
 * @CreateDate: 2019/4/18 15:18
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/4/18 15:18
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Entity
@Table(name = "TABLE_APPROVAL")
public class TableApproval implements Serializable {
    //主键自增
    @Id
    @Column(name = "ID")
    private Integer id = 0;
    //审批编号
    @NotNull
    @Column(name = "APPLICATION_NUMBER")
    private String applicationNumber;
    //流程节点
    @NotNull
    @Column(name = "PROCESS_NODE")
    private String processNode;
    //审批状态
    @NotNull
    @Column(name = "APPROVAL_STATUS")
    private String approvalStatus;
    //审批人
    @NotNull
    @Column(name = "USER_NAME")
    private String userName;
    //备注
    @Column(name = "REMARKS")
    private String remarks;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApplicationNumber() {
        return applicationNumber;
    }

    public void setApplicationNumber(String applicationNumber) {
        this.applicationNumber = applicationNumber;
    }

    public String getProcessNode() {
        return processNode;
    }

    public void setProcessNode(String processNode) {
        this.processNode = processNode;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    @Override
    public String toString() {
        return "TableApproval [applicationNumber=" + applicationNumber + ", processNode=" + processNode + ", approvalStatus=" + approvalStatus
                + ", userName=" + userName + ", remarks=" + remarks + ", id=" + id+"]";
    }
}
