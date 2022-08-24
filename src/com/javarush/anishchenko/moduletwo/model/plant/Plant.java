package com.javarush.anishchenko.moduletwo.model.plant;

public abstract class Plant implements PlantAction {

    @Override
    public void grow() {
        System.out.println("Plant is growing...");
    }
}
