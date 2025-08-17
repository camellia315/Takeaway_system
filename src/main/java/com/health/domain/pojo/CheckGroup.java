package com.health.domain.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class CheckGroup implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String code;
    private String name;
    private String helpCode;
    private String sex;
    private String remark;
    private String attention;
}