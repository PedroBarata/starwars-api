package com.starwarsapi.service;

import com.starwarsapi.entity.Planet;
import com.starwarsapi.repository.PlanetRepository;
import com.starwarsapi.response.Response;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PlanetServiceTest {
    private static final String ID = "5ebc7a7a65c0bf7a292d900c";
    private static final String FIELD_NAME = "Tatooine";
    private static final String FIELD_CLIMATE = "arid";
    private static final String FIELD_TERRAIN = "temperate";
    private static final Integer FIELD_FILMS = 5;

    @Autowired
    PlanetRepository planetRepository;

    @Autowired
    PlanetService planetService;

    @Test
    public void a_testContextLoads() {
        assertThat(planetService).isNotNull();
        assertThat(planetRepository).isNotNull();
    }

    @Test
    public void b_createOrUpdatePlanet() {
        Planet planet = new Planet(ID, FIELD_NAME, FIELD_CLIMATE, FIELD_TERRAIN, FIELD_FILMS);
        planetService.createOrUpdate(planet);
    }

    @Test
    public void c_testFindAllShouldBeGreaterOrEqualOne() {
        Page<Planet> planets = this.planetService.findAll(0, 10);
        assertThat(planets.getTotalElements()).isGreaterThanOrEqualTo(1);
    }

    @Test
    public void d_testFindByIdShouldBeEqualOne() {
        Optional<Planet> planet = this.planetService.findById(ID);
        assertThat(planet.isPresent()).isTrue();
    }

    @Test
    public void e_testFindByNameShouldBeGreaterOrEqualOne() {
        Page<Planet> planet = planetService.findByNameContaining(FIELD_NAME, 0, 20);
        assertThat(planet.getTotalElements()).isGreaterThanOrEqualTo(1);
    }

    @Test
    public void f_testFindByNonExistingNameShouldBeEqualZero() {
        Page<Planet> planet = planetService.findByNameContaining("Corus142354", 0, 20);
        assertThat(planet.getTotalElements()).isGreaterThanOrEqualTo(0);
    }

    @Test
    public void g_testCreateOrUpdateShouldPersistsPlanet() {
        Planet planet = new Planet("Kamino", FIELD_CLIMATE, "ocean");
        Response<Planet> persistedPlanet = planetService.createOrUpdate(planet).getBody();
        assertThat(persistedPlanet.getData().getId()).isNotNull();
    }

    @Test
    public void h_testCreateOrUpdateDuplicatePlanetShouldThrowError() {
        Planet planet = new Planet(FIELD_NAME, FIELD_CLIMATE, FIELD_TERRAIN);
        planetService.createOrUpdate(planet);
        Response<Planet> persistedPlanet = planetService.createOrUpdate(planet).getBody();
        assertThat(persistedPlanet.getErrors().get(0)).isEqualTo("Already exists a planet with this name: " + FIELD_NAME);
        assertThat(persistedPlanet.getData()).isNull();
    }

    @Test
    public void i_testCreateOrUpdateWithoutNameShouldThrowError() {
        Planet planet = new Planet(null, FIELD_CLIMATE, FIELD_TERRAIN);
        Response<Planet> persistedPlanet = planetService.createOrUpdate(planet).getBody();
        assertThat(persistedPlanet.getErrors().get(0)).isEqualTo("No name information");
        assertThat(persistedPlanet.getData()).isNull();
    }

    @Test
    public void j_testCreateOrUpdateWithFewInfoNameSwapiShouldThrowError() {
        Planet planet = new Planet("Cor", FIELD_CLIMATE, FIELD_TERRAIN);
        Response<Planet> persistedPlanet = planetService.createOrUpdate(planet).getBody();
        assertThat(persistedPlanet.getErrors().get(0)).isEqualTo("More than one data found. Please, be more specific with planet name");
        assertThat(persistedPlanet.getData()).isNull();
    }

    @Test
    public void k_testCreateOrUpdateWithWrongNameSwapiShouldThrowError() {
        Planet planet = new Planet("Cor123456", FIELD_CLIMATE, FIELD_TERRAIN);
        Response<Planet> persistedPlanet = planetService.createOrUpdate(planet).getBody();
        assertThat(persistedPlanet.getErrors().get(0)).isEqualTo("No data found with name");
        assertThat(persistedPlanet.getData()).isNull();
    }

    @Test
    public void l_testCreateOrUpdateWithoutTerrainShouldThrowError() {
        Planet planet = new Planet(FIELD_NAME, FIELD_CLIMATE, null);
        Response<Planet> persistedPlanet = planetService.createOrUpdate(planet).getBody();
        assertThat(persistedPlanet.getErrors().get(0)).isEqualTo("No terrain information");
        assertThat(persistedPlanet.getData()).isNull();
    }

    @Test
    public void m_testCreateOrUpdateWithoutClimateShouldThrowError() {
        Planet planet = new Planet(FIELD_NAME, null, FIELD_TERRAIN);
        Response<Planet> persistedPlanet = planetService.createOrUpdate(planet).getBody();
        assertThat(persistedPlanet.getErrors().get(0)).isEqualTo("No climate information");
        assertThat(persistedPlanet.getData()).isNull();
    }

    @Test
    public void n_deleteAllPlanets() {
        planetRepository.deleteAll();
    }
}
