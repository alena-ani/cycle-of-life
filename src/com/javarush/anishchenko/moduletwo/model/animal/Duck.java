package com.javarush.anishchenko.moduletwo.model.animal;

import com.javarush.anishchenko.moduletwo.model.animal.Animal;

public class Duck extends Animal {
    @Override
    public void eat() {
        System.out.println("Duck eat caterpillar and Herb");
    }
}
