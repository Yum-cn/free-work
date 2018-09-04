package com.anhuay.common.controller;

import com.anhuay.system.domain.UserToken;
import com.common.id.IdWorker;

import org.springframework.stereotype.Controller;
import com.anhuay.common.utils.ShiroUtils;
import com.anhuay.system.domain.UserDO;

@Controller
public class BaseController {
	
	IdWorker idWorker = new IdWorker(3);
	
	public UserDO getUser() {
		return ShiroUtils.getUser();
	}

	public Long getUserId() {
		return getUser().getUserId();
	}

	public String getUsername() {
		return getUser().getUsername();
	}
	
	public long getId() {
		return idWorker.nextId();
	}
}