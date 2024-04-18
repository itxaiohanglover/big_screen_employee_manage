package com.tyut.employee.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.A;

/**
 * @author xh
 * @Date 2022/6/1
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChartVo {
    /**
     * 标题
     */
    private String title;
    /**
     * 数字
     */
    private Integer number;
}
