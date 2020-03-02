package com.capgmini.movieprojet.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.capgmini.movieprojet.models.Movie;

public interface MovieRepository extends PagingAndSortingRepository<Movie, Integer>{

}
