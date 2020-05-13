package com.starwarsapi.controller;

import com.starwarsapi.entity.Planet;
import com.starwarsapi.response.Response;
import com.starwarsapi.service.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @GetMapping("find")
    public ResponseEntity<Response<Planet>> findByName(@RequestParam("name") String name) {
        Response<Planet> response = new Response<>();
        Optional<Planet> planet = planetService.findByName(name);
        if (planet.isPresent()) {
            response.setData(planet.get());
            return ResponseEntity.ok(response);
        } else {
            response.getErrors().add("No data found with name:" + name);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping()
    @ResponseBody
    public ResponseEntity<Response<Page<Planet>>> findAll(@RequestParam(name = "page", defaultValue = "0") int page,
                                                          @RequestParam(name = "count", defaultValue = "20") int count) {
        Response<Page<Planet>> response = new Response<>();
        response.setData(planetService.findAll(page, count));
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
