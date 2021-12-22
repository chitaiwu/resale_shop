package com.resale.shop.data;

import java.util.Date;

import lombok.Data;

@Data
public class BuyerVO {
    private Integer bi_seq;
    private String bi_id;
    private String bi_pwd;
    private String bi_name;
    private String bi_birth;
    private Integer bi_gender;   
    private String bi_address;
    private String bi_phone;
    private String bi_email;
    private Integer bi_grade;
    private Integer bi_status;
    private Date bi_reg_dt;
    private Date bi_mod_dt;
    private Date bi_del_dt;
}
