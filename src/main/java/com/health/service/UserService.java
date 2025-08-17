package com.health.service;

import com.health.domain.dto.LoginDTO;

public interface UserService {
    /**
     * 用户登录
     * @param loginDTO 登录信息
     * @return 登录成功后返回的Token
     */
    String login(LoginDTO loginDTO);

    /**
     * 用户退出
     * @param token 用户当前的登录token
     */
    void logout(String token);
}