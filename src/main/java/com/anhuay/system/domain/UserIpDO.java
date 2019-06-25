package com.anhuay.system.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 用户访问IP记录
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2019-06-25 12:13:24
 */
public class UserIpDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键编号
	private Long id;
	//访客IP
	private String visitorIp;
	//错误次数
	private Integer errorCount;
	//锁定时间
	private Long lockTime;
	//备注
	private String remark;
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
	 * 设置：访客IP
	 */
	public void setVisitorIp(String visitorIp) {
		this.visitorIp = visitorIp;
	}
	/**
	 * 获取：访客IP
	 */
	public String getVisitorIp() {
		return visitorIp;
	}
	/**
	 * 设置：错误次数
	 */
	public void setErrorCount(Integer errorCount) {
		this.errorCount = errorCount;
	}
	/**
	 * 获取：错误次数
	 */
	public Integer getErrorCount() {
		return errorCount;
	}
	/**
	 * 设置：锁定时间
	 */
	public void setLockTime(Long lockTime) {
		this.lockTime = lockTime;
	}
	/**
	 * 获取：锁定时间
	 */
	public Long getLockTime() {
		return lockTime;
	}
	/**
	 * 设置：备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：备注
	 */
	public String getRemark() {
		return remark;
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
