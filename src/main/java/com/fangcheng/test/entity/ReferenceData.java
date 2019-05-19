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
 * @ClassName: ReferenceData
 * @Description: java类作用描述
 * @Author: FangCheng
 * @CreateDate: 2019/5/13 14:41
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/5/13 14:41
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Entity
@Table(name = "REFERENCE_DATA")
public class ReferenceData implements Serializable {
    @Id
    @NotNull
    @Column(name = "SCHOOLYEAR")
    private String schoolYear;
    @NotNull
    @Column(name = "LIVING_EXPENSES")
    private int livingExpenses;

    @Column(name = "TUITION1")
    private int tuition1;
    @Column(name = "TUITION2")
    private int tuition2;
    @Column(name = "TUITION3")
    private int tuition3;
    @Column(name = "TUITION4")
    private int tuition4;
    @Column(name = "TUITION5")
    private int tuition5;
    @Column(name = "TUITION6")
    private int tuition6;
    @Column(name = "TUITION7")
    private int tuition7;
    @Column(name = "TUITION8")
    private int tuition8;
    @Column(name = "TUITION9")
    private int tuition9;
    @Column(name = "TUITION10")
    private int tuition10;
    @Column(name = "TUITION11")
    private int tuition11;

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public int getLivingExpenses() {
        return livingExpenses;
    }

    public void setLivingExpenses(int livingExpenses) {
        this.livingExpenses = livingExpenses;
    }

    public int getTuition1() {
        return tuition1;
    }

    public void setTuition1(int tuition1) {
        this.tuition1 = tuition1;
    }

    public int getTuition2() {
        return tuition2;
    }

    public void setTuition2(int tuition2) {
        this.tuition2 = tuition2;
    }

    public int getTuition3() {
        return tuition3;
    }

    public void setTuition3(int tuition3) {
        this.tuition3 = tuition3;
    }

    public int getTuition4() {
        return tuition4;
    }

    public void setTuition4(int tuition4) {
        this.tuition4 = tuition4;
    }

    public int getTuition5() {
        return tuition5;
    }

    public void setTuition5(int tuition5) {
        this.tuition5 = tuition5;
    }

    public int getTuition6() {
        return tuition6;
    }

    public void setTuition6(int tuition6) {
        this.tuition6 = tuition6;
    }

    public int getTuition7() {
        return tuition7;
    }

    public void setTuition7(int tuition7) {
        this.tuition7 = tuition7;
    }

    public int getTuition8() {
        return tuition8;
    }

    public void setTuition8(int tuition8) {
        this.tuition8 = tuition8;
    }

    public int getTuition9() {
        return tuition9;
    }

    public void setTuition9(int tuition9) {
        this.tuition9 = tuition9;
    }

    public int getTuition10() {
        return tuition10;
    }

    public void setTuition10(int tuition10) {
        this.tuition10 = tuition10;
    }

    public int getTuition11() {
        return tuition11;
    }

    public void setTuition11(int tuition11) {
        this.tuition11 = tuition11;
    }
    @Override
    public String toString() {
        return "ReferenceData [schoolYear=" + schoolYear + ", livingExpenses=" + livingExpenses + ", tuition1=" + tuition1
                + ", tuition2=" + tuition2 + ", tuition3=" + tuition3 + ", tuition4=" + tuition4
                + ", tuition5=" + tuition5 + ", tuition6=" + tuition6 + ", tuition7=" + tuition7
                + ", tuition8=" + tuition8 + ", tuition9=" + tuition9 + ", tuition10=" + tuition10
                + ", tuition11=" + tuition11 + "]";/*", basePlaceP=" + basePlaceP+ ", basePlaceC=" + basePlaceC
                + ", basePlaceA=" + basePlaceA + ", userSecurity=" + userSecurity + ", securityAnwser=" + securityAnwser
                + ", idType=" + idType + ", nation=" + nation  +", politicalOutlook=" + politicalOutlook
                + ", nameUsedBefore=" + nameUsedBefore + ", QQ=" + QQ + ", postalAddress=" + postalAddress
                + ", postalNumber=" + postalNumber+*/
    }
}
