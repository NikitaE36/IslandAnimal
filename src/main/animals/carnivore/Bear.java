package main.animals.carnivore;

import main.Direction;
import main.island.simulation.Location;

import java.util.concurrent.ThreadLocalRandom;

public class Bear extends Carnivore {
    public Bear() {
        super("Медведь", 500, 5, 2, 80);
    }

    @Override
    public Direction chooseDirection() {
        // Медведи могут предпочитать двигаться к местам с большим количеством добычи
        return Direction.values()[ThreadLocalRandom.current().nextInt(Direction.values().length)];
    }

    @Override
    public void reproduce(Location location) {
        if (location.getAnimals().stream()
                .filter(a -> a instanceof Bear && a != this)
                .count() > 0) {
            if (ThreadLocalRandom.current().nextDouble() < 0.3) { // 30% шанс размножения
                Bear offspring = new Bear();
                location.addAnimal(offspring);
                offspring.setLocation(location);
            }
        }
    }
}
