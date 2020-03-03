package com.capgmini.movieprojet.models.projections;

import org.springframework.data.rest.core.config.Projection;

import com.capgmini.movieprojet.models.Movie;

@Projection(name = "MovieProjection", types = Movie.class)
public interface MovieInterface {
	int getId();
	String getTitle();
	int getDuration();
	String getGenres();
}
