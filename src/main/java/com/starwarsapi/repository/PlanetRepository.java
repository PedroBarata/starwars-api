package com.starwarsapi.repository;

import com.starwarsapi.entity.Planet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlanetRepository extends MongoRepository<Planet, String> {

    Optional<Planet> findByNameIgnoreCase(String name);
}
