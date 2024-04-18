package com.tyut.employee.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.tyut.employee.entity.DeptEntity;
import com.tyut.employee.service.DeptService;
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
@RequestMapping("employee/dept")
public class DeptController {
    @Autowired
    private DeptService deptService;

    /**
     * 查询部门树形菜单
     */
    @GetMapping("/list/tree")
    public R listWithTree() {
        List<DeptEntity> entities = deptService.listWithTree();
        return R.ok().put("data", entities);
    }


    /**
     * 列表x
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = deptService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		DeptEntity dept = deptService.getById(id);

        return R.ok().put("dept", dept);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public R save(@RequestBody DeptEntity dept){
		deptService.save(dept);

        return R.ok();
    }

    /**
     * 批量修改
     */
    @PostMapping("/update/sort")
    public R updateSort(@RequestBody DeptEntity[] category){
        deptService.updateBatchById(Arrays.asList(category));
        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public R update(@RequestBody DeptEntity dept){
		deptService.updateById(dept);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
		deptService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
