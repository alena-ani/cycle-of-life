package com.javarush.anishchenko.moduletwo;

import com.javarush.anishchenko.moduletwo.model.Iceland;

public class CleanManager {

    private final AnimalAttributeProvider animalAttributeProvider;

    private final Iceland iceland;

    public CleanManager(AnimalAttributeProvider animalAttributeProvider, Iceland iceland) {
        this.animalAttributeProvider = animalAttributeProvider;
        this.iceland = iceland;
    }
}
