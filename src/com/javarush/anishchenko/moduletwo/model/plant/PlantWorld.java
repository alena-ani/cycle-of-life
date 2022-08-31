package com.javarush.anishchenko.moduletwo.model.plant;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PlantWorld {

    private Set<PlantPopulation> plantPopulations = new HashSet<>();

    public boolean addPlants(PlantType plantType, List<Plant> plantsList) {
        PlantPopulation plantPopulation = findGroupOfPlants(plantType);
        if (plantPopulation != null) {
            plantPopulation.addPlants(plantsList);
            return true;
        }

        return plantPopulations.add(new PlantPopulation(plantType, plantsList));
    }

    public Set<PlantPopulation> getPlantPopulations() {
        return plantPopulations;
    }

    private PlantPopulation findGroupOfPlants(PlantType plantType) {
        for (PlantPopulation plantPopulation : plantPopulations) {
            if (plantType.equals(plantPopulation.getPlantType())) {
                return plantPopulation;
            }
        }
        return null;
    }
}
