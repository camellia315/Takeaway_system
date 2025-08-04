package com.health.domain.pojo;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

@Data
public class Member implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String fileNumber;
    private String name;
    private String sex;
    private String idCard;
    private String phoneNumber;
    private Date regTime;
    private String password;
    private String email;
    private Date birthday;
    private String remark;
}