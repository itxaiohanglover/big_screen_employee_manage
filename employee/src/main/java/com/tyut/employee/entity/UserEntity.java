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
@TableName("em_user")
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 工号
	 */
	@TableId
	private Long id;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 性别（0代表女1代表男，默认男）
	 */
	private Integer sex;
	/**
	 * 出生日期
	 */
	private Date birthday;
	/**
	 * 籍贯
	 */
	private String birth;
	/**
	 * 学历
	 */
	private String education;
	/**
	 * 毕业院校
	 */
	private String school;
	/**
	 * 专业
	 */
	private String major;
	/**
	 * 照片
	 */
	private String photo;
	/**
	 * 简历
	 */
	private String resume;

}
