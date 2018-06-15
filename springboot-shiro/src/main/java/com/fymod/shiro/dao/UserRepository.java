package com.fymod.shiro.dao;

import com.fymod.shiro.domain.User;

public interface UserRepository extends BaseRepository<User, Long> {
	
	public User findByUsername(String username);
}
