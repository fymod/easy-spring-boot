package com.fymod.shiro.service;

import com.fymod.shiro.domain.User;

public interface ILoginService {

	public User findByUsername(String username);
	
}
