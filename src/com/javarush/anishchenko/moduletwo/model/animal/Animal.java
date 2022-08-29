package com.javarush.anishchenko.moduletwo.model.animal;

public abstract class Animal implements AnimalAction{

    public static final double SATURATION_PERCENT = 0.25;

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

    @Override
    public void eat(Animal animalToEat) {
        // System.out.println(this.getClass().getSimpleName() +  " eating " + animalToEat.getWeight() + " of " + animalToEat.getClass().getSimpleName());
        saturation += animalToEat.getWeight();
        if (saturation > maxSaturation) {
            saturation = maxSaturation;
        }
        animalToEat.setBitten(true);
    }

    @Override
    public void moves() {
        System.out.println("Moving...");
    }

    @Override
    public void breeds() {
        System.out.println("Breeds...");
    }

    @Override
    public void dies() {
        System.out.println("Dies...");
    }

    public boolean isBitten() {
        return bitten;
    }

    public void setBitten(boolean bitten) {
        this.bitten = bitten;
    }

    public double getSaturation() {
        return saturation;
    }

    public void setSaturation(double saturation) {
        this.saturation = saturation;
    }

    public double getMaxSaturation() {
        return maxSaturation;
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