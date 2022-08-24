package com.javarush.anishchenko.moduletwo.model;

public class AnimalAttributes {

    // вес
    double weight;

    // мак. количество животных
    int maxAmount;

    //скорость
    int speed;

    // мак насыщение для животного
    double maxSaturation;

    String emoji;

    public String getEmoji() {
        return emoji;
    }

    public void setEmoji(String emoji) {
        this.emoji = emoji;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(int maxAmount) {
        this.maxAmount = maxAmount;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public double getMaxSaturation() {
        return maxSaturation;
    }

    public void setMaxSaturation(double maxSaturation) {
        this.maxSaturation = maxSaturation;
    }

}

