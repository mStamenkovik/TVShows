package com.tvshows.web;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tvshows.model.Show;
import com.tvshows.service.ShowService;

@Controller
@RequestMapping("/shows")
public class ShowController {


	@Autowired
	private ShowService service;

	public ShowService getService() {
		return service;
	}


	@RequestMapping(value = "/test", method = RequestMethod.GET)
	@ResponseBody
	public String test(){
		return "Hi";
	}

	@RequestMapping(value = "add", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Show create(@RequestParam String title, @RequestParam String tmdb_id,
			@RequestParam String overview, @RequestParam String poster_path, @RequestParam String genres) {
		Show show = new Show();
		show.setTitle(title);
		show.setTmdbId(tmdb_id);
		show.setOverview(overview);
		show.setPosterPath(poster_path);
		show.setGenres(genres);

		return getService().saveOrUpdate(show);
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Show> getAll() {
		return getService().findAll();
	}


	@RequestMapping(value = "/by_title", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Show> getByTitle(@RequestParam String title) {
		return getService().findByTitle(title);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Show get(@PathVariable Long id, HttpServletRequest request,
			HttpServletResponse response) {
		Show entity = getService().findById(id);
		if (entity == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		return entity;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public void delete(@PathVariable Long id) {
		getService().delete(id);
	}
}

