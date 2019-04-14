package com.fangcheng.test.dao;

import com.fangcheng.test.entity.TableAuthor;

import java.util.List;

/**
 * @ProjectName: _222
 * @Package: com.fangcheng.test.dao
 * @ClassName: TableAuthorDao
 * @Description: java类作用描述
 * @Author: FangCheng
 * @CreateDate: 2019/3/24 17:47
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/3/24 17:47
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface TableAuthorDao {
    List<TableAuthor> findAll();

    TableAuthor findByAuthorId(int authorId);
    TableAuthor findByAuthorType(String authorType);
}
