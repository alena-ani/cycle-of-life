package com.javarush.anishchenko.moduletwo.model.statistic;

import com.javarush.anishchenko.moduletwo.model.Coordinate;
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
        addStatisticMetricValue(animalType, StatisticMetric.DIED, died);
    }

    public void addBornAnimalStatistic(AnimalType animalType, int born) {
        addStatisticMetricValue(animalType, StatisticMetric.BORN, born);
    }

    public void addEatenAnimalStatistic(AnimalType animalType, int eaten) {
        addStatisticMetricValue(animalType, StatisticMetric.EATEN, eaten);
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public Map<AnimalType, AnimalStatistic> getStatistic() {
        return statistic;
    }

    private void addStatisticMetricValue(AnimalType animalType, StatisticMetric statisticMetric, int metricValue) {
        AnimalStatistic animalStatistic = statistic.get(animalType);
        if (animalStatistic == null) {
            animalStatistic = new AnimalStatistic();
        }
        setMetricValue(animalStatistic, statisticMetric, metricValue);
        statistic.put(animalType, animalStatistic);
    }

    private void setMetricValue(AnimalStatistic animalStatistic, StatisticMetric metric, int metricValue) {
        switch (metric) {
            case BORN:
                animalStatistic.setBorn(metricValue);
                break;
            case DIED:
                animalStatistic.setDied(metricValue);
                break;
            case EATEN:
                animalStatistic.setEaten(metricValue);
                break;
            default:
                throw new RuntimeException("Unknown metric value: " + metric);
        }
    }
}
