package com.javarush.anishchenko.moduletwo;

import com.javarush.anishchenko.moduletwo.model.AnimalAttributes;
import com.javarush.anishchenko.moduletwo.model.AnimalWorld;
import com.javarush.anishchenko.moduletwo.model.Coordinate;
import com.javarush.anishchenko.moduletwo.model.Iceland;
import com.javarush.anishchenko.moduletwo.model.Location;
import com.javarush.anishchenko.moduletwo.model.animal.Animal;
import com.javarush.anishchenko.moduletwo.model.animal.AnimalPopulation;
import com.javarush.anishchenko.moduletwo.model.animal.AnimalType;
import com.javarush.anishchenko.moduletwo.model.plant.Plant;
import com.javarush.anishchenko.moduletwo.model.plant.PlantType;

import java.util.List;

public class IcelandManager {

    private final AttributeProvider attributeProvider;

    private final Iceland iceland;

    private final WalkManager walkManager;

    private final FoodManager foodManager;

    private final BirthManager birthManager;

    private final CleanManager cleanManager;

    private final ObserverManager observerManager;

    public IcelandManager(AttributeProvider attributeProvider,
                          EatingProbabilityProvider eatingProbabilityProvider,
                          Iceland iceland) {

        this.observerManager = new ObserverManager();
        this.attributeProvider = attributeProvider;
        this.iceland = iceland;
        this.walkManager = new WalkManager(attributeProvider, iceland);
        this.foodManager = new FoodManager(eatingProbabilityProvider, iceland);
        this.birthManager = new BirthManager(attributeProvider, iceland);
        this.cleanManager = new CleanManager(observerManager, iceland);
    }

    public void fillIceland() {
        System.out.println("Start filling iceland with animals and plants ...");
        fillAnimals();
        fillPlants();
        System.out.println("Iceland successfully filled with animals");
    }

    public void startLife(int days) {
        fillIceland();
        showIceland();
        int day = 0;
        while (day++ < days) {
            System.out.println("=======================  DAY " + day + " =========================");
            System.out.println();
            liveOneDay(day);
            showIceland();
        }
    }

    private void liveOneDay(int day) {
        observerManager.startObserve(day);
        // TODO grow plant
        walkManager.move();
        foodManager.haveDinner();
        cleanManager.clean();
        birthManager.birth();
    }

    public void showIceland() {
        System.out.println("Total number of animals: " + iceland.getTotalAnimals());
        for (int i = 0; i < iceland.getLength(); i++) {
            for (int j = 0; j < iceland.getWidth(); j++) {
                Location location = iceland.getLocation(new Coordinate(i, j));
                AnimalWorld animalWorld = location.getAnimalWorld();
                AnimalPopulation animalPopulation = animalWorld.getFirstPopulation();
                if (animalPopulation != null) {
                    String emoji = attributeProvider.getEmoji(animalPopulation.getAnimalType());
                    System.out.printf("%5s (%4d/%4d)",
                            emoji,
                            animalPopulation.getAnimals().size(),
                            animalWorld.getTotal());
                } else {
                    System.out.printf("%4s", "EMPTY");
                }
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private void fillPlants() {
        for (int i = 0; i < iceland.getLength(); i++) {
            for (int j = 0; j < iceland.getWidth(); j++) {
                Coordinate coordinate = new Coordinate(i, j);
                Location location = iceland.getLocation(coordinate);
                int plantsAmount = RandomUtil.getNumber(0, AttributeProvider.MAX_PLANTS_AMOUNT_PER_CELL);
                List<Plant> plants = PlantFactory.createPlants(PlantType.HERB, plantsAmount);
                location.getPlantWorld().addPlants(PlantType.HERB, plants);
            }
        }
    }

    private void fillAnimals() {
        for (int i = 0; i < iceland.getLength(); i++) {
            for (int j = 0; j < iceland.getWidth(); j++) {
                Coordinate coordinate = new Coordinate(i, j);
                Location location = iceland.getLocation(coordinate);
                for (AnimalType animalType : AnimalType.values()) {
                    AnimalAttributes animalAttributes = attributeProvider.getAnimalAttributes(animalType);
                    int animalsCountOnLocation = RandomUtil.getNumber(0, animalAttributes.getMaxAmount());
                    if (animalsCountOnLocation == 0) {
                        continue;
                    }
                    List<Animal> animals = AnimalFactory.createAnimals(animalType, animalsCountOnLocation, animalAttributes);
                    location.addAnimals(animalType, animals);
                }
            }
        }
    }
}
