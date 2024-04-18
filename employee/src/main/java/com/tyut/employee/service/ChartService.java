package com.tyut.employee.service;

import com.tyut.employee.vo.ChartVo;

import java.util.List;
import java.util.Map;

/**
 * @author xh
 * @Date 2022/6/1
 */
public interface ChartService {
    List<ChartVo> getCountNum(Map<String, String> params);
}
