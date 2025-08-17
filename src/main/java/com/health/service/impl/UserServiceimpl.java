package com.health.service.impl;

import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.health.domain.dto.LoginDTO;
import com.health.domain.pojo.User;
import com.health.mapper.UserMapper;
import com.health.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceimpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public static final String LOGIN_USER_KEY_PREFIX = "login:token:";
    public static final Long LOGIN_USER_TTL = 30L; // Token有效期30分钟

    @Override
    public String login(LoginDTO loginDTO) {
        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();

        if (StrUtil.isEmpty(username) || StrUtil.isEmpty(password)) {
            throw new RuntimeException("用户名密码都不能为空");
        }

        User user = userMapper.selectByUsername(username);

        if (ObjUtil.isEmpty(user)) {
            throw new RuntimeException("用户不存在，请联系管理员！");
        }

        String pwd = DigestUtil.md5Hex(password);
        if (!StrUtil.equals(pwd, user.getPassword())) { // 这里应该用加密后的 pwd
            throw new RuntimeException("密码错误！");
        }

        // 登录成功，生成Token
        String token = UUID.randomUUID().toString().replace("-", "");
        String redisKey = LOGIN_USER_KEY_PREFIX + token;

        // 将用户信息存入Redis，并设置有效期
        redisTemplate.opsForValue().set(redisKey, user, LOGIN_USER_TTL, TimeUnit.MINUTES);

        // 返回Token
        return token;
    }

    @Override
    public void logout(String token) {
        if (StrUtil.isNotEmpty(token)) {
            String redisKey = LOGIN_USER_KEY_PREFIX + token;
            redisTemplate.delete(redisKey);
        }
    }
}