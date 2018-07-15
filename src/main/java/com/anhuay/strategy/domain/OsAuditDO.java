package com.anhuay.strategy.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 主机审计表
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-07-15 17:32:57
 */
public class OsAuditDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键编号
	private Long id;
	//模板编号
	private Long templetId;
	//本地文件审计状态
	private Integer localFileStatus;
	//本地文件审计规则
	private String localFileRules;
	//光盘文件审计状态
	private Integer diskFileStatus;
	//光盘文件审计规则
	private String diskFileRules;
	//U盘文件审计状态
	private Integer udiskFileStatus;
	//U盘文件审计规则
	private String udiskFileRules;
	//开机关机审计状态
	private Integer osOnoffStatus;
	//开机关机审计规则
	private String osOnoffRules;
	//进程监控审计状态
	private Integer processMonitorStatus;
	//进程监控审计规则
	private String processMonitorRules;
	//打印监控审计状态
	private Integer printMonitorStatus;
	//打印监控审计规则
	private String printMonitorRules;
	//主机信息审计状态
	private Integer osInfoStatus;
	//主机信息审计规则
	private String osInfoRules;
	//系统日志审计状态
	private Integer systemLogStatus;
	//系统日志审计规则
	private String systemLogRules;
	//账户监控审计状态
	private Integer accountMonitorStatus;
	//账户监控审计规则
	private String accountMonitorRules;
	//共享监控审计状态
	private Integer shareMonitorStatus;
	//共享监控审计规则
	private String shareMonitorRules;
	//异常监控审计状态
	private Integer exceptionMonitorStatus;
	//异常监控审计规则
	private String exceptionMonitorRules;
	//主机配置审计状态
	private Integer osConfigStatus;
	//主机配置审计规则
	private String osConfigRules;
	//移动介质审计状态
	private Integer moveMediaStatus;
	//移动介质审计规则
	private String moveMediaRules;
	//端口监控审计状态
	private Integer portMonitorStatus;
	//端口监控审计规则
	private String portMonitorRules;
	//服务监控审计状态
	private Integer serverMonitorStatus;
	//服务监控审计规则
	private String serverMonitorRules;
	//连接监控审计状态
	private Integer connectionMonitorStatus;
	//连接监控审计规则
	private String connectionMonitorRules;
	//网络流量审计状态
	private Integer networkFlowStatus;
	//网络流量审计规则
	private String networkFlowRules;
	//磁盘空间审计状态
	private Integer diskSpaceStatus;
	//磁盘空间审计规则
	private String diskSpaceRules;
	//文件控制审计状态
	private Integer fileControlStatus;
	//文件控制审计规则
	private String fileControlRules;
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
	 * 设置：本地文件审计状态
	 */
	public void setLocalFileStatus(Integer localFileStatus) {
		this.localFileStatus = localFileStatus;
	}
	/**
	 * 获取：本地文件审计状态
	 */
	public Integer getLocalFileStatus() {
		return localFileStatus;
	}
	/**
	 * 设置：本地文件审计规则
	 */
	public void setLocalFileRules(String localFileRules) {
		this.localFileRules = localFileRules;
	}
	/**
	 * 获取：本地文件审计规则
	 */
	public String getLocalFileRules() {
		return localFileRules;
	}
	/**
	 * 设置：光盘文件审计状态
	 */
	public void setDiskFileStatus(Integer diskFileStatus) {
		this.diskFileStatus = diskFileStatus;
	}
	/**
	 * 获取：光盘文件审计状态
	 */
	public Integer getDiskFileStatus() {
		return diskFileStatus;
	}
	/**
	 * 设置：光盘文件审计规则
	 */
	public void setDiskFileRules(String diskFileRules) {
		this.diskFileRules = diskFileRules;
	}
	/**
	 * 获取：光盘文件审计规则
	 */
	public String getDiskFileRules() {
		return diskFileRules;
	}
	/**
	 * 设置：U盘文件审计状态
	 */
	public void setUdiskFileStatus(Integer udiskFileStatus) {
		this.udiskFileStatus = udiskFileStatus;
	}
	/**
	 * 获取：U盘文件审计状态
	 */
	public Integer getUdiskFileStatus() {
		return udiskFileStatus;
	}
	/**
	 * 设置：U盘文件审计规则
	 */
	public void setUdiskFileRules(String udiskFileRules) {
		this.udiskFileRules = udiskFileRules;
	}
	/**
	 * 获取：U盘文件审计规则
	 */
	public String getUdiskFileRules() {
		return udiskFileRules;
	}
	/**
	 * 设置：开机关机审计状态
	 */
	public void setOsOnoffStatus(Integer osOnoffStatus) {
		this.osOnoffStatus = osOnoffStatus;
	}
	/**
	 * 获取：开机关机审计状态
	 */
	public Integer getOsOnoffStatus() {
		return osOnoffStatus;
	}
	/**
	 * 设置：开机关机审计规则
	 */
	public void setOsOnoffRules(String osOnoffRules) {
		this.osOnoffRules = osOnoffRules;
	}
	/**
	 * 获取：开机关机审计规则
	 */
	public String getOsOnoffRules() {
		return osOnoffRules;
	}
	/**
	 * 设置：进程监控审计状态
	 */
	public void setProcessMonitorStatus(Integer processMonitorStatus) {
		this.processMonitorStatus = processMonitorStatus;
	}
	/**
	 * 获取：进程监控审计状态
	 */
	public Integer getProcessMonitorStatus() {
		return processMonitorStatus;
	}
	/**
	 * 设置：进程监控审计规则
	 */
	public void setProcessMonitorRules(String processMonitorRules) {
		this.processMonitorRules = processMonitorRules;
	}
	/**
	 * 获取：进程监控审计规则
	 */
	public String getProcessMonitorRules() {
		return processMonitorRules;
	}
	/**
	 * 设置：打印监控审计状态
	 */
	public void setPrintMonitorStatus(Integer printMonitorStatus) {
		this.printMonitorStatus = printMonitorStatus;
	}
	/**
	 * 获取：打印监控审计状态
	 */
	public Integer getPrintMonitorStatus() {
		return printMonitorStatus;
	}
	/**
	 * 设置：打印监控审计规则
	 */
	public void setPrintMonitorRules(String printMonitorRules) {
		this.printMonitorRules = printMonitorRules;
	}
	/**
	 * 获取：打印监控审计规则
	 */
	public String getPrintMonitorRules() {
		return printMonitorRules;
	}
	/**
	 * 设置：主机信息审计状态
	 */
	public void setOsInfoStatus(Integer osInfoStatus) {
		this.osInfoStatus = osInfoStatus;
	}
	/**
	 * 获取：主机信息审计状态
	 */
	public Integer getOsInfoStatus() {
		return osInfoStatus;
	}
	/**
	 * 设置：主机信息审计规则
	 */
	public void setOsInfoRules(String osInfoRules) {
		this.osInfoRules = osInfoRules;
	}
	/**
	 * 获取：主机信息审计规则
	 */
	public String getOsInfoRules() {
		return osInfoRules;
	}
	/**
	 * 设置：系统日志审计状态
	 */
	public void setSystemLogStatus(Integer systemLogStatus) {
		this.systemLogStatus = systemLogStatus;
	}
	/**
	 * 获取：系统日志审计状态
	 */
	public Integer getSystemLogStatus() {
		return systemLogStatus;
	}
	/**
	 * 设置：系统日志审计规则
	 */
	public void setSystemLogRules(String systemLogRules) {
		this.systemLogRules = systemLogRules;
	}
	/**
	 * 获取：系统日志审计规则
	 */
	public String getSystemLogRules() {
		return systemLogRules;
	}
	/**
	 * 设置：账户监控审计状态
	 */
	public void setAccountMonitorStatus(Integer accountMonitorStatus) {
		this.accountMonitorStatus = accountMonitorStatus;
	}
	/**
	 * 获取：账户监控审计状态
	 */
	public Integer getAccountMonitorStatus() {
		return accountMonitorStatus;
	}
	/**
	 * 设置：账户监控审计规则
	 */
	public void setAccountMonitorRules(String accountMonitorRules) {
		this.accountMonitorRules = accountMonitorRules;
	}
	/**
	 * 获取：账户监控审计规则
	 */
	public String getAccountMonitorRules() {
		return accountMonitorRules;
	}
	/**
	 * 设置：共享监控审计状态
	 */
	public void setShareMonitorStatus(Integer shareMonitorStatus) {
		this.shareMonitorStatus = shareMonitorStatus;
	}
	/**
	 * 获取：共享监控审计状态
	 */
	public Integer getShareMonitorStatus() {
		return shareMonitorStatus;
	}
	/**
	 * 设置：共享监控审计规则
	 */
	public void setShareMonitorRules(String shareMonitorRules) {
		this.shareMonitorRules = shareMonitorRules;
	}
	/**
	 * 获取：共享监控审计规则
	 */
	public String getShareMonitorRules() {
		return shareMonitorRules;
	}
	/**
	 * 设置：异常监控审计状态
	 */
	public void setExceptionMonitorStatus(Integer exceptionMonitorStatus) {
		this.exceptionMonitorStatus = exceptionMonitorStatus;
	}
	/**
	 * 获取：异常监控审计状态
	 */
	public Integer getExceptionMonitorStatus() {
		return exceptionMonitorStatus;
	}
	/**
	 * 设置：异常监控审计规则
	 */
	public void setExceptionMonitorRules(String exceptionMonitorRules) {
		this.exceptionMonitorRules = exceptionMonitorRules;
	}
	/**
	 * 获取：异常监控审计规则
	 */
	public String getExceptionMonitorRules() {
		return exceptionMonitorRules;
	}
	/**
	 * 设置：主机配置审计状态
	 */
	public void setOsConfigStatus(Integer osConfigStatus) {
		this.osConfigStatus = osConfigStatus;
	}
	/**
	 * 获取：主机配置审计状态
	 */
	public Integer getOsConfigStatus() {
		return osConfigStatus;
	}
	/**
	 * 设置：主机配置审计规则
	 */
	public void setOsConfigRules(String osConfigRules) {
		this.osConfigRules = osConfigRules;
	}
	/**
	 * 获取：主机配置审计规则
	 */
	public String getOsConfigRules() {
		return osConfigRules;
	}
	/**
	 * 设置：移动介质审计状态
	 */
	public void setMoveMediaStatus(Integer moveMediaStatus) {
		this.moveMediaStatus = moveMediaStatus;
	}
	/**
	 * 获取：移动介质审计状态
	 */
	public Integer getMoveMediaStatus() {
		return moveMediaStatus;
	}
	/**
	 * 设置：移动介质审计规则
	 */
	public void setMoveMediaRules(String moveMediaRules) {
		this.moveMediaRules = moveMediaRules;
	}
	/**
	 * 获取：移动介质审计规则
	 */
	public String getMoveMediaRules() {
		return moveMediaRules;
	}
	/**
	 * 设置：端口监控审计状态
	 */
	public void setPortMonitorStatus(Integer portMonitorStatus) {
		this.portMonitorStatus = portMonitorStatus;
	}
	/**
	 * 获取：端口监控审计状态
	 */
	public Integer getPortMonitorStatus() {
		return portMonitorStatus;
	}
	/**
	 * 设置：端口监控审计规则
	 */
	public void setPortMonitorRules(String portMonitorRules) {
		this.portMonitorRules = portMonitorRules;
	}
	/**
	 * 获取：端口监控审计规则
	 */
	public String getPortMonitorRules() {
		return portMonitorRules;
	}
	/**
	 * 设置：服务监控审计状态
	 */
	public void setServerMonitorStatus(Integer serverMonitorStatus) {
		this.serverMonitorStatus = serverMonitorStatus;
	}
	/**
	 * 获取：服务监控审计状态
	 */
	public Integer getServerMonitorStatus() {
		return serverMonitorStatus;
	}
	/**
	 * 设置：服务监控审计规则
	 */
	public void setServerMonitorRules(String serverMonitorRules) {
		this.serverMonitorRules = serverMonitorRules;
	}
	/**
	 * 获取：服务监控审计规则
	 */
	public String getServerMonitorRules() {
		return serverMonitorRules;
	}
	/**
	 * 设置：连接监控审计状态
	 */
	public void setConnectionMonitorStatus(Integer connectionMonitorStatus) {
		this.connectionMonitorStatus = connectionMonitorStatus;
	}
	/**
	 * 获取：连接监控审计状态
	 */
	public Integer getConnectionMonitorStatus() {
		return connectionMonitorStatus;
	}
	/**
	 * 设置：连接监控审计规则
	 */
	public void setConnectionMonitorRules(String connectionMonitorRules) {
		this.connectionMonitorRules = connectionMonitorRules;
	}
	/**
	 * 获取：连接监控审计规则
	 */
	public String getConnectionMonitorRules() {
		return connectionMonitorRules;
	}
	/**
	 * 设置：网络流量审计状态
	 */
	public void setNetworkFlowStatus(Integer networkFlowStatus) {
		this.networkFlowStatus = networkFlowStatus;
	}
	/**
	 * 获取：网络流量审计状态
	 */
	public Integer getNetworkFlowStatus() {
		return networkFlowStatus;
	}
	/**
	 * 设置：网络流量审计规则
	 */
	public void setNetworkFlowRules(String networkFlowRules) {
		this.networkFlowRules = networkFlowRules;
	}
	/**
	 * 获取：网络流量审计规则
	 */
	public String getNetworkFlowRules() {
		return networkFlowRules;
	}
	/**
	 * 设置：磁盘空间审计状态
	 */
	public void setDiskSpaceStatus(Integer diskSpaceStatus) {
		this.diskSpaceStatus = diskSpaceStatus;
	}
	/**
	 * 获取：磁盘空间审计状态
	 */
	public Integer getDiskSpaceStatus() {
		return diskSpaceStatus;
	}
	/**
	 * 设置：磁盘空间审计规则
	 */
	public void setDiskSpaceRules(String diskSpaceRules) {
		this.diskSpaceRules = diskSpaceRules;
	}
	/**
	 * 获取：磁盘空间审计规则
	 */
	public String getDiskSpaceRules() {
		return diskSpaceRules;
	}
	/**
	 * 设置：文件控制审计状态
	 */
	public void setFileControlStatus(Integer fileControlStatus) {
		this.fileControlStatus = fileControlStatus;
	}
	/**
	 * 获取：文件控制审计状态
	 */
	public Integer getFileControlStatus() {
		return fileControlStatus;
	}
	/**
	 * 设置：文件控制审计规则
	 */
	public void setFileControlRules(String fileControlRules) {
		this.fileControlRules = fileControlRules;
	}
	/**
	 * 获取：文件控制审计规则
	 */
	public String getFileControlRules() {
		return fileControlRules;
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
