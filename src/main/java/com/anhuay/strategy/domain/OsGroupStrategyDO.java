package com.anhuay.strategy.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 主机组策略表
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-07-29 16:01:40
 */
public class OsGroupStrategyDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键编号
	private Long id;
	//模板编号
	private Long templetId;
	//模板名称
	private String templetName;
	//主机组编号
	private Long osGroupIds;
	//主机组名称
	private String osGroupNames;
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
	 * 设置：主机组编号
	 */
	public void setOsGroupIds(Long osGroupIds) {
		this.osGroupIds = osGroupIds;
	}
	/**
	 * 获取：主机组编号
	 */
	public Long getOsGroupIds() {
		return osGroupIds;
	}
	/**
	 * 设置：主机组名称
	 */
	public void setOsGroupNames(String osGroupNames) {
		this.osGroupNames = osGroupNames;
	}
	/**
	 * 获取：主机组名称
	 */
	public String getOsGroupNames() {
		return osGroupNames;
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
