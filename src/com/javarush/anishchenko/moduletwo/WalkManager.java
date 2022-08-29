package com.javarush.anishchenko.moduletwo;

import com.javarush.anishchenko.moduletwo.model.*;
import com.javarush.anishchenko.moduletwo.model.animal.Animal;
import com.javarush.anishchenko.moduletwo.model.animal.AnimalPopulation;
import com.javarush.anishchenko.moduletwo.model.animal.AnimalType;

import java.util.Iterator;


public class WalkManager {

    private final AttributeProvider attributeProvider;

    private final Iceland iceland;

    public WalkManager(AttributeProvider attributeProvider, Iceland iceland) {
        this.attributeProvider = attributeProvider;
        this.iceland = iceland;
    }

    public void move() {
        System.out.println("Moving started");
        for (int i = 0; i < iceland.getLength(); i++) {
            for (int j = 0; j < iceland.getWidth(); j++) {
                Coordinate coordinate = new Coordinate(i, j);
                Location location = iceland.getLocation(coordinate);
                AnimalWorld animalWorld = location.getAnimalWorld();
                Iterator<AnimalPopulation> animalPopulationIterator = animalWorld.getAnimalPopulations().iterator();
                while (animalPopulationIterator.hasNext()) {
                    AnimalPopulation animalPopulation = animalPopulationIterator.next();
                    AnimalType animalType = animalPopulation.getAnimalType();
                    int maxAmountOfAnimals = attributeProvider.getMaxAmountOfAnimals(animalType);
                    Iterator<Animal> animalIterator = animalPopulation.getAnimals().iterator();
                    while (animalIterator.hasNext()) {
                        Animal animal = animalIterator.next();
                        Coordinate locationCoordinate = location.getCoordinate();
                        Coordinate newCoordinate = getNewCoordinate(locationCoordinate, animal.getSpeed());
                        if (locationCoordinate.equals(newCoordinate)) {
                            // выпали те же координаты, поэтому животное останется на месте
                            continue;
                        }
                        Location newLocation = iceland.getLocation(newCoordinate);
                        AnimalWorld newAnimalWorld = newLocation.getAnimalWorld();
                        AnimalPopulation population = newAnimalWorld.getAnimalPopulation(animalType);
                        if (population != null) {
                            if (population.getAnimals().size() < maxAmountOfAnimals) {
                                population.addAnimal(animal);
                            } else {
                                continue;
                            }
                        } else {
                            newAnimalWorld.getAnimalPopulations().add(new AnimalPopulation(animalType, animal));
                        }
                        animalIterator.remove();
                    }
                    if (animalPopulation.getAnimals().size() == 0) {
                        animalPopulationIterator.remove();
                    }
                }
            }
        }
        System.out.println("Moving completed");
    }

    private Coordinate getNewCoordinate(Coordinate oldCoordinate, int step){
        Direction direction = RandomUtil.getDirection();
        int oldRow = oldCoordinate.getRow();
        int oldColumn = oldCoordinate.getColumn();
        int newRow = 0;
        int newColumn = 0;
        int row = 0, column = 0;
        switch (direction) {
            case SOUTH:
                row = oldRow + step;
                if (row < 0) {
                    newRow = row;
                }
                break;
            case NORTH:
                row = oldRow - step;
                if (row >= 0) {
                    newRow = row;
                }
                break;
            case WEST:
                column = oldColumn - step;
                if (column >= 0) {
                    newColumn = column;
                }
                break;
            case EAST:
                column = oldColumn + step;
                if (column < 0) {
                    newColumn = column;
                }
                break;
        }

        return new Coordinate(newRow, newColumn);
    }

}
