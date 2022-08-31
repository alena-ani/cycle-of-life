package com.javarush.anishchenko.moduletwo.model.animal;

import com.javarush.anishchenko.moduletwo.model.plant.Plant;

public abstract class Animal implements AnimalAction {

    public static final double SATURATION_PERCENT = 0.25;

    public static final double HUNGRY_PERCENT = 0.10;

    protected boolean bitten = false;

    protected double saturation = 0.0d;

    protected double maxSaturation = 0.0d;

    protected double weight = 0.0d;

    protected int speed = 0;

    public boolean wantEat() {
        return saturation < maxSaturation;
    }

    public boolean hasNoStrength() {
        return saturation < maxSaturation * SATURATION_PERCENT;
    }

    public void starvation() {
        double newSaturation = this.saturation - saturation * HUNGRY_PERCENT;
        if (newSaturation > 0) {
            this.saturation = newSaturation;
        } else {
            this.saturation = 0;
        }
    }

    @Override
    public void eat(Animal animalToEat) {
        saturation += animalToEat.getWeight();
        if (saturation > maxSaturation) {
            saturation = maxSaturation;
        }
        animalToEat.setBitten(true);
    }

    @Override
    public void eat(Plant plant) {
        saturation += plant.getWeight();
        if (saturation > maxSaturation) {
            saturation = maxSaturation;
        }
    }

    public boolean isBitten() {
        return bitten;
    }

    public void setBitten(boolean bitten) {
        this.bitten = bitten;
    }

    public void setMaxSaturation(double maxSaturation) {
        this.maxSaturation = maxSaturation;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}