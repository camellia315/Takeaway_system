package com.health.controller;

import com.health.domain.dto.LoginDTO;
import com.health.domain.pojo.User;
import com.health.domain.vo.Result;
import com.health.service.UserService;
import com.health.service.impl.UserServiceimpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    // 登录
    @PostMapping("/login")
    public Result<String> login(@RequestBody LoginDTO loginDTO) {
        String token = userService.login(loginDTO);
        return Result.success("登录成功", token);
    }

    // 退出
    @PostMapping("/logout")
    public Result<Void> logout(@RequestHeader("Authorization") String token) {
        userService.logout(token);
        return Result.success("退出成功", null);
    }

    // 获取当前登录用户信息 (用于在主页显示用户名)
    @GetMapping("/profile")
    public Result<User> profile(@RequestHeader("Authorization") String token) {
        String redisKey = UserServiceimpl.LOGIN_USER_KEY_PREFIX + token;
        User user = (User) redisTemplate.opsForValue().get(redisKey);
        if (user == null) {
            return Result.error("用户未登录或登录已超时");
        }
        return Result.success("获取用户信息成功", user);
    }
}