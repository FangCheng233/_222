package com.fangcheng.test.service.impl;

import com.fangcheng.test.dao.TableAuthorDao;
import com.fangcheng.test.entity.TableAuthor;
import com.fangcheng.test.service.TableAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ProjectName: _222
 * @Package: com.fangcheng.test.service.impl
 * @ClassName: TableServiceAuthorImpl
 * @Description: java类作用描述
 * @Author: FangCheng
 * @CreateDate: 2019/3/24 17:47
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/3/24 17:47
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Service("tableAuthorService")
@Transactional
public class TableAuthorServiceImpl implements TableAuthorService {
    @Autowired
    TableAuthorDao dao;

    public TableAuthor findByAuthorId(int authorId) {
        return dao.findByAuthorId(authorId);
    }

    public TableAuthor findByAuthorType(String authorType) {
        return dao.findByAuthorType(authorType);
    }

    @Override
    public List<TableAuthor> findAll() {
        return dao.findAll();
    }
}
