package com.starwarsapi.controller;

import com.starwarsapi.entity.Planet;
import com.starwarsapi.repository.PlanetRepository;
import com.starwarsapi.service.PlanetService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PlanetControllerTest {

    @MockBean
    private PlanetRepository planetRepository;

    @Autowired
    private PlanetService planetService;

    private JacksonTester<Planet> jsonEntity;

    @Test
    public void contextLoads() throws Exception {
    }

  /*  @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        JacksonTester.initFields(this, new ObjectMapper());
    }*/

}
