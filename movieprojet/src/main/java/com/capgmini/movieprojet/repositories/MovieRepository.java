package com.capgmini.movieprojet.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.capgmini.movieprojet.models.Movie;

public interface MovieRepository extends PagingAndSortingRepository<Movie, Integer>{
	
	public Page<Movie> findByOrderByTitleAsc(Pageable page);
	
	@Query(value = "select m from Movie as m where duration >= 120")
	public Page<Movie> findMovieDuration(Pageable page);
	
	public Page<Movie> findByDurationGreaterThanEqual(Pageable page,int duree);

}
