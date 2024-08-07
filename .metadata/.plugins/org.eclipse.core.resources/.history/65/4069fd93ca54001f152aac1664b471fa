package com.data.jpa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.data.jpa.entity.Movie;
import com.data.jpa.repo.MovieRepository;
import com.data.jpa.service.IMovieService;

@Service
public class MovieServiceImpl implements IMovieService {

	@Autowired
	private MovieRepository movieRepository;
	
	public String createMovie(Movie movie) {
		Movie createdMovie = movieRepository.save(movie);
		if(createdMovie.getMovieId() != null)
			return "Movie "+createdMovie.getMovieName()+" Saved Successfully With Id : "+ createdMovie.getMovieId();
		else
			throw new IllegalArgumentException("Something went wrong...");
	}

}
