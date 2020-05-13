package com.starwarsapi;

import com.starwarsapi.entity.Planet;
import com.starwarsapi.repository.PlanetRepository;
import com.starwarsapi.service.PlanetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class StarwarsapiApplication {

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
        Planet planet = new Planet("Coruscant", "Arid", "temperate");
        List<Planet> findPlanet = planetRepository.findByNameIgnoreCaseContaining(planet.getName());
        if (findPlanet == null || findPlanet.size() == 0) {
			planetService.createOrUpdate(planet);
        }
    }

}
