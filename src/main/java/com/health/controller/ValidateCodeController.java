package com.health.controller;

import com.health.constant.RedisConstant;
import com.health.domain.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/validateCode")
@Slf4j
public class ValidateCodeController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping("/send4Login")
    public Result<Void> send4Login(String telephone) {
        // 1. 生成6位数字验证码
        String code = RandomStringUtils.randomNumeric(6);

        // 2. 打印到控制台，模拟发送短信
        log.info("手机号 {} 的登录验证码为：{}", telephone, code);

        // 3. 将验证码存入Redis，有效期5分钟
        String redisKey = RedisConstant.SENDTYPE_LOGIN + telephone;
        stringRedisTemplate.opsForValue().set(redisKey, code, 5, TimeUnit.MINUTES);

        return Result.success("验证码发送成功", null);
    }
}