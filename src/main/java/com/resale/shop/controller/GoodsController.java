package com.resale.shop.controller;

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
    public String getGoods(Model model,
        @RequestParam @Nullable String type,
        @RequestParam @Nullable String keyword,
        @RequestParam @Nullable Integer offset
        
    ) {
        model.addAttribute("data", service.getGoodsList(type, keyword, offset));
        return "/goods/list";
    }
}
