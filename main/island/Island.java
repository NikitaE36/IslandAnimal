package main.island;

import main.Direction;
import main.island.simulation.Location;

public class Island {
    private Location[][] grid;
    private int width, height;

    public Island(int width, int height) {
        this.width = width;
        this.height = height;
        grid = new Location[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                grid[i][j] = new Location(i, j);
            }
        }
    }

    public Location getLocation(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            return grid[x][y];
        }
        return null;
    }

    public Location getAdjacentLocation(Location current, Direction direction) {
        if (direction == null) return current;

        int x = current.getX();
        int y = current.getY();
        switch (direction) {
            case NORTH: y--; break;
            case SOUTH: y++; break;
            case EAST: x++; break;
            case WEST: x--; break;
        }
        return getLocation(x, y);
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }
}
