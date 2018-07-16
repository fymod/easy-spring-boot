package com.fymod.helloworld;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 添加注解@Entity，能自动生成数据表
 */
@Entity
public class MyTable {
	
	/**
	 * 定义主键和主键生成方式
	 */
	@Id
	@GeneratedValue
	private Long tableId;
	
	/**
	 * @Column 定时列，可以添加name，length等诸多属性
	 */
	@Column
	private String name;

	public Long getTableId() {
		return tableId;
	}

	public void setTableId(Long tableId) {
		this.tableId = tableId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

