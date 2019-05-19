package com.fangcheng.test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigInteger;

/**
 * @ProjectName: _222
 * @Package: com.fangcheng.test.entity
 * @ClassName: File
 * @Description: java类作用描述
 * @Author: FangCheng
 * @CreateDate: 2019/5/14 16:01
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/5/14 16:01
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Entity
@Table(name = "FILE")
public class File implements Serializable {
    @Id
    @NotNull
    @Column(name = "ID")
    private String id;
    //文件名
    @Column(name = "NAME")
    private String name;
    //文件类型
    @Column(name = "SUFFIX")
    private String suffix;
    //文件下载地址
    @NotNull
    @Column(name = "VISIT_URL")
    private String visitUrl;
    //文件大小
    @Column(name = "SIZE")
    private int size;
    //文件创建时间
    @Column(name = "CREATE_TIME")
    private String creatTime;
    //下载次数
    @Column(name = "DOWNLOAD_TIMES")
    private int downloadTimes;
    //最后一次访问时间
    @Column(name = "LAST_MODIFY_TIME")
    private String lastModifyTime;
    //上传人ID
    @NotNull
    @Column(name = "USER_ID")
    private String userId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getVisitUrl() {
        return visitUrl;
    }

    public void setVisitUrl(String visitUrl) {
        this.visitUrl = visitUrl;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime;
    }

    public int getDownloadTimes() {
        return downloadTimes;
    }

    public void setDownloadTimes(int downloadTimes) {
        this.downloadTimes = downloadTimes;
    }

    public String getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(String lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
