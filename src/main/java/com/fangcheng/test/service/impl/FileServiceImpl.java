package com.fangcheng.test.service.impl;

import com.fangcheng.test.dao.FileDao;
import com.fangcheng.test.entity.File;
import com.fangcheng.test.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ProjectName: _222
 * @Package: com.fangcheng.test.service.impl
 * @ClassName: FileServiceImpl
 * @Description: java类作用描述
 * @Author: FangCheng
 * @CreateDate: 2019/5/14 19:26
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/5/14 19:26
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Service("fileService")
@Transactional
public class FileServiceImpl implements FileService {

    @Autowired
    private FileDao fileDao;

    public File findById(String id) {
        return fileDao.findById(id);
    }
    public void update(File file){
        File entity = fileDao.findById(file.getId());
        if(entity!=null) {
            entity.setVisitUrl(file.getVisitUrl());
        }
    }
    @Override
    public void save(File file) {
        fileDao.save(file);
    }
    @Override
    public void deleteFileById(String id) {
        fileDao.deletById(id);
    }
}
