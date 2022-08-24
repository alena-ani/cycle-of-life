package com.javarush.anishchenko.moduletwo.model.plant;

public class PlantPopulation {

    private final PlantType plantType;
    private final Plant plant;
    private final int amount;

    public PlantPopulation(PlantType plantType, Plant plant, int amount) {
        this.plantType = plantType;
        this.plant = plant;
        this.amount = amount;
    }

    public PlantType getPlantType() {
        return plantType;
    }

    public Plant getPlant() {
        return plant;
    }

    public int getAmount() {
        return amount;
    }
}
