package com.health.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.health.constant.RedisConstant;
import com.health.domain.pojo.Setmeal;
import com.health.domain.vo.PageBean;
import com.health.mapper.SetmealMapper;
import com.health.service.SetmealService;
import com.health.utils.AliOssUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private SetmealMapper setmealMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private AliOssUtils aliOssUtils;

    @Override
    public String upload(MultipartFile file) {
        String fileUrl = aliOssUtils.upload(file);
        if (fileUrl != null) {
            String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
            stringRedisTemplate.opsForSet().add(RedisConstant.SETMEAL_PIC_RESOURCES, fileName);
            return fileUrl;
        }
        return null;
    }

    @Override
    public void add(Setmeal setmeal, List<Integer> checkgroupIds) {
        setmealMapper.add(setmeal);
        if (checkgroupIds != null && !checkgroupIds.isEmpty()) {
            setSetmealAndCheckGroup(setmeal.getId(), checkgroupIds);
        }
        if (setmeal.getImg() != null) {
            String fileName = setmeal.getImg().substring(setmeal.getImg().lastIndexOf("/") + 1);
            stringRedisTemplate.opsForSet().add(RedisConstant.SETMEAL_PIC_DB_RESOURCES, fileName);
        }
    }

    @Override
    public PageBean<Setmeal> findPage(Integer currentPage, Integer pageSize, String queryString) {
        PageHelper.startPage(currentPage, pageSize);
        Page<Setmeal> page = setmealMapper.selectByCondition(queryString);
        return new PageBean<>(page.getTotal(), page.getResult());
    }

    // 【新增】
    @Override
    public Setmeal findById(Integer id) {
        return setmealMapper.findById(id);
    }

    // 【新增】
    @Override
    public List<Integer> findCheckGroupIdsBySetmealId(Integer id) {
        return setmealMapper.findCheckGroupIdsBySetmealId(id);
    }

    // 【新增】
    @Override
    public void edit(Setmeal setmeal, List<Integer> checkgroupIds) {
        // 1. 更新套餐基本信息
        setmealMapper.edit(setmeal);
        // 2. 清理旧的关联关系
        setmealMapper.deleteAssociation(setmeal.getId());
        // 3. 建立新的关联关系
        if (checkgroupIds != null && !checkgroupIds.isEmpty()) {
            setSetmealAndCheckGroup(setmeal.getId(), checkgroupIds);
        }
    }

    // 【新增】
    @Override
    public void deleteById(Integer id) {
        // 在实际生产项目中，删除套餐前应先判断该套餐是否关联了订单
        // 1. 先删除套餐与检查组的关联关系
        setmealMapper.deleteAssociation(id);
        // 2. 再删除套餐本身
        setmealMapper.deleteById(id);
    }

    private void setSetmealAndCheckGroup(Integer setmealId, List<Integer> checkgroupIds) {
        for (Integer checkgroupId : checkgroupIds) {
            Map<String, Integer> map = new HashMap<>();
            map.put("setmeal_id", setmealId);
            map.put("checkgroup_id", checkgroupId);
            setmealMapper.setSetmealAndCheckGroup(map);
        }
    }
}