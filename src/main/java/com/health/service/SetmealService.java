package com.health.service;

import com.health.domain.pojo.Setmeal;
import com.health.domain.vo.PageBean;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public interface SetmealService {
    String upload(MultipartFile file);
    void add(Setmeal setmeal, List<Integer> checkgroupIds);
    PageBean<Setmeal> findPage(Integer currentPage, Integer pageSize, String queryString);
    // 【新增】
    Setmeal findById(Integer id);
    // 【新增】
    List<Integer> findCheckGroupIdsBySetmealId(Integer id);
    // 【新增】
    void edit(Setmeal setmeal, List<Integer> checkgroupIds);
    // 【新增】
    void deleteById(Integer id);
}