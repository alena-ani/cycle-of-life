package com.javarush.anishchenko.moduletwo.manager;

import com.javarush.anishchenko.moduletwo.model.statistic.AnimalStatistic;
import com.javarush.anishchenko.moduletwo.model.Coordinate;
import com.javarush.anishchenko.moduletwo.model.statistic.LocationStatisticData;
import com.javarush.anishchenko.moduletwo.model.statistic.StatisticData;
import com.javarush.anishchenko.moduletwo.model.statistic.StatisticMetric;
import com.javarush.anishchenko.moduletwo.model.animal.AnimalType;

import java.util.HashMap;
import java.util.Map;

public class StatisticManager {

    private final Map<Integer, StatisticData> statistics;

    private int currentDay = 1;

    public StatisticManager() {
        this.statistics = new HashMap<>();
    }

    public void startObserve(int day) {
        StatisticData statisticData = new StatisticData(day);
        statistics.put(day, statisticData);
        this.currentDay = day;
    }

    public void showStatistics () {
        System.out.println("Statistic on day " + currentDay);
        StatisticData statisticData = statistics.get(currentDay);
        for (Map.Entry<Coordinate, LocationStatisticData> locationStatisticEntry : statisticData.getLocationStatistics().entrySet()) {
            Map<AnimalType, AnimalStatistic> typeAnimalStatisticMap = locationStatisticEntry.getValue().getStatistic();
            for (Map.Entry<AnimalType, AnimalStatistic> entry : typeAnimalStatisticMap.entrySet()) {
                System.out.println("Animal type: " + entry.getKey() + " at " + locationStatisticEntry.getKey());
                AnimalStatistic animalStatistic = entry.getValue();
                System.out.println("The number of animals that ate: " + animalStatistic.getEaten());
                System.out.println("The number of animals that were born: " + animalStatistic.getBorn());
                System.out.println("The number of animals that died: " + animalStatistic.getDied());
                System.out.println();
            }
        }
    }

    public void addDiedAnimalsStatistic(Coordinate coordinate, AnimalType animalType, int died) {
        addAnimalStatistic(coordinate, animalType, StatisticMetric.DIED, died);
    }

    public void addEatenAnimalsStatistic (Coordinate coordinate, AnimalType animalType, int eaten) {
        addAnimalStatistic(coordinate, animalType, StatisticMetric.EATEN, eaten);
    }

    public void addBornAnimalStatistic(Coordinate coordinate, AnimalType animalType, int bornCount) {
        addAnimalStatistic(coordinate, animalType, StatisticMetric.BORN, bornCount);
    }

    private void addAnimalStatistic(Coordinate coordinate, AnimalType animalType, StatisticMetric statisticMetric, int metricValue) {
        addStatisticMetricValue(coordinate, animalType, statisticMetric, metricValue);
    }

    private void addStatisticMetricValue(Coordinate coordinate, AnimalType animalType, StatisticMetric metric, int metricValue) {
        StatisticData statisticData = statistics.get(currentDay);
        if (statisticData == null) {
            statisticData = new StatisticData(currentDay);
        }
        LocationStatisticData locationStatisticData = statisticData.getLocationStatistic(coordinate);
        if (locationStatisticData == null) {
            locationStatisticData = new LocationStatisticData(coordinate);
        }
        switch (metric) {
                case BORN:
                    locationStatisticData.addBornAnimalStatistic(animalType, metricValue);
                    break;
                case DIED:
                    locationStatisticData.addDiedAnimalStatistic(animalType, metricValue);
                    break;
                case EATEN:
                    locationStatisticData.addEatenAnimalStatistic(animalType, metricValue);
                    break;
                default:
                    throw new RuntimeException("Unknown metric value: " + metric);
            }
        statisticData.addStatistic(coordinate, locationStatisticData);
        statistics.put(currentDay, statisticData);
    }

}
