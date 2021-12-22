package com.resale.shop.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.resale.shop.data.BuyerHistoryVO;
import com.resale.shop.data.BuyerVO;
import com.resale.shop.mapper.BuyerMapper;
import com.resale.shop.utils.AESAlgorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuyerService {
    @Autowired BuyerMapper mapper;

    public Map<String, Object> getBuyerList(Integer offset, String keyword) {
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

        List<BuyerVO> list = mapper.getBuyerInfo(offset, keyword);
        
        Integer cnt = mapper.getBuyerCount(keyword);
        Integer page_cnt = cnt  / 10 ;
        if(cnt % 10 > 0 ) page_cnt++;

        resultMap.put("status", true);
        resultMap.put("total", cnt);
        resultMap.put("pageCnt", page_cnt);
        resultMap.put("list", list);
        return resultMap;
    }
    public Map<String, Object> addBuyerInfo(BuyerVO data) throws Exception{
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        if(data.getBi_name() == null || data.getBi_name().equals("")) {
            resultMap.put("status", false);
            resultMap.put("message", "이름을 입력하세요.");
            return resultMap;
        } 
        if(data.getBi_id() == null || data.getBi_id().equals("")) {
            resultMap.put("status", false);
            resultMap.put("message", "아이디를 입력하세요.");
            return resultMap;
        }
        if(data.getBi_birth().equals("") || data.getBi_birth() == null) {
            resultMap.put("status", false);
            resultMap.put("reason", "birth");
            resultMap.put("message", "생년월일을 입력해주세요.");
            return resultMap;
        }
        if(data.getBi_phone().equals("") || data.getBi_phone() == null) {
            resultMap.put("status", false);
            resultMap.put("reason", "phone");
            resultMap.put("message", "전화번호를 입력해주세요.");
            return resultMap;
        }
        if(data.getBi_email().equals("") || data.getBi_email() == null) {
            resultMap.put("status", false);
            resultMap.put("reason", "email");
            resultMap.put("message", "이메일을 입력해주세요.");
            return resultMap;
        }
        
        String pwd = data.getBi_pwd();
        String encrypted = AESAlgorithm.Encrypt(pwd);
        data.setBi_pwd(encrypted); 
        
        mapper.addBuyerInfo(data);
        resultMap.put("status", true);
        resultMap.put("message", "고객 정보가 추가되었습니다.");
        
        // 가장 최근에 추가된 고객의 seq 번호 가져오기
        Integer seq = mapper.getRecentAddBuyerSeq();
        // add 동작에 대한 History 추가
        BuyerHistoryVO history = new BuyerHistoryVO();
        history.setBh_bi_seq(seq);
        history.setBh_type("new");
        String content = data.getBi_name()+"|"+data.getBi_id()+"|"+data.getBi_birth()+"|"+data.getBi_gender()+"|"+data.getBi_phone();
        history.setBh_content(content);
        mapper.insertBuyerHistory(history);
        
        return resultMap;
    }
    public Map<String, Object> deleteBuyer(Integer seq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        mapper.deleteBuyer(seq);
        resultMap.put("status", true);
        resultMap.put("message", "고객 정보가 삭제되었습니다.");

        BuyerHistoryVO history = new BuyerHistoryVO();
        history.setBh_bi_seq(seq);
        history.setBh_type("delete");
        // String content = data.getDi_name()+" | "+data.getDi_graduate_score()+" | "+data.getDi_status();
        // history.setDeph_content(content);
        mapper.insertBuyerHistory(history);

        return resultMap;
    }
    public Map<String, Object> getBuyerInfoBySeq(Integer seq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        resultMap.put("status", true);
        resultMap.put("data", mapper.getBuyerInfoBySeq(seq));
        return resultMap;
    }
    
    public Map<String, Object> updateBuyer(BuyerVO data) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        mapper.updateBuyer(data);
        resultMap.put("status", true);
        resultMap.put("message", "수정되었습니다.");

        BuyerHistoryVO history = new BuyerHistoryVO();
        history.setBh_bi_seq(data.getBi_seq());
        history.setBh_type("update");
        String content = data.getBi_name()+"|"+data.getBi_id()+"|"+data.getBi_birth()+"|"+data.getBi_gender()+"|"+data.getBi_phone();
        history.setBh_content(content);
        mapper.insertBuyerHistory(history);

        return resultMap;
    }
}
