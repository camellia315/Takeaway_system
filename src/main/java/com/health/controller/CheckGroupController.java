package com.health.controller;

import com.health.domain.pojo.CheckGroup;
import com.health.domain.query.PageQueryDTO;
import com.health.domain.vo.PageBean;
import com.health.domain.vo.Result;
import com.health.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/checkgroup")
public class CheckGroupController {

    @Autowired
    private CheckGroupService checkGroupService;

    // 【已修正】返回Result对象，保持API一致性
    @PostMapping("/findPage")
    public Result<PageBean<CheckGroup>> findPage(@RequestBody PageQueryDTO pageQueryDTO) {
        PageBean<CheckGroup> pageBean = checkGroupService.findPage(
                pageQueryDTO.getCurrentPage(),
                pageQueryDTO.getPageSize(),
                pageQueryDTO.getQueryString()
        );
        return Result.success("查询检查组列表成功", pageBean);
    }

    @PostMapping("/add")
    public Result<Void> add(@RequestBody CheckGroup checkGroup, @RequestParam List<Integer> checkitemIds) {
        checkGroupService.add(checkGroup, checkitemIds);
        return Result.success("新增检查组成功", null);
    }

    @GetMapping("/findById")
    public Result<CheckGroup> findById(Integer id) {
        CheckGroup checkGroup = checkGroupService.findById(id);
        return Result.success("查询检查组成功", checkGroup);
    }

    @GetMapping("/findCheckItemIdsByCheckGroupId")
    public Result<List<Integer>> findCheckItemIdsByCheckGroupId(Integer id) {
        List<Integer> checkitemIds = checkGroupService.findCheckItemIdsByCheckGroupId(id);
        return Result.success("查询检查项ID列表成功", checkitemIds);
    }

    @PostMapping("/edit")
    public Result<Void> edit(@RequestBody CheckGroup checkGroup, @RequestParam List<Integer> checkitemIds) {
        checkGroupService.edit(checkGroup, checkitemIds);
        return Result.success("编辑检查组成功", null);
    }


    @GetMapping("/findAll")
    public Result<List<CheckGroup>> findAll() {
        List<CheckGroup> list = checkGroupService.findAll();
        return Result.success("查询所有检查组成功", list);
    }

    @GetMapping("/delete")
    public Result<Void> delete(Integer id) {
        checkGroupService.deleteById(id);
        return Result.success("删除检查组成功", null);
    }
}