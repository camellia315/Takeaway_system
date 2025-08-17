package com.health.service;

import com.health.domain.pojo.CheckItem;
import com.health.domain.vo.PageBean;

import java.util.List;

/**
 * Service interface for CheckItem business logic.
 * @author Your Name
 */
public interface CheckItemService {
    void add(CheckItem checkItem);
    PageBean<CheckItem> findPage(Integer currentPage, Integer pageSize, String queryString);
    void deleteById(Integer id);
    CheckItem findById(Integer id);
    void edit(CheckItem checkItem);
    List<CheckItem> findAll(); // 新增方法
}