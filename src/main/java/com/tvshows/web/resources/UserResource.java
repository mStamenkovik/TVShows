package com.tvshows.web.resources;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tvshows.model.User;
import com.tvshows.security.TokenTransfer;
import com.tvshows.security.TokenUtils;
import com.tvshows.service.CrudUserService;


@RestController
@RequestMapping("/data/rest")
public class UserResource {

	private static final int TOKEN_DURATION = 30 * 24 * 60 * 60; // 30 days

	@Autowired
	private UserDetailsService userService;

	@Autowired
	private CrudUserService crudUserService;
	
	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager authManager;

	/**
	 * Authenticates a user and creates an authentication token.
	 * 
	 * @param username
	 *            The name of the user.
	 * @param password
	 *            The password of the user.
	 * @return A transfer containing the authentication token.
	 * @throws IOException 
	 */
	@RequestMapping(value = "/user/authenticate", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public TokenTransfer authenticate(
			@RequestParam("username") String username,
			@RequestParam("password") String password,
			@RequestParam("rememberMe") boolean rememberMe,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		
		boolean error = false;
		
		try {
		
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				username, password);
		
		
		Authentication authentication = this.authManager
				.authenticate(authenticationToken);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		}
		catch(BadCredentialsException e){
			error = true;
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		
		}
		catch (Exception e) {
			error = true;
			System.out.println("ERROR INTERNAL: " + e.getMessage().toString());
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		
		

		/*
		 * Reload user as password of authentication principal will be null
		 * after authorization and password is needed for token generation
		 */
		
		
		if (!error){
		
		
		UserDetails userDetails = this.userService.loadUserByUsername(username);
		Cookie cookie = new Cookie("token", TokenUtils.createToken(userDetails));
		if (rememberMe) {
			cookie.setMaxAge(TOKEN_DURATION);
		}
		cookie.setPath(request.getContextPath());
		response.addCookie(cookie);
		return new TokenTransfer(TokenUtils.createToken(userDetails));
		}
		
		return null;
	}

	
	@RequestMapping(value = "/user/test", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public void test(
			@RequestParam("token") String token,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String username = TokenUtils.getUserNameFromToken(token);
		
		
		UserDetails userDetails = this.userService.loadUserByUsername(username);
		
		if (TokenUtils.validateToken(token, userDetails)){
			response.sendError(HttpServletResponse.SC_OK);
		}
		else{
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		}
		
		
		
	}
	
	@RequestMapping(value = "/user/username", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public boolean checkUsername(
			@RequestParam("username") String username){
		
		User user = crudUserService.findByUsername(username);
		if (user != null){
			
			return true;
		}
		else{
			return false;
		}
	}
	
	
	
	@RequestMapping(value = "/user/register", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public void register(
			@RequestParam("firstname") String firstName,
			@RequestParam("lastname") String lastName,
			@RequestParam("username") String username,
			@RequestParam("password") String password,
			@RequestParam("email") String email,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		
		User user = crudUserService.findByUsername(username);
		
		if (user != null){
			
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
		else {
			
			user = new User();
			
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setEmail(email);
			user.setPassword(password);
			user.setUsername(username);
			user.setRole(User.Role.ROLE_USERS);
			
			try {
				
				crudUserService.save(user);
				
			}
			catch(Exception e){
				response.sendError(HttpServletResponse.SC_CONFLICT);
			}
			
		}
		
		
	}
	
	
}

