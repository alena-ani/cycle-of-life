package com.javarush.anishchenko.moduletwo;

import com.javarush.anishchenko.moduletwo.model.Iceland;

public class ObserverManager {

    private final AnimalAttributeProvider animalAttributeProvider;

    private final Iceland iceland;

    public ObserverManager(AnimalAttributeProvider animalAttributeProvider, Iceland iceland) {
        this.animalAttributeProvider = animalAttributeProvider;
        this.iceland = iceland;
    }
}
