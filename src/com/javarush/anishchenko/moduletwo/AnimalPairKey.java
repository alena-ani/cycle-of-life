package com.javarush.anishchenko.moduletwo;

import com.javarush.anishchenko.moduletwo.model.animal.AnimalType;

public class AnimalPairKey {

   AnimalType herbiovore;

   AnimalType carnivire;

    public AnimalPairKey(AnimalType herbiovore, AnimalType carnivire) {
        this.herbiovore = herbiovore;
        this.carnivire = carnivire;
    }

    public AnimalType getCarnivire() {
        return carnivire;
    }

    public AnimalType getHerbiovore() {
        return herbiovore;
    }
}
