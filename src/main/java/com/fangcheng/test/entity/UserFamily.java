package com.fangcheng.test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @ProjectName: _222
 * @Package: com.fangcheng.test.entity
 * @ClassName: UserFamily
 * @Description: java类作用描述
 * @Author: FangCheng
 * @CreateDate: 2019/4/21 13:30
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/4/21 13:30
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Entity
@Table(name = "TABLE_FAMILY")
public class UserFamily implements Serializable {
    @Id
    @NotNull
    @Column(name = "ID")
    private String id;

    @NotNull
    @Column(name = "USER_ID")
    private String userId;

    @NotNull
    @Column(name = "USER_NAME")
    private String userName;

    @NotNull
    @Column(name = "USER_SEX")
    private String userSex;

    @NotNull
    @Column(name = "USER_AGE")
    private Integer userAge;

    @NotNull
    @Column(name = "RELATIONSHIP")
    private String relationship;

    @Column(name = "HEALTH")
    private String health;


    @Column(name = "OCCUPATION")
    private String occupation;

    @NotNull
    @Column(name = "ANNUAL_INCOME")
    private Integer annualIncome;

    @NotNull
    @Column(name = "WORK_UNIT")
    private String workUnit;

    @NotNull
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;


    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getUserSex() { return userSex; }
    public void setUserSex(String userSex) { this.userSex = userSex; }

    public Integer getUserAge() { return userAge; }
    public void setUserAge(Integer userAge) { this.userAge = userAge; }

    public String getRelationship() { return relationship; }
    public void setRelationship(String relationship) { this.relationship = relationship; }

    public String getHealth() { return health; }
    public void setHealth(String health) { this.health = health; }

    public String getOccupation() { return occupation; }
    public void setOccupation(String occupation) { this.occupation = occupation; }

    public Integer getAnnualIncome() { return annualIncome; }
    public void setAnnualIncome(Integer annualIncome) { this.annualIncome = annualIncome; }

    public String getWorkUnit() { return workUnit; }
    public void setWorkUnit(String workUnit) { this.workUnit = workUnit; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    @Override
    public String toString() {
        return "UserFamily [id=" + id + ", userId=" + userId + ", userSex=" + userSex
                + ", userAge=" + userAge + ", relationship=" + relationship + ", health=" + health
                + ", occupation=" + occupation + ", annualIncome=" + annualIncome +", workUnit=" + workUnit
                + ", phoneNumber=" + phoneNumber +", userName=" + userName +",]";
    }
}