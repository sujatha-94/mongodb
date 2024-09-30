package com.example.demo.repository;

import com.example.demo.entity.Movie;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MovieRepository extends MongoRepository<Movie, String> {
    List<Movie> findByGenre(String genre);
    List<Movie> findByPopularTrue();
    
}