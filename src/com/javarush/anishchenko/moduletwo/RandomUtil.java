package com.javarush.anishchenko.moduletwo;

import com.javarush.anishchenko.moduletwo.model.Coordinate;

import java.util.concurrent.ThreadLocalRandom;

public final class RandomUtil {

    public static Coordinate getCoordinate(int length, int width) {
        int row = RandomUtil.getNumber(0, length);
        int column = RandomUtil.getNumber(0, width);
        return new Coordinate(row, column);
    }

    public static Direction getDirection() {
        int index = RandomUtil.getNumber(0, Direction.DIRECTIONS.length);
        return Direction.DIRECTIONS[index];
    }

    public static int getNumber(int min, int max) {
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
