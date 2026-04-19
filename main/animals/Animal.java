package main.animals;

import main.Direction;
import main.island.Island;
import main.island.simulation.Location;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal {
    protected String name;
    protected double weight;
    protected int maxPerCell;
    protected int speed;
    protected double foodNeeded;
    protected Location location;
    protected boolean isAlive = true;
    protected Random random = ThreadLocalRandom.current();

    // Состояние голода
    protected int daysWithoutFood = 0;
    protected final int DAYS_TO_DIE_FROM_HUNGER = 30;

    public Animal(String name, double weight, int maxPerCell, int speed, double foodNeeded) {
        this.name = name;
        this.weight = weight;
        this.maxPerCell = maxPerCell;
        this.speed = speed;
        this.foodNeeded = foodNeeded;
    }

    public abstract void eat(Location location);
    public abstract void reproduce(Location location);
    public abstract Direction chooseDirection();

    public void move(Island island) {
        if (!isAlive) return;
        Direction direction = chooseDirection();
        Location newLocation = island.getAdjacentLocation(location, direction);
        if (newLocation != null) {
            location.removeAnimal(this);
            newLocation.addAnimal(this);
            this.location = newLocation;
        }
    }

    public void updateHunger() {
        if (!isAlive) return;
        daysWithoutFood++;
        if (daysWithoutFood >= DAYS_TO_DIE_FROM_HUNGER) {
            dieFromHunger();
        }
    }


    public boolean isHungry() {
        return daysWithoutFood >= DAYS_TO_DIE_FROM_HUNGER;
    }

    public void dieFromHunger() {
        if (isHungry()) {
            die();
            System.out.printf("%s умер от голода в локации (%d,%d)\n",
                    name, location.getX(), location.getY());
        }
    }

    public String getName() { return name; }
    public Location getLocation() { return location; }
    public void setLocation(Location location) { this.location = location; }
    public boolean isAlive() { return isAlive; }
    public void die() { isAlive = false; }
}
