package com.health.mapper;

import com.health.domain.pojo.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    /**
     * 根据手机号查询会员信息
     * @param telephone 手机号
     * @return 会员对象
     */
    Member findByTelephone(String telephone);

    /**
     * 新增会员（自动注册）
     * @param member 会员信息
     */
    void add(Member member);
}