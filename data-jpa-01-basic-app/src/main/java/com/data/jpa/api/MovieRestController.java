package com.data.jpa.api;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.data.jpa.entity.Movie;
import com.data.jpa.exceptions.MovieNotFoundException;
import com.data.jpa.service.IMovieService;

@RestController
@RequestMapping("/api/data/jpa/movie")
public class MovieRestController {
	
	@Autowired
	private IMovieService movieService;
	
	@PostMapping("/create")
	public ResponseEntity<String> registerMovie(@RequestBody Movie movie){
		ResponseEntity<String> resp = null;
		try {
			String response = movieService.createMovie(movie);
			resp = new ResponseEntity<String>(response, HttpStatus.CREATED);
		} catch (Exception e) {
			resp = new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return resp;
	}
	
	@GetMapping("/fetch/count")
	public ResponseEntity<String> fetchMoviesCount(){
		ResponseEntity<String> resp = null;
		try {
			Long movieCount = movieService.fetchMovieCount();
			resp = new ResponseEntity<String>("Total '"+movieCount+"' movies are available!", HttpStatus.OK);
		} catch (Exception e) {
			resp = new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		return resp;
	}
	
	@GetMapping("/check/{movieId}")
	public ResponseEntity<String> isMoviePresentById(@PathVariable(name = "movieId") Long movieId){
		ResponseEntity<String> resp = null;
		try {
			resp = new ResponseEntity<String>(movieService.checkMovieById(movieId), HttpStatus.OK);
		} catch (MovieNotFoundException mnfe) {
			resp = new ResponseEntity<String>(mnfe.getMessage(), HttpStatus.NOT_FOUND);
		}
		return resp;
	}
	
	@GetMapping("/fetch/all")
	public ResponseEntity<?> fetchAllMovies(){
		ResponseEntity<?> resp = null;
		try {
			Iterable<Movie> allMovies = movieService.fetchAllMovies();
			System.out.println("--------printing list of movies using enhanced for loop---------");
			for(Movie movie : allMovies) {
				System.out.println(movie);
			}
			System.out.println("--------printing list of movies using forEach() method---------");
			allMovies.forEach(movie -> System.out.println(movie));
			System.out.println("--------printing list of movies using forEach() + static method reference---------");
			Arrays.asList(allMovies).stream().forEach(System.out::println);
			resp = new ResponseEntity<Iterable<Movie>>(allMovies, HttpStatus.OK);
		} catch (Exception e) {
			resp = new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		return resp;
	}
	
	@PostMapping("/fetch/ids")
	public ResponseEntity<?> fetchAllMoviesByIds(@RequestBody List<Long> ids){
		ResponseEntity<?> resp = null;
		try {
			resp = new ResponseEntity<Iterable<Movie>>(movieService.fetchAllMoviesByIds(ids), HttpStatus.OK);
		} catch (Exception e) {
			resp = new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		return resp;
	} 
	
	@GetMapping("/fetch/id/{movieId}")
	public ResponseEntity<?> fetchMovieById(@PathVariable(name = "movieId") Long movieId){
		ResponseEntity<?> resp = null;
		try {
			resp = new ResponseEntity<Movie>(movieService.fetchMovieById(movieId), HttpStatus.OK);
		} catch (MovieNotFoundException mnfe) {
			resp = new ResponseEntity<String>(mnfe.getMessage(), HttpStatus.NOT_FOUND);
		}
		return resp;
	}
	
	@PatchMapping("/edit/{movieId}")
	public ResponseEntity<String> updateReleaseDateAndRating(@PathVariable(name = "movieId") Long movieId, 
			@RequestParam(required = false) String newReleaseDate,
			@RequestParam(required = false) Float newRating){
		
		ResponseEntity<String> resp = null;
		try {
			resp = new ResponseEntity<>(movieService.updateMovieReleaseDateAndRating(movieId, newReleaseDate, newRating), HttpStatus.OK);
		} catch (MovieNotFoundException mnfe) {
			resp = new ResponseEntity<>(mnfe.getMessage(),HttpStatus.NOT_MODIFIED);
		}
		return resp;
	}
	
	@PutMapping("/edit")
	public ResponseEntity<String> updateReleaseDateAndRating(@RequestBody Movie movie){
		ResponseEntity<String> resp = null;
		try {
			resp = new ResponseEntity<>(movieService.updateMovieObject(movie), HttpStatus.OK);
		} catch (MovieNotFoundException mnfe) {
			resp = new ResponseEntity<>(mnfe.getMessage(),HttpStatus.NOT_MODIFIED);
		}
		return resp;
	}
	
	@DeleteMapping("/delete/{movieId}")
	public ResponseEntity<String> deleteMovieById(@PathVariable(name = "movieId") Long movieId){
		ResponseEntity<String> resp = null;
		try {
			resp = new ResponseEntity<>(movieService.deleteMovieById(movieId), HttpStatus.OK);
		} catch (MovieNotFoundException mnfe) {
			resp = new ResponseEntity<>(mnfe.getMessage(), HttpStatus.NOT_FOUND);
		}
		return resp;
	}
	
	@PostMapping("/delete/all/ids")
	public ResponseEntity<String> deleteAllMoviesByIds(@RequestBody List<Long> movieIds){
		ResponseEntity<String> resp = null;
		try {
			resp = new ResponseEntity<>(movieService.deleteAllMoviesById(movieIds), HttpStatus.OK);
		} catch (Exception e) {
			resp = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		return resp;
	}
	
	@DeleteMapping("/delete/entity/{movieId}")
	public ResponseEntity<String> deleteMovieByEntity(@PathVariable(name = "movieId") Long movieId){
		ResponseEntity<String> resp = null;
		try {
			resp = new ResponseEntity<>(movieService.deleteByMovie(movieId), HttpStatus.OK);
		} catch (MovieNotFoundException mnfe) {
			resp = new ResponseEntity<>(mnfe.getMessage(), HttpStatus.NOT_FOUND);
		}
		return resp;
	}
	
	@PostMapping("/delete/all/entities")
	public ResponseEntity<String> deleteAllMoviesByEntities(@RequestBody List<Long> movieIds){
		ResponseEntity<String> resp = null;
		try {
			resp = new ResponseEntity<>(movieService.deleteAllByMovies(movieIds), HttpStatus.OK);
		} catch (Exception e) {
			resp = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		return resp;
	}
	
	@DeleteMapping("/delete/all")
	public ResponseEntity<String> deleteAllMovies(){
		ResponseEntity<String> resp = null;
		try {
			resp = new ResponseEntity<>(movieService.deleteAllMovies(), HttpStatus.OK);
		} catch (Exception e) {
			resp = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		return resp;
	}
}
