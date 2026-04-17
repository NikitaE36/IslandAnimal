package main.animals.carnivore;

import main.Direction;
import main.island.simulation.Location;

import java.util.concurrent.ThreadLocalRandom;

public class Fox extends Carnivore {
    public Fox() {
        super("Лиса", 8, 30, 2, 2);
    }

    @Override
    public Direction chooseDirection() {
        // Лисы могут предпочитать двигаться к местам с большим количеством добычи
        return Direction.values()[ThreadLocalRandom.current().nextInt(Direction.values().length)];
    }

    @Override
    public void reproduce(Location location) {
        if (location.getAnimals().stream()
                .filter(a -> a instanceof Fox && a != this)
                .count() > 0) {
            if (ThreadLocalRandom.current().nextDouble() < 0.3) { // 30% шанс размножения
                Fox offspring = new Fox();
                location.addAnimal(offspring);
                offspring.setLocation(location);
            }
        }
    }
}
