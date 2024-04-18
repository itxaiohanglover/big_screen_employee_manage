package com.tyut.employee.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tyut.employee.utils.PageUtils;
import com.tyut.employee.entity.UserDeptEntity;

import java.util.Map;

/**
 *
 *
 * @author Liu
 * @email 1531137510@qq.com
 * @date 2022-05-10 18:29:55
 */
public interface UserDeptService extends IService<UserDeptEntity> {

    PageUtils queryPage(Map<String, Object> params);

    Integer getUserNumsByDeptId(Integer id);

    Integer staMonthUserNums(Integer id);

    PageUtils queryUserAndDept(Map<String, Object> params, Integer deptId);
}

