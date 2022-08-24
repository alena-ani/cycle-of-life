package com.javarush.anishchenko.moduletwo.model.animal;

import java.util.ArrayList;
import java.util.List;

public class AnimalPopulation {

    private final AnimalType animalType;
    private final List<Animal> animals = new ArrayList<>();

    public AnimalPopulation(AnimalType animalType, Animal animal) {
        this.animalType = animalType;
        this.animals.add(animal);
    }

    public AnimalPopulation(AnimalType animalType, List<Animal> animals) {
        this.animalType = animalType;
        this.animals.addAll(animals);
    }

    public AnimalType getAnimalType() {
        return animalType;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public void addAnimals(List<Animal> animalsList) {
        animals.addAll(animalsList);
    }
}
