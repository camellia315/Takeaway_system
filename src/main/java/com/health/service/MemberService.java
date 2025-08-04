package com.health.service;

import com.health.domain.dto.MemberLoginDTO;

public interface MemberService {
    /**
     * 手机端会员登录/注册
     * @param loginDTO 包含手机号和验证码
     * @return 返回登录令牌Token
     */
    String login(MemberLoginDTO loginDTO);
}