package com.javarush.anishchenko.moduletwo.model;

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

    public Location getLocation(int row, int column) {
        return locations[row][column];
    }

    private void initializeLocations() {
        for (int i = 0; i < this.length; i++) {
            for (int j = 0; j < this.width; j++) {
                this.locations[i][j] = new Location(i, j);
            }
        }
    }
}
