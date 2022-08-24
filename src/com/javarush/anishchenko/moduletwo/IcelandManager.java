package com.javarush.anishchenko.moduletwo;

import com.javarush.anishchenko.moduletwo.model.AnimalAttributeProvider;
import com.javarush.anishchenko.moduletwo.model.AnimalAttributes;
import com.javarush.anishchenko.moduletwo.model.Iceland;
import com.javarush.anishchenko.moduletwo.model.Location;
import com.javarush.anishchenko.moduletwo.model.animal.Animal;
import com.javarush.anishchenko.moduletwo.model.animal.AnimalType;

import java.util.ArrayList;
import java.util.List;

public class IcelandManager {

    private static final int ANIMALS_AMOUNT = 50;

    private final AnimalAttributeProvider animalAttributeProvider;

    private final Iceland iceland;

    public IcelandManager(AnimalAttributeProvider animalAttributeProvider, Iceland iceland) {
        this.animalAttributeProvider = animalAttributeProvider;
        this.iceland = iceland;
    }

    public void fillIceland() {
        System.out.println("Start filling iceland with animals");
        for (AnimalType animalType : AnimalType.values()) {
            AnimalAttributes attributes = animalAttributeProvider.getAttributes(animalType);
            int amount = ANIMALS_AMOUNT;
            while (amount > 0) {
                int animalsAmount = RandomUtil.getRandomNumber(1, amount);
                int row = RandomUtil.getRandomNumber(0, iceland.getLength());
                int column = RandomUtil.getRandomNumber(0, iceland.getWidth());
                if (attributes.getMaxAmount() >= animalsAmount) {
                    List<Animal> animals = createAnimals(animalType, animalsAmount);
                    System.out.println("Set " + animalsAmount + " " + animalType + " on [" + row + "," +column + "]");
                    Location location = iceland.getLocation(row, column);
                    location.addAnimals(animalType, animals);
                    amount = amount - animalsAmount;
                }
            }
        }
        System.out.println("Iceland successfully filled with animals");
    }

    public void showIceland() {
        System.out.println();
        for (int i = 0; i < iceland.getLength(); i++) {
            for (int j = 0; j < iceland.getWidth(); j++) {
                Location location = iceland.getLocation(i, j);
                AnimalType animalTypeOfPopulation = location.getAnimalWorld().getFirstPopulation();
                String emoji = animalAttributeProvider.getEmoji(animalTypeOfPopulation);
                System.out.print(emoji);
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private List<Animal> createAnimals(AnimalType animalType, int amount) {
        List<Animal> animals = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            animals.add(AnimalFactory.createAnimal(animalType));
        }
        return animals;
    }

}
