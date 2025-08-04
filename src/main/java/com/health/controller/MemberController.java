package com.health.controller;

import com.health.constant.MessageConstant;
import com.health.domain.dto.MemberLoginDTO;
import com.health.domain.vo.Result;
import com.health.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping("/login")
    public Result<String> login(@RequestBody MemberLoginDTO loginDTO) {
        String token = memberService.login(loginDTO);
        return Result.success(MessageConstant.LOGIN_SUCCESS, token);
    }
}