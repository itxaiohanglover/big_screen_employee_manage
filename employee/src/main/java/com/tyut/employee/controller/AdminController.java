package com.tyut.employee.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.tyut.employee.entity.AdminEntity;
import com.tyut.employee.service.AdminService;
import com.tyut.employee.utils.PageUtils;
import com.tyut.employee.utils.R;



/**
 *
 *
 * @author Liu
 * @email 1531137510@qq.com
 * @date 2022-05-10 18:29:55
 */
@RestController
@RequestMapping("employee/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    /**
     * 列表
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = adminService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		AdminEntity admin = adminService.getById(id);

        return R.ok().put("admin", admin);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public R save(@RequestBody AdminEntity admin){
		adminService.save(admin);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public R update(@RequestBody AdminEntity admin){
		adminService.updateById(admin);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
		adminService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
