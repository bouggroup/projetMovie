package com.capgmini.movieprojet.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgmini.movieprojet.models.Movie;
import com.capgmini.movieprojet.models.projections.MovieInterface;
import com.capgmini.movieprojet.repositories.MovieRepository;

@CrossOrigin
@RestController
@RequestMapping("/movies")
public class MovieController {

	private final ProjectionFactory projectionFactory;
	
	
	@Autowired
	public MovieController(ProjectionFactory projectionFactory) {
		this.projectionFactory = projectionFactory;
	}

	
	@Autowired
	private MovieRepository movieRepository;
	
	
	@GetMapping(value = "/{id:[0-9]+}")
	public ResponseEntity<MovieInterface> findById(@PathVariable("id") int id){

		if(!this.movieRepository.existsById(id))
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		Movie movie = this.movieRepository.findById(id).get();

		return new ResponseEntity<MovieInterface>(projectionFactory.createProjection(MovieInterface.class,
				movie), HttpStatus.OK);
	}
	
	
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
