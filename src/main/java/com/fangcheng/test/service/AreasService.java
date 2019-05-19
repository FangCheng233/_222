package com.fangcheng.test.service;

import com.fangcheng.test.entity.Areas;

import java.math.BigInteger;
import java.util.List;

/**
 * @ProjectName: _222
 * @Package: com.fangcheng.test.service
 * @ClassName: AreasService
 * @Description: java类作用描述
 * @Author: FangCheng
 * @CreateDate: 2019/5/11 12:35
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/5/11 12:35
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface AreasService {
    boolean isPoorerAreas(BigInteger id);
    Areas findById(BigInteger id);
    List<Areas> findByName(String provinceName,String cityName,String countyName);
    void updata(BigInteger id,int poorerAreas);
    void deleteById(String id);
    List<Areas> findAll();
    List<Areas> findAllProvince();
    List<Areas> findAllCity(String provinceName);
    List<Areas> findAllCountry(String cityName);
    List<Areas> findAllTown(String countryName);
}
