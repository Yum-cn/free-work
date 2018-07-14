package com.anhuay.strategy.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 设备控制表
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-07-07 11:11:50
 */
public class DeviceControlDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键编号
	private Long id;
	//模板编号
	private Long templetId;
	//设备控制状态
	private Integer deviceControlStatus;
	//设备控制规则
	private String deviceControlRules;
	//状态
	private Integer status;
	//创建时间
	private Integer createTime;
	//修改时间
	private Integer updateTime;

	/**
	 * 设置：主键编号
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：主键编号
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：模板编号
	 */
	public void setTempletId(Long templetId) {
		this.templetId = templetId;
	}
	/**
	 * 获取：模板编号
	 */
	public Long getTempletId() {
		return templetId;
	}
	/**
	 * 设置：设备控制状态
	 */
	public void setDeviceControlStatus(Integer deviceControlStatus) {
		this.deviceControlStatus = deviceControlStatus;
	}
	/**
	 * 获取：设备控制状态
	 */
	public Integer getDeviceControlStatus() {
		return deviceControlStatus;
	}
	/**
	 * 设置：设备控制规则
	 */
	public void setDeviceControlRules(String deviceControlRules) {
		this.deviceControlRules = deviceControlRules;
	}
	/**
	 * 获取：设备控制规则
	 */
	public String getDeviceControlRules() {
		return deviceControlRules;
	}
	/**
	 * 设置：状态
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Integer createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Integer getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：修改时间
	 */
	public void setUpdateTime(Integer updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：修改时间
	 */
	public Integer getUpdateTime() {
		return updateTime;
	}
}
