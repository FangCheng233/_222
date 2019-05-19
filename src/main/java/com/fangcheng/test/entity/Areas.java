package com.fangcheng.test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigInteger;

/**
 * @ProjectName: _222
 * @Package: com.fangcheng.test.entity
 * @ClassName: Areas
 * @Description: java类作用描述
 * @Author: FangCheng
 * @CreateDate: 2019/5/11 12:19
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/5/11 12:19
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Entity
@Table(name = "AREAS")
public class Areas implements Serializable {
    @Id
    @NotNull
    @Column(name = "ID")
    private BigInteger id;
    //省编号
    @NotNull
    @Column(name = "PROVINCE_ID")
    private BigInteger provinceId;
    //省名称
    @NotNull
    @Column(name = "PROVINCE_NAME")
    private String provinceName;
    //省编号
    @NotNull
    @Column(name = "CITY_ID")
    private BigInteger cityId;
    //省名称
    @NotNull
    @Column(name = "CITY_NAME")
    private String cityName;
    //省编号
    @NotNull
    @Column(name = "COUNTY_ID")
    private BigInteger countyId;
    //省名称
    @NotNull
    @Column(name = "COUNTY_NAME")
    private String countyName;
    //qiong
    @NotNull
    @Column(name = "POORER_AREAS")
    private Integer poorerAreas;

    public BigInteger getId() {
        return id;
    }
    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getProvinceId() {
        return provinceId;
    }
    public void setProvinceId(BigInteger provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public BigInteger getCityId() {
        return cityId;
    }
    public void setCityId(BigInteger cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public BigInteger getCountyId() {
        return countyId;
    }
    public void setCountyId(BigInteger countyId) {
        this.countyId = countyId;
    }

    public String getCountyName() {
        return countyName;
    }
    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public Integer getPoorerAreas() {
        return poorerAreas;
    }
    public void setPoorerAreas(Integer poorerAreas) {
        this.poorerAreas = poorerAreas;
    }
    @Override
    public String toString() {
        return "[id=" + id + ", provinceId=" + provinceId + ", provinceName=" + provinceName
                + ", cityId=" + cityId + ", cityName=" + cityName + ", countyId=" + countyId
                + ", countyName=" + countyName +", poorerAreas=" + poorerAreas +"]";
    }
}
