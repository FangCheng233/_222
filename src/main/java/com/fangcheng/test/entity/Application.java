package com.fangcheng.test.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @ProjectName: _222
 * @Package: com.fangcheng.test.entity
 * @ClassName: Application
 * @Description: java类作用描述
 * @Author: FangCheng
 * @CreateDate: 2019/3/28 14:32
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/3/28 14:32
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Entity
@Table(name = "TABLE_APPLICATION")
public class Application implements Serializable {
    @Id
    @NotNull
    @Column(name = "APPLICATION_NUMBER")
    private String applicationNumber;
    //学号
    @NotNull
    @Column(name = "USER_ID")
    private String userId;
    //学年
    @NotNull
    @Column(name = "SCHOOL_YEAR")
    private String schoolYear;
    //贫困申请级别
    @NotNull
    @Column(name = "POVERTY_LEVEL")
    private String povertyLevel;
    //年收入
    @NotNull
    @Column(name = "YEARLY_INCOME")
    private int yearlyIncome;
    //家庭人口数
    @NotNull
    @Column(name = "POPULATION_SIZE")
    private int populationSize;
    //当地最低生活保障
    @Column(name = "GUARANTEE")
    private int guarantee;
    //负债情况
    @Column(name = "LIABILITIES")
    private String liabilities;
    //饮食比例
    @Column(name = "PERCENTAGE")
    private String percentage;
    //自然灾害
    @Column(name = "NATURAL_DISASTER")
    private String naturalDisaster;
    //意外灾害
    @Column(name = "UNEXPECTED_ACCIDENT")
    private String unexpectedAccident;
    //成员情况
    @Column(name = "MEMBERSHIP_SITUATION")
    private String membershipSituation;
    //失业情况
    @Column(name = "UNEMPLOYMENT_SITUATION")
    private String unemploymentSituation;
    //受资助情况
    @Column(name = "FUNDED_SITUATION")
    private String fundedSituation;
    //其他情况
    @Column(name = "OTHER_SITUATION")
    private String otherSituation;
    //地址
    @Column(name = "ADDRESS")
    private String address;
    //家庭通讯地址
    @Column(name = "POSTAL_ADDRESS")
    private String postalAddress;
    //邮政编码
    @Column(name = "POSTAL_CODE")
    private int postalCode;
    //联系人
    @Column(name = "ADDRESSEE")
    private String addressee;
    //联系人电话
    @Column(name = "CONTACT_NUMBER")
    private String contactNumber;
    //紧急联系人
    @Column(name = "EMERGENCY_CONTACT")
    private String emergencyContact;
    //紧急联系人电话
    @Column(name = "EMERGENCY_CONTACT_NUMBER")
    private String emergencyContactNumber;
    //申请理由
    @NotNull
    @Column(name = "REASONS_FOR_APPLICATION",columnDefinition = "TEXT",nullable = false)
    private String reasonsForApplication;
    //申请状态
    @NotNull
    @Column(name = "APPROVAL_STATUS",nullable = false)
    private String approvalStatus;
    //流程节点
    @NotNull
    @Column(name = "PROCESS_NODE",nullable = false)
    private String processNode;
    //状态节点 1 代表学生  2 代表辅导员 3 代表院系  4 代表学工部流程结束 归档
    @NotNull
    @Column(name = "STATUS_NODES",nullable = false)
    private Integer statusNodes;
    //系统审核结果
    @Column(name = "SYSTEM_AUDIT",nullable = false)
    private String systemAudit;
    //系统审核评分
    @Column(name = "SYSTEM_VALUE",nullable = false)
    private Integer systemValue;
    //系统备注
    @Column(name = "REMARKS")
    private String remarks;
    //证明
    @Column(name = "PROVE")
    private String prove;
    //医疗支出
    @Column(name = "MEDICAL")
    private String medical;



