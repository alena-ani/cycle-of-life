package com.javarush.anishchenko.moduletwo;

import com.javarush.anishchenko.moduletwo.model.AnimalAttributeProvider;
import com.javarush.anishchenko.moduletwo.model.Iceland;

public class Main {

    private static final int width = 10;
    private static final int length = 10;

    public static void main(String[] args) {
        AnimalAttributeProvider animalAttributeProvider = new AnimalAttributeProvider();
        Iceland iceland = new Iceland(length, width);
        IcelandManager icelandManager = new IcelandManager(animalAttributeProvider, iceland);
        System.out.println("Filling iceland...");
        icelandManager.fillIceland();
        icelandManager.showIceland();
    }



}
