package com.javarush.anishchenko.moduletwo.model;

import com.javarush.anishchenko.moduletwo.model.animal.Animal;
import com.javarush.anishchenko.moduletwo.model.animal.AnimalType;
import com.javarush.anishchenko.moduletwo.model.animal.AnimalPopulation;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AnimalWorld {

    private Set<AnimalPopulation> animals = new HashSet<>();

    public boolean addAnimal(AnimalType animalType, Animal animal) {
        AnimalPopulation animalPopulation = findGroupOfAnimals(animalType);
        if (animalPopulation != null) {
            animalPopulation.addAnimal(animal);
            return true;
        }
        return animals.add(new AnimalPopulation(animalType, animal));
    }

    public boolean addAnimals(AnimalType animalType, List<Animal> animalsList) {
        AnimalPopulation animalPopulation = findGroupOfAnimals(animalType);
        if (animalPopulation != null) {
            animalPopulation.addAnimals(animalsList);
            return true;
        }

        return animals.add(new AnimalPopulation(animalType, animalsList));
    }

    public Set<AnimalPopulation> getAnimals() {
        return animals;
    }

    public AnimalType getFirstPopulation() {
        if (animals.size() == 0) {
            return null;
        }

        return animals.iterator().next().getAnimalType();
    }

    private AnimalPopulation findGroupOfAnimals(AnimalType animalType) {
        for (AnimalPopulation animalPopulation : animals) {
            if (animalType.equals(animalPopulation.getAnimalType())) {
                return animalPopulation;
            }
        }
        return null;
    }
}
