package com.starwarsapi.repository;

import com.starwarsapi.entity.Planet;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PlanetRepository extends MongoRepository<Planet, String> {

    List<Planet> findByNameIgnoreCaseContaining(String name);
}
