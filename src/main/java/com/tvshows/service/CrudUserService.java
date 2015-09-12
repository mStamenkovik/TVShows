package com.tvshows.service;

import com.tvshows.model.User;

public interface CrudUserService extends BaseEntityCrudService<User>{
 
	 public User findByUsername(String username);
}
