package com.starwarsapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.starwarsapi.entity.Planet;
import com.starwarsapi.repository.PlanetRepository;
import com.starwarsapi.service.PlanetService;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class PlanetControllerTest {
    private static final String ID = "5ebc7a7a65c0bf7a292d900c";
    public static final String API = "/api/planets";
    private static final String FIELD_NAME = "Tatooine";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testContextLoads() throws Exception {
        assertThat(objectMapper).isNotNull();
        assertThat(mockMvc).isNotNull();
    }

    @Test
    public void testGetByIdShouldReturnStatus200() throws Exception {
        mockMvc.perform(get(API +  "/" + ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetByIdShouldThrowError() throws Exception {
        mockMvc.perform(get(API + "/12345")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetByNameShouldReturnStatus200() throws Exception {
        mockMvc.perform(get(API + "/find?name=" + FIELD_NAME)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetByNameShouldThrowError() throws Exception {
        mockMvc.perform(get(API + "/find?name=" + "testName")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetAllShouldReturnStatus200() throws Exception {
        mockMvc.perform(get(API)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllPageShouldReturnStatus200() throws Exception {
        mockMvc.perform(get(API + "?page=0&count=10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
