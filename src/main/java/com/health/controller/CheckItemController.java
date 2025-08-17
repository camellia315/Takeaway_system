package com.health.controller;

import com.health.constant.MessageConstant;
import com.health.domain.pojo.CheckItem;
import com.health.domain.query.PageQueryDTO;
import com.health.domain.vo.PageBean;
import com.health.domain.vo.Result;
import com.health.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/checkitem")
public class CheckItemController {

    @Autowired
    private CheckItemService checkItemService;

    @PostMapping("/add")
    public Result<Void> add(@RequestBody CheckItem checkItem) {
        checkItemService.add(checkItem);
        return Result.success(MessageConstant.ADD_CHECKITEM_SUCCESS, null);
    }

    @PostMapping("/findPage")
    public Result<PageBean<CheckItem>> findPage(@RequestBody PageQueryDTO pageQueryDTO) {
        PageBean<CheckItem> pageBean = checkItemService.findPage(
                pageQueryDTO.getCurrentPage(),
                pageQueryDTO.getPageSize(),
                pageQueryDTO.getQueryString()
        );
        return Result.success(MessageConstant.QUERY_SUCCESS, pageBean);
    }

    @GetMapping("/delete")
    public Result<Void> delete(Integer id) {
        checkItemService.deleteById(id);
        return Result.success(MessageConstant.DELETE_CHECKITEM_SUCCESS, null);
    }

    @GetMapping("/findById")
    public Result<CheckItem> findById(Integer id) {
        CheckItem checkItem = checkItemService.findById(id);
        return Result.success(MessageConstant.QUERY_CHECKITEM_SUCCESS, checkItem);
    }

    @PostMapping("/edit")
    public Result<Void> edit(@RequestBody CheckItem checkItem) {
        checkItemService.edit(checkItem);
        return Result.success(MessageConstant.EDIT_CHECKITEM_SUCCESS, null);
    }

    @GetMapping("/findAll")
    public Result<List<CheckItem>> findAll() {
        List<CheckItem> list = checkItemService.findAll();
        return Result.success(MessageConstant.QUERY_CHECKITEM_SUCCESS, list);
    }
}