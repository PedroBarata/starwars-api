package com.starwarsapi;

import com.starwarsapi.controller.PlanetControllerTest;
import com.starwarsapi.service.PlanetServiceTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

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
