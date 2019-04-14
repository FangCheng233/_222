package com.fangcheng.test.service;

import com.fangcheng.test.entity.TableAuthor;

import java.util.List;

/**
 * @ProjectName: _222
 * @Package: com.fangcheng.test.service
 * @ClassName: TableAuthorService
 * @Description: java类作用描述
 * @Author: FangCheng
 * @CreateDate: 2019/3/24 17:46
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/3/24 17:46
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface TableAuthorService {
    TableAuthor findByAuthorId(int authorId);
    TableAuthor findByAuthorType(String authorType);
    List<TableAuthor> findAll();
}
