package com.javarush.anishchenko.moduletwo;

import com.javarush.anishchenko.moduletwo.model.animal.*;

public final class AnimalFactory {

    public static Animal createAnimal(AnimalType animalType) {
        switch (animalType) {
            case WOLF: return new Wolf();
            case BOA: return new Boa();
            case FOX: return new Fox();
            case BEAR: return new Bear();
            case EAGLE: return new Eagle();
            case HORSE: return new Horse();
            case DEER: return new Deer();
            case RABBIT: return new Rabbit();
            case MOUSE:return new Mouse();
            case GOAT: return new Goat();
            case SHEEP: return new Sheep();
            case BOAR: return new Boar();
            case BUFFALO: return new Buffalo();
            case DUCK: return new Duck();
            case CATERPILLAR: return new Caterpillar();

            default:
                throw new RuntimeException("Unknown animal type:" + animalType);
        }
    }
}
