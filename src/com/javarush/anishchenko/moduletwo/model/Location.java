package com.javarush.anishchenko.moduletwo.model;

import com.javarush.anishchenko.moduletwo.model.animal.Animal;
import com.javarush.anishchenko.moduletwo.model.animal.AnimalType;
import com.javarush.anishchenko.moduletwo.model.plant.Plant;
import com.javarush.anishchenko.moduletwo.model.plant.PlantPopulation;
import com.javarush.anishchenko.moduletwo.model.plant.PlantType;

import java.util.List;

public class Location {

    private final int row;
    private final int column;
    private final AnimalWorld animalWorld;
    private final PlantWorld plantWorld;

    public Location(int row, int column) {
        this.row = row;
        this.column = column;
        this.animalWorld = new AnimalWorld();
        this.plantWorld = new PlantWorld();
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void addAnimal(AnimalType animalType, Animal animal) {
        animalWorld.addAnimal(animalType, animal);
    }

    public void addAnimals(AnimalType animalType, List<Animal> animals) {
        animalWorld.addAnimals(animalType, animals);
    }

    public void addPlant(PlantType plantType, Plant plant, int amount) {
        plantWorld.addPlant(new PlantPopulation(plantType, plant, amount));
    }

    public AnimalWorld getAnimalWorld() {
        return animalWorld;
    }

    public PlantWorld getPlantWorld() {
        return plantWorld;
    }

    public String show() {
        return "[ ]";
    }
}
