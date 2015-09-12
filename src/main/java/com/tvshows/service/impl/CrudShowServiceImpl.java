package com.tvshows.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tvshows.model.Show;
import com.tvshows.model.User;
import com.tvshows.repository.ShowRepository;
import com.tvshows.repository.UserRepository;
import com.tvshows.service.CrudShowService;

@Service
public class CrudShowServiceImpl extends SimpleBaseEntityCrudServiceImpl<Show, ShowRepository> implements CrudShowService {
	
	@Autowired
	private ShowRepository repository;
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	protected ShowRepository getRepository() {
		// TODO Auto-generated method stub
		return repository;
	}

	public List<Show> findByTmdbId(String tmdbId) {
		
		return getRepository().findByTmdbId(tmdbId);
	}


	public boolean checkIfShowIsRecomendedByUser(Show show, User user) {
		
		if (show.getUsers() == null)
			return false;
		else {
			
			return show.getUsers().contains(user);
		}
		
	}


}
