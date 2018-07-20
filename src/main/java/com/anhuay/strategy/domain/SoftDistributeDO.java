package com.anhuay.strategy.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 软件分发表
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-07-19 20:25:53
 */
public class SoftDistributeDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键编号
	private Long id;
	//模板编号
	private Long templetId;
	//下载服务器地址
	private Integer deviceControlStatus;
	//默认保存文件目录
	private String deviceControlRules;
	//下载重试状态
	private Integer downloadRetryStatus;
	//最大重试次数
	private Integer maxRetryStatus;
	//重试间隔(分)
	private Integer retryInterval;
	//分发软件名单编号
	private String softIds;
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
	 * 设置：下载服务器地址
	 */
	public void setDeviceControlStatus(Integer deviceControlStatus) {
		this.deviceControlStatus = deviceControlStatus;
	}
	/**
	 * 获取：下载服务器地址
	 */
	public Integer getDeviceControlStatus() {
		return deviceControlStatus;
	}
	/**
	 * 设置：默认保存文件目录
	 */
	public void setDeviceControlRules(String deviceControlRules) {
		this.deviceControlRules = deviceControlRules;
	}
	/**
	 * 获取：默认保存文件目录
	 */
	public String getDeviceControlRules() {
		return deviceControlRules;
	}
	/**
	 * 设置：下载重试状态
	 */
	public void setDownloadRetryStatus(Integer downloadRetryStatus) {
		this.downloadRetryStatus = downloadRetryStatus;
	}
	/**
	 * 获取：下载重试状态
	 */
	public Integer getDownloadRetryStatus() {
		return downloadRetryStatus;
	}
	/**
	 * 设置：最大重试次数
	 */
	public void setMaxRetryStatus(Integer maxRetryStatus) {
		this.maxRetryStatus = maxRetryStatus;
	}
	/**
	 * 获取：最大重试次数
	 */
	public Integer getMaxRetryStatus() {
		return maxRetryStatus;
	}
	/**
	 * 设置：重试间隔(分)
	 */
	public void setRetryInterval(Integer retryInterval) {
		this.retryInterval = retryInterval;
	}
	/**
	 * 获取：重试间隔(分)
	 */
	public Integer getRetryInterval() {
		return retryInterval;
	}
	/**
	 * 设置：分发软件名单编号
	 */
	public void setSoftIds(String softIds) {
		this.softIds = softIds;
	}
	/**
	 * 获取：分发软件名单编号
	 */
	public String getSoftIds() {
		return softIds;
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
