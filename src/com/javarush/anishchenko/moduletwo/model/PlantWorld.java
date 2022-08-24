package com.javarush.anishchenko.moduletwo.model;

import com.javarush.anishchenko.moduletwo.model.plant.PlantPopulation;

import java.util.HashSet;
import java.util.Set;

public class PlantWorld {

    private Set<PlantPopulation> plants = new HashSet<>();

    public boolean addPlant(PlantPopulation plantPopulation) {
        return plants.add(plantPopulation);
    }

    public Set<PlantPopulation> getPlants() {
        return plants;
    }
}
