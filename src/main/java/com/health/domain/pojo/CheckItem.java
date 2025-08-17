package com.health.domain.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * POJO for CheckItem
 * @author Your Name
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckItem implements Serializable {
    private Integer id;      // Primary Key
    private String code;     // Item Code
    private String name;     // Item Name
    private String sex;      // Gender Applicability ('0' for Any, '1' for Male, '2' for Female)
    private String age;      // Age Range
    private Float price;     // Price
    private String type;     // Type ('1' for Checkup, '2' for Test)
    private String attention;// Attention Notes
    private String remark;   // Description
}