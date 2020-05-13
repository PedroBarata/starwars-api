package com.starwarsapi.service.impl;

import com.starwarsapi.dto.SwapiPlanetDTO;
import com.starwarsapi.entity.Planet;
import com.starwarsapi.repository.PlanetRepository;
import com.starwarsapi.response.Response;
import com.starwarsapi.service.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.ObjectError;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class PlanetServiceImpl implements PlanetService {

    @Value("${swapi.uri}")
    private String swapiURI;

    @Autowired
    private PlanetRepository planetRepository;

    @Override
    public Optional<Planet> findById(String id) {
        return planetRepository.findById(id);
    }

    @Override
    public List<Planet> findByName(String name) {
        return planetRepository.findByNameIgnoreCaseContaining(name);
    }

    @Override
    public List<Planet> findAll() {
        return planetRepository.findAll(Sort.by("name"));
    }

    @Override
    public void delete(String id) {
        planetRepository.deleteById(id);
    }

    @Override
    public ResponseEntity<Response<Planet>> createOrUpdate(Planet planet) {
        Response<Planet> response = new Response<>();
        BindingResult result = new DataBinder(planet).getBindingResult();
        try {
            validateCreatePlanet(planet, result);
            SwapiPlanetDTO swapiPlanet = requestAndCreateSwapiPlanetByName(planet);
            validateSwapiPlanetURI(swapiPlanet, result);
            if (result.hasErrors()) {
                result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
                return ResponseEntity.badRequest().body(response);
            }
            planet.setQtyFilms(swapiPlanet.getResults().get(0).getFilms().size());
            Planet planetPersisted = planetRepository.save(planet);
            response.setData(planetPersisted);
        } catch (Exception e) {
            response.getErrors().add(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    private SwapiPlanetDTO requestAndCreateSwapiPlanetByName(Planet planet) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(swapiURI + "planets/?search=" + planet.getName(), SwapiPlanetDTO.class);
    }

    private void validateSwapiPlanetURI(SwapiPlanetDTO swapiPlanet, BindingResult result) {
        if (swapiPlanet == null || swapiPlanet.getCount() == 0) {
            result.addError(new ObjectError("SwapiPlanet", "No data found with name"));
            return;
        }
        if (swapiPlanet.getCount() > 1) {
            result.addError(new ObjectError("SwapiPlanet", "More than one data found. Please, be more specific with planet name"));
            return;
        }
    }


    private void validateCreatePlanet(Planet planet, BindingResult result) {
        if (planet.getName() == null) {
            result.addError(new ObjectError("Planet", "No name information"));
            return;
        }
        if (planet.getTerrain() == null) {
            result.addError(new ObjectError("Planet", "No terrain information"));
            return;
        }
        if (planet.getClimate() == null) {
            result.addError(new ObjectError("Planet", "No climate information"));
            return;
        }
    }

}
