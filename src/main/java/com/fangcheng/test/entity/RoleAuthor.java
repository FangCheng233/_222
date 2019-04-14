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
 * @ClassName: RoleAuthor
 * @Description: java类作用描述
 * @Author: FangCheng
 * @CreateDate: 2019/3/29 21:30
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/3/29 21:30
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Entity
@Table(name = "ROLE_AUTHOR")
public class RoleAuthor implements Serializable {

    @Id
    @NotEmpty
    @Column(name = "USER_ID")
    private String userId;

    @NotEmpty
    @Column(name = "AUTHOR_ID")
    private Integer authorId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

}
