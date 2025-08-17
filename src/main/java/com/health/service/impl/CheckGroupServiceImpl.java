package com.health.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.health.domain.pojo.CheckGroup;
import com.health.domain.vo.PageBean;
import com.health.mapper.CheckGroupMapper;
import com.health.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CheckGroupServiceImpl implements CheckGroupService {

    @Autowired
    private CheckGroupMapper checkGroupMapper;

    @Override
    public PageBean<CheckGroup> findPage(Integer currentPage, Integer pageSize, String queryString) {
        PageHelper.startPage(currentPage, pageSize);
        Page<CheckGroup> page = checkGroupMapper.selectByCondition(queryString);
        return new PageBean<>(page.getTotal(), page.getResult());
    }

    @Override
    public void add(CheckGroup checkGroup, List<Integer> checkitemIds) {
        // 1. 新增检查组，获取返回的主键ID
        checkGroupMapper.add(checkGroup);
        Integer checkGroupId = checkGroup.getId();

        // 2. 维护检查组和检查项的关联关系
        if (checkitemIds != null && !checkitemIds.isEmpty()) {
            setCheckGroupAndCheckItem(checkGroupId, checkitemIds);
        }
    }

    @Override
    public CheckGroup findById(Integer id) {
        return checkGroupMapper.findById(id);
    }

    @Override
    public List<Integer> findCheckItemIdsByCheckGroupId(Integer id) {
        return checkGroupMapper.findCheckItemIdsByCheckGroupId(id);
    }

    @Override
    public void edit(CheckGroup checkGroup, List<Integer> checkitemIds) {
        // 1. 更新检查组基本信息
        checkGroupMapper.edit(checkGroup);
        Integer checkGroupId = checkGroup.getId();

        // 2. 清理旧的关联关系
        checkGroupMapper.deleteAssociation(checkGroupId);

        // 3. 建立新的关联关系
        if (checkitemIds != null && !checkitemIds.isEmpty()) {
            setCheckGroupAndCheckItem(checkGroupId, checkitemIds);
        }
    }

    @Override
    public void deleteById(Integer id) {
        // 检查是否被套餐关联
        if (checkGroupMapper.findSetmealCountByCheckGroupId(id) > 0) {
            throw new RuntimeException("当前检查组被套餐引用，不能删除");
        }
        // 先删除关联关系
        checkGroupMapper.deleteAssociation(id);
        // 再删除检查组
        checkGroupMapper.deleteById(id);
    }


    @Override
    public List<CheckGroup> findAll() {
        return checkGroupMapper.findAll();
    }
    // 封装设置关联关系的方法
    private void setCheckGroupAndCheckItem(Integer checkGroupId, List<Integer> checkitemIds) {
        for (Integer checkitemId : checkitemIds) {
            Map<String, Integer> map = new HashMap<>();
            map.put("checkgroup_id", checkGroupId);
            map.put("checkitem_id", checkitemId);
            checkGroupMapper.setCheckGroupAndCheckItem(map);
        }
    }
}