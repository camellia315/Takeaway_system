package com.health.mapper;

import com.health.domain.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select * from t_user where username = #{username}")
    User selectByUsername(String username);
}
