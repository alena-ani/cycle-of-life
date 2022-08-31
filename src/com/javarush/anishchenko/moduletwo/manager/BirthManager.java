package com.javarush.anishchenko.moduletwo.manager;

import com.javarush.anishchenko.moduletwo.factory.AnimalFactory;
import com.javarush.anishchenko.moduletwo.model.animal.AnimalAttributes;
import com.javarush.anishchenko.moduletwo.model.animal.AnimalWorld;
import com.javarush.anishchenko.moduletwo.model.Coordinate;
import com.javarush.anishchenko.moduletwo.model.Iceland;
import com.javarush.anishchenko.moduletwo.model.Location;
import com.javarush.anishchenko.moduletwo.model.animal.Animal;
import com.javarush.anishchenko.moduletwo.model.animal.AnimalPopulation;
import com.javarush.anishchenko.moduletwo.model.animal.AnimalType;
import com.javarush.anishchenko.moduletwo.provider.AttributeProvider;

import java.util.List;


public class BirthManager {

    private final AttributeProvider attributeProvider;
    private final StatisticManager statisticManager;
    private final Iceland iceland;

    public BirthManager(AttributeProvider attributeProvider, Iceland iceland, StatisticManager statisticManager) {
        this.attributeProvider = attributeProvider;
        this.statisticManager = statisticManager;
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
                        statisticManager.addBornAnimalStatistic(location.getCoordinate(), animalPopulation.getAnimalType(), bornAnimalsCount);
                    }
                }
            }
        }
    }
}