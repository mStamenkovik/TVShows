package com.tvshows.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tvshows.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);

}
