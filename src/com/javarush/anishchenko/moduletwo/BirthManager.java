package com.javarush.anishchenko.moduletwo;

import com.javarush.anishchenko.moduletwo.model.AnimalAttributes;
import com.javarush.anishchenko.moduletwo.model.AnimalWorld;
import com.javarush.anishchenko.moduletwo.model.Coordinate;
import com.javarush.anishchenko.moduletwo.model.Iceland;
import com.javarush.anishchenko.moduletwo.model.Location;
import com.javarush.anishchenko.moduletwo.model.animal.Animal;
import com.javarush.anishchenko.moduletwo.model.animal.AnimalPopulation;
import com.javarush.anishchenko.moduletwo.model.animal.AnimalType;

import java.util.List;


public class BirthManager {

    private final AttributeProvider attributeProvider;

    private final Iceland iceland;

    public BirthManager(AttributeProvider attributeProvider, Iceland iceland) {
        this.attributeProvider = attributeProvider;
        this.iceland = iceland;
    }

    public void birth() {
        for (int i = 0; i < iceland.getLength(); i++) {
            for (int j = 0; j < iceland.getWidth(); j++) {
                Location location = iceland.getLocation(new Coordinate(i, j));
                AnimalWorld animalWorld = location.getAnimalWorld();
                for (AnimalPopulation animalPopulation : animalWorld.getAnimalPopulations()) {
                    AnimalType animalType = animalPopulation.getAnimalType();
                    int maxAnimalCountPerCell = attributeProvider.getMaxAmountOfAnimals(animalType);
                    int liveAnimalsCount = animalPopulation.getLiveAnimalsCount();
                    if (liveAnimalsCount > 0 && liveAnimalsCount < maxAnimalCountPerCell) {
                        int bornAnimalsCount = liveAnimalsCount / 2;
                        int newAnimalsCount = liveAnimalsCount + bornAnimalsCount;
                        if (newAnimalsCount > maxAnimalCountPerCell) {
                            bornAnimalsCount = maxAnimalCountPerCell - liveAnimalsCount;
                        }
                        AnimalAttributes animalAttributes = attributeProvider.getAnimalAttributes(animalType);
                        List<Animal> bornAnimals = AnimalFactory.createAnimals(animalType, bornAnimalsCount, animalAttributes);
                        animalPopulation.addAnimals(bornAnimals);
                    }
                }
            }
        }
    }
}