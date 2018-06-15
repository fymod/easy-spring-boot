package com.fymod.shiro.config;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.fymod.shiro.domain.Permission;
import com.fymod.shiro.domain.Role;
import com.fymod.shiro.domain.User;
import com.fymod.shiro.service.ILoginService;

//实现AuthorizingRealm接口用户认证
public class MyShiroRealm extends AuthorizingRealm {

	@Autowired
	private ILoginService loginService;

	// 角色权限和对应权限添加
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		// 获取登录用户名
		String username = (String) principalCollection.getPrimaryPrincipal();
		// 查询用户名称
		User user = loginService.findByUsername(username);
		// 添加角色和权限
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		for (Role role : user.getRoles()) {
			simpleAuthorizationInfo.addRole(role.getRoleName()); // 添加角色
			for (Permission permission : role.getPermissions()) {
				simpleAuthorizationInfo.addStringPermission(permission.getPermission()); // 添加权限
			}
		}
		return simpleAuthorizationInfo;
	}

	// 用户认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
			throws AuthenticationException {
		// 加这一步的目的是在Post请求的时候会先进认证，然后再到请求
		if (authenticationToken.getPrincipal() == null) {
			return null;
		}
		// 获取用户信息
		String username = authenticationToken.getPrincipal().toString();
		User user = loginService.findByUsername(username);
		if (user == null) {
			// 这里返回后会报出对应异常
			return null;
		} else {
			// 这里验证authenticationToken和simpleAuthenticationInfo的信息
			SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username,
					user.getPassword().toString(), getName());
			return simpleAuthenticationInfo;
		}
	}
}
