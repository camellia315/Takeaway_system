package com.health.mapper;

import com.github.pagehelper.Page;
import com.health.domain.pojo.Setmeal;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;

@Mapper
public interface SetmealMapper {
    void add(Setmeal setmeal);
    void setSetmealAndCheckGroup(Map<String, Integer> map);
    Page<Setmeal> selectByCondition(String queryString);
    // 【新增】
    Setmeal findById(Integer id);
    // 【新增】
    List<Integer> findCheckGroupIdsBySetmealId(Integer id);
    // 【新增】
    void edit(Setmeal setmeal);
    // 【新增】
    void deleteAssociation(Integer id);
    // 【新增】
    void deleteById(Integer id);
}