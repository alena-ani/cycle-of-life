package com.javarush.anishchenko.moduletwo.model.animal;

public class Bear extends CarnivoreAnimal {

    @Override
    public void dies() {
        System.out.println("Bear dies");
    }

}
