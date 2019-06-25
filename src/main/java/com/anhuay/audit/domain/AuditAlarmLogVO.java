package com.anhuay.audit.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-07-30 14:46:55
 */
public class AuditAlarmLogVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//日志id
	private Long id;
	//客户端ip地址
	private String osIp;
	//预留与系统主机ID对应
	private String osId;
	//事件客体
	private String info;
	//事件内容（详细信息）
	private String details;
	//事件结果（1-成功，0-失败）
	private String result;
	//事件发生时间
	private Date entryTime;
	//风险级别（0-紧急，1-警报，2-关键，3-错误，4-警告，5-通知，6-信息，7-调试）
	private String level;
	//事件种类（m-管理操作，a-用户操作，s-其他）
	private String type;
	//行为类别（1-违规行为，2-异常行为，3-一般行为）
	private String beType;
	//日志分类（1.本地文件审计，2.U 盘文件审计，3.开机关机审计，4.进程监控审计，5.打印监控审计，6.主机信息审计，7.系统日志审计，8.账户监控审计，9.共享监控审计， 10.异常监控审计，11.主机配置审计， 12.移动介质审计，13.端口监控审计，14.服务监控审计，15.连接监控审计，16.网络流量检测，17.磁盘空间检测，18.文件控制，19.光盘刻录审计）
	private String logType;
	//说明（保留字段）
	private String notes;
	//创建者（保留字段）
	private String createBy;
	//创建时间（保留字段）
	private Date createTime;
	//最后更新者（保留字段）
	private String updateBy;
	//最后更新时间（保留字段）
	private Date updateTime;
	//部门名称
	private String deptName;
	//用户名称（责任人）
	private String personLiableName;
	//主机名称
	private String osName;
	

	private String nodeId;//主体id
	
	private String logId;//日志id
	
	private String program;//部件
	

	/**
	 * 设置：客户端ip地址
	 */
	public void setOsIp(String osIp) {
		this.osIp = osIp;
	}
	/**
	 * 获取：客户端ip地址
	 */
	public String getOsIp() {
		return osIp;
	}
	/**
	 * 设置：预留与系统主机ID对应
	 */
	public void setOsId(String osId) {
		this.osId = osId;
	}
	/**
	 * 获取：预留与系统主机ID对应
	 */
	public String getOsId() {
		return osId;
	}
	/**
	 * 设置：事件客体
	 */
	public void setInfo(String info) {
		this.info = info;
	}
	/**
	 * 获取：事件客体
	 */
	public String getInfo() {
		return info;
	}
	/**
	 * 设置：事件内容（详细信息）
	 */
	public void setDetails(String details) {
		this.details = details;
	}
	/**
	 * 获取：事件内容（详细信息）
	 */
	public String getDetails() {
		return details;
	}
	/**
	 * 设置：事件结果（1-成功，0-失败）
	 */
	public void setResult(String result) {
		this.result = result;
	}
	/**
	 * 获取：事件结果（1-成功，0-失败）
	 */
	public String getResult() {
		return result;
	}
	/**
	 * 设置：事件发生时间
	 */
	public void setEntryTime(Date entryTime) {
		this.entryTime = entryTime;
	}
	/**
	 * 获取：事件发生时间
	 */
	public Date getEntryTime() {
		return entryTime;
	}
	/**
	 * 设置：风险级别（0-紧急，1-警报，2-关键，3-错误，4-警告，5-通知，6-信息，7-调试）
	 */
	public void setLevel(String level) {
		this.level = level;
	}
	/**
	 * 获取：风险级别（0-紧急，1-警报，2-关键，3-错误，4-警告，5-通知，6-信息，7-调试）
	 */
	public String getLevel() {
		return level;
	}
	/**
	 * 设置：事件种类（m-管理操作，a-用户操作，s-其他）
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：事件种类（m-管理操作，a-用户操作，s-其他）
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置：行为类别（1-违规行为，2-异常行为，3-一般行为）
	 */
	public void setBeType(String beType) {
		this.beType = beType;
	}
	/**
	 * 获取：行为类别（1-违规行为，2-异常行为，3-一般行为）
	 */
	public String getBeType() {
		return beType;
	}
	/**
	 * 设置：日志分类（1.本地文件审计，2.U 盘文件审计，3.开机关机审计，4.进程监控审计，5.打印监控审计，6.主机信息审计，7.系统日志审计，8.账户监控审计，9.共享监控审计， 10.异常监控审计，11.主机配置审计， 12.移动介质审计，13.端口监控审计，14.服务监控审计，15.连接监控审计，16.网络流量检测，17.磁盘空间检测，18.文件控制，19.光盘刻录审计）
	 */
	public void setLogType(String logType) {
		this.logType = logType;
	}
	/**
	 * 获取：日志分类（1.本地文件审计，2.U 盘文件审计，3.开机关机审计，4.进程监控审计，5.打印监控审计，6.主机信息审计，7.系统日志审计，8.账户监控审计，9.共享监控审计， 10.异常监控审计，11.主机配置审计， 12.移动介质审计，13.端口监控审计，14.服务监控审计，15.连接监控审计，16.网络流量检测，17.磁盘空间检测，18.文件控制，19.光盘刻录审计）
	 */
	public String getLogType() {
		return logType;
	}
	/**
	 * 设置：说明（保留字段）
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}
	/**
	 * 获取：说明（保留字段）
	 */
	public String getNotes() {
		return notes;
	}
	/**
	 * 设置：创建者（保留字段）
	 */
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	/**
	 * 获取：创建者（保留字段）
	 */
	public String getCreateBy() {
		return createBy;
	}
	/**
	 * 设置：创建时间（保留字段）
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间（保留字段）
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：最后更新者（保留字段）
	 */
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	/**
	 * 获取：最后更新者（保留字段）
	 */
	public String getUpdateBy() {
		return updateBy;
	}
	/**
	 * 设置：最后更新时间（保留字段）
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：最后更新时间（保留字段）
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getPersonLiableName() {
		return personLiableName;
	}
	public void setPersonLiableName(String personLiableName) {
		this.personLiableName = personLiableName;
	}
	public String getOsName() {
		return osName;
	}
	public void setOsName(String osName) {
		this.osName = osName;
	}
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	public String getProgram() {
		return program;
	}
	public void setProgram(String program) {
		this.program = program;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLogId() {
		return logId;
	}
	public void setLogId(String logId) {
		this.logId = logId;
	}
	
}
