package com.tvshows.service;

import java.util.List;

import com.tvshows.model.Show;


public interface ShowService {
	
	public Show saveOrUpdate(Show entity);

	public List<Show> findAll();

	public List<Show> findByTitle(String title);

	public Show findById(Long id);

	public void delete(Long id);
}
