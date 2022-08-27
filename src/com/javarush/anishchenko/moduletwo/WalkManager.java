package com.javarush.anishchenko.moduletwo;

import com.javarush.anishchenko.moduletwo.model.*;
import com.javarush.anishchenko.moduletwo.model.animal.Animal;
import com.javarush.anishchenko.moduletwo.model.animal.AnimalPopulation;
import com.javarush.anishchenko.moduletwo.model.animal.AnimalType;

import java.util.Iterator;


public class WalkManager {

    private final AnimalAttributeProvider animalAttributeProvider;

    private final Iceland iceland;

    public WalkManager(AnimalAttributeProvider animalAttributeProvider, Iceland iceland) {
        this.animalAttributeProvider = animalAttributeProvider;
        this.iceland = iceland;
    }

    public void move() {
        for (int i = 0; i < iceland.getLength(); i++) {
            for (int j = 0; j < iceland.getWidth(); j++) {
                Location location = iceland.getLocation(i, j);
                AnimalWorld animalWorld = location.getAnimalWorld();
                Iterator<AnimalPopulation> animalPopulationIterator = animalWorld.getAnimalPopulations().iterator();
                while (animalPopulationIterator.hasNext()) {
                    AnimalPopulation animalPopulation = animalPopulationIterator.next();
                    AnimalType animalType = animalPopulation.getAnimalType();
                    int step = animalAttributeProvider.getSpeed(animalType);
                    int maxAmountOfAnimals = animalAttributeProvider.getMaxAmountOfAnimals(animalType);
                    Iterator<Animal> animalIterator = animalPopulation.getAnimals().iterator();
                    while (animalIterator.hasNext()) {
                        Animal animal = animalIterator.next();
                        Direction direction = generateDirection();
                        int newRow = 0;
                        int newColumn = 0;
                        switch (direction) {
                            case SOUTH:
                                if (i + step < 0) {
                                    newRow = i + step;
                                }
                                break;
                            case NORTH:
                                if (i - step >= 0) {
                                    newRow = i - step;
                                }
                                break;
                            case WEST:
                                if (j - step >= 0) {
                                    newColumn = j - step;
                                }
                                break;
                            case EAST:
                                if (j + step < 0) {
                                    newColumn = j + step;
                                }
                                break;
                        }

                        if (i == newRow && j == newColumn) {
                            // выпали те же координаты, поэтому животное останется на месте
                            continue;
                        }

                        Location newLocation = iceland.getLocation(newRow, newColumn);
                        AnimalWorld locationAnimalWorld = newLocation.getAnimalWorld();
                        AnimalPopulation population = locationAnimalWorld.getAnimalPopulation(animalType);
                        if (population != null) {
                            if (population.getAnimals().size() < maxAmountOfAnimals) {
                                population.addAnimal(animal);
                            } else {
                                continue;
                            }
                        } else {
                            locationAnimalWorld.getAnimalPopulations().add(new AnimalPopulation(animalType, animal));
                        }
                        animalIterator.remove();
                    }
                    if (animalPopulation.getAnimals().size() == 0) {
                        animalPopulationIterator.remove();
                    }
                }
            }
        }
    }

    private Direction generateDirection() {
        int index = RandomUtil.getRandomNumber(0, Direction.DIRECTIONS.length);
        return Direction.DIRECTIONS[index];
    }

}
