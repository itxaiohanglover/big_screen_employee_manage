package com.tyut.employee.service.impl;

import com.tyut.employee.entity.UserEntity;
import com.tyut.employee.service.DeptService;
import com.tyut.employee.service.UserService;
import com.tyut.employee.vo.UserDeptVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tyut.employee.utils.PageUtils;
import com.tyut.employee.utils.Query;

import com.tyut.employee.dao.UserDeptDao;
import com.tyut.employee.entity.UserDeptEntity;
import com.tyut.employee.service.UserDeptService;


@Service("userDeptService")
@Slf4j
public class UserDeptServiceImpl extends ServiceImpl<UserDeptDao, UserDeptEntity> implements UserDeptService {

    @Autowired
    private UserService userService;

    @Autowired
    private DeptService deptService;


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UserDeptEntity> page = this.page(
                new Query<UserDeptEntity>().getPage(params),
                new QueryWrapper<UserDeptEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public Integer getUserNumsByDeptId(Integer id) {
        QueryWrapper<UserDeptEntity> queryWrapper = new QueryWrapper<>();
        if(id != 0) {
            queryWrapper.eq("dept_id", id);
        }
        return baseMapper.selectCount(queryWrapper);
    }

    /**
     * 统计本月新增部员数量
     */
    @Override
    public Integer staMonthUserNums(Integer id) {
        LocalDate localDate = LocalDate.now();
        LocalDate startTime = localDate.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate endTime = localDate.with(TemporalAdjusters.lastDayOfMonth());
        log.info("startTime:{}, endTime:{}", startTime, endTime);
        QueryWrapper<UserDeptEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.between("create_time", startTime, endTime);
        if(id != 0) {
            queryWrapper.eq("dept_id", id);
        }
        return baseMapper.selectCount(queryWrapper);
    }

    /**
     * 根据部门ID，查询用户部门关系表
     */
    @Override
    public PageUtils queryUserAndDept(Map<String, Object> params, Integer deptId) {
        QueryWrapper<UserDeptEntity> queryWrapper = new QueryWrapper<UserDeptEntity>();
        if(deptId != 0) {
            queryWrapper.eq("dept_id", deptId);
        }
        String key = (String) params.get("key");
        if(!StringUtils.isEmpty(key)) {
            queryWrapper.and((wrapper -> wrapper.like("user_id", key)));
        }
        IPage<UserDeptEntity> page = this.page(new Query<UserDeptEntity>().getPage(params), queryWrapper);
        PageUtils pageUtils = new PageUtils(page);
        List<UserDeptVo> userDeptVos = page.getRecords().stream().map(userDeptEntity -> {
            UserDeptVo userDeptVo = new UserDeptVo();
            BeanUtils.copyProperties(userDeptEntity, userDeptVo);
            userDeptVo.setUsername(userService.getUserNameByUserId(userDeptVo.getUserId()));
            userDeptVo.setDeptName(deptService.getDeptNameByDeptId(userDeptVo.getDeptId()));
            return userDeptVo;
        }).collect(Collectors.toList());
        pageUtils.setList(userDeptVos);
        return pageUtils;
    }

}
