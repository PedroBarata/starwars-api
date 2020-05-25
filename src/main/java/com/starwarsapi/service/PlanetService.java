package com.starwarsapi.service;

import com.starwarsapi.entity.Planet;
import com.starwarsapi.response.Response;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface PlanetService {

    Optional<Planet> findById(String id);

    Optional<Planet> findByName(String name);

    Page<Planet> findAll(int page, int count);

    void delete(String id);

    ResponseEntity<Response<Planet>> createOrUpdate(Planet planet);
}
