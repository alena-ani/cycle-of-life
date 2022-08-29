package com.javarush.anishchenko.moduletwo;

import com.javarush.anishchenko.moduletwo.model.AnimalWorld;
import com.javarush.anishchenko.moduletwo.model.Iceland;
import com.javarush.anishchenko.moduletwo.model.Location;
import com.javarush.anishchenko.moduletwo.model.animal.Animal;
import com.javarush.anishchenko.moduletwo.model.animal.AnimalPopulation;

import java.util.Iterator;

public class CleanManager {

    public static final double SATURATION_PERCENT = 0.25;
    private final AnimalAttributeProvider animalAttributeProvider;

    private final Iceland iceland;

    public CleanManager(AnimalAttributeProvider animalAttributeProvider, Iceland iceland) {
        this.animalAttributeProvider = animalAttributeProvider;
        this.iceland = iceland;
    }

    public void clean() {
        for (int i = 0; i < iceland.getLength(); i++) {
            for (int j = 0; j < iceland.getWidth(); j++) {
                Location location = iceland.getLocation(i, j);
                AnimalWorld animalWorld = location.getAnimalWorld();
                Iterator<AnimalPopulation> animalPopulationIterator = animalWorld.getAnimalPopulations().iterator();
                while (animalPopulationIterator.hasNext()) {
                    AnimalPopulation animalPopulation = animalPopulationIterator.next();
                    Iterator<Animal> animalIterator = animalPopulation.getAnimals().iterator();
                    while (animalIterator.hasNext()) {
                        Animal animal = animalIterator.next();
                        if (animal.isBitten() || animal.getSaturation() < animal.getMaxSaturation() * SATURATION_PERCENT) {
                            animalIterator.remove();
                        }
                    }
                    if (animalPopulation.getAnimals().isEmpty()) {
                        animalPopulationIterator.remove();
                    }
                }
            }
        }
    }
}
