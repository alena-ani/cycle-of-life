package com.javarush.anishchenko.moduletwo;

import com.javarush.anishchenko.moduletwo.model.animal.*;

public final class AnimalFactory {

    public static Animal createAnimal(AnimalType animalType) {
        switch (animalType) {
            case BOA: return new Boa();
            case FOX: return new Fox();
            case BEAR: return new Bear();
            // TODO add other animals
            default:
                return new Buffalo();
                //throw new RuntimeException("Unknown animal type:" + animalType);
        }
    }
}
