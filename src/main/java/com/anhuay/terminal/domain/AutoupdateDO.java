package com.anhuay.terminal.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-08-25 13:56:37
 */
public class AutoupdateDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//客户端ip
	private String osIp;
	//客户端id
	private String osId;
	//任务id
	private String taskId;
	//升级文件地址
	private String url;
	//服务端版本
	private String srvVersion;
	//更新标识（1-待更新，2-已下发，3-更新完成）
	private String status;
	//
	private Date createtime;
	//
	private Date updatetime;
	//
	private String notes;

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：客户端ip
	 */
	public void setOsIp(String osIp) {
		this.osIp = osIp;
	}
	/**
	 * 获取：客户端ip
	 */
	public String getOsIp() {
		return osIp;
	}
	/**
	 * 设置：客户端id
	 */
	public void setOsId(String osId) {
		this.osId = osId;
	}
	/**
	 * 获取：客户端id
	 */
	public String getOsId() {
		return osId;
	}
	/**
	 * 设置：任务id
	 */
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	/**
	 * 获取：任务id
	 */
	public String getTaskId() {
		return taskId;
	}
	/**
	 * 设置：升级文件地址
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * 获取：升级文件地址
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * 设置：服务端版本
	 */
	public void setSrvVersion(String srvVersion) {
		this.srvVersion = srvVersion;
	}
	/**
	 * 获取：服务端版本
	 */
	public String getSrvVersion() {
		return srvVersion;
	}
	/**
	 * 设置：更新标识（1-待更新，2-已下发，3-更新完成）
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：更新标识（1-待更新，2-已下发，3-更新完成）
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 设置：
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	/**
	 * 获取：
	 */
	public Date getCreatetime() {
		return createtime;
	}
	/**
	 * 设置：
	 */
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	/**
	 * 获取：
	 */
	public Date getUpdatetime() {
		return updatetime;
	}
	/**
	 * 设置：
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}
	/**
	 * 获取：
	 */
	public String getNotes() {
		return notes;
	}
}
