package com.javarush.anishchenko.moduletwo;

import com.javarush.anishchenko.moduletwo.model.Iceland;

public class Main {

    private static final int width = 10;
    private static final int length = 10;

    private static final int DAYS_ON_ICELAND = 3;

    public static void main(String[] args) {
        AttributeProvider attributeProvider = new AttributeProvider();
        EatingProbabilityProvider eatingProbabilityProvider = new EatingProbabilityProvider();
        Iceland iceland = new Iceland(length, width);
        IcelandManager icelandManager = new IcelandManager(attributeProvider, eatingProbabilityProvider, iceland);
        icelandManager.startLife(DAYS_ON_ICELAND);
    }
}
