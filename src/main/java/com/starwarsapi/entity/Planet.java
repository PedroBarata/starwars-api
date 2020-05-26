package com.starwarsapi.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.List;

@Document
public class Planet {

    @Id
    private String id;

    @NotNull(message = "Field Name is required")
    private String name;

    @NotNull(message = "Field Climate is required")
    private String climate;

    @NotNull(message = "Field Terrain is required")
    private String terrain;

    @Transient
    private List<String> films;

    private Integer qtyFilms;

    public Planet() {

    }

    public Planet(String id, String name, String climate, String terrain, Integer qtyFilms) {
        this.id = id;
        this.name = name;
        this.climate = climate;
        this.terrain = terrain;
        this.qtyFilms = qtyFilms;
    }

    public Planet(String name, String climate, String terrain) {
        this.name = name;
        this.climate = climate;
        this.terrain = terrain;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClimate() {
        return climate;
    }

    public void setClimate(String climate) {
        this.climate = climate;
    }

    public String getTerrain() {
        return terrain;
    }

    public void setTerrain(String terrain) {
        this.terrain = terrain;
    }

    public List<String> getFilms() {
        return films;
    }

    public void setFilms(List<String> films) {
        this.films = films;
    }

    public Integer getQtyFilms() {
        return qtyFilms;
    }

    public void setQtyFilms(Integer qtyFilms) {
        this.qtyFilms = qtyFilms;
    }
}
