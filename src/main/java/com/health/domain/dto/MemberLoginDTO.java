package com.health.domain.dto;

import lombok.Data;
import java.io.Serializable;

@Data
public class MemberLoginDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String telephone;
    private String validateCode;
}