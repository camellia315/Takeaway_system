package com.health.domain.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable; // 导入Serializable

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable { // 实现接口

        private static final long serialVersionUID = 1L; // 添加序列化版本号

        private Integer id;
        private String birthday;
        private String gender;
        private String username;
        private String password;
        private String remark;
        private String station;
        private String telephone;
}