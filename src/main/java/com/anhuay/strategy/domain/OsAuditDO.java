package com.anhuay.strategy.domain;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;



/**
 * 主机审计表
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-07-07 19:26:32
 */
public class OsAuditDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键编号
	@JsonFormat(shape=JsonFormat.Shape.STRING)
	private Long id;
	//模板编号
	@JsonFormat(shape=JsonFormat.Shape.STRING)
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
	//违规接入审计状态
	private Integer irregularConnectionStatus;
	//违规接入审计规则
	private String irregularConnectionRules;
	//安装目录审计状态
	private Integer installCatalogStatus;
	//安装目录审计规则
	private String installCatalogRules;
	//违规卸载审计状态
	private Integer irregularUninstallStatus;
	//违规卸载审计规则
	private String irregularUninstallRules;
	//正常卸载审计状态
	private Integer regularUninstallStatus;
	//正常卸载审计规则
	private String regularUninstallRules;
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
	 * 设置：违规接入审计状态
	 */
	public void setIrregularConnectionStatus(Integer irregularConnectionStatus) {
		this.irregularConnectionStatus = irregularConnectionStatus;
	}
	/**
	 * 获取：违规接入审计状态
	 */
	public Integer getIrregularConnectionStatus() {
		return irregularConnectionStatus;
	}
	/**
	 * 设置：违规接入审计规则
	 */
	public void setIrregularConnectionRules(String irregularConnectionRules) {
		this.irregularConnectionRules = irregularConnectionRules;
	}
	/**
	 * 获取：违规接入审计规则
	 */
	public String getIrregularConnectionRules() {
		return irregularConnectionRules;
	}
	/**
	 * 设置：安装目录审计状态
	 */
	public void setInstallCatalogStatus(Integer installCatalogStatus) {
		this.installCatalogStatus = installCatalogStatus;
	}
	/**
	 * 获取：安装目录审计状态
	 */
	public Integer getInstallCatalogStatus() {
		return installCatalogStatus;
	}
	/**
	 * 设置：安装目录审计规则
	 */
	public void setInstallCatalogRules(String installCatalogRules) {
		this.installCatalogRules = installCatalogRules;
	}
	/**
	 * 获取：安装目录审计规则
	 */
	public String getInstallCatalogRules() {
		return installCatalogRules;
	}
	/**
	 * 设置：违规卸载审计状态
	 */
	public void setIrregularUninstallStatus(Integer irregularUninstallStatus) {
		this.irregularUninstallStatus = irregularUninstallStatus;
	}
	/**
	 * 获取：违规卸载审计状态
	 */
	public Integer getIrregularUninstallStatus() {
		return irregularUninstallStatus;
	}
	/**
	 * 设置：违规卸载审计规则
	 */
	public void setIrregularUninstallRules(String irregularUninstallRules) {
		this.irregularUninstallRules = irregularUninstallRules;
	}
	/**
	 * 获取：违规卸载审计规则
	 */
	public String getIrregularUninstallRules() {
		return irregularUninstallRules;
	}
	/**
	 * 设置：正常卸载审计状态
	 */
	public void setRegularUninstallStatus(Integer regularUninstallStatus) {
		this.regularUninstallStatus = regularUninstallStatus;
	}
	/**
	 * 获取：正常卸载审计状态
	 */
	public Integer getRegularUninstallStatus() {
		return regularUninstallStatus;
	}
	/**
	 * 设置：正常卸载审计规则
	 */
	public void setRegularUninstallRules(String regularUninstallRules) {
		this.regularUninstallRules = regularUninstallRules;
	}
	/**
	 * 获取：正常卸载审计规则
	 */
	public String getRegularUninstallRules() {
		return regularUninstallRules;
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
