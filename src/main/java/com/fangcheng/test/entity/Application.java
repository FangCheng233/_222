package com.fangcheng.test.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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
    //人均年收入
    @NotNull
    @Column(name = "PER_CAPITA_INCOME")
    private int perCapitaIncome;
    //负债情况
    @Column(name = "LIABILITIES")
    private String liabilities;
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
    private String emeergencyContact;
    //紧急联系人电话
    @Column(name = "EMERGENCY_CONTACT_NUMBER")
    private String emeergencyContactNumber;
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

    public int getPerCapitaIncome() {
        return perCapitaIncome;
    }

    public void setPerCapitaIncome(int perCapitaIncome) {
        this.perCapitaIncome = perCapitaIncome;
    }

    public String getLiabilities() {
        return liabilities;
    }

    public void setLiabilities(String liabilities) {
        this.liabilities = liabilities;
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

    public String getEmeergencyContact() {
        return emeergencyContact;
    }
    public void setEmeergencyContact(String emeergencyContact) {
        this.emeergencyContact = emeergencyContact;
    }

    public String getEmeergencyContactNumber() {
        return emeergencyContactNumber;
    }
    public void setEmeergencyContactNumber(String emeergencyContactNumber) { this.emeergencyContactNumber = emeergencyContactNumber; }

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

/*    @NotEmpty
    @ManyToMany(cascade=CascadeType.REFRESH)
    @JoinTable(name = "TABLE_APPROVAL",
            joinColumns = { @JoinColumn(name = "APPLICATION_NUMBER") },
            inverseJoinColumns = { @JoinColumn(name = "RF_AUTHOR_ID") })
    private Set<TableAuthor> tableAuthors = new HashSet<TableAuthor>();

    public Set<TableAuthor> getTableAuthors() { return tableAuthors; }

    public void setTableAuthors(Set<TableAuthor> tableAuthors) {
        this.tableAuthors = tableAuthors; }*/
    //申请书对象
    @Override
    public String toString() {
        return "Application [applicationNumber=" + applicationNumber + ", userId=" + userId + ", schoolYear=" + schoolYear
                + ", povertyLevel=" + povertyLevel + ", yearlyIncome=" + yearlyIncome + ", populationSize=" + populationSize
                + ", perCapitaIncome=" + perCapitaIncome + ", liabilities=" + liabilities +", naturalDisaster=" + naturalDisaster
                + ", unexpectedAccident=" + unexpectedAccident + ", membershipSituation=" + membershipSituation + ", unemploymentSituation=" + unemploymentSituation
                +", otherSituation=" + otherSituation + ", address=" + address + ", postalAddress=" + postalAddress
                +", postalCode=" + postalCode + ", addressee=" + addressee + ", contactNumber=" + contactNumber
                +", emeergencyContact=" + emeergencyContact + ", emeergencyContactNumber=" + emeergencyContactNumber + ", reasonsForApplication=" + reasonsForApplication
                + ", approvalStatus=" + approvalStatus + ", processNode=" + processNode + ", fundedSituation=" + fundedSituation+",]";
    }

}
