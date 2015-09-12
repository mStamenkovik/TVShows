package com.tvshows.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "shows")
public class Show extends BaseEntity {
	
	@NotEmpty	
	private String tmdbId;
	
	private String title;
	
	private String overview;
	 
	private String posterPath;
	
	private String genres;
	
	@ManyToMany(mappedBy = "shows", fetch = FetchType.EAGER)
	private List<User> users;
	
	@ManyToMany(mappedBy = "watchlist", fetch = FetchType.EAGER)
	@JsonBackReference
	private List<User> user;

	public String getTmdbId() {
		return tmdbId;
	}

	public List<User> getUser() {
		return user;
	}

	public void setUser(List<User> user) {
		this.user = user;
	}

	public void setTmdbId(String tmdbId) {
		this.tmdbId = tmdbId;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public String getPosterPath() {
		return posterPath;
	}

	public void setPosterPath(String posterPath) {
		this.posterPath = posterPath;
	}

	public String getGenres() {
		return genres;
	}

	public void setGenres(String genres) {
		this.genres = genres;
	}
	
	@JsonIgnore
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	
	
	
	
}
