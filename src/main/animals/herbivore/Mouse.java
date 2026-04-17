package main.animals.herbivore;

import main.Direction;
import main.island.simulation.Location;

import java.util.concurrent.ThreadLocalRandom;

public class Mouse extends Herbivore {
    public Mouse() {
        super("Кролик", 0.05, 500, 1, 0.01);
    }

    @Override
    public Direction chooseDirection() {
        return Direction.values()[ThreadLocalRandom.current().nextInt(Direction.values().length)];
    }

    @Override
    public void reproduce(Location location) {
        long mousesInCell = location.getAnimals().stream()
                .filter(a -> a instanceof Mouse)
                .count();

        if (mousesInCell > 1 && mousesInCell < 100) {
            int offspringCount = ThreadLocalRandom.current().nextInt(1, 4); // 1–3 детёныша
            for (int i = 0; i < offspringCount; i++) {
                Mouse offspring = new Mouse();
                location.addAnimal(offspring);
                offspring.setLocation(location);
            }
        }
    }
}

