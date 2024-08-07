package com.data.jpa.repo;

import org.springframework.data.repository.CrudRepository;

import com.data.jpa.entity.Movie;

public interface MovieRepository extends CrudRepository<Movie, Long> {

}
