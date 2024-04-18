package com.tyut.employee.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tyut.employee.utils.PageUtils;
import com.tyut.employee.entity.UserEntity;

import java.util.Map;

/**
 *
 *
 * @author Liu
 * @email 1531137510@qq.com
 * @date 2022-05-10 18:29:55
 */
public interface UserService extends IService<UserEntity> {

    PageUtils queryPage(Map<String, Object> params);

    boolean updateResumeById(Long id, String fileUrl);

    String getUserNameByUserId(Long userId);
}

