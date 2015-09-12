package com.tvshows.web.resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tvshows.model.Genre;
import com.tvshows.model.Show;
import com.tvshows.model.User;
import com.tvshows.security.TokenUtils;
import com.tvshows.service.CrudShowService;
import com.tvshows.service.CrudUserService;
import com.tvshows.web.CrudResource;

import ch.qos.logback.core.net.SyslogOutputStream;


@RestController
@RequestMapping("/data/rest/shows")
public class ShowResource extends CrudResource<Show, CrudShowService> {
	
	@Autowired
	private CrudShowService service;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	/*@Autowired
	private UserRepository userRepo;*/
	
	@Autowired
	private CrudUserService userService;

	
	@Override
	public CrudShowService getService() {
		// TODO Auto-generated method stub
		return service;
	}
	
	@RequestMapping(value="/watchlist/all", method = RequestMethod.GET, produces = "application/json")
	public List<Show> getWatchlistShows(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
				
		User user = new User();
		System.out.println("SE INSTANCIRA USER");
		//Getting user authentication token
		String authToken = checkToken(request, response);
		
		//Check validation of user token
		try{
			user = validateToken(authToken);
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		//If token isnt valid send Unauthorized error
		if (user == null)
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		else{	
			
			List<User> users = userService.findAll();
			
			ArrayList<Show> allShowsWatchlist = new ArrayList<Show>();
			
			for (User u : users) {
				
				List<Show> shows = u.getWatchlist();
				for (Show show : shows) {
					allShowsWatchlist.add(show);
				}
				
			}
			
			return allShowsWatchlist;

		}
		return null;
		
		
	}
	
	@RequestMapping(value="/recomended/all", method = RequestMethod.GET, produces = "application/json")
	public List<Show> getRecomendedShows(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
				
		User user = new User();
		System.out.println("SE INSTANCIRA USER");
		//Getting user authentication token
		String authToken = checkToken(request, response);
		
		//Check validation of user token
		try{
			user = validateToken(authToken);
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		//If token isnt valid send Unauthorized error
		if (user == null)
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		else{	
			
			List<User> users = userService.findAll();
			
			ArrayList<Show> allShowsWatchlist = new ArrayList<Show>();
			
			for (User u : users) {
				
				List<Show> shows = u.getShows();
				for (Show show : shows) {
					allShowsWatchlist.add(show);
				}
				
			}
			
			return allShowsWatchlist;

		}
		return null;
		
		
	}
	
	
	@RequestMapping(value = "/number/recommendations/{tid}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public int getNumOfRec(@PathVariable String tid, HttpServletRequest request, HttpServletResponse response) throws IOException{
				
		List<Show> show = service.findByTmdbId(tid);
		
		
		if (show!=null){
			
			if (show.size() > 0){
			
			List<User> users = show.get(0).getUsers();
			if (users != null){
				
				return users.size();
			}
			else
				return 0;
			}
			else
				return 0;
			
		}
		else
			return 0;
	}
	
	
	@RequestMapping(value = "/getRecomended", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Show> getReclist(HttpServletRequest request, HttpServletResponse response) throws IOException{
				
		//Creating User object for requesting user
		User user = new User();
		System.out.println("SE INSTANCIRA USER");
		//Getting user authentication token
		String authToken = checkToken(request, response);
		
		//Check validation of user token
		try{
			user = validateToken(authToken);
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		//If token isnt valid send Unauthorized error
		if (user == null)
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		else{	
			List<Show> shows = user.getShows();
			return shows;
		}
		return null;		
	}
	
	@RequestMapping(value="/genre/{id}", method = RequestMethod.GET, produces = "application/json")
	public String getGenre(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		try {
			
			return Genre.genres.get(id);
			
		} catch (Exception e) {
			// TODO: handle exception
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
			return "Error";
	}
	
	
	@RequestMapping(value="/addToWatchlist", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public void addToWatchlist(@RequestParam("tmdb_id") String tmdb_id,
			@RequestParam("title") String title,
			@RequestParam("overview") String overview, 
			@RequestParam("poster_path") String poster_path,
			@RequestParam("genres") String genres,
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		//Creating User object for requesting user
				User user = new User();
				
				//Getting user authentication token
				String authToken = checkToken(request, response);
				
				//Check validation of user token
				try{
					user = validateToken(authToken);
				
				}
				catch(Exception e){
					System.out.println(e.getMessage());
				}
				
				//If token isnt valid send Unauthorized error
				if (user == null)
					response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
				else{
					
					//Get show object from show tmdb id
					
					List<Show> showWithId = service.findByTmdbId(tmdb_id);
					
					//If show is in database get it, else create object and put it in database
					Show show = null;
					if (showWithId.size() > 0){
						System.out.println(showWithId.get(0).getTitle());
						show = showWithId.get(0);
					}
					else {
					
				    show = createShow(tmdb_id, title, overview, poster_path, genres);
					show.setUsers(new ArrayList<User>());
					System.out.println("///////Creating show: "  + title);
					service.save(show);
					}
					
					
					List<Show> watchList = user.getWatchlist();
					System.out.println("watchList: ");
					for (Show show1 : watchList) {
						System.out.println(show1.getTitle());
					}
					
					if(!watchList.contains(show)){
						watchList.add(show);
						user.setWatchlist(watchList);
	                    
						userService.save(user);
					}
				
				}
				
	}
	
	@RequestMapping(value = "/getWatchlist", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Show> getWatchlist(HttpServletRequest request, HttpServletResponse response) throws IOException{
				
		//Creating User object for requesting user
		User user = new User();
		System.out.println("SE INSTANCIRA USER");
		//Getting user authentication token
		String authToken = checkToken(request, response);
		
		//Check validation of user token
		try{
			user = validateToken(authToken);
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		//If token isnt valid send Unauthorized error
		if (user == null)
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		else{	
			List<Show> shows = user.getWatchlist();
			return shows;
		}
		return null;		
	}
	
	@RequestMapping(value = "/removeFromWatchlist", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public void removeFromWatchlist(
			@RequestParam("tmdb_id") String tmdb_id,
			HttpServletRequest request, HttpServletResponse response) throws IOException{
				
		//Creating User object for requesting user
		User user = new User();
		System.out.println("SE INSTANCIRA USER");
		//Getting user authentication token
		String authToken = checkToken(request, response);
		
		//Check validation of user token
		try{
			user = validateToken(authToken);
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		//If token isnt valid send Unauthorized error
		if (user == null)
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		else{
			
			//Get show ovject from show tmdb id
			System.out.println("ZEMANJE SHOW");
			List<Show> showWithId = service.findByTmdbId(tmdb_id);
			
			//If show is in database get it, else create object and put it in database
			Show show = null;
			if (showWithId.size() > 0){
				show = showWithId.get(0);
			}
			else {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			}
			
			//Check if user has already added show to watchlist
			System.out.println("/////////////////////////////////// 001 " + show.getTitle());
			List<Show> shows = user.getWatchlist();
			if(shows.contains(show)){
				shows.remove(show);
				user.setWatchlist(shows);
				userService.save(user);
			}
		}		
	}
	
	
	@RequestMapping(value = "/recomend", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public void recomendShow(
			@RequestParam("tmdb_id") String tmdb_id,
			@RequestParam("title") String title,
			@RequestParam("overview") String overview, 
			@RequestParam("poster_path") String poster_path,
			@RequestParam("genres") String genres,
			HttpServletRequest request, HttpServletResponse response) throws IOException{
				
		//Creating User object for requesting user
		User user = new User();
		
		//Getting user authentication token
		String authToken = checkToken(request, response);
				
		//Check validation of user token
		try{
			user = validateToken(authToken);
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		//If token isnt valid send Unauthorized error
		if (user == null)
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		else{
			
			//Get show ovject from show tmdb id			
			List<Show> showWithId = service.findByTmdbId(tmdb_id);
			
			//If show is in database get it, else create object and put it in database
			Show show = null;
			if (showWithId.size() > 0){
				System.out.println(showWithId.get(0).getTitle());
				show = showWithId.get(0);
			}
			else {
			
			show = createShow(tmdb_id, title, overview, poster_path, genres);			
			service.save(show);
			
			}
			
			//Check if user has already recommended show
			boolean check = service.checkIfShowIsRecomendedByUser(show, user);
			System.out.println("GLASAL: " + check);
			
			if (!check)
			{
				List<Show> shows = user.getShows();
				if(!shows.contains(show)){
					System.out.println("\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\ " + show.getTitle());
					shows.add(show);
					user.setShows(shows);
			    	userService.save(user);			 
				}
			}
	   }	
	}
	
	@RequestMapping(value = "/watchlist/check", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public boolean checkWatchlist(@RequestParam("tmdb_id") String tmdb_id, HttpServletRequest request, HttpServletResponse response)
					throws IOException {
		
		//Creating User object for requesting user
		User user = new User();
		
		String authToken = checkToken(request, response);
		
		//Check validation of user token
		try{
		   user = validateToken(authToken);
		
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		//If token isnt valid send Unauthorized error
		if (user == null)
			return false;
		else{
			
			//Get show ovject from show tmdb id
			
			List<Show> showWithId = service.findByTmdbId(tmdb_id);
			
			//If show is in database get it, else create object and put it in database
			Show show = null;
			if (showWithId.size() > 0){
				System.out.println(showWithId.get(0).getTitle());
				show = showWithId.get(0);
			}
			else {
			
				return false;
			}
			
			//Check if user has already recommended show
			List<Show> shows = user.getWatchlist();
			if(shows.contains(show)){
				return true;
			}
			else return false;
		}	
	}
	
	@RequestMapping(value = "/recomend/check", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public boolean checkRecomendation(@RequestParam("tmdb_id") String tmdb_id, HttpServletRequest request, HttpServletResponse response)
					throws IOException {
		
		//Creating User object for requesting user
		User user = new User();
		
		String authToken = checkToken(request, response);
		
		//Check validation of user token
		try{
		   user = validateToken(authToken);
		
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		//If token isnt valid send Unauthorized error
		if (user == null)
			return false;
		else{
			
			//Get show ovject from show tmdb id
			
			List<Show> showWithId = service.findByTmdbId(tmdb_id);
			
			//If show is in database get it, else create object and put it in database
			Show show = null;
			if (showWithId.size() > 0){
				System.out.println(showWithId.get(0).getTitle());
				show = showWithId.get(0);
			}
			else {
			
				return false;
			}
			
			//Check if user has already recommended show
			boolean check = service.checkIfShowIsRecomendedByUser(show, user);
			return check;
		}	
	}
	
	@RequestMapping(value = "/unrecomend", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public void unRecomendShow(
			@RequestParam("tmdb_id") String tmdb_id,
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		
		//Creating User object for requesting user
		User user = new User();
		System.out.println("SE INSTANCIRA USER");
		//Getting user authentication token
		String authToken = checkToken(request, response);
		
		//Check validation of user token
		try{
			user = validateToken(authToken);
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		//If token isnt valid send Unauthorized error
		if (user == null)
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		else{
			
			//Get show ovject from show tmdb id
			System.out.println("ZEMANJE SHOW");
			List<Show> showWithId = service.findByTmdbId(tmdb_id);
			
			//If show is in database get it, else create object and put it in database
			Show show = null;
			if (showWithId.size() > 0){
				show = showWithId.get(0);
			}
			else {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			}
			
			//Check if user has already recommended show
			boolean check = service.checkIfShowIsRecomendedByUser(show, user);
			
			if (check){
				List<Show> shows = user.getShows();
				shows.remove(show);
				user.setShows(shows);
				userService.save(user);
			}
		}
		
		
		
	}
	
private String checkToken(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		String authToken = request.getHeader("X-Auth-Token");
		if (authToken == null) {
			authToken = request.getParameter("token");
		}
		
		//If Token is null return Unauthorized
		if (authToken == null){
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		    return null;
		}
		
		//Remove "s from token string
		if (authToken.charAt(0) == '"')
		authToken = authToken.substring(1, authToken.length()-1);
		return authToken;
	}
	
	private User validateToken(String authToken){
		User user = new User();
		boolean tokenValidation = false;
		
		try{
			String username = TokenUtils.getUserNameFromToken(authToken);
			if (username.charAt(0) == '"')
				username = username.substring(1);
			UserDetails userDetails= this.userDetailsService.loadUserByUsername(username);
			user = userService.findByUsername(username);
			tokenValidation = TokenUtils.validateToken(authToken, userDetails);
			
			}
			catch(Exception e){
				System.out.println(e.getMessage());
			}
		
		if(!tokenValidation){
			return null;
		}
		else 
			return user;
		
	}
	
	public Show createShow(String tmdb_id, String title, String overview, String poster_path, String genres){
		
		Show show = new Show();
		show.setGenres(genres);
		show.setOverview(overview);
		show.setPosterPath(poster_path);
		show.setTitle(title);
		show.setTmdbId(tmdb_id);
		show.setUsers(new ArrayList<User>());
		
		return show;
	}
	
}
