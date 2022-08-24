package com.javarush.anishchenko.moduletwo;

import com.javarush.anishchenko.moduletwo.model.animal.AnimalType;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import java.util.Scanner;

public class EatingAnimals {

    private final Map<AnimalPairKey, Integer> animalEatingProbabilities = new HashMap<>();

    public EatingAnimals() {
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
                            AnimalType herbiovore = AnimalType.ANIMALS[i];
                            AnimalType carnivire = AnimalType.ANIMALS[j];
                            AnimalPairKey animalPairKey = new AnimalPairKey(herbiovore, carnivire);
                            animalEatingProbabilities.put(animalPairKey, probabilityValue);
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERROR - check file!");
        }
    }

    public Integer getProbability(AnimalType firstAnimal, AnimalType secondAnimal) {
        AnimalPairKey animalPairKey = new AnimalPairKey(firstAnimal, secondAnimal);
        Integer probablityValue = animalEatingProbabilities.get(animalPairKey);
        if (probablityValue == null) {
            animalPairKey = new AnimalPairKey(secondAnimal, firstAnimal);
            probablityValue = animalEatingProbabilities.get(animalPairKey);
        }
        return probablityValue;
    }

}

