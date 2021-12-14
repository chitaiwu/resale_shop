package com.resale.shop.controller;

import java.util.Map;

import com.resale.shop.service.GoodsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GoodsController {
    @Autowired GoodsService service;
    @GetMapping("/goods")
    public String getGoods(
        Model model, @RequestParam @Nullable Integer offset,
        @RequestParam @Nullable String keyword
    ) {
        Map<String, Object> resultMap = service.getGoodsList(offset, keyword);
        model.addAttribute("data", resultMap);
        return "/goods/list";
    }
}
