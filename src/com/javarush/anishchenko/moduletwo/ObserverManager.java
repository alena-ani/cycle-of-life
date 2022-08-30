package com.javarush.anishchenko.moduletwo;

import com.javarush.anishchenko.moduletwo.model.Coordinate;
import com.javarush.anishchenko.moduletwo.model.LocationStatisticData;
import com.javarush.anishchenko.moduletwo.model.StatisticData;
import com.javarush.anishchenko.moduletwo.model.animal.AnimalType;

import java.util.HashMap;
import java.util.Map;

public class ObserverManager {

    private final Map<Integer, StatisticData> statistics;

    private int currentDay = 1;

    public ObserverManager() {
        this.statistics = new HashMap<>();
    }

    public void startObserve(int day) {
        StatisticData statisticData = new StatisticData(day);
        statistics.put(day, statisticData);
        this.currentDay = day;
    }

    public void addDiedAnimalsStatistic(Coordinate coordinate, AnimalType animalType, int died) {
        StatisticData statisticData = statistics.get(currentDay);
        if (statisticData == null) {
            statisticData = new StatisticData(currentDay);
        }
        LocationStatisticData locationStatisticData = new LocationStatisticData(coordinate);
        locationStatisticData.addDiedAnimalStatistic(animalType, died);
        statisticData.addStatictic(locationStatisticData);
        statistics.put(currentDay, statisticData);
    }

    public void addEatenAnimalsStatistic (Coordinate coordinate, AnimalType animalType, int eaten) {
        StatisticData statisticData = statistics.get(currentDay);
        if (statisticData == null) {
            statisticData = new StatisticData(currentDay);
        }
        LocationStatisticData locationStatisticData = new LocationStatisticData(coordinate);
        locationStatisticData.addEatenAnimalStatistic(animalType, eaten);
        statistics.put(currentDay,statisticData);
    }

    public int getCurrentDay() {
        return currentDay;
    }
}
