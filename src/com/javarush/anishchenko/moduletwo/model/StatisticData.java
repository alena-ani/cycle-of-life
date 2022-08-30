package com.javarush.anishchenko.moduletwo.model;

import java.util.ArrayList;
import java.util.List;

public class StatisticData {

    private int day;
    private List<LocationStatisticData> locationStatistics;

    public StatisticData(int day) {
        this.day = day;
        this.locationStatistics = new ArrayList<>();
    }

    public void addStatictic(LocationStatisticData locationStatisticData) {
        locationStatistics.add(locationStatisticData);
    }

    public int getDay() {
        return day;
    }
}
