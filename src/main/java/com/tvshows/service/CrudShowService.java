package com.tvshows.service;

import java.util.List;

import com.tvshows.model.Show;
import com.tvshows.model.User;


public interface CrudShowService extends BaseEntityCrudService<Show> {
	
	public List<Show> findByTmdbId(String tmdbId);
	
	public boolean checkIfShowIsRecomendedByUser(Show show, User user);

	
}
