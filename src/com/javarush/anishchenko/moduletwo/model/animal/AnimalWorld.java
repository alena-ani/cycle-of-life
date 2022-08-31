package com.javarush.anishchenko.moduletwo.model.animal;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AnimalWorld {

    private Set<AnimalPopulation> animalPopulations = new HashSet<>();

    public AnimalWorld() {
    }

    public AnimalWorld(AnimalWorld animalWorld) {
        Set<AnimalPopulation> animalPopulationsCopy = new HashSet<>();
        for (AnimalPopulation animalPopulation : animalWorld.getAnimalPopulations()) {
            AnimalPopulation animalOperationCopy = new AnimalPopulation(animalPopulation.getAnimalType(), animalPopulation.getAnimals());
            animalPopulationsCopy.add(animalOperationCopy);
        }
        this.animalPopulations = new HashSet<>(animalPopulationsCopy);
    }

    public boolean addAnimals(AnimalType animalType, List<Animal> animalsList) {
        AnimalPopulation animalPopulation = findGroupOfAnimals(animalType);
        if (animalPopulation != null) {
            animalPopulation.addAnimals(animalsList);
            return true;
        }

        return animalPopulations.add(new AnimalPopulation(animalType, animalsList));
    }

    public Set<AnimalPopulation> getAnimalPopulations() {
        return animalPopulations;
    }

    public AnimalPopulation getAnimalPopulation(AnimalType animalType) {
        return findGroupOfAnimals(animalType);
    }

    public AnimalPopulation getFirstPopulation() {
        if (animalPopulations.size() == 0) {
            return null;
        }
        return animalPopulations.iterator().next();
    }

    public int getTotal() {
        int total = 0;
        for (AnimalPopulation animalPopulation : animalPopulations) {
            total += animalPopulation.getAnimals().size();
        }
        return total;
    }

    private AnimalPopulation findGroupOfAnimals(AnimalType animalType) {
        for (AnimalPopulation animalPopulation : animalPopulations) {
            if (animalType.equals(animalPopulation.getAnimalType())) {
                return animalPopulation;
            }
        }
        return null;
    }
}
