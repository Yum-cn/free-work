package com.anhuay.strategy.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 光盘刻录表
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-07-19 20:25:53
 */
public class DiskRecordDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键编号
	private Long id;
	//模板编号
	private Long templetId;
	//光盘刻录审计状态
	private Integer diskRecordStatus;
	//光盘刻录审计规则
	private String diskRecordRules;
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
	 * 设置：光盘刻录审计状态
	 */
	public void setDiskRecordStatus(Integer diskRecordStatus) {
		this.diskRecordStatus = diskRecordStatus;
	}
	/**
	 * 获取：光盘刻录审计状态
	 */
	public Integer getDiskRecordStatus() {
		return diskRecordStatus;
	}
	/**
	 * 设置：光盘刻录审计规则
	 */
	public void setDiskRecordRules(String diskRecordRules) {
		this.diskRecordRules = diskRecordRules;
	}
	/**
	 * 获取：光盘刻录审计规则
	 */
	public String getDiskRecordRules() {
		return diskRecordRules;
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
