package com.fangcheng.test.entity;

import java.io.Serializable;

/**
 * @ProjectName: _222
 * @Package: com.fangcheng.test.entity
 * @ClassName: TableAuthorType
 * @Description: java类作用描述
 * @Author: FangCheng
 * @CreateDate: 2019/3/24 22:40
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/3/24 22:40
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public enum  TableAuthorType implements Serializable {
    STUDENT("STUDENT"), COUNSELLOR("COUNSELLOR"),COLLEGE("COLLEGE"), ADMIN("ADMIN");
    String tableAuthorType;
    private TableAuthorType(String tableAuthorType){
        this.tableAuthorType = tableAuthorType;
    }
    public String getTableAuthorType() {
        return tableAuthorType;
    }
}
