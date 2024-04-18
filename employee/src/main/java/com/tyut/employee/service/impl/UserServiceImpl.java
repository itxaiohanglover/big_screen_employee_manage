package com.tyut.employee.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tyut.employee.utils.PageUtils;
import com.tyut.employee.utils.Query;

import com.tyut.employee.dao.UserDao;
import com.tyut.employee.entity.UserEntity;
import com.tyut.employee.service.UserService;


@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UserEntity> page = this.page(
                new Query<UserEntity>().getPage(params),
                new QueryWrapper<UserEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * 根据ID更新简历
     * @return
     */
    @Override
    public boolean updateResumeById(Long id, String fileUrl) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(id);
        userEntity.setResume(fileUrl);
        int count = baseMapper.updateById(userEntity);
        return count > 0;
    }

    /**
     * 根据用户ID查询用户名
     */
    @Override
    public String getUserNameByUserId(Long userId) {
        return baseMapper.selectOne(new QueryWrapper<UserEntity>().eq("id", userId)).getName();
    }

}
