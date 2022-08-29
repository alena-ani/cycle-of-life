package com.javarush.anishchenko.moduletwo;

import com.javarush.anishchenko.moduletwo.model.Iceland;

public class ObserverManager {

    private final AttributeProvider attributeProvider;

    private final Iceland iceland;

    public ObserverManager(AttributeProvider attributeProvider, Iceland iceland) {
        this.attributeProvider = attributeProvider;
        this.iceland = iceland;
    }
}
