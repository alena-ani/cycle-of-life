package com.javarush.anishchenko.moduletwo.factory;

import com.javarush.anishchenko.moduletwo.model.plant.Herb;
import com.javarush.anishchenko.moduletwo.model.plant.Plant;
import com.javarush.anishchenko.moduletwo.model.plant.PlantType;

import java.util.ArrayList;
import java.util.List;

public final class PlantFactory {

    public static List<Plant> createPlants(PlantType plantType, int plantsCount) {
        List<Plant> plants = new ArrayList<>();
        for (int i = 0; i < plantsCount; i++) {
            plants.add(createPlant(plantType));
        }
        return plants;
    }

    public static Plant createPlant(PlantType plantType) {
        if (PlantType.HERB.equals(plantType)) {
            return new Herb();
        }

        throw new RuntimeException("Unknown plant type: " + plantType);
    }

    private PlantFactory() {
        throw new UnsupportedOperationException();
    }
}
