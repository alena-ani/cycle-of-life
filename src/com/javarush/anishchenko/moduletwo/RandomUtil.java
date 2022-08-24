package com.javarush.anishchenko.moduletwo;

public final class RandomUtil {

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    private RandomUtil() {
        throw new UnsupportedOperationException();
    }
}
