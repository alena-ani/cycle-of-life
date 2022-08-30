package com.javarush.anishchenko.moduletwo.model;

import java.util.ArrayList;
import java.util.List;

public class Iceland {

    private final int length;
    private final int width;

    private final Location[][] locations;

    public Iceland(int length, int width) {
        this.length = length;
        this.width = width;
        this.locations = new Location[length][width];
        System.out.println("Creating iceland with dimensions: " + length + "x" + width);
        initializeLocations();
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public Location getLocation(Coordinate coordinate) {
        return locations[coordinate.getRow()][coordinate.getColumn()];
    }

    public Location[][] getLocations() {
        return locations;
    }


    // TODO move to class with statistics
    public int getTotalAnimals() {
        int total = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                Coordinate coordinate = new Coordinate(i, j);
                Location location = getLocation(coordinate);
                total += location.getAnimalWorld().getTotal();
            }
        }
        return total;
    }

    private void initializeLocations() {
        for (int i = 0; i < this.length; i++) {
            for (int j = 0; j < this.width; j++) {
                this.locations[i][j] = new Location(i, j);
            }
        }
    }
}
