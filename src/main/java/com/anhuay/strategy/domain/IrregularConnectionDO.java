package com.anhuay.strategy.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 违规外联表
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-07-21 17:18:00
 */
public class IrregularConnectionDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键编号
	private Long id;
	//模板编号
	private Long templetId;
	//外联监控状态（启用、禁用）
	private Integer monitorStatus;
	//外联监控规则
	private String monitorRules;
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
	 * 设置：外联监控状态（启用、禁用）
	 */
	public void setMonitorStatus(Integer monitorStatus) {
		this.monitorStatus = monitorStatus;
	}
	/**
	 * 获取：外联监控状态（启用、禁用）
	 */
	public Integer getMonitorStatus() {
		return monitorStatus;
	}
	/**
	 * 设置：外联监控规则
	 */
	public void setMonitorRules(String monitorRules) {
		this.monitorRules = monitorRules;
	}
	/**
	 * 获取：外联监控规则
	 */
	public String getMonitorRules() {
		return monitorRules;
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
