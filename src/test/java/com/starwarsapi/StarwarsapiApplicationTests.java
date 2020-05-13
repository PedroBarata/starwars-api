package com.starwarsapi;

import com.starwarsapi.service.PlanetServiceTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {PlanetServiceTest.class})
@Suite.SuiteClasses({
		PlanetServiceTest.class
})
@RunWith(Suite.class)
public class StarwarsapiApplicationTests {


    @Test
    public void contextLoads() {
    }



	/*@Test
	public void contextLoads() {
	}*/


	/*@Bean
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
	}*/

}
