package com.fangcheng.test.service.impl;

import com.fangcheng.test.dao.AreasDao;
import com.fangcheng.test.entity.Areas;
import com.fangcheng.test.service.AreasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

/**
 * @ProjectName: _222
 * @Package: com.fangcheng.test.service.impl
 * @ClassName: AreasServiceImpl
 * @Description: java类作用描述
 * @Author: FangCheng
 * @CreateDate: 2019/5/11 12:36
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/5/11 12:36
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Service("areasService")
@Transactional
public class AreasServiceImpl implements AreasService {
    @Autowired
    private AreasDao areasDao;
    @Override
    public boolean isPoorerAreas(BigInteger id) {
        Areas areas = areasDao.findById(id);
        if(areas.getPoorerAreas()==0){//0代表不是贫困县
            return false;
        }
        return true;
    }

    @Override
    public Areas findById(BigInteger id) {
        return areasDao.findById(id);
    }

    @Override
    public List<Areas> findByName(String provinceName, String cityName, String countyName) {
        return areasDao.findByName(provinceName,cityName,countyName);
    }

    @Override
    public void updata(BigInteger id,int poorerAreas) {
        Areas areas = areasDao.findById(id);
        areas.setPoorerAreas(poorerAreas);
    }
    @Override
    public List<Areas> findAll() {
        return areasDao.findAll();
    }
    @Override
    public List<Areas> findAllProvince() {
        return areasDao.findAllProvince();
    }
    public void deleteById(String id){
        areasDao.deleteById(id);
    };
    @Override
    public List<Areas> findAllCity(String provinceName) {
        return areasDao.findAllCity(provinceName);
    }

    @Override
    public List<Areas> findAllCounty(String cityName) {
        return areasDao.findAllCounty(cityName);
    }

    @Override
    public List<Areas> findAllTown(String countryName) {
        return areasDao.findAllTown(countryName);
    }


}
