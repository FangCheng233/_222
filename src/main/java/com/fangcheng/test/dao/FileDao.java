package com.fangcheng.test.dao;

import com.fangcheng.test.entity.File;

/**
 * @ProjectName: _222
 * @Package: com.fangcheng.test.dao
 * @ClassName: FileDao
 * @Description: java类作用描述
 * @Author: FangCheng
 * @CreateDate: 2019/5/17 16:18
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/5/17 16:18
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface FileDao {
    File findById(String id);
    void save(File file);
    void  deletById(String id);
}
