package com.starwarsapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.starwarsapi.entity.Planet;
import com.starwarsapi.repository.PlanetRepository;
import com.starwarsapi.service.PlanetService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class PlanetControllerTest {

    @MockBean
    private PlanetRepository planetRepository;

    @Autowired
    private PlanetService planetService;

    private JacksonTester<Planet> jsonEntity;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        JacksonTester.initFields(this, new ObjectMapper());
    }

}
