package com.fangcheng.test.service.impl;

import com.fangcheng.test.dao.ReferenceDataDao;
import com.fangcheng.test.entity.ReferenceData;
import com.fangcheng.test.service.ReferenceDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ProjectName: _222
 * @Package: com.fangcheng.test.service.impl
 * @ClassName: ReferenceDataServiceImpl
 * @Description: java类作用描述
 * @Author: FangCheng
 * @CreateDate: 2019/5/13 14:56
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/5/13 14:56
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Service("referenceDataService")
@Transactional
public class ReferenceDataServiceImpl implements ReferenceDataService {
    @Autowired
    private ReferenceDataDao referenceDataDao;

    @Override
    public ReferenceData findBySchoolYear(String schoolYear) {
        return referenceDataDao.findBySchoolYear(schoolYear);
    }

    @Override
    public List<ReferenceData> findAll() {
        return referenceDataDao.findAll();
    }
    @Override
    public void updata(ReferenceData referenceData) {
        ReferenceData entity = referenceDataDao.findBySchoolYear(referenceData.getSchoolYear());
        entity.setLivingExpenses(referenceData.getLivingExpenses());
        entity.setTuition1(referenceData.getTuition1());
        entity.setTuition2(referenceData.getTuition2());
        entity.setTuition3(referenceData.getTuition3());
        entity.setTuition4(referenceData.getTuition4());
        entity.setTuition5(referenceData.getTuition5());
        entity.setTuition6(referenceData.getTuition6());
        entity.setTuition7(referenceData.getTuition7());
        entity.setTuition8(referenceData.getTuition8());
        entity.setTuition9(referenceData.getTuition9());
        entity.setTuition10(referenceData.getTuition10());
        entity.setTuition11(referenceData.getTuition11());
    }

    @Override
    public void save(ReferenceData referenceData) {
        referenceDataDao.save(referenceData);
    }
}
