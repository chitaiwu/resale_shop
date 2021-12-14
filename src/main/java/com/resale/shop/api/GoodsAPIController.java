package com.resale.shop.api;

import java.util.Map;

import com.resale.shop.data.GoodsVO;
import com.resale.shop.service.GoodsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GoodsAPIController {
    @Autowired GoodsService service;
    @PostMapping("/goods/add")
    public Map<String, Object> postGoodsAdd(@RequestBody GoodsVO data) {
        return service.addGoods(data);
    }
    @DeleteMapping("/goods/delete")
    public Map<String, Object> deletGoods(@RequestParam Integer seq) {
        return service.deleteGoods(seq);
    }
    @GetMapping("/goods/get")
    public Map<String, Object> getGoodsInfoBySeq(@RequestParam Integer seq) {
        return service.getGoodsInfoBySeq(seq);
    }
    @PatchMapping("/goods/update")
    public Map<String, Object> patchDepartmentInfo(@RequestBody GoodsVO data) {
        return service.updateGoodsInfo(data);
    }
}