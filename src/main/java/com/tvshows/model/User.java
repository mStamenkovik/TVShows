package com.tvshows.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name="users")
public class User extends BaseEntity{
	
	public static enum Role {
		ROLE_USERS, ROLE_ADMIN
	}
     
	@Column(unique = true)
	 private String username;
	
	@Column(name="user_password")
	 private String password;
	
    private String email;
    
    private String firstName;
    
    private String lastName;
    
    @ManyToMany( fetch = FetchType.EAGER)
    @JoinTable(name="users_shows", joinColumns = { @JoinColumn(name="users_id") }, 
          inverseJoinColumns = { @JoinColumn(name="shows_id") })
	private List<Show> shows;
    
    @ManyToMany( fetch = FetchType.EAGER)
    @JoinTable(name="watchlist", joinColumns = { @JoinColumn(name="user_id") }, 
          inverseJoinColumns = { @JoinColumn(name="show_id") })
    @JsonManagedReference
    private List<Show> watchlist;
    
    @Enumerated(EnumType.STRING)
	@Column(name = "user_role", length = 20, nullable = false)
	private Role role;
    
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	public List<Show> getShows() {
		return shows;
	}
	public void setShows(List<Show> shows) {
		this.shows = shows;
	}
	public List<Show> getWatchlist() {
		return watchlist;
	}
	public void setWatchlist(List<Show> watchlist) {
		this.watchlist = watchlist;
	}
    
    
	
}
