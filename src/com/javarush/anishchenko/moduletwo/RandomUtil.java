package com.javarush.anishchenko.moduletwo;

import java.util.concurrent.ThreadLocalRandom;

public final class RandomUtil {

    public static int getRandomNumber(int min, int max) {
        // return (int) ((Math.random() * (max - min)) + min);
        if (min == max) {
            return max;
        }
        return ThreadLocalRandom.current().nextInt(min, max);
    }

    private RandomUtil() {
        throw new UnsupportedOperationException();
    }
}
