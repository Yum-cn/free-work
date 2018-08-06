package com.anhuay.system.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anhuay.common.annotation.Log;
import com.anhuay.common.controller.BaseController;
import com.anhuay.common.domain.FileDO;
import com.anhuay.common.domain.Tree;
import com.anhuay.common.service.FileService;
import com.anhuay.common.utils.IPUtils;
import com.anhuay.common.utils.MD5Utils;
import com.anhuay.common.utils.R;
import com.anhuay.common.utils.ShiroUtils;
import com.anhuay.system.domain.MenuDO;
import com.anhuay.system.domain.UserExtendDO;
import com.anhuay.system.service.MenuService;
import com.anhuay.system.service.UserExtendService;

import net.sf.json.JSONObject;

@Controller
public class LoginController extends BaseController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	MenuService menuService;
	@Autowired
	FileService fileService;
	@Autowired
	private UserExtendService userExtendService;

	@GetMapping({ "/", "" })
	String welcome(Model model) {

		return "redirect:/index";
	}

	@Log("请求访问主页")
	@GetMapping({ "/index" })
	String index(Model model) {
		List<Tree<MenuDO>> menus = menuService.listMenuTree(getUserId());
		model.addAttribute("menus", menus);
		model.addAttribute("name", getUser().getName());
		FileDO fileDO = fileService.get(getUser().getPicId());
		if (fileDO != null && fileDO.getUrl() != null) {
			if (fileService.isExist(fileDO.getUrl())) {
				model.addAttribute("picUrl", fileDO.getUrl());
			} else {
				model.addAttribute("picUrl", "/img/photo_s.jpg");
			}
		} else {
			model.addAttribute("picUrl", "/img/photo_s.jpg");
		}
		model.addAttribute("username", getUser().getUsername());
		return "index_v1";
	}

	@GetMapping("/login")
	String login() {
		return "login";
	}

	@Log("登录")
	@PostMapping("/login")
	@ResponseBody
	R ajaxLogin(String username, String password, HttpServletRequest request) {

		password = MD5Utils.encrypt(username, password);
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", getUserId());
			List<UserExtendDO> userExtendList = userExtendService.list(map);

			UserExtendDO bean = CollectionUtils.isNotEmpty(userExtendList) ? userExtendList.get(0) : null;

			if (bean == null) {
				return R.ok();
			}

			R r = checkLoginTime(request, bean);
			if (StringUtils.equals(String.valueOf(r.get("code")), "1")) {//失败
				return r;
			}

			// 检查密码有效期 需要增加最后一次修改密码时间
			JSONObject passwordJson = JSONObject.fromObject(bean.getPasswordRules());

			return R.ok();
		} catch (AuthenticationException e) {
			return R.error("用户或密码错误");
		}
	}

	private R checkLoginTime(HttpServletRequest request, UserExtendDO bean) {
		JSONObject loginJson = JSONObject.fromObject(bean.getLoginRules());
		String ip = IPUtils.getIpAddr(request);
		String device_white_values = loginJson.optString("device_white_values");
		if (StringUtils.equals("1", loginJson.optString("white_status"))
				&& StringUtils.isNotBlank(device_white_values)) {
			if (!StringUtils.contains(device_white_values, ip)) {
				return R.error("本机IP不在管理端访问IP白名单范围之内！");
			}
		}
		// 管理端登录时段
		Map<String, String> weekMap = new HashMap<String, String>();
		weekMap.put("1", "Sunday");
		weekMap.put("2", "Tuesday");
		weekMap.put("3", "Wednesday");
		weekMap.put("4", "Wednesday");
		weekMap.put("5", "Thursday");
		weekMap.put("6", "Friday");
		weekMap.put("7", "Saturday");

		String login_day = loginJson.optString("login_day");
		String login_start_time = loginJson.optString("login_start_time");
		String login_end_time = loginJson.optString("login_end_time");
		if (StringUtils.equals("1", loginJson.optString("login_time_status"))) {

			if (StringUtils.isNotBlank(login_day)) {
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, 2);
				String todayIndex = String.valueOf(cal.get(Calendar.DAY_OF_WEEK));

				if (!StringUtils.contains(login_day, weekMap.get(todayIndex))) {
					return R.error("今天不在管理端登录日内，请改天登录！");
				}
			}

			if (StringUtils.isNotBlank(login_start_time) && StringUtils.isNotBlank(login_end_time)) {
				String pattern = "HH:mm";
				SimpleDateFormat sdf = new SimpleDateFormat(pattern);// 格式化为年月
				try {
					Calendar currCal = Calendar.getInstance();
					currCal.setTime(sdf.parse(sdf.format(new Date())));

					Calendar min = Calendar.getInstance();
					Calendar max = Calendar.getInstance();

					min.setTime(sdf.parse(login_start_time));
					max.setTime(sdf.parse(login_end_time));

					if (currCal.after(max) && currCal.before(min) && !currCal.equals(min) && !currCal.equals(max)) {
						return R.error("今天不在管理端登录时段内，请择时登录！");
					}

				} catch (ParseException e) {
					e.printStackTrace();
				}

			}

		}
		return R.ok();
	}

	@GetMapping("/logout")
	String logout() {
		ShiroUtils.logout();
		return "redirect:/login";
	}

	@GetMapping("/main")
	String main() {
		return "main";
	}

}
