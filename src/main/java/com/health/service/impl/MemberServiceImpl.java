package com.health.service.impl;

import com.health.constant.MessageConstant;
import com.health.constant.RedisConstant;
import com.health.domain.dto.MemberLoginDTO;
import com.health.domain.pojo.Member;
import com.health.mapper.MemberMapper;
import com.health.service.MemberService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public String login(MemberLoginDTO loginDTO) {
        String telephone = loginDTO.getTelephone();
        String validateCode = loginDTO.getValidateCode();
        String redisKey = RedisConstant.SENDTYPE_LOGIN + telephone;

        // 1. 从Redis中获取缓存的验证码
        String codeInRedis = stringRedisTemplate.opsForValue().get(redisKey);

        // 2. 校验验证码
        if (StringUtils.isEmpty(codeInRedis) || !codeInRedis.equals(validateCode)) {
            throw new RuntimeException(MessageConstant.VALIDATE_CODE_ERROR);
        }

        // 3. 验证码正确，删除Redis中的验证码
        stringRedisTemplate.delete(redisKey);

        // 4. 判断当前用户是否为会员（根据手机号）
        Member member = memberMapper.findByTelephone(telephone);

        // 5. 如果不是会员，自动完成注册
        if (member == null) {
            member = new Member();
            member.setPhoneNumber(telephone);
            member.setRegTime(new Date());
            memberMapper.add(member);
        }

        // 6. 登录成功，为用户生成Token
        String token = UUID.randomUUID().toString().replace("-", "");
        String tokenKey = "login:member:" + token; // 使用新的前缀以区分后台用户

        // 将会员信息存入Redis，作为登录凭证，有效期30分钟
        // 注意：这里简单将会员ID存入，实际可存JSON格式的会员信息
        stringRedisTemplate.opsForValue().set(tokenKey, String.valueOf(member.getId()), 30, TimeUnit.MINUTES);

        return token;
    }
}