package com.tyut.employee.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author xh
 * @Date 2022/6/2
 */
@Data
public class UserDeptVo {
    /**
     * 主键ID
     */
    private Integer id;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 部门ID
     */
    private Integer deptId;
    /**
     * 部门名
     */
    private String deptName;
    /**
     * 部门录入时间
     */
    private Date createTime;

}
