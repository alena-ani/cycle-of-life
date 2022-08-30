package com.javarush.anishchenko.moduletwo.model;

import com.javarush.anishchenko.moduletwo.model.animal.AnimalType;

import java.util.HashMap;
import java.util.Map;

public class LocationStatisticData {

    private final Coordinate coordinate;
    private final Map<AnimalType, AnimalStatistic> statistic;

    public LocationStatisticData(Coordinate coordinate) {
        this.statistic = new HashMap<>();
        this.coordinate = coordinate;
    }

    public void addDiedAnimalStatistic(AnimalType animalType, int died) {
        AnimalStatistic animalStatistic = statistic.get(animalType);
        if (animalStatistic == null) {
            animalStatistic = new AnimalStatistic();
        }
        animalStatistic.setDied(died);
        statistic.put(animalType, animalStatistic);
    }

    public void addEatenAnimalStatistic(AnimalType animalType, int eaten){
        AnimalStatistic animalStatistic = statistic.get(animalType);
        if (animalStatistic == null) {
            animalStatistic = new AnimalStatistic();
        }
        animalStatistic.setEaten(eaten);
        statistic.put(animalType, animalStatistic);
    }

    public Map<AnimalType, AnimalStatistic> getStatistic() {
        return statistic;
    }
}
