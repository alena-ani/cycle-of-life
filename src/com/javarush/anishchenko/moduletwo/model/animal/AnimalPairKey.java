package com.javarush.anishchenko.moduletwo.model.animal;

import java.util.Objects;

public class AnimalPairKey {

   private final AnimalType herbivore;

   private final AnimalType carnivore;

    public AnimalPairKey(AnimalType carnivore, AnimalType herbivore) {
        this.herbivore = herbivore;
        this.carnivore = carnivore;
    }

    public AnimalType getCarnivore() {
        return carnivore;
    }

    public AnimalType getHerbivore() {
        return herbivore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnimalPairKey that = (AnimalPairKey) o;
        return herbivore == that.herbivore && carnivore == that.carnivore;
    }

    @Override
    public int hashCode() {
        return Objects.hash(herbivore, carnivore);
    }
}
