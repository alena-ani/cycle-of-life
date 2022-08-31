package com.javarush.anishchenko.moduletwo.provider;

import com.javarush.anishchenko.moduletwo.model.animal.AnimalPairKey;
import com.javarush.anishchenko.moduletwo.model.animal.AnimalType;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import java.util.Scanner;

public class EatingProbabilityProvider {

    private final Map<AnimalPairKey, Integer> animalEatingProbabilities = new HashMap<>();

    private static final Map<AnimalType, Integer> PLANTS_EATING_PROBABILITY = new HashMap<>();

    static {
        PLANTS_EATING_PROBABILITY.put(AnimalType.HORSE, 100);
        PLANTS_EATING_PROBABILITY.put(AnimalType.DEER, 100);
        PLANTS_EATING_PROBABILITY.put(AnimalType.RABBIT, 100);
        PLANTS_EATING_PROBABILITY.put(AnimalType.MOUSE, 100);
        PLANTS_EATING_PROBABILITY.put(AnimalType.GOAT, 100);
        PLANTS_EATING_PROBABILITY.put(AnimalType.SHEEP, 100);
        PLANTS_EATING_PROBABILITY.put(AnimalType.BOAR, 100);
        PLANTS_EATING_PROBABILITY.put(AnimalType.BUFFALO, 100);
        PLANTS_EATING_PROBABILITY.put(AnimalType.DUCK, 100);
        PLANTS_EATING_PROBABILITY.put(AnimalType.CATERPILLAR, 100);
    }

    public EatingProbabilityProvider() {
      loadAnimalEatingProbabilities();
    }

    private void loadAnimalEatingProbabilities() {
        try (Scanner sc = new Scanner(
                new BufferedReader((new FileReader("/Users/alena/IdeaProjects/cycle-of-life/src/com/javarush/anishchenko/moduletwo/parameters/hitprobability"))))) {
            int size = AnimalType.ANIMALS.length;
            while (sc.hasNextInt()) {
                for (int i = 0; i < size; i++) {
                    String[] line = sc.nextLine().trim().split(" ");
                    for (int j = 0; j < size; j++) {
                        int probabilityValue = Integer.parseInt(line[j]);
                        if (probabilityValue > 0) {
                            AnimalType carnivore = AnimalType.ANIMALS[i];
                            AnimalType herbivore = AnimalType.ANIMALS[j];
                            AnimalPairKey animalPairKey = new AnimalPairKey(carnivore, herbivore);
                            animalEatingProbabilities.put(animalPairKey, probabilityValue);
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERROR - check file!");
        }
    }

    public Integer getAnimalEatProbability(AnimalPairKey animalPairKey) {
        return animalEatingProbabilities.get(animalPairKey);
    }

    public Integer getPlantEatProbability(AnimalType animalType) {
        Integer plantEatingProbability = PLANTS_EATING_PROBABILITY.get(animalType);
        if (plantEatingProbability == null) {
            return 0;
        }
        return plantEatingProbability;
    }

    public Integer getAnimalEatProbability(AnimalType firstAnimal, AnimalType secondAnimal) {
        AnimalPairKey animalPairKey = new AnimalPairKey(firstAnimal, secondAnimal);
        Integer probabilityValue = animalEatingProbabilities.get(animalPairKey);
        if (probabilityValue == null) {
            animalPairKey = new AnimalPairKey(secondAnimal, firstAnimal);
            probabilityValue = animalEatingProbabilities.get(animalPairKey);
        }
        return probabilityValue;
    }

}