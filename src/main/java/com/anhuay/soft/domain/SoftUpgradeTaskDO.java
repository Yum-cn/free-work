package com.anhuay.soft.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 软件分发升级任务管理
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-08-25 11:42:52
 */
public class SoftUpgradeTaskDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键编号
	private Long id;
	//终端程序编号
	private Long softFileId;
	//终端文件名称
	private String softFileName;
	//终端程序下载地址
	private String softFileDownloadUrl;
	//升级任务名称
	private String upgradeName;
	//升级主机编号
	private String osIds;
	//升级主机IP
	private String osIps;
	//升级时间
	private String upgradeTime;
	//版本号
	private String upgradeVersion;
	//通知状态(1已通知 2 未通知)
	private Integer noticeStatus;
	//任务状态(1待下发 2升级中 3已更新)
	private Integer taskStatus;
	//升级规则
	private String upgradeRules;
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
	 * 设置：终端程序编号
	 */
	public void setSoftFileId(Long softFileId) {
		this.softFileId = softFileId;
	}
	/**
	 * 获取：终端程序编号
	 */
	public Long getSoftFileId() {
		return softFileId;
	}
	/**
	 * 设置：终端文件名称
	 */
	public void setSoftFileName(String softFileName) {
		this.softFileName = softFileName;
	}
	/**
	 * 获取：终端文件名称
	 */
	public String getSoftFileName() {
		return softFileName;
	}
	/**
	 * 设置：终端程序下载地址
	 */
	public void setSoftFileDownloadUrl(String softFileDownloadUrl) {
		this.softFileDownloadUrl = softFileDownloadUrl;
	}
	/**
	 * 获取：终端程序下载地址
	 */
	public String getSoftFileDownloadUrl() {
		return softFileDownloadUrl;
	}
	/**
	 * 设置：升级任务名称
	 */
	public void setUpgradeName(String upgradeName) {
		this.upgradeName = upgradeName;
	}
	/**
	 * 获取：升级任务名称
	 */
	public String getUpgradeName() {
		return upgradeName;
	}
	/**
	 * 设置：升级主机编号
	 */
	public void setOsIds(String osIds) {
		this.osIds = osIds;
	}
	/**
	 * 获取：升级主机编号
	 */
	public String getOsIds() {
		return osIds;
	}
	/**
	 * 设置：升级主机IP
	 */
	public void setOsIps(String osIps) {
		this.osIps = osIps;
	}
	/**
	 * 获取：升级主机IP
	 */
	public String getOsIps() {
		return osIps;
	}
	/**
	 * 设置：升级时间
	 */
	public void setUpgradeTime(String upgradeTime) {
		this.upgradeTime = upgradeTime;
	}
	/**
	 * 获取：升级时间
	 */
	public String getUpgradeTime() {
		return upgradeTime;
	}
	/**
	 * 设置：版本号
	 */
	public void setUpgradeVersion(String upgradeVersion) {
		this.upgradeVersion = upgradeVersion;
	}
	/**
	 * 获取：版本号
	 */
	public String getUpgradeVersion() {
		return upgradeVersion;
	}
	/**
	 * 设置：通知状态(1已通知 2 未通知)
	 */
	public void setNoticeStatus(Integer noticeStatus) {
		this.noticeStatus = noticeStatus;
	}
	/**
	 * 获取：通知状态(1已通知 2 未通知)
	 */
	public Integer getNoticeStatus() {
		return noticeStatus;
	}
	/**
	 * 设置：任务状态(1待下发 2升级中 3已更新)
	 */
	public void setTaskStatus(Integer taskStatus) {
		this.taskStatus = taskStatus;
	}
	/**
	 * 获取：任务状态(1待下发 2升级中 3已更新)
	 */
	public Integer getTaskStatus() {
		return taskStatus;
	}
	/**
	 * 设置：升级规则
	 */
	public void setUpgradeRules(String upgradeRules) {
		this.upgradeRules = upgradeRules;
	}
	/**
	 * 获取：升级规则
	 */
	public String getUpgradeRules() {
		return upgradeRules;
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
