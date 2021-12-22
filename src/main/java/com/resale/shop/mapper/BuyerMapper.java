package com.resale.shop.mapper;

import java.util.List;

import com.resale.shop.data.BuyerHistoryVO;
import com.resale.shop.data.BuyerVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BuyerMapper {
    public List<BuyerVO> getBuyerInfo(Integer offset, String keyword);
    public Integer getBuyerCount(String keyword);

    public void addBuyerInfo(BuyerVO data);
    public void deleteBuyer(Integer seq);

    public BuyerVO getBuyerInfoBySeq(Integer seq);
    public void updateBuyer(BuyerVO data);

    public void insertBuyerHistory(BuyerHistoryVO data);
    public Integer getRecentAddBuyerSeq();
}
