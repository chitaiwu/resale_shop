package com.resale.shop.data;

import java.util.Date;

import lombok.Data;

@Data
public class BuyerHistoryVO {
    private Integer bh_seq;
    private Integer bh_bi_seq;
    private String bh_type;
    private String bh_content;
    private Date bh_reg_dt;
    
}
