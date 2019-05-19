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
 * @ClassName: TableClass
 * @Description: java类作用描述
 * @Author: FangCheng
 * @CreateDate: 2019/4/19 16:26
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/4/19 16:26
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Entity
@Table(name = "TABLE_CLASS")
public class TableClass implements Serializable {
    @Id
    @NotNull
    @Column(name = "CLASS_ID")
    private String classId;

    @NotNull
    @Column(name = "CLASS_NAME")
    private String className;

    @NotNull
    @Column(name = "TEACHER_ID")
    private String teacherId;

    @NotNull
    @Column(name = "TEACHER_NAME")
    private String teacherName;

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    @Override
    public String toString() {
        return "TableClass [classId=" + classId + ", className=" + className + ", teacherId=" + teacherId
                +", teacherName=" + teacherName +  "]";
    }
}
