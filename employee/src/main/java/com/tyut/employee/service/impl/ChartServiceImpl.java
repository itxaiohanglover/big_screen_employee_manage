package com.tyut.employee.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tyut.employee.service.ChartService;
import com.tyut.employee.service.DeptService;
import com.tyut.employee.service.UserDeptService;
import com.tyut.employee.vo.ChartVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author xh
 * @Date 2022/6/1
 */
@Service("chartService")
public class ChartServiceImpl implements ChartService {

    @Autowired
    private DeptService deptService;

    @Autowired
    private UserDeptService userDeptService;

    // 统计子部门个数，部门成员个数，本月部员新加入个数
    @Override
    public List<ChartVo> getCountNum(Map<String, String> params) {
        int id = 0;
        if(!StringUtils.isEmpty(params.get("id"))) {
            try {
                id = Integer.parseInt(params.get("id"));
            } catch (Exception e) {
                return new ArrayList<>();
            }
        }
        Integer deptNums = deptService.getDeptNumById(id);
        Integer userNums = userDeptService.getUserNumsByDeptId(id);
        Integer monthUserNums = userDeptService.staMonthUserNums(id);
        List<ChartVo> chartVos = new ArrayList<>();
        chartVos.add(new ChartVo("子部门", deptNums));
        chartVos.add(new ChartVo("部门成员", userNums));
        chartVos.add(new ChartVo("本月部员", monthUserNums));
        return chartVos;

    }
}
