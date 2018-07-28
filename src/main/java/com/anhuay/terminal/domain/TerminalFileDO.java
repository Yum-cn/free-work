package com.anhuay.terminal.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 终端程序上传
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-07-28 15:55:35
 */
public class TerminalFileDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键编号
	private Long id;
	//上传地址
	private String uploadUrl;
	//文件名称
	private String fileName;
	//文件标识
	private String fileTag;
	//文件大小
	private Long fileSize;
	//上传时间
	private Long uploadTime;
	//CRC值
	private String crcValue;
	//下载地址
	private String downUrl;
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
	 * 设置：上传地址
	 */
	public void setUploadUrl(String uploadUrl) {
		this.uploadUrl = uploadUrl;
	}
	/**
	 * 获取：上传地址
	 */
	public String getUploadUrl() {
		return uploadUrl;
	}
	/**
	 * 设置：文件名称
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * 获取：文件名称
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * 设置：文件标识
	 */
	public void setFileTag(String fileTag) {
		this.fileTag = fileTag;
	}
	/**
	 * 获取：文件标识
	 */
	public String getFileTag() {
		return fileTag;
	}
	/**
	 * 设置：文件大小
	 */
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
	/**
	 * 获取：文件大小
	 */
	public Long getFileSize() {
		return fileSize;
	}
	/**
	 * 设置：上传时间
	 */
	public void setUploadTime(Long uploadTime) {
		this.uploadTime = uploadTime;
	}
	/**
	 * 获取：上传时间
	 */
	public Long getUploadTime() {
		return uploadTime;
	}
	/**
	 * 设置：CRC值
	 */
	public void setCrcValue(String crcValue) {
		this.crcValue = crcValue;
	}
	/**
	 * 获取：CRC值
	 */
	public String getCrcValue() {
		return crcValue;
	}
	/**
	 * 设置：下载地址
	 */
	public void setDownUrl(String downUrl) {
		this.downUrl = downUrl;
	}
	/**
	 * 获取：下载地址
	 */
	public String getDownUrl() {
		return downUrl;
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
