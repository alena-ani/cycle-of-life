package com.javarush.anishchenko.moduletwo.model;

import com.javarush.anishchenko.moduletwo.model.animal.AnimalType;

import java.io.FileInputStream;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class AnimalAttributeProvider {

    private static final String ATTRIBUTES_CONFIG = "/Users/alena/IdeaProjects/cycle-of-life/src/com/javarush/anishchenko/moduletwo/animals.properties";
    private static final String WEIGHT = ".weight";
    private static final String MAX_COUNT_PER_CELL = ".max.count.per.cell";
    private static final String SPEED = ".speed";
    private static final String MAX_SATURATE = ".max.saturate";
    private static final String EMOJI = ".emoji";

    private final Map<AnimalType, AnimalAttributes> animalAttributeMap = new HashMap<>();

    public AnimalAttributeProvider() {
        loadAnimalAttributes();
    }

    public AnimalAttributes getAttributes(AnimalType animal) {
        return animalAttributeMap.get(animal);
    }

    public String getEmoji(AnimalType animalType) {
        AnimalAttributes animalAttributes = animalAttributeMap.get(animalType);
        if (animalAttributes == null) {
            return "";
        }
        String emoji = animalAttributes.getEmoji();
        return emoji != null ? emoji : "";
    }

    private void loadAnimalAttributes() {
        try (FileInputStream fis = new FileInputStream(ATTRIBUTES_CONFIG)) {
            Properties properties = new Properties();
            properties.load(fis);
            for (AnimalType animalType : AnimalType.values()) {
                System.out.println("Loading attributes for " + animalType);
                AnimalAttributes animalAttributes = new AnimalAttributes();

                String animalKey = animalType.toString().toLowerCase();

                String weight = properties.getProperty(animalKey + WEIGHT);
                String maxCount = properties.getProperty(animalKey + MAX_COUNT_PER_CELL);
                String speed = properties.getProperty(animalKey + SPEED);
                String maxSaturate = properties.getProperty(animalKey + MAX_SATURATE);
                String emoji = properties.getProperty(animalKey + EMOJI);

                animalAttributes.setWeight(weight != null ? Double.parseDouble(weight) : 0.0d);
                animalAttributes.setMaxAmount(maxCount != null ? Integer.parseInt(maxCount) : Integer.MAX_VALUE);
                animalAttributes.setSpeed(speed != null ? Integer.parseInt(speed) : 0);
                animalAttributes.setMaxSaturation(maxSaturate != null ? Double.parseDouble(maxSaturate) : 0.0d);
                animalAttributes.setEmoji(emoji);

                animalAttributeMap.put(animalType, animalAttributes);

            }
        } catch (IOException e) {
            System.err.println("ERROR: Problem reading properties with animal attributes!");
        }
    }
}


