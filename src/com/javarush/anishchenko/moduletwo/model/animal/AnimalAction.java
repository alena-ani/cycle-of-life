package com.javarush.anishchenko.moduletwo.model.animal;

import com.javarush.anishchenko.moduletwo.model.plant.Plant;

public interface AnimalAction {

    void eat(Animal animalToEat);

    void eat(Plant plant);
}
