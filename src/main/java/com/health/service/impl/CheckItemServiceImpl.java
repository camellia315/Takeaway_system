package com.health.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.health.domain.pojo.CheckItem;
import com.health.domain.vo.PageBean;
import com.health.mapper.CheckItemMapper;
import com.health.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class CheckItemServiceImpl implements CheckItemService {

    @Autowired
    private CheckItemMapper checkItemMapper;

    @Override
    public void add(CheckItem checkItem) {
        checkItemMapper.add(checkItem);
    }

    @Override
    public PageBean<CheckItem> findPage(Integer currentPage, Integer pageSize, String queryString) {
        // 1. Initialize PageHelper
        PageHelper.startPage(currentPage, pageSize);
        // 2. Execute query
        Page<CheckItem> page = checkItemMapper.selectByCondition(queryString);
        // 3. Return result in PageBean
        return new PageBean<>(page.getTotal(), page.getResult());
    }

    @Override
    public void deleteById(Integer id) {
        // Check for existing associations before deleting
        long count = checkItemMapper.findCountByCheckItemId(id);
        if (count > 0) {
            // If the item is associated with a check group, prevent deletion
            throw new RuntimeException("当前检查项被引用，不能删除");
        }
        checkItemMapper.deleteById(id);
    }

    @Override
    public List<CheckItem> findAll() {
        return checkItemMapper.findAll();
    }

    @Override
    public CheckItem findById(Integer id) {
        return checkItemMapper.findById(id);
    }

    @Override
    public void edit(CheckItem checkItem) {
        checkItemMapper.edit(checkItem);
    }
}