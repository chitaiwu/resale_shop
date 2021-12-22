package com.resale.shop.api;

import java.util.Map;

import com.resale.shop.data.BuyerVO;
import com.resale.shop.service.BuyerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BuyerAPIController {
    @Autowired BuyerService service;
    @PostMapping("customer/add")
    public Map<String, Object> postCustomerAdd(@RequestBody BuyerVO data) throws Exception {
        return service.addBuyerInfo(data);
    }
    @DeleteMapping("customer/delete")
    public Map<String, Object> deleteCustomer(@RequestParam Integer seq) {
        return service.deleteBuyer(seq);
    }
    @GetMapping("/customer/get")
    public Map<String, Object> getCustomerInfoBySeq(@RequestParam Integer seq) {
        return service.getBuyerInfoBySeq(seq);
    }
    @PatchMapping("/customer/update")
    public Map<String, Object> patchCustomerInfo(@RequestBody BuyerVO data) {
        return service.updateBuyer(data);
    }
}
