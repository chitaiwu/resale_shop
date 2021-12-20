package com.resale.shop.data;

import java.sql.Date;

import lombok.Data;

@Data
public class GoodsVO {
    private Integer gi_seq;
    private Integer gi_gc_seq;
    private String gi_name;
    private String gi_sub;
    private Integer gi_price;
    private Integer gi_stock;
    private Date gi_reg_dt;
    private Date gi_mod_dt;
    private String category_name;
    private Integer gi_bi_seq;
}
