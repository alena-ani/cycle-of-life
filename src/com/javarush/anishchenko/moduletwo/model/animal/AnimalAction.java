package com.javarush.anishchenko.moduletwo.model.animal;

public interface AnimalAction {

    void eat(Animal animalToEat);

    void moves();

    void breeds();

    void dies();
}
