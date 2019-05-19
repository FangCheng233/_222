package com.fangcheng.test.service;

import com.fangcheng.test.entity.File;


/**
 * @ProjectName: _222
 * @Package: com.fangcheng.test.service
 * @ClassName: FileService
 * @Description: java类作用描述
 * @Author: FangCheng
 * @CreateDate: 2019/5/14 19:26
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/5/14 19:26
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface FileService {
    File findById(String id);
    void update(File file);
    void save(File file);
    void deleteFileById(String id);
}
