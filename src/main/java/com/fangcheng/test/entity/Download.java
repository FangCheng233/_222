package com.fangcheng.test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.print.attribute.standard.MediaSize;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @ProjectName: _222
 * @Package: com.fangcheng.test.entity
 * @ClassName: Download
 * @Description: java类作用描述
 * @Author: FangCheng
 * @CreateDate: 2019/5/14 16:01
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/5/14 16:01
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Entity
@Table(name = "DOWNLOAD")
public class Download implements Serializable {
    @Id
    @NotNull
    @Column(name = "ID")
    private int id;
    //操作时间
    @NotNull
    @Column(name = "CREATE_TIME")
    private String createTime;
    //用户id
    @NotNull
    @Column(name = "USER_ID")
    private String userId;
    //文件ID
    @NotNull
    @Column(name = "FILEID")
    private String fileId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }
}
