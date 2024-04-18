package com.tyut.employee.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tyut.employee.utils.PageUtils;
import com.tyut.employee.entity.AdminEntity;

import java.util.Map;

/**
 *
 *
 * @author Liu
 * @email 1531137510@qq.com
 * @date 2022-05-10 18:29:55
 */
public interface AdminService extends IService<AdminEntity> {

    PageUtils queryPage(Map<String, Object> params);

    AdminEntity login(AdminEntity loginVo);
}

