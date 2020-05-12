package com.starwarsapi.service;

import com.starwarsapi.entity.Planet;
import com.starwarsapi.response.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Component
public interface PlanetService {

    Optional<Planet> findById(String id);

    List<Planet> findByName(String name);

    List<Planet> findAll();

    void delete(String id);

    ResponseEntity<Response<Planet>> createOrUpdate(HttpServletRequest request, Planet planet, BindingResult result);
}
