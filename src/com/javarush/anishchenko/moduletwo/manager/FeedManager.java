package com.javarush.anishchenko.moduletwo.manager;

import com.javarush.anishchenko.moduletwo.model.animal.AnimalPairKey;
import com.javarush.anishchenko.moduletwo.model.animal.AnimalWorld;
import com.javarush.anishchenko.moduletwo.model.Coordinate;
import com.javarush.anishchenko.moduletwo.model.Iceland;
import com.javarush.anishchenko.moduletwo.model.Location;
import com.javarush.anishchenko.moduletwo.model.animal.Animal;
import com.javarush.anishchenko.moduletwo.model.animal.AnimalPopulation;
import com.javarush.anishchenko.moduletwo.model.animal.AnimalType;
import com.javarush.anishchenko.moduletwo.model.plant.Plant;
import com.javarush.anishchenko.moduletwo.model.plant.PlantPopulation;
import com.javarush.anishchenko.moduletwo.provider.EatingProbabilityProvider;
import com.javarush.anishchenko.moduletwo.util.RandomUtil;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FeedManager {

    private static final int PROBABILITY_MAX_PERCENT_VALUE = 100;
    private static final int PROBABILITY_MIN_PERCENT_VALUE = 0;

    private final EatingProbabilityProvider eatingProbabilityProvider;
    private final StatisticManager statisticManager;
    private final Iceland iceland;


    public FeedManager(EatingProbabilityProvider eatingProbabilityProvider,
                       Iceland iceland, StatisticManager statisticManager) {
        this.statisticManager = statisticManager;
        this.eatingProbabilityProvider = eatingProbabilityProvider;
        this.iceland = iceland;

    }

    public void haveDinner() {
        System.out.println("Hunt is started...");
        for (int i = 0; i < iceland.getLength(); i++) {
            for (int j = 0; j < iceland.getWidth(); j++) {
                Coordinate locationCoordinate = new Coordinate(i, j);
                Location location = iceland.getLocation(locationCoordinate);
                AnimalWorld huntedAnimalWorld = new AnimalWorld(location.getAnimalWorld());
                int animalsTotal = huntedAnimalWorld.getTotal();
                Map<AnimalType, Integer> eatingAnimalsCountMap = new HashMap<>();
                while (animalsTotal > 0) {
                    Set<AnimalPopulation> animalPopulationSet = huntedAnimalWorld.getAnimalPopulations();
                    AnimalPopulation[] animalPopulations = new AnimalPopulation[animalPopulationSet.size()];
                    animalPopulationSet.toArray(animalPopulations);
                    int index = 0;
                    int animalPopulationsCount = animalPopulations.length;
                    if (animalPopulationsCount == 0) {
                        continue;
                    } else if (animalPopulationsCount > 1) {
                        index = RandomUtil.getNumber(0, animalPopulationsCount);
                    } else {
                        index = 0;
                    }

                    AnimalPopulation population = animalPopulations[index];
                    List<Animal> animals = population.getAnimals();
                    int animalsCount = animals.size();
                    if (animalsCount == 0) {
                        continue;
                    } else if (animalsCount == 1) {
                        index = 0;
                    } else {
                        index = RandomUtil.getNumber(0, animalsCount);
                    }

                    AnimalType animalType = population.getAnimalType();
                    Animal animal = animals.get(index);
                    AnimalPopulation huntedAnimalPopulation = huntedAnimalWorld.getAnimalPopulation(animalType);
                    if (animal.isBitten() || !animal.wantEat()) {
                        huntedAnimalPopulation.remove(animal);
                        animalsTotal--;
                        if (huntedAnimalPopulation.getAnimals().isEmpty()) {
                            huntedAnimalWorld.getAnimalPopulations().remove(huntedAnimalPopulation);
                        }
                        continue;
                    }

                    AnimalPopulation animalPopulationToEat = findAnimalPopulationToEat(animalType, animalPopulations);
                    if (animalPopulationToEat == null) {
                        tryToEatPlants(location, animalType, animal);
                        huntedAnimalPopulation.remove(animal);
                        animalsTotal--;
                        if (huntedAnimalPopulation.getAnimals().isEmpty()) {
                            huntedAnimalWorld.getAnimalPopulations().remove(huntedAnimalPopulation);
                        }
                        continue;
                    }

                    index = RandomUtil.getNumber(0, animalPopulationToEat.getAnimals().size());
                    Animal animalToEat = animalPopulationToEat.getAnimals().get(index);
                    animal.eat(animalToEat);

                    Integer count = eatingAnimalsCountMap.get(animalType);
                    if (count == null) {
                        count = 0;
                    }
                    eatingAnimalsCountMap.put(animalType, ++count);

                    huntedAnimalPopulation.remove(animal);
                    animalsTotal--;
                    if (huntedAnimalPopulation.getAnimals().isEmpty()) {
                        huntedAnimalWorld.getAnimalPopulations().remove(huntedAnimalPopulation);
                    }

                }

                for (Map.Entry<AnimalType, Integer> entry : eatingAnimalsCountMap.entrySet()) {
                    statisticManager.addEatenAnimalsStatistic(locationCoordinate, entry.getKey(), entry.getValue());
                }
            }
        }
        System.out.println("Hunt completed...");
    }

    public void haveStarvation() {
        for (int i = 0; i < iceland.getLength(); i++) {
            for (int j = 0; j < iceland.getWidth(); j++) {
                Coordinate locationCoordinate = new Coordinate(i, j);
                Location location = iceland.getLocation(locationCoordinate);
                AnimalWorld animalWorld = location.getAnimalWorld();
                for (AnimalPopulation animalPopulation: animalWorld.getAnimalPopulations()) {
                    for (Animal animal: animalPopulation.getAnimals()) {
                        animal.starvation();
                    }
                }
            }
        }
    }

    private void tryToEatPlants(Location location, AnimalType animalType, Animal animal) {
        if (eatingProbabilityProvider.getPlantEatProbability(animalType) == null) {
            return;
        }

        Iterator<PlantPopulation> plantPopulationIterator = location.getPlantWorld().getPlantPopulations().iterator();
        while (plantPopulationIterator.hasNext()) {
            PlantPopulation plantPopulation = plantPopulationIterator.next();
            Iterator<Plant> plantIterator = plantPopulation.getPlants().iterator();
            while (plantIterator.hasNext() && animal.wantEat()) {
                Plant plant = plantIterator.next();
                animal.eat(plant);
                plantIterator.remove();
            }
            if (plantPopulation.getPlants().isEmpty()) {
                plantPopulationIterator.remove();
            }
        }
    }

    private AnimalPopulation findAnimalPopulationToEat(AnimalType animalType, AnimalPopulation[] animalPopulations) {
        for (AnimalPopulation animalPopulationToEat : animalPopulations) {
            AnimalType animalTypeToEat = animalPopulationToEat.getAnimalType();
            AnimalPairKey animalPairKey = new AnimalPairKey(animalType, animalTypeToEat);
            Integer eatingProbability = eatingProbabilityProvider.getAnimalEatProbability(animalPairKey);
            if (eatingProbability == null) {
                continue;
            }
            int probabilityRandomValue = RandomUtil.getNumber(PROBABILITY_MIN_PERCENT_VALUE, PROBABILITY_MAX_PERCENT_VALUE);

            if (probabilityRandomValue > eatingProbability) {
                break;
            }
            return animalPopulationToEat;
        }
        return null;
    }
}
