package com.tyut.employee.controller;

import com.tyut.employee.service.ChartService;
import com.tyut.employee.utils.R;
import com.tyut.employee.vo.ChartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author xh
 * @Date 2022/6/1
 */
@RestController
public class ChartController {

    @Autowired
    private ChartService chartService;

    /**
     * 统计子部门个数，部门成员个数，本月部员新加入个数
     *
     */
    @GetMapping("/getCountNum")
    public R getCountNum(@RequestParam Map<String, String> params) {
        // 统计子部门个数，部门成员个数，本月部员新加入个数
        List<ChartVo> chartVos = chartService.getCountNum(params);
        return R.ok().put("data", chartVos);
    }
}
