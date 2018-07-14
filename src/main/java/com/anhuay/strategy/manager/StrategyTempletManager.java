package com.anhuay.strategy.manager;

import com.common.util.BaseResult;

import net.sf.json.JSONObject;

/**
 * 管理测试版
 * @author   Yum
 */
public interface StrategyTempletManager {

	/**
	 * 保存策略模板
	 * @author   Yum
	 */
	BaseResult<Object> saveStrategyTemplet(JSONObject body);
	/**
	 * 保存策略模板-标签项
	 * @author   Yum
	 */
	BaseResult<Object> saveStrategyTempletTab(JSONObject body);
	
	/**
	 * 逻辑删除
	 * @author   Yum
	 */
	BaseResult<Object> removeStrategyTemplet(Long id);
	
	
	/**
	 * 批量逻辑删除
	 * @author   Yum
	 */
	BaseResult<Object> batchRemoveStrategyTemplet(Long[] ids);

	

}