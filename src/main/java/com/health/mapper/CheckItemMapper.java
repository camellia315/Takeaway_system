package com.health.mapper;

import com.github.pagehelper.Page;
import com.health.domain.pojo.CheckItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface CheckItemMapper {


    void add(CheckItem checkItem);


    Page<CheckItem> selectByCondition(String queryString);


    void deleteById(Integer id);


    long findCountByCheckItemId(Integer id);


    CheckItem findById(Integer id);


    void edit(CheckItem checkItem);

    List<CheckItem> findAll(); // 新增方法
}