package com.data.jpa.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "data_jpa_movie_tab")
@Data
public class Movie implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "movie_id")
	private Long movieId;
	@Column(name = "movie_name")
	private String movieName;
	@Column(name = "movie_release_date")
	private String releaseDate;
	@Column(name = "movie_rating")
	private Float rating;
	
}
