package com.tyut.employee.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 *
 *
 * @author Liu
 * @email 1531137510@qq.com
 * @date 2022-05-10 18:29:55
 */
@Data
@TableName("em_dept")
public class DeptEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 部门ID
	 */
	@TableId
	private Integer id;
	/**
	 * 父ID
	 */
	@JsonProperty(value = "pId")
	private Integer pId;
	/**
	 * 部门名称
	 */
	private String name;
	/**
	 * 层级
	 */
	private Integer deptLevel;
	/**
	 * 是否显示[0-不显示，1显示]
	 */
	@TableLogic(value = "1", delval = "0")
	private Integer showStatus;
	/**
	 * 排序
	 */
	private Integer sort;

	@JsonInclude(JsonInclude.Include.NON_EMPTY) // 属性为空不参与序列化，这里方便前端处理
	@TableField(exist = false) // 数据库表中不存在该字段
	private List<DeptEntity> children;

}
