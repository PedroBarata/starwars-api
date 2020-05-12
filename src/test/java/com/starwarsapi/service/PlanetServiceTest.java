package com.starwarsapi.service;

import com.starwarsapi.repository.PlanetRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class PlanetServiceTest {

    @MockBean
    private PlanetRepository planetRepository;

    @Autowired
    private PlanetService planetService;


   /* @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }*/
}
