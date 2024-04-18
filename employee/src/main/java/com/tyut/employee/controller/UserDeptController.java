package com.tyut.employee.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.tyut.employee.entity.UserDeptEntity;
import com.tyut.employee.service.UserDeptService;
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
@RequestMapping("employee/userdept")
public class UserDeptController {
    @Autowired
    private UserDeptService userDeptService;

    /**
     * 列表， 接口过时
     */
    @GetMapping("/list")
    @Deprecated
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = userDeptService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 根据部门ID，查询用户部门关系表
     */
    @GetMapping("/list/{deptId}")
    public R listUserAndDept(@RequestParam Map<String, Object> params, @PathVariable("deptId") Integer deptId){
        PageUtils page = userDeptService.queryUserAndDept(params, deptId);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		UserDeptEntity userDept = userDeptService.getById(id);

        return R.ok().put("userDept", userDept);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public R save(@RequestBody UserDeptEntity userDept){
		userDeptService.save(userDept);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public R update(@RequestBody UserDeptEntity userDept){
		userDeptService.updateById(userDept);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
		userDeptService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
