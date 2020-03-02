package com.capgmini.movieprojet.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgmini.movieprojet.models.Movie;
import com.capgmini.movieprojet.repositories.MovieRepository;

@CrossOrigin
@RestController
@RequestMapping("/movies")
public class MovieController {

	@Autowired
	private MovieRepository movieRepository;
	
	
	@GetMapping
	public Page<Movie> listMovies (@PageableDefault(page = 0, size = 8) Pageable page){
		return this.movieRepository.findAll(page);
	}
	
	
	@GetMapping("/AZ")
	public Page<Movie> listMoviesAZ (@PageableDefault(page = 0, size = 8) Pageable page){
		return this.movieRepository.findByOrderByTitleAsc(page);
		}
	
	@GetMapping("/duration")
	public Page<Movie> listMoviesDuration (@PageableDefault(page = 0, size = 8) Pageable page){
		return this.movieRepository.findMovieDuration(page);
		}
	
	@GetMapping("/duree")
	public Page<Movie> listMoviesDuree (@PageableDefault(page = 0, size = 8) Pageable page,@RequestParam("duree")int duree){
		return this.movieRepository.findByDurationGreaterThanEqual(page, duree);
		}
	
	
	
}
