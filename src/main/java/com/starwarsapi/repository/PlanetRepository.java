package com.starwarsapi.repository;

import com.starwarsapi.entity.Planet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanetRepository extends MongoRepository<Planet, String> {

    List<Planet> findByNameIgnoreCaseContaining(String name);
}
