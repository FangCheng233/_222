package com.fangcheng.test.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @ProjectName: _222
 * @Package: com.fc.test.entity
 * @ClassName: User
 * @Description: java类作用描述
 * @Author: FangCheng
 * @CreateDate: 2019/3/24 14:49
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/3/24 14:49
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Entity
@Table(name = "TABLE_USER")
public class User implements Serializable {
    //学号
    @Id
    @NotNull
    @Column(name="USER_ID", unique=true, nullable=false)
    private String userId;
    //密码
    @NotNull
    @Column(name = "PASSWORD",nullable = false)
    private String password;
    //姓名
    @NotNull
    @Column(name="USER_NAME", nullable=false)
    private String userName;
    //曾用名
    @Column(name="NAME_USED_BEFORE", nullable=false)
    private String nameUsedBefore;
    //组织   学生  辅导员  学工部
    @Column(name="GROUP_ID", nullable=false)
    private String groupId;
    //创建时间
    @Column(name="CREAT_TIME", nullable=true)
    private String creatTime;
    //性别
    @Column(name="USER_SEX", nullable=true)
    private String userSex;
    //班级
    @Column(name="USER_CLASS", nullable=true)
    private String userClass;
    //专业
    @Column(name="USER_MAJOR", nullable=true)
    private String userMajor;
    //学院
    @Column(name="USER_COLLEGE", nullable=true)
    private String userCollege;
    //QQ
    @Column(name="QQ", nullable=true)
    private String QQ;
    //邮箱
    @Column(name="USER_EMAIL", nullable=true)
    private String userEmail;
    //年级
    @Column(name="USER_GRADE", nullable=true)
    private String userGrade;
    //出生日期
    @Column(name="BIRTH_DATE", nullable=true)
    private Date birthDate;
    //手机号
    @Column(name="PHONE_NUMBER", nullable=true)
    private String phoneNumber;
    //身份证号
    @Column(name="ID_NUMBER", nullable=true)
    private String idNumber;
    //证件类型
    @Column(name="ID_TYPE", nullable=true)
    private String idType;
    //民族
    @Column(name="NATION", nullable=true)
    private String nation;
    //政治面貌
    @Column(name="POLITICAL_OUTLOOK", nullable=true)
    private String politicalOutlook;
    //籍贯-省
    @Column(name="BASE_PLACE_P", nullable=true)
    private String basePlaceP;
    //籍贯-市
    @Column(name="BASE_PLACE_C", nullable=true)
    private String basePlaceC;
    //籍贯-区
    @Column(name="BASE_PLACE_A", nullable=true)
    private String basePlaceA;
    //地址
    @Column(name="ADDRESS", nullable=true)
    private String postalAddress;
    //邮编
    @Column(name="POSTALNUMBER", nullable=true)
    private String postalNumber;
    //密保问题
    @Column(name="USER_SECURITY", nullable=true)
    private String userSecurity;
    //密保答案
    @Column(name="SECURITY_ANWSER", nullable=true)
    private String securityAnwser;
    //初始贫困级别
    @Column(name="ISPOOR", nullable=true)
    private String isPoor;
    //初始评定结果
    @Column(name="PRE_AUDIT_REMARKS", nullable=true)
    private String preAuditRemarks;
    //减免金额
    @Column(name="WAIVER_AMOUNT", nullable=true)
    private Integer waiverAmount;


    public String getUserId(){ return userId; }
    public void setUserId(String userId) {this.userId = userId; }

    public String getPassword() { return password;}
    public void setPassword(String password) { this.password = password; }

    public String getUserName() { return userName;}
    public void setUserName(String userName) { this.userName = userName; }

    public String getNameUsedBefore() {return nameUsedBefore; }
    public void setNameUsedBefore(String nameUsedBefore) {this.nameUsedBefore = nameUsedBefore; }

    public String getGroupId() { return groupId;    }
    public void setGroupId(String groupId) { this.groupId = groupId; }

    public String getCreatTime() { return creatTime; }
    public void setCreatTime(String creatTime) {this.creatTime = creatTime; }

    public String getUserSex() { return userSex;}
    public void setUserSex(String userSex) { this.userSex = userSex; }

    public String getUserClass() { return userClass;}
    public void setUserClass(String userClass) { this.userClass = userClass; }

    public String getUserMajor() { return userMajor;}
    public void setUserMajor(String userMajor) { this.userMajor = userMajor; }

