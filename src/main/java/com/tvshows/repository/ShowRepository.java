package com.tvshows.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tvshows.model.Show;


public interface ShowRepository extends JpaRepository<Show, Long> {
	
	
	List<Show> findByTitleLikeOrderByIdDesc(String title);
	
	List<Show> findByTmdbId(String tmdbId);
	
}
