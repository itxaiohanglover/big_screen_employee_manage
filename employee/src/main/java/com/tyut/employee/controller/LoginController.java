package com.tyut.employee.controller;

import com.tyut.employee.entity.AdminEntity;
import com.tyut.employee.service.AdminService;
import com.tyut.employee.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author xh
 * @Date 2022/5/25
 */
@RestController
public class LoginController {
    @Autowired
    AdminService adminService;
    @PostMapping("/login")
    public R login(@Valid @RequestBody AdminEntity loginVo, BindingResult bindingResult) {
        // 判断是否验证成功
        if (bindingResult.hasErrors()) {
            return R.error(101, "账号、密码不能为空，且密码必须大于6位");
        }
        AdminEntity adminEntity = adminService.login(loginVo);
        return adminEntity != null ? R.ok().put("adminEntity", adminEntity) : R.error(102, "账号或密码错误");
    }
}
