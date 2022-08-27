package com.javarush.anishchenko.moduletwo;

import com.javarush.anishchenko.moduletwo.model.*;
import com.javarush.anishchenko.moduletwo.model.animal.Animal;
import com.javarush.anishchenko.moduletwo.model.animal.AnimalPopulation;
import com.javarush.anishchenko.moduletwo.model.animal.AnimalType;

import java.util.ArrayList;
import java.util.List;

public class IcelandManager {

    private static final int ANIMALS_AMOUNT = 50;

    private final AnimalAttributeProvider animalAttributeProvider;

    private final EatingProbabilityProvider eatingProbabilityProvider;

    private final Iceland iceland;

    private final WalkManager walkManager;

    private final FoodManager foodManager;

    private final BirthManager birthManager;

    private final CleanManager cleanManager;

    private final ObserverManager observerManager;

    public IcelandManager(AnimalAttributeProvider animalAttributeProvider,
                          EatingProbabilityProvider eatingProbabilityProvider,
                          Iceland iceland) {

        this.animalAttributeProvider = animalAttributeProvider;
        this.eatingProbabilityProvider = eatingProbabilityProvider;
        this.iceland = iceland;
        this.walkManager = new WalkManager(animalAttributeProvider, iceland);
        this.foodManager = new FoodManager(eatingProbabilityProvider, animalAttributeProvider, iceland);
        this.birthManager = new BirthManager(animalAttributeProvider, iceland);
        this.cleanManager = new CleanManager(animalAttributeProvider, iceland);
        this.observerManager = new ObserverManager(animalAttributeProvider, iceland);
    }

    public void fillIceland() {
        // System.out.println("Start filling iceland with animals...");
        for (AnimalType animalType : AnimalType.values()) {
            AnimalAttributes attributes = animalAttributeProvider.getAttributes(animalType);
            int amount = ANIMALS_AMOUNT;
            while (amount > 0) {
                int animalsAmount = RandomUtil.getRandomNumber(1, amount);
                int row = RandomUtil.getRandomNumber(0, iceland.getLength());
                int column = RandomUtil.getRandomNumber(0, iceland.getWidth());
                if (attributes.getMaxAmount() >= animalsAmount) {
                    List<Animal> animals = createAnimals(animalType, animalsAmount);
                    // System.out.println("Set " + animalsAmount + " " + animalType + " on [" + row + "," +column + "]");
                    Location location = iceland.getLocation(row, column);
                    location.addAnimals(animalType, animals);
                    amount = amount - animalsAmount;
                }
            }
        }
        System.out.println("Iceland successfully filled with animals");
    }

    public void startLife(int days) {
        int day = 0;
        while (day++ < days) {
            liveOneDay();
        }
    }

    private void liveOneDay() {
        // TODO grow plant
        walkManager.move();
        foodManager.haveDinner();
        // TODO die
        // TODO born
        showIceland();
    }

    public void showIceland() {
        System.out.println("Total number of animals: " + iceland.getTotalAnimals());
        for (int i = 0; i < iceland.getLength(); i++) {
            for (int j = 0; j < iceland.getWidth(); j++) {
                Location location = iceland.getLocation(i, j);
                AnimalWorld animalWorld = location.getAnimalWorld();
                AnimalPopulation animalPopulation = animalWorld.getFirstPopulation();
                if (animalPopulation != null) {
                    String emoji = animalAttributeProvider.getEmoji(animalPopulation.getAnimalType());
                    System.out.printf("%12s (%d/%d)",
                            animalPopulation.getAnimalType(),
                            animalPopulation.getAnimals().size(),
                            animalWorld.getTotal());
                } else {
                    System.out.printf("%12s", "EMPTY");
                }
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private List<Animal> createAnimals(AnimalType animalType, int animalsCount) {
        AnimalAttributes animalAttributes = animalAttributeProvider.getAttributes(animalType);
        List<Animal> animals = new ArrayList<>();
        for (int i = 0; i < animalsCount; i++) {
            Animal animal = AnimalFactory.createAnimal(animalType);
            animal.setMaxSaturation(animalAttributes.getMaxSaturation());
            animal.setSpeed(animalAttributes.getSpeed());
            animal.setWeight(animalAttributes.getWeight());
            animals.add(animal);
        }
        return animals;
    }

}
