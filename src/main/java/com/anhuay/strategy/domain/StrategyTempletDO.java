package com.anhuay.strategy.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 策略模板表
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-07-22 15:48:51
 */
public class StrategyTempletDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键编号
	private Long id;
	//模板名称
	private String templetName;
	//模板描述
	private String templetDesc;
	//模板类型(0-普通模板 1-默认模板)
	private Integer templetType;
	//主机审计编号
	private Long osAuditId;
	//违规外联编号
	private Long irregularConnectionId;
	//设备控制编号
	private Long deviceControlId;
	//光盘刻录编号
	private Long diskRecordId;
	//资产管理编号
	private Long assetManagementId;
	//软件分发编号
	private Long softDistributeId;
	//状态
	private Integer status;
	//创建时间
	private Long createTime;
	//修改时间
	private Long updateTime;

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
	 * 设置：模板名称
	 */
	public void setTempletName(String templetName) {
		this.templetName = templetName;
	}
	/**
	 * 获取：模板名称
	 */
	public String getTempletName() {
		return templetName;
	}
	/**
	 * 设置：模板描述
	 */
	public void setTempletDesc(String templetDesc) {
		this.templetDesc = templetDesc;
	}
	/**
	 * 获取：模板描述
	 */
	public String getTempletDesc() {
		return templetDesc;
	}
	/**
	 * 设置：模板类型(0-普通模板 1-默认模板)
	 */
	public void setTempletType(Integer templetType) {
		this.templetType = templetType;
	}
	/**
	 * 获取：模板类型(0-普通模板 1-默认模板)
	 */
	public Integer getTempletType() {
		return templetType;
	}
	/**
	 * 设置：主机审计编号
	 */
	public void setOsAuditId(Long osAuditId) {
		this.osAuditId = osAuditId;
	}
	/**
	 * 获取：主机审计编号
	 */
	public Long getOsAuditId() {
		return osAuditId;
	}
	/**
	 * 设置：违规外联编号
	 */
	public void setIrregularConnectionId(Long irregularConnectionId) {
		this.irregularConnectionId = irregularConnectionId;
	}
	/**
	 * 获取：违规外联编号
	 */
	public Long getIrregularConnectionId() {
		return irregularConnectionId;
	}
	/**
	 * 设置：设备控制编号
	 */
	public void setDeviceControlId(Long deviceControlId) {
		this.deviceControlId = deviceControlId;
	}
	/**
	 * 获取：设备控制编号
	 */
	public Long getDeviceControlId() {
		return deviceControlId;
	}
	/**
	 * 设置：光盘刻录编号
	 */
	public void setDiskRecordId(Long diskRecordId) {
		this.diskRecordId = diskRecordId;
	}
	/**
	 * 获取：光盘刻录编号
	 */
	public Long getDiskRecordId() {
		return diskRecordId;
	}
	/**
	 * 设置：资产管理编号
	 */
	public void setAssetManagementId(Long assetManagementId) {
		this.assetManagementId = assetManagementId;
	}
	/**
	 * 获取：资产管理编号
	 */
	public Long getAssetManagementId() {
		return assetManagementId;
	}
	/**
	 * 设置：软件分发编号
	 */
	public void setSoftDistributeId(Long softDistributeId) {
		this.softDistributeId = softDistributeId;
	}
	/**
	 * 获取：软件分发编号
	 */
	public Long getSoftDistributeId() {
		return softDistributeId;
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
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Long getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：修改时间
	 */
	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：修改时间
	 */
	public Long getUpdateTime() {
		return updateTime;
	}
}
