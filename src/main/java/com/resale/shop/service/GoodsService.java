package com.resale.shop.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.resale.shop.data.GoodsHistoryVO;
import com.resale.shop.data.GoodsVO;
import com.resale.shop.mapper.GoodsMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodsService {
    @Autowired GoodsMapper mapper;

    public Map<String, Object> getGoodsList(Integer offset, String keyword) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        if(offset == null) {
            offset = 0;
            resultMap.put("offset",offset);
        }
        if(keyword == null) {
            keyword = "%%";
            resultMap.put("keyword","");
        }
        else {
            resultMap.put("keyword",keyword);
            keyword = "%"+keyword+"%";
        }
        List<GoodsVO> list = mapper.selectGoodsAll(offset, keyword);

        Integer cnt = mapper.getGoodsCount(keyword);
        Integer page_cnt = cnt  / 10 ;
        if(cnt % 10 > 0 ) page_cnt++;

        resultMap.put("status", true);
        resultMap.put("total", cnt);
        resultMap.put("pageCnt", page_cnt);
        resultMap.put("list", list);
        return resultMap;
    }
    public Map<String, Object> addGoods(GoodsVO data) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        if(data.getGi_name() == null || data.getGi_name().equals("")) {
            resultMap.put("status", false);
            resultMap.put("message", "제품명을 입력하세요.");
            return resultMap;
        } 
        
        if(data.getGi_price() == null || data.getGi_price() == 0) {
            resultMap.put("status", false);
            resultMap.put("message", "가격을 입력하세요.");
            return resultMap;
        }

        if(data.getGi_stock() == null || data.getGi_stock() == 0) {
            resultMap.put("status", false);
            resultMap.put("message", "재고를 입력하세요.");
            return resultMap;
        }
        
        mapper.addGoods(data);
        resultMap.put("status", true);
        resultMap.put("message", "제품이 추가되었습니다.");
        // 가장 최근에 추가된 학과의 seq 번호 가져오기
        Integer seq = mapper.selectLatestDataSeq();
        // add 동작에 대한 History 추가
        GoodsHistoryVO history = new GoodsHistoryVO();
        history.setGoh_gi_seq(seq);
        history.setGoh_type("new");
        String content = data.getGi_gc_seq()+"|"+data.getGi_name()+"|"+data.getGi_price()+"|"+data.getGi_stock();
        history.setGoh_content(content);
        mapper.insertGoodsHistory(history);

        return resultMap;
    }
    public Map<String, Object> deleteGoods(Integer seq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        mapper.deleteGoods(seq);
        resultMap.put("status", true);
        resultMap.put("message", "제품이 삭제되었습니다.");

        GoodsHistoryVO history = new GoodsHistoryVO();
        history.setGoh_gi_seq(seq);
        history.setGoh_type("delete");
        // String content = data.getGi_gc_seq()+"|"+data.getGi_name()+"|"+data.getGi_price()+"|"+data.getGi_stock();
        // history.setGoh_content(content);
        mapper.insertGoodsHistory(history);
        
        return resultMap;
    }
    public Map<String, Object> getGoodsInfoBySeq(Integer seq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        resultMap.put("status", true);
        resultMap.put("data", mapper.getGoodsInfoBySeq(seq));
        return resultMap;
    }
    public Map<String, Object> updateGoodsInfo(GoodsVO data) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        mapper.updateGoods(data);
        resultMap.put("status", true);
        resultMap.put("message", "수정되었습니다.");

        GoodsHistoryVO history = new GoodsHistoryVO();
        history.setGoh_gi_seq(data.getGi_seq());
        history.setGoh_type("update");
        String content = data.getGi_gc_seq()+"|"+data.getGi_name()+"|"+data.getGi_price()+"|"+data.getGi_stock();
        history.setGoh_content(content);
        mapper.insertGoodsHistory(history);

        return resultMap;
    }
}
