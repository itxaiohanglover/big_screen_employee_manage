package com.tyut.employee.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.tyut.employee.entity.UserEntity;
import com.tyut.employee.service.UserService;
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
@RequestMapping("employee/user")
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping("/updateResumeById")
    public R updateResumeById(@RequestParam Map<String, String> params) {
        Long id = Long.valueOf(params.get("id"));
        String fileUrl = String.valueOf(params.get("fileUrl"));
        boolean flag = userService.updateResumeById(id, fileUrl);
        if(flag) {
            return R.ok();
        }else {
            return R.error();
        }
    }

    /**
     * 列表x
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = userService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		UserEntity user = userService.getById(id);

        return R.ok().put("user", user);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public R save(@RequestBody UserEntity user){
		userService.save(user);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public R update(@RequestBody UserEntity user){
		userService.updateById(user);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		userService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
