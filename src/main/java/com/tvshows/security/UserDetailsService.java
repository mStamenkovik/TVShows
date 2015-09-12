package com.tvshows.security;

import com.tvshows.model.User;
import com.tvshows.repository.ShowRepository;
import com.tvshows.repository.UserRepository;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

	@Autowired
	private UserRepository repository;
	
	protected UserRepository getRepository() {
		// TODO Auto-generated method stub
		return repository;
	}

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repository.findByUsername(username);
		return new UserDetails(user);
	}

}
