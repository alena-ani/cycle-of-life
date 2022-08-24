package com.javarush.anishchenko.moduletwo.model.animal;

import com.javarush.anishchenko.moduletwo.model.AnimalAttributeProvider;

public abstract class Animal implements AnimalAction{

    private AnimalAttributeProvider animalAttributeManager;

    @Override
    public void eat() {
        System.out.println("Eating...");
    }

    @Override
    public void moves() {
        System.out.println("Moving...");
    }

    @Override
    public void breeds() {
        System.out.println("Breeds...");

    }

    @Override
    public void dies() {
        System.out.println("Dies...");
    }

    public void setAnimalAttributes(AnimalAttributeProvider animalAttributeProvider) {
        this.animalAttributeManager = animalAttributeProvider;
    }
}