package main.animals.herbivore;

import main.Direction;
import main.island.simulation.Location;

import java.util.concurrent.ThreadLocalRandom;

public class Buffalo extends Herbivore {
    public Buffalo() {
        super("Буйвол", 700, 10, 3, 100);
    }

    @Override
    public Direction chooseDirection() {
        return Direction.values()[ThreadLocalRandom.current().nextInt(Direction.values().length)];
    }

    @Override
    public void reproduce(Location location) {
        long buffaloInCell = location.getAnimals().stream()
                .filter(a -> a instanceof Buffalo)
                .count();

        if (buffaloInCell > 1 && buffaloInCell < 100) {
            int offspringCount = ThreadLocalRandom.current().nextInt(1, 4); // 1–3 детёныша
            for (int i = 0; i < offspringCount; i++) {
                Buffalo offspring = new Buffalo();
                location.addAnimal(offspring);
                offspring.setLocation(location);
            }
        }
    }
}
