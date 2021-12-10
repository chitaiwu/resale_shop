package com.resale.shop.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.resale.shop.mapper.DashboardMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {
    @Autowired DashboardMapper mapper;

    public Map<String, Object> getCounts() {
        List<Integer> cateCntList = new ArrayList<Integer>();
        cateCntList.add(mapper.getTotalCategorycnt());

        List<Integer> goodsCntList = new ArrayList<Integer>();
        goodsCntList.add(mapper.getTotalGoodscnt());

        List<Integer> customerCntList = new ArrayList<Integer>();
        customerCntList.add(mapper.getTotalCustomercnt());
        customerCntList.add(mapper.getPlatinumCustomercnt());
        customerCntList.add(mapper.getGoldtomercnt());
        customerCntList.add(mapper.getSilevrCustomercnt());
        customerCntList.add(mapper.getNormalCustomercnt());

        List<Integer> reviewCntList = new ArrayList<Integer>();
        reviewCntList.add(mapper.getTotalReviewcnt());

        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("category", cateCntList);
        map.put("goods", goodsCntList);
        map.put("customer", customerCntList);
        map.put("review", reviewCntList);
        return map;
    }
}
