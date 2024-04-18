package com.tyut.employee.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tyut.employee.utils.PageUtils;
import com.tyut.employee.utils.Query;

import com.tyut.employee.dao.AdminDao;
import com.tyut.employee.entity.AdminEntity;
import com.tyut.employee.service.AdminService;

import javax.validation.constraints.NotBlank;


@Service("adminService")
public class AdminServiceImpl extends ServiceImpl<AdminDao, AdminEntity> implements AdminService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<AdminEntity> queryWrapper = new QueryWrapper<>();
        String key = (String) params.get("key");
        if(!StringUtils.isEmpty(key)) {
            queryWrapper.like("username", key);
        }
        IPage<AdminEntity> page = this.page(
                new Query<AdminEntity>().getPage(params),
                queryWrapper
        );

        return new PageUtils(page);
    }

    @Override
    public AdminEntity login(AdminEntity loginVo) {
        AdminDao adminDao = this.baseMapper;
        // 1.根据用户名查询数据库是否存在账号
        @NotBlank String username = loginVo.getUsername();
        AdminEntity adminEntity = adminDao.selectOne(new QueryWrapper<AdminEntity>().eq("username", username));
        // 2.若存在核对密码
        // TODO 加盐加密
        if(adminEntity != null && adminEntity.getPassword().equals(loginVo.getPassword())) {
            return adminEntity;
        }
        return null;
    }

}
