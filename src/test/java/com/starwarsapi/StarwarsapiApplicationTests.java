package com.starwarsapi;

import com.starwarsapi.controller.PlanetControllerTest;
import com.starwarsapi.repository.PlanetRepository;
import com.starwarsapi.service.PlanetServiceTest;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.beans.factory.annotation.Autowired;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {PlanetServiceTest.class})
@Suite.SuiteClasses({
        PlanetControllerTest.class,
        PlanetServiceTest.class
})
@RunWith(Suite.class)
public class StarwarsapiApplicationTests {

    @Test
    public void contextLoads() {
    }



}
