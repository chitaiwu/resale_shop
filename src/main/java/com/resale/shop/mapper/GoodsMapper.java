package com.resale.shop.mapper;

import java.util.List;

import com.resale.shop.data.GoodsVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoodsMapper {
    public List<GoodsVO> selectGoodsAll(Integer offset, String keyword);
    public Integer getGoodsCount(String keyword);
    public void addGoods(GoodsVO data);
    public void deleteGoods(Integer seq);

    public GoodsVO getGoodsInfoBySeq(Integer seq);
    public void updateGoods(GoodsVO data);
}
