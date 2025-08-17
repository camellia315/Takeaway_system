package com.health.service;

import com.health.domain.pojo.CheckGroup;
import com.health.domain.vo.PageBean;
import java.util.List;

public interface CheckGroupService {
    PageBean<CheckGroup> findPage(Integer currentPage, Integer pageSize, String queryString);
    void add(CheckGroup checkGroup, List<Integer> checkitemIds);
    CheckGroup findById(Integer id);
    List<Integer> findCheckItemIdsByCheckGroupId(Integer id);
    void edit(CheckGroup checkGroup, List<Integer> checkitemIds);
    void deleteById(Integer id);
    List<CheckGroup> findAll();
}