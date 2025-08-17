package com.health.domain.pojo;

import lombok.Data;
import java.io.Serializable;

@Data
public class Setmeal implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private String code;
    private String helpCode;
    private String sex;
    private String age;
    private Float price;
    private String remark;
    private String attention;
    private String img;
}