package com.tvshows.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tvshows.model.Show;
import com.tvshows.repository.ShowRepository;
import com.tvshows.service.ShowService;

@Service
public class ShowServiceImpl implements ShowService {
	
	@Autowired
	private ShowRepository repository;
	
	public Show saveOrUpdate(Show entity) {
		// TODO Auto-generated method stub
		return repository.save(entity);
	}

	public List<Show> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	public List<Show> findByTitle(String title) {
		// TODO Auto-generated method stub
		return repository.findByTitleLikeOrderByIdDesc(title);
	}

	public Show findById(Long id) {
		// TODO Auto-generated method stub
		return repository.findOne(id);
	}

	public void delete(Long id) {
		// TODO Auto-generated method stub
		repository.delete(id);
	}

}