    public String getUserCollege() { return userCollege;}
    public void setUserCollege(String userCollege) { this.userCollege = userCollege; }

    public String getQQ() { return QQ; }
    public void setQQ(String QQ) { this.QQ = QQ; }

    public String getUserEmail() { return userEmail;}
    public void setUserEmail(String userEmail) { this.userEmail = userEmail; }

    public String getUserGrade() { return userGrade;}
    public void setUserGrade(String userGrade) { this.userGrade = userGrade; }

    public Date getBirthDate() { return birthDate;}
    public void setBirthDate(Date birthDate) { this.birthDate = birthDate; }

    public String getPhoneNumber() { return phoneNumber;}
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getIdNumber() { return idNumber;}
    public void setIdNumber(String idNumber) { this.idNumber = idNumber; }

    public String getIdType() { return idType; }
    public void setIdType(String idType) { this.idType = idType; }

    public String getPoliticalOutlook() {return politicalOutlook; }
    public void setPoliticalOutlook(String politicalOutlook) {this.politicalOutlook = politicalOutlook; }

    public String getNation() { return nation; }
    public void setNation(String nation) { this.nation = nation; }

    public String getBasePlaceP() { return basePlaceP; }
    public void setBasePlaceP(String basePlaceP) { this.basePlaceP = basePlaceP; }

    public String getBasePlaceC() { return basePlaceC; }
    public void setBasePlaceC(String basePlaceC) { this.basePlaceC = basePlaceC; }

    public String getBasePlaceA() { return basePlaceA; }
    public void setBasePlaceA(String basePlaceA) { this.basePlaceA = basePlaceA; }

    public String getPostalNumber() {
        return postalNumber;
    }

    public void setPostalNumber(String postalNumber) {
        this.postalNumber = postalNumber;
    }

    public String getUserSecurity() { return userSecurity;}
    public void setUserSecurity(String userSecurity) { this.userSecurity = userSecurity; }

    public String getSecurityAnwser() { return securityAnwser;}
    public void setSecurityAnwser(String securityAnwser) { this.securityAnwser = securityAnwser; }

    public String getPostalAddress() { return postalAddress; }
    public void setPostalAddress(String postalAddress) { this.postalAddress = postalAddress; }

    public String getIsPoor() { return isPoor; }
    public void setIsPoor(String isPoor) { this.isPoor = isPoor; }

    public String getPreAuditRemarks() { return preAuditRemarks; }
    public void setPreAuditRemarks(String preAuditRemarks) { this.preAuditRemarks = preAuditRemarks; }

    public Integer getWaiverAmount() { return waiverAmount; }
    public void setWaiverAmount(Integer waiverAmount) { this.waiverAmount = waiverAmount; }

    @NotEmpty
    @ManyToMany(cascade=CascadeType.REFRESH)
    @JoinTable(name = "ROLE_AUTHOR",
    joinColumns = { @JoinColumn(name = "RF_USER_ID") },
    inverseJoinColumns = { @JoinColumn(name = "RF_AUTHOR_ID") })

    private Set<TableAuthor> tableAuthors = new HashSet<TableAuthor>();
    public Set<TableAuthor> getTableAuthors() { return tableAuthors; }
    public void setTableAuthors(Set<TableAuthor> tableAuthors) { this.tableAuthors = tableAuthors; }

    @Override
    public String toString() {
        return "[userId=" + userId + ", password=" + password + ", userName=" + userName
                + ", groupId=" + groupId + ", userSex=" + userSex + ", userClass=" + userClass
                + ", userMajor=" + userMajor + ", userCollege=" + userCollege +", userEmail=" + userEmail
                + ", userGrade=" + userGrade + ", birthDate=" + birthDate + ", phoneNumber=" + phoneNumber
                + ", idNumber=" + idNumber +", basePlaceP=" + basePlaceP+ ", basePlaceC=" + basePlaceC
                + ", basePlaceA=" + basePlaceA + ", userSecurity=" + userSecurity + ", securityAnwser=" + securityAnwser
                + ", idType=" + idType + ", nation=" + nation  +", politicalOutlook=" + politicalOutlook
                + ", nameUsedBefore=" + nameUsedBefore + ", QQ=" + QQ + ", postalAddress=" + postalAddress
                + ", postalNumber=" + postalNumber + ", isPoor=" + isPoor + ", preAuditRemarks=" + preAuditRemarks
                + ", waiverAmount=" + waiverAmount+"]";/**/
    }
}
