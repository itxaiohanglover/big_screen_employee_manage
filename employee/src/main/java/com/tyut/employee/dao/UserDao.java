package com.tyut.employee.dao;

import com.tyut.employee.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * 
 * @author Liu
 * @email 1531137510@qq.com
 * @date 2022-05-10 18:29:55
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {
	
}
