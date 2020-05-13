package com.starwarsapi.service;

import com.starwarsapi.entity.Planet;
import com.starwarsapi.repository.PlanetRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PlanetServiceTest {

    private static final String FIELD_NAME = "Tatooine";
    private static final String FIELD_CLIMATE = "arid";
    private static final String FIELD_TERRAIN = "temperate";
    private static final String ID = "5dc4c9734e9b1214ed7a9e8a";

    @MockBean
    PlanetRepository planetRepository;

    @Autowired
    PlanetService planetService;

    @Before
    public void setUp() {
        Planet planet = new Planet(ID, FIELD_NAME, FIELD_CLIMATE, FIELD_TERRAIN);
        planetRepository.save(planet);
    }

    @Test
    public void testContextLoads() {
        assertThat(planetService).isNotNull();
        assertThat(planetRepository).isNotNull();
    }


    @Test
    public void testFindAll() {
        List<Planet> planets = this.planetService.findAll();
        assertThat(planets.size()).isGreaterThanOrEqualTo(1);
    }
    /*

    @Test
    public void testFindOneByName() {
        List<Planet> planets = planetService.findByName("Coruscant");
        assertThat(planets.size()).isEqualTo(1);
    }*/

    /*@Test
    public void testFindAll() {
        List<Planet> planets = planetService.findAll();
        assertThat(planets.size()).isGreaterThanOrEqualTo(1);
    }*/

}
