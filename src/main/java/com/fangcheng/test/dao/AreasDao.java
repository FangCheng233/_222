package com.fangcheng.test.dao;

import com.fangcheng.test.entity.Areas;

import java.math.BigInteger;
import java.util.List;

/**
 * @ProjectName: _222
 * @Package: com.fangcheng.test.dao
 * @ClassName: AreasDao
 * @Description: java类作用描述
 * @Author: FangCheng
 * @CreateDate: 2019/5/11 13:10
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/5/11 13:10
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface AreasDao {
    Areas findById(BigInteger id);
    List<Areas> findByName(String provinceName,String cityName,String countyName);
    List<Areas> findAllProvince();
    List<Areas> findAllCity(String provinceName);
    List<Areas> findAllCounty(String cityName);
    List<Areas> findAllTown(String countryName);
    List<Areas> findAll();
    void  deleteById(String id);
}
