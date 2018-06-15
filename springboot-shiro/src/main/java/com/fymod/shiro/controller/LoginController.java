package com.fymod.shiro.controller;

import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

	/**
	 * 登录。只支持Post方法。参数为username和password
	 */
	@PostMapping("/login")
	public String login(@RequestBody Map<String, Object> map) {
		Subject subject = SecurityUtils.getSubject(); // 添加用户认证信息
		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(map.get("username").toString(),
				map.get("password").toString());
		subject.login(usernamePasswordToken); // 进行验证，这里可以捕获异常，然后返回对应信息
		return "login";
	}

	@RequestMapping(value = "/index")
	public String index() {
		return "index";
	}
	
	@GetMapping(value = "/login")
	public String login() {
		return "login界面";
	}
	@GetMapping(value = "/logout")
	public String logout() {
		return "logout";
	}
	@GetMapping(value = "/error")
	public String error() {
		return "error";
	}

	// 注解的使用
	@RequiresRoles("admin")
	@RequiresPermissions("permission1")
	@RequestMapping(value = "/test")
	public String test() {
		return "success!";
	}
	
}
