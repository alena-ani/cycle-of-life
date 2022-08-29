package com.javarush.anishchenko.moduletwo.model.plant;

import com.javarush.anishchenko.moduletwo.model.animal.Animal;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class PlantPopulation {

    private final PlantType plantType;
    private final List<Plant> plants = new ArrayList<>();

    public PlantPopulation(PlantType plantType, Plant plant) {
        this.plantType = plantType;
        this.plants.add(plant);
    }

    public PlantPopulation(PlantType plantType, List<Plant> plants) {
        this.plantType = plantType;
        this.plants.addAll(plants);
    }

    public PlantType getPlantType() {
        return plantType;
    }

    public void addPlant(Plant plant) {
        plants.add(plant);
    }

    public void addPlants(List<Plant> plantsList) {
        plants.addAll(plantsList);
    }
}
