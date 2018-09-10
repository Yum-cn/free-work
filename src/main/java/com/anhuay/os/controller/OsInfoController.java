package com.anhuay.os.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anhuay.common.annotation.Log;
import com.anhuay.common.domain.Tree;
import com.anhuay.common.domain.TreeCode;
import com.anhuay.common.utils.PageUtils;
import com.anhuay.common.utils.Query;
import com.anhuay.common.utils.R;
import com.anhuay.common.utils.TreeCodeUtil;
import com.anhuay.os.domain.OsInfoDO;
import com.anhuay.os.domain.OsInfoVO;
import com.anhuay.os.service.OsInfoService;
import com.anhuay.strategy.manager.impl.StrategyTempletManagerImpl;
import com.anhuay.strategy.service.DeptStrategyService;
import com.anhuay.strategy.service.OsGroupStrategyService;
import com.anhuay.strategy.service.OsStrategyService;
import com.anhuay.strategy.service.StrategyTempletService;
import com.anhuay.system.domain.DeptDO;
import com.anhuay.system.license.License;
import com.anhuay.system.license.LicenseException;
import com.anhuay.system.license.LicenseManager;
import com.anhuay.system.license.LicenseNotFoundException;
import com.anhuay.system.service.DeptService;
import com.common.constant.CommonEnum;

/**
 * 主机信息表
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-07-24 14:45:03
 */

@Controller
@RequestMapping("/os/osInfo")
public class OsInfoController {

	private Logger logger = LoggerFactory.getLogger(OsInfoController.class);

	@Autowired
	private OsInfoService osInfoService;
	@Autowired
	private OsStrategyService osStrategyService;
	@Autowired
	private OsGroupStrategyService osGroupStrategyService;
	@Autowired
	private DeptStrategyService deptStrategyService;
	@Autowired
	private DeptService sysDeptService;
	@Autowired
	private StrategyTempletService strategyTempletService;

	@GetMapping()
	@RequiresPermissions("os:osInfo:osInfo")
	String OsInfo() {
		return "os/osInfo/osInfo";
	}

	@GetMapping("/select")
	String OsInfoSelect() {
		return "os/osInfo/osInfoSelect";
	}

	@GetMapping("/v2")
	String OsInfoSelectV2() {
		return "os/osInfo/osInfoV2";
	}

	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);
		List<OsInfoDO> osInfoList = osInfoService.list(query);

		List<OsInfoVO> osInfoVOList = new ArrayList<OsInfoVO>();
		if (CollectionUtils.isNotEmpty(osInfoList)) {
			for (int j = 0; j < osInfoList.size(); j++) {
				OsInfoVO osInfoVO = new OsInfoVO();
				try {
					BeanUtils.copyProperties(osInfoVO, osInfoList.get(j));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// 默认策略
				osInfoVO.setTempletType(4);
				osInfoVO.setTempletName("默认策略");

				try {
					// 主机、主机组、部门、默认
					String osIp = osInfoVO.getOsIp();
					Long id = osInfoVO.getId();
					String strategyNames = osStrategyService.selectOsStrategy(osIp);
					if (StringUtils.isNotBlank(strategyNames)) {
						osInfoVO.setTempletType(1);
						osInfoVO.setTempletName(strategyNames);
					} else {
						// 主机组策略
						strategyNames = osGroupStrategyService.selectOsGroupStrategy(osIp);
						if (StringUtils.isNotBlank(strategyNames)) {
							osInfoVO.setTempletType(2);
							osInfoVO.setTempletName(strategyNames);
						} else {
							// 部门策略
							Integer deptId = osInfoVO.getDeptId();
							if (deptId != null && deptId > 0) {

								List<TreeCode> treeCodeList = new ArrayList<TreeCode>();
								List<DeptDO> sysDepts = sysDeptService.list(new HashMap<String, Object>(16));
								for (DeptDO sysDept : sysDepts) {
									TreeCode tree = new TreeCode();
									tree.setTreeCodeId(sysDept.getDeptId().toString());
									tree.setParentId(sysDept.getParentId().toString());
									tree.setTreeCodeName(sysDept.getName());
									treeCodeList.add(tree);
								}
								TreeCodeUtil treeCodeUtil = new TreeCodeUtil();
								String deptIds = treeCodeUtil.getAllChildId(treeCodeList, String.valueOf(deptId));
								if (StringUtils.isNotBlank(deptIds)) {
									strategyNames = deptStrategyService.selectDeptStrategy(deptIds.split(","));
									if (StringUtils.isNotBlank(strategyNames)) {
										osInfoVO.setTempletType(3);
										osInfoVO.setTempletName(strategyNames);
									}
								}

							}
						}
					}
				} catch (Exception e) {
					logger.error(e.getMessage());
				}

				osInfoVO.setServerTime(System.currentTimeMillis() / 1000);
				osInfoVOList.add(osInfoVO);

			}

		}

		int total = osInfoService.count(query);
		PageUtils pageUtils = new PageUtils(osInfoVOList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("os:osInfo:add")
	String add() {
		return "os/osInfo/add";
	}

	@Log("编辑主机信息")
	@GetMapping("/edit/{id}")
	@RequiresPermissions("os:osInfo:edit")
	String edit(@PathVariable("id") Long id, Model model) {
		OsInfoDO osInfo = osInfoService.get(id);
		model.addAttribute("osInfo", osInfo);
		return "os/osInfo/add";
	}

	/**
	 * 保存
	 */
	@Log("保存主机信息")
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("os:osInfo:add")
	public R save(OsInfoDO osInfo) {

		osInfo.setUpdateTime(System.currentTimeMillis() / 1000);
		if (osInfo.getId() != null && osInfo.getId() > 0) {
			if (osInfoService.update(osInfo) > 0) {
				return R.ok();
			}
		} else {
			osInfo.setStatus(CommonEnum.STATUS.ONE.value);
			osInfo.setCreateTime(System.currentTimeMillis() / 1000);

			/*
			 * try { LicenseManager licenseManager =
			 * LicenseManager.getInstance(); int total = osInfoService.count(new
			 * HashMap<String, Object>()); License license =
			 * licenseManager.getLicense(); System.out.println("license = " +
			 * license); int number =
			 * NumberUtils.toInt(license.getFeature("number"));
			 * 
			 * if (total >= number) { return
			 * R.error(CommonEnum.CODE.INVALID_LICENSE.code,
			 * CommonEnum.CODE.INVALID_LICENSE.description); } } catch
			 * (Exception e) { e.printStackTrace(); return
			 * R.error(CommonEnum.CODE.INVALID_LICENSE.code,
			 * CommonEnum.CODE.INVALID_LICENSE.description); }
			 */

			if (osInfoService.save(osInfo) > 0) {
				return R.ok();
			}
		}
		return R.error();
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("os:osInfo:edit")
	public R update(OsInfoDO osInfo) {
		osInfoService.update(osInfo);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@Log("删除主机信息")
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("os:osInfo:remove")
	public R remove(Long id) {
		if (osInfoService.updateStatus(id) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("os:osInfo:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids) {
		osInfoService.batchUpdateStatus(ids);
		return R.ok();
	}

}
