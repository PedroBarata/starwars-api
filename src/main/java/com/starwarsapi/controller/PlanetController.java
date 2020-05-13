package com.starwarsapi.controller;

import com.starwarsapi.entity.Planet;
import com.starwarsapi.response.Response;
import com.starwarsapi.service.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("/api/planets")
@CrossOrigin(origins = "*")
public class PlanetController {

    @Autowired
    private PlanetService planetService;

    @GetMapping(value = "{id}")
    public ResponseEntity<Response<Planet>> findById(@PathVariable("id") String id) {
        Response<Planet> response = new Response<>();
        Optional<Planet> planet = planetService.findById(id);
        if (planet.isPresent()) {
            response.setData(planet.get());
            return ResponseEntity.ok(response);

        } else {
            response.getErrors().add("No data found with id:" + id);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping()
    @ResponseBody
    public ResponseEntity<Response<List<Planet>>> findByNameOrFindAll(@RequestParam("name") Optional<String> name) {
        List<Planet> planets;
        Response<List<Planet>> response = new Response<>();
        if (name.isPresent()) {
            planets = planetService.findByName(name.get());
            response.setData(planets);
        } else {
            planets = planetService.findAll();
            response.setData(planets);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping()
    public ResponseEntity<Response<Planet>> create(@RequestBody Planet planet) {
        return planetService.createOrUpdate(planet);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Response<String>> delete(@PathVariable("id") String id) {
        Response<String> response = new Response<String>();
        Optional<Planet> planet = planetService.findById(id);
        if (planet.isPresent()) {
            planetService.delete(id);
            return ResponseEntity.ok(new Response<String>());

        } else {
            response.getErrors().add("No data found with id:" + id);
            return ResponseEntity.badRequest().body(response);
        }
    }
}
