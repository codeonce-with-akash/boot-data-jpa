package com.data.jpa.service;

import java.util.List;

import com.data.jpa.entity.Movie;

public interface IMovieService {
	//1. save operation
	public String createMovie(Movie movie);
	//2. count operation
	public Long fetchMovieCount();
	//3. exist-by-id operation
	public String checkMovieById(Long movieId);
	//4. findAll operation
	public Iterable<Movie> fetchAllMovies();
	//5. findAllByIds operation
	public Iterable<Movie> fetchAllMoviesByIds(List<Long> ids);
	//6. findById operation
	public Movie fetchMovieById(Long movieId);
	//7. partial object update operation
	public String updateMovieReleaseDateAndRating(Long movieId, String newReleaseDate, Float newRating);
	//8. full object update operation
	public String updateMovieObject(Movie movie);
	//9. deleteById operation
	public String deleteMovieById(Long movieId);
	//10. deleteAllByIds operation
	public String deleteAllMoviesById(List<Long> movieIds);
	//11. delete operation
	public String deleteByMovie(Long movieId);
	//12. deleteAll(-) operation
	public String deleteAllByMovies(List<Long> movieIds);
	//13. deleteAll() operation
	public String deleteAllMovies();
}
