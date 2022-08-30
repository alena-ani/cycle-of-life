package com.javarush.anishchenko.moduletwo;

import com.javarush.anishchenko.moduletwo.model.AnimalWorld;
import com.javarush.anishchenko.moduletwo.model.Coordinate;
import com.javarush.anishchenko.moduletwo.model.Iceland;
import com.javarush.anishchenko.moduletwo.model.Location;
import com.javarush.anishchenko.moduletwo.model.animal.Animal;
import com.javarush.anishchenko.moduletwo.model.animal.AnimalPopulation;

import java.util.Iterator;

public class CleanManager {

    private final ObserverManager observerManager;

    private final Iceland iceland;

    public CleanManager(ObserverManager observerManager, Iceland iceland) {
        this.observerManager = observerManager;
        this.iceland = iceland;
    }

    public void clean() {
        for (int i = 0; i < iceland.getLength(); i++) {
            for (int j = 0; j < iceland.getWidth(); j++) {
                Location location = iceland.getLocation(new Coordinate(i, j));
                AnimalWorld animalWorld = location.getAnimalWorld();
                Iterator<AnimalPopulation> animalPopulationIterator = animalWorld.getAnimalPopulations().iterator();
                while (animalPopulationIterator.hasNext()) {
                    AnimalPopulation animalPopulation = animalPopulationIterator.next();
                    Iterator<Animal> animalIterator = animalPopulation.getAnimals().iterator();
                    int animalsCount = animalPopulation.getAnimalCount();
                    while (animalIterator.hasNext()) {
                        Animal animal = animalIterator.next();
                        if (animal.isBitten() || animal.hasNoStrength()) {
                            animalIterator.remove();
                        }
                    }
                    int diedAnimals = animalsCount - animalPopulation.getAnimalCount();
                    observerManager.addDiedAnimalsStatistic(location.getCoordinate(), animalPopulation.getAnimalType(), diedAnimals);
                    if (animalPopulation.getAnimals().isEmpty()) {
                        animalPopulationIterator.remove();
                    }
                }
            }
        }
    }
}
