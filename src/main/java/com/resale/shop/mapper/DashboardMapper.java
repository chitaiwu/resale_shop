package com.resale.shop.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DashboardMapper {
    public Integer getTotalCategorycnt();
    public Integer getTotalGoodscnt();

    public Integer getTotalCustomercnt();
    public Integer getPlatinumCustomercnt();
    public Integer getGoldtomercnt();
    public Integer getSilevrCustomercnt();
    public Integer getNormalCustomercnt();

    public Integer getTotalReviewcnt();

    public Date getGoodsUpdateDate();
}
