package com.javarush.anishchenko.moduletwo.model;

import com.javarush.anishchenko.moduletwo.model.animal.Animal;
import com.javarush.anishchenko.moduletwo.model.animal.AnimalType;
import com.javarush.anishchenko.moduletwo.model.animal.AnimalWorld;
import com.javarush.anishchenko.moduletwo.model.plant.PlantWorld;

import java.util.List;

public class Location {

    private final Coordinate coordinate;
    private final AnimalWorld animalWorld;
    private final PlantWorld plantWorld;

    public Location(int row, int column) {
        this.coordinate = new Coordinate(row, column);
        this.animalWorld = new AnimalWorld();
        this.plantWorld = new PlantWorld();
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void addAnimal(AnimalType animalType, Animal animal) {
        animalWorld.addAnimal(animalType, animal);
    }

    public void addAnimals(AnimalType animalType, List<Animal> animals) {
        animalWorld.addAnimals(animalType, animals);
    }

    public AnimalWorld getAnimalWorld() {
        return animalWorld;
    }

    public PlantWorld getPlantWorld() {
        return plantWorld;
    }
}
