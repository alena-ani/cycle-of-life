package com.javarush.anishchenko.moduletwo.model.animal;

import com.javarush.anishchenko.moduletwo.model.animal.CarnivoreAnimal;

public class Wolf extends CarnivoreAnimal {
    @Override
    public void eat() {
        System.out.println("wolf eat animal");
    }
}
