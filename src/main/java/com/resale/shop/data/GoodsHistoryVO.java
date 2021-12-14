package com.resale.shop.data;

import java.sql.Date;

import lombok.Data;

@Data
public class GoodsHistoryVO {
    private Integer goh_seq;
    private String goh_type;
    private String goh_content;
    private Date goh_reg_dt;
    private Integer goh_gi_seq;
}
