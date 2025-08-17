package com.health.controller;

import com.health.constant.MessageConstant;
import com.health.domain.pojo.Setmeal;
import com.health.domain.query.PageQueryDTO;
import com.health.domain.vo.PageBean;
import com.health.domain.vo.Result;
import com.health.service.SetmealService;
import lombok.extern.slf4j.Slf4j; // 【新增】导入日志注解
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequestMapping("/setmeal")
@Slf4j // 【新增】添加日志注解
public class SetmealController {

    @Autowired
    private SetmealService setmealService;

    @PostMapping("/upload")
    public Result<String> upload(@RequestParam("imgFile") MultipartFile imgFile) {
        String fileUrl = setmealService.upload(imgFile);
        if (fileUrl != null) {
            // 【新增】在后端控制台打印日志
            log.info("图片上传成功，访问URL为：{}", fileUrl);
            return Result.success(MessageConstant.PIC_UPLOAD_SUCCESS, fileUrl);
        }
        return Result.error(MessageConstant.PIC_UPLOAD_FAIL);
    }

    @PostMapping("/add")
    public Result<Void> add(@RequestBody Setmeal setmeal, @RequestParam List<Integer> checkgroupIds) {
        setmealService.add(setmeal, checkgroupIds);
        return Result.success(MessageConstant.ADD_SETMEAL_SUCCESS, null);
    }

    @PostMapping("/findPage")
    public PageBean<Setmeal> findPage(@RequestBody PageQueryDTO pageQueryDTO) {
        return setmealService.findPage(
                pageQueryDTO.getCurrentPage(),
                pageQueryDTO.getPageSize(),
                pageQueryDTO.getQueryString()
        );
    }

    @GetMapping("/findById")
    public Result<Setmeal> findById(Integer id) {
        Setmeal setmeal = setmealService.findById(id);
        return Result.success(MessageConstant.QUERY_SETMEAL_SUCCESS, setmeal);
    }

    @GetMapping("/findCheckGroupIdsBySetmealId")
    public Result<List<Integer>> findCheckGroupIdsBySetmealId(Integer id) {
        List<Integer> ids = setmealService.findCheckGroupIdsBySetmealId(id);
        return Result.success("查询检查组ID成功", ids);
    }

    @PostMapping("/edit")
    public Result<Void> edit(@RequestBody Setmeal setmeal, @RequestParam List<Integer> checkgroupIds) {
        setmealService.edit(setmeal, checkgroupIds);
        return Result.success(MessageConstant.EDIT_SETMEAL_SUCCESS, null);
    }

    @GetMapping("/delete")
    public Result<Void> delete(Integer id) {
        setmealService.deleteById(id);
        return Result.success(MessageConstant.DELETE_SETMEAL_SUCCESS, null);
    }
}