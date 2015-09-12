package com.tvshows.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Genre {
	
    public static final Map<Integer, String> genres;
    static {
        Map<Integer, String> aMap = new HashMap<Integer, String>();
        aMap.put(28, "Action");
        aMap.put(12, "Adventure");
        aMap.put(16, "Animation");
        aMap.put(35, "Comedy");
        aMap.put(80, "Crime");
        aMap.put(99, "Documentary");
        aMap.put(18, "Drama");
        aMap.put(10751, "Family");
        aMap.put(14, "Fantasy");
        aMap.put(10769, "Foreign");
        aMap.put(36, "History");
        aMap.put(27, "Horror");
        aMap.put(10402, "Music");
        aMap.put(9648, "Mystery");
        aMap.put(10749, "Romance");
        aMap.put(878, "Science Fiction");
        aMap.put(10770, "TV Movie");
        aMap.put(53, "Thriller");
        aMap.put(10752, "War");
        aMap.put(37, "Western");
        aMap.put(10765, "Sci-Fi & Fantasy");
        genres = Collections.unmodifiableMap(aMap);
    }
	
	
}
