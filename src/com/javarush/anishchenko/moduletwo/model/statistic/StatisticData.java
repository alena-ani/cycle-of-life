package com.javarush.anishchenko.moduletwo.model.statistic;

import com.javarush.anishchenko.moduletwo.model.Coordinate;
import com.javarush.anishchenko.moduletwo.model.statistic.LocationStatisticData;

import java.util.HashMap;
import java.util.Map;

public class StatisticData {

    private int day;
    private Map<Coordinate, LocationStatisticData> locationStatistics;

    public StatisticData(int day) {
        this.day = day;
        this.locationStatistics = new HashMap<>();
    }

    public LocationStatisticData getLocationStatistic(Coordinate coordinate) {
        return locationStatistics.get(coordinate);
    }

    public void addStatistic(Coordinate coordinate, LocationStatisticData locationStatisticData) {
        locationStatistics.put(coordinate, locationStatisticData);
    }

    public Map<Coordinate, LocationStatisticData> getLocationStatistics() {
        return locationStatistics;
    }

    public int getDay() {
        return day;
    }
}
