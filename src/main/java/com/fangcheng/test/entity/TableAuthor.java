package com.fangcheng.test.entity;

import javax.persistence.*;
import java.io.Serializable;


/**
 * @ProjectName: _222
 * @Package: com.fc.test.entity
 * @ClassName: RoleAuthor
 * @Description: java类作用描述
 * @Author: FangCheng
 * @CreateDate: 2019/3/24 16:42
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/3/24 16:42
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Entity
@Table(name = "TABLE_AUTHOR")
public class TableAuthor implements Serializable {
//权限编号
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Id
    @Column(name = "AUTHOR_ID")
    private Integer authorId;

    @Column(name = "AUTHOR_TYPE",length = 15,unique = true, nullable = false)
    private String authorType = TableAuthorType.STUDENT.getTableAuthorType();


    public Integer getAuthorId() { return authorId; }
    public void setAuthorId(Integer authorId) { this.authorId = authorId; }

    public String getAuthorType() { return authorType; }
    public void setAuthorType(String authorType) { this.authorType = authorType; }


    //重写hashCode()方法
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((authorId == null) ? 0 : authorId.hashCode());
        result = prime * result + ((authorType == null) ? 0 : authorType.hashCode());
        return result;
    }
//重写equals()方法
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof TableAuthor))
            return false;
        TableAuthor other = (TableAuthor) obj;
        if (authorId == null) {
            if (other.authorId != null)
                return false;
        } else if (!authorId.equals(other.authorId))
            return false;
        if (authorType == null) {
            if (other.authorType != null)
                return false;
        } else if (!authorType.equals(other.authorType))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "TableAuthor [authorId=" + authorId + ", authorType=" + authorType + "]";
    }
}