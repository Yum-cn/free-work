package com.anhuay.soft.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 软件分类
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-07-31 21:17:58
 */
public class SoftClassificationDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键编号
	private Long id;
	//名称
	private String fileName;
	//版本
	private String fileVersion;
	//上传时间
	private Long uploadTime;
	//自定义标签
	private String fileTag;
	//备注
	private String remark;
	//软件分类编号
	private Long dictId;
	//软件分类名称
	private String dictName;
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
	 * 设置：名称
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * 获取：名称
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * 设置：版本
	 */
	public void setFileVersion(String fileVersion) {
		this.fileVersion = fileVersion;
	}
	/**
	 * 获取：版本
	 */
	public String getFileVersion() {
		return fileVersion;
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
	 * 设置：自定义标签
	 */
	public void setFileTag(String fileTag) {
		this.fileTag = fileTag;
	}
	/**
	 * 获取：自定义标签
	 */
	public String getFileTag() {
		return fileTag;
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
	 * 设置：软件分类编号
	 */
	public void setDictId(Long dictId) {
		this.dictId = dictId;
	}
	/**
	 * 获取：软件分类编号
	 */
	public Long getDictId() {
		return dictId;
	}
	/**
	 * 设置：软件分类名称
	 */
	public void setDictName(String dictName) {
		this.dictName = dictName;
	}
	/**
	 * 获取：软件分类名称
	 */
	public String getDictName() {
		return dictName;
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
