package com.fangcheng.test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
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
    @NotEmpty
    @Column(name = "APPLICATION_NUMBER")
    private String applicationNumber;

    @NotEmpty
    @Column(name = "USER_ID")
    private String userId;

    @NotEmpty
    @Column(name = "SCHOOL_YEAR")
    private String schoolYear;

    @NotEmpty
    @Column(name = "POVERTY_LEVEL")
    private String povertyLevel;

    @NotEmpty
    @Column(name = "YEARLY_INCOME")
    private int yearlyIncome;

    @NotEmpty
    @Column(name = "POPULATION_SIZE")
    private int populationSize;

    @NotEmpty
    @Column(name = "PER_CAPITA_INCOME")
    private int perCapitaIncome;

    @Column(name = "LIABILITIES")
    private String liabilities;

    @Column(name = "NATURAL_DISASTER")
    private String naturalDisaster;

    @Column(name = "UNEXPECTED_ACCIDENT")
    private String unexpectedAccident;

    @Column(name = "MEMBERSHIP_SITUATION")
    private String membershipSituation;

    @Column(name = "UNEMPLOYMENT_SITUATION")
    private String unemploymentSituation;

    @Column(name = "OTHER_SITUATION")
    private String otherSituation;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "POSTAL_ADDRESS")
    private String postalAddress;

    @Column(name = "POSTAL_CODE")
    private int postalCode;

    @Column(name = "ADDRESSEE")
    private String addressee;

    @Column(name = "CONTACT_NUMBER")
    private int contactNumber;

    @Column(name = "EMERGENCY_CONTACT")
    private String emeergencyContact;

    @Column(name = "EMERGENCY_CONTACT_NUMBER")
    private int emeergencyContactNumber;

    @NotEmpty
    @Column(name = "REASONS_FOR_APPLICATION",columnDefinition = "TEXT",nullable = false)
    private String reasonsForApplication;

    @NotEmpty
    @Column(name = "APPROVAL_STATUS",nullable = false)
    private String approvalStatus;

    @NotEmpty
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

    public String getUnemploymentSituation() {
        return unemploymentSituation;
    }

    public void setUnemploymentSituation(String unemploymentSituation) {
        this.unemploymentSituation = unemploymentSituation;
    }

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

    public void setPostalAddress(String postalAddress) {
        this.postalAddress = postalAddress;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public String getAddressee() {
        return addressee;
    }

    public void setAddressee(String addressee) {
        this.addressee = addressee;
    }

    public int getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(int contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmeergencyContact() {
        return emeergencyContact;
    }

    public void setEmeergencyContact(String emeergencyContact) {
        this.emeergencyContact = emeergencyContact;
    }

    public int getEmeergencyContactNumber() {
        return emeergencyContactNumber;
    }

    public void setEmeergencyContactNumber(int emeergencyContactNumber) {
        this.emeergencyContactNumber = emeergencyContactNumber;
    }

    public String getReasonsForApplication() {
        return reasonsForApplication;
    }

    public void setReasonsForApplication(String reasonsForApplication) {
        this.reasonsForApplication = reasonsForApplication;
    }

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
                + ", approvalStatus=" + approvalStatus + ", processNode=" + processNode+",]";
    }

}
