package com.javarush.anishchenko.moduletwo;

import com.javarush.anishchenko.moduletwo.model.animal.AnimalType;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import java.util.Scanner;

public class EatingProbabilityProvider {

    private final Map<AnimalPairKey, Integer> animalEatingProbabilities = new HashMap<>();

    public EatingProbabilityProvider() {
      loadAnimalEatingProbabilities();
    }

    private void loadAnimalEatingProbabilities() {
        try (Scanner sc = new Scanner(
                new BufferedReader((new FileReader("/Users/alena/IdeaProjects/cycle-of-life/src/com/javarush/anishchenko/moduletwo/hitprobability"))))) {
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

    public Integer getProbability(AnimalPairKey animalPairKey) {
        return animalEatingProbabilities.get(animalPairKey);
    }

    public Integer getProbability(AnimalType firstAnimal, AnimalType secondAnimal) {
        AnimalPairKey animalPairKey = new AnimalPairKey(firstAnimal, secondAnimal);
        Integer probabilityValue = animalEatingProbabilities.get(animalPairKey);
        if (probabilityValue == null) {
            animalPairKey = new AnimalPairKey(secondAnimal, firstAnimal);
            probabilityValue = animalEatingProbabilities.get(animalPairKey);
        }
        return probabilityValue;
    }

}

