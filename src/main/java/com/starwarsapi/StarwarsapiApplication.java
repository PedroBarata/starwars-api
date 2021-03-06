package com.starwarsapi;

import com.starwarsapi.entity.Planet;
import com.starwarsapi.repository.PlanetRepository;
import com.starwarsapi.service.PlanetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

@SpringBootApplication
public class StarwarsapiApplication {

    private static final String FIELD_NAME = "Tatooine";
    private static final String FIELD_CLIMATE = "arid";
    private static final String FIELD_TERRAIN = "temperate";
    private static final String ID = "5ebc7a7a65c0bf7a292d900c";

    public static void main(String[] args) {
        SpringApplication.run(StarwarsapiApplication.class, args);
    }

    @Bean
    CommandLineRunner init(PlanetRepository planetRepository, PlanetService planetService) {
        return args -> {
            initPlanet(planetRepository, planetService);
        };
    }

    private void initPlanet(PlanetRepository planetRepository, PlanetService planetService) {
        Planet planet = new Planet(ID, FIELD_NAME, FIELD_CLIMATE, FIELD_TERRAIN, null);
        Optional<Planet> findPlanet = planetRepository.findByNameIgnoreCase(planet.getName());
        if (findPlanet.isPresent()) {
            planetRepository.deleteById(findPlanet.get().getId());
        }
        planetService.createOrUpdate(planet);
    }

}
