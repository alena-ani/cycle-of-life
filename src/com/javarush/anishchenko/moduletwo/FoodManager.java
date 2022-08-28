package com.javarush.anishchenko.moduletwo;

import com.javarush.anishchenko.moduletwo.model.AnimalWorld;
import com.javarush.anishchenko.moduletwo.model.Iceland;
import com.javarush.anishchenko.moduletwo.model.Location;
import com.javarush.anishchenko.moduletwo.model.animal.Animal;
import com.javarush.anishchenko.moduletwo.model.animal.AnimalPopulation;
import com.javarush.anishchenko.moduletwo.model.animal.AnimalType;

import java.util.List;
import java.util.Set;

public class FoodManager {

    private static final int PROBABILITY_MAX_PERCENT_VALUE = 100;
    private static final int PROBABILITY_MIN_PERCENT_VALUE = 0;

    private final EatingProbabilityProvider eatingProbabilityProvider;

    private final AnimalAttributeProvider animalAttributeProvider;

    private final Iceland iceland;

    public FoodManager(EatingProbabilityProvider eatingProbabilityProvider,
                       AnimalAttributeProvider animalAttributeProvider,
                       Iceland iceland) {

        this.eatingProbabilityProvider = eatingProbabilityProvider;
        this.animalAttributeProvider = animalAttributeProvider;
        this.iceland = iceland;
    }

    public void haveDinner() {
        System.out.println("Hunt is started...");
        for (int i = 0; i < iceland.getLength(); i++) {
            for (int j = 0; j < iceland.getWidth(); j++) {
                Location location = iceland.getLocation(i, j);
                AnimalWorld huntedAnimalWorld = new AnimalWorld(location.getAnimalWorld());
                int animalsTotal = huntedAnimalWorld.getTotal();
                while (animalsTotal > 0) {
                    Set<AnimalPopulation> animalPopulationSet = huntedAnimalWorld.getAnimalPopulations();
                    AnimalPopulation[] animalPopulations = new AnimalPopulation[animalPopulationSet.size()];
                    animalPopulationSet.toArray(animalPopulations);
                    int index = 0;
                    int animalPopulationsCount = animalPopulations.length;
                    if (animalPopulationsCount == 0) {
                        continue;
                    } else if (animalPopulationsCount > 1) {
                        index = RandomUtil.getRandomNumber(0, animalPopulationsCount);
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
                        index = RandomUtil.getRandomNumber(0, animalsCount);
                    }

                    AnimalType animalType = population.getAnimalType();
                    Animal animal = animals.get(index);
                    AnimalPopulation huntedAnimalPopulation = huntedAnimalWorld.getAnimalPopulation(animalType);
                    if (animal.isBitten() || !animal.wantEat()) {
                        System.out.println("Animal " + animalType + ": " + animal + " won't hunt");
                        huntedAnimalPopulation.remove(animal);
                        animalsTotal--;
                        if (huntedAnimalPopulation.getAnimals().isEmpty()) {
                            huntedAnimalWorld.getAnimalPopulations().remove(huntedAnimalPopulation);
                        }
                        continue;
                    }

                    AnimalPopulation animalPopulationToEat = findAnimalPopulationToEat(animalType, animalPopulations);
                    if (animalPopulationToEat == null) {
                        System.out.println("Animal " + animalType + ": " + animal + " won't hunt. No food found");
                        huntedAnimalPopulation.remove(animal);
                        animalsTotal--;
                        if (huntedAnimalPopulation.getAnimals().isEmpty()) {
                            huntedAnimalWorld.getAnimalPopulations().remove(huntedAnimalPopulation);
                        }
                        continue;
                    }

                    index = RandomUtil.getRandomNumber(0, animalPopulationToEat.getAnimals().size());
                    Animal animalToEat = animalPopulationToEat.getAnimals().get(index);
                    animal.eat(animalToEat);
                    huntedAnimalPopulation.remove(animal);
                    animalsTotal--;
                    if (huntedAnimalPopulation.getAnimals().isEmpty()) {
                        huntedAnimalWorld.getAnimalPopulations().remove(huntedAnimalPopulation);
                    }
                }
            }
        }
        System.out.println("Hunt completed...");
    }

    private AnimalPopulation findAnimalPopulationToEat(AnimalType animalType, AnimalPopulation[] animalPopulations) {
        for (AnimalPopulation animalPopulationToEat : animalPopulations) {
            AnimalType animalTypeToEat = animalPopulationToEat.getAnimalType();
            AnimalPairKey animalPairKey = new AnimalPairKey(animalType, animalTypeToEat);
            Integer eatingProbability = eatingProbabilityProvider.getProbability(animalPairKey);
            if (eatingProbability == null) {
                continue;
            }
            int probabilityRandomValue = RandomUtil.getRandomNumber(PROBABILITY_MIN_PERCENT_VALUE, PROBABILITY_MAX_PERCENT_VALUE);
            System.out.println("Animal " + animalType + " want to eat "
                    + animalPopulationToEat + " with probability: "
                    + probabilityRandomValue + ", eatingProbability=" + eatingProbability);

            if (probabilityRandomValue > eatingProbability) {
                break;
            }
            return animalPopulationToEat;
        }
        return null;
    }
}
