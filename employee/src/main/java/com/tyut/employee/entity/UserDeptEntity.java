package com.tyut.employee.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author Liu
 * @email 1531137510@qq.com
 * @date 2022-05-10 18:29:55
 */
@Data
@TableName("em_user_dept")
public class UserDeptEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@TableId
	private Integer id;
	/**
	 * 员工ID
	 */
	private Long userId;
	/**
	 * 部门ID
	 */
	private Integer deptId;
	/**
	 * 部门录入时间
	 */
	private Date createTime;

}