    public String getApplicationNumber() { return applicationNumber; }
    public void setApplicationNumber(String applicationNumber) { this.applicationNumber = applicationNumber; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getSchoolYear() { return schoolYear; }
    public void setSchoolYear(String schoolYear) { this.schoolYear = schoolYear; }

    public String getPovertyLevel() {
        return povertyLevel;
    }

    public void setPovertyLevel(String povertyLevel) {
        this.povertyLevel = povertyLevel;
    }

    public int getYearlyIncome() {
        return yearlyIncome;
    }

    public void setYearlyIncome(int yearlyIncome) {
        this.yearlyIncome = yearlyIncome;
    }

    public int getPopulationSize() {
        return populationSize;
    }

    public void setPopulationSize(int populationSize) {
        this.populationSize = populationSize;
    }

    public int getGuarantee() {
        return guarantee;
    }

    public void setGuarantee(int guarantee) {
        this.guarantee = guarantee;
    }

    public String getLiabilities() {
        return liabilities;
    }

    public void setLiabilities(String liabilities) {
        this.liabilities = liabilities;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public String getNaturalDisaster() {
        return naturalDisaster;
    }

    public void setNaturalDisaster(String naturalDisaster) {
        this.naturalDisaster = naturalDisaster;
    }

    public String getUnexpectedAccident() {
        return unexpectedAccident;
    }

    public void setUnexpectedAccident(String unexpectedAccident) {
        this.unexpectedAccident = unexpectedAccident;
    }

    public String getMembershipSituation() {
        return membershipSituation;
    }

    public void setMembershipSituation(String membershipSituation) {
        this.membershipSituation = membershipSituation;
    }

    public String getUnemploymentSituation() { return unemploymentSituation; }
    public void setUnemploymentSituation(String unemploymentSituation) {this.unemploymentSituation = unemploymentSituation; }

    public String getFundedSituation() { return fundedSituation; }
    public void setFundedSituation(String fundedSituation) { this.fundedSituation = fundedSituation; }

    public String getOtherSituation() {
        return otherSituation;
    }
    public void setOtherSituation(String otherSituation) {
        this.otherSituation = otherSituation;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalAddress() {
        return postalAddress;
    }
    public void setPostalAddress(String postalAddress) { this.postalAddress = postalAddress; }

    public int getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(int postalCode) { this.postalCode = postalCode; }

    public String getAddressee() {
        return addressee;
    }
    public void setAddressee(String addressee) {
        this.addressee = addressee;
    }

    public String getContactNumber() {
        return contactNumber;
    }
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }
    public void setEmergencyContact(String emergencyContact) { this.emergencyContact = emergencyContact; }

    public String getEmergencyContactNumber() {
        return emergencyContactNumber;
    }
    public void setEmergencyContactNumber(String emergencyContactNumber) { this.emergencyContactNumber = emergencyContactNumber; }

    public String getReasonsForApplication() {
        return reasonsForApplication;
    }
    public void setReasonsForApplication(String reasonsForApplication) { this.reasonsForApplication = reasonsForApplication; }

    public String getApprovalStatus() {
        return approvalStatus;
    }
    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getProcessNode() {
        return processNode;
    }
    public void setProcessNode(String processNode) {
        this.processNode = processNode;
    }

    public Integer getStatusNodes() {return statusNodes; }
    public void setStatusNodes(Integer statusNodes) {this.statusNodes = statusNodes; }

    public String getSystemAudit() {
        return systemAudit;
    }

    public void setSystemAudit(String systemAudit) {
        this.systemAudit = systemAudit;
    }

    public Integer getSystemValue() {
        return systemValue;
    }

    public void setSystemValue(Integer systemValue) {
        this.systemValue = systemValue;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getProve() {
        return prove;
    }

    public void setProve(String prove) {
        this.prove = prove;
    }

    //申请书对象
    @Override
    public String toString() {
        return "Application [applicationNumber=" + applicationNumber + ", userId=" + userId + ", schoolYear=" + schoolYear
                + ", povertyLevel=" + povertyLevel + ", yearlyIncome=" + yearlyIncome + ", populationSize=" + populationSize
                + ", guarantee=" + guarantee + ", liabilities=" + liabilities +", naturalDisaster=" + naturalDisaster
                + ", unexpectedAccident=" + unexpectedAccident + ", membershipSituation=" + membershipSituation + ", unemploymentSituation=" + unemploymentSituation
                +", otherSituation=" + otherSituation + ", address=" + address + ", postalAddress=" + postalAddress
                +", postalCode=" + postalCode + ", addressee=" + addressee + ", contactNumber=" + contactNumber
                +", emergencyContact=" + emergencyContact + ", emergencyContactNumber=" + emergencyContactNumber + ", reasonsForApplication=" + reasonsForApplication
                + ", approvalStatus=" + approvalStatus + ", processNode=" + processNode + ", fundedSituation=" + fundedSituation
                + ", statusNodes=" + statusNodes  + ", systemAudit=" + systemAudit + ", systemValue=" + systemValue
                + ", remarks=" + remarks + ", percentage=" + percentage + ", prove=" + prove +",]";
    }

}
