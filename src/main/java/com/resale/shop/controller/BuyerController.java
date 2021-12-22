package com.resale.shop.controller;

import java.util.Map;

import com.resale.shop.service.BuyerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BuyerController {
    @Autowired BuyerService Service;
    @GetMapping("/customer")
    public String getCustomer(
        Model model, @RequestParam @Nullable Integer offset,
        @RequestParam @Nullable String keyword
        ) {
        Map<String, Object> resultMap = Service.getBuyerList(offset, keyword);
        model.addAttribute("data", resultMap);
        return "/buyer/list";
    }
}
