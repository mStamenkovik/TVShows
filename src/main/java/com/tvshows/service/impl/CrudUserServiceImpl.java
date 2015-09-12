package com.tvshows.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tvshows.model.User;
import com.tvshows.repository.UserRepository;
import com.tvshows.service.CrudUserService;

@Service
public class CrudUserServiceImpl extends SimpleBaseEntityCrudServiceImpl<User, UserRepository> implements CrudUserService{

	@Autowired
	private UserRepository userRepository;	
	
	@Override
	protected UserRepository getRepository() {
		// TODO Auto-generated method stub
		return userRepository;
	}

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepository.findByUsername(username);
	}
	

}
