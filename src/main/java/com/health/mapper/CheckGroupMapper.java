package com.health.mapper;

import com.github.pagehelper.Page;
import com.health.domain.pojo.CheckGroup;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;

@Mapper
public interface CheckGroupMapper {
    // 分页查询
    Page<CheckGroup> selectByCondition(String queryString);
    // 新增检查组
    void add(CheckGroup checkGroup);
    // 设置检查组和检查项的关联关系
    void setCheckGroupAndCheckItem(Map<String, Integer> map);
    // 根据ID查询
    CheckGroup findById(Integer id);
    // 根据检查组ID查询关联的检查项ID列表
    List<Integer> findCheckItemIdsByCheckGroupId(Integer id);
    // 编辑检查组
    void edit(CheckGroup checkGroup);
    // 根据检查组ID删除关联关系
    void deleteAssociation(Integer id);
    // 检查是否被套餐引用
    long findSetmealCountByCheckGroupId(Integer id);
    // 根据ID删除检查组
    void deleteById(Integer id);
    List<CheckGroup> findAll(); // 新增方法
}