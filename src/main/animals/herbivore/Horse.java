package main.animals.herbivore;

import main.Direction;
import main.island.simulation.Location;

import java.util.concurrent.ThreadLocalRandom;

public class Horse extends Herbivore {
public Horse() {
    super("Лошадь", 400, 20, 4, 60);
}

    @Override
    public Direction chooseDirection() {
        return Direction.values()[ThreadLocalRandom.current().nextInt(Direction.values().length)];
    }

    @Override
    public void reproduce(Location location) {
        long horseInCell = location.getAnimals().stream()
                .filter(a -> a instanceof Horse)
                .count();

        if (horseInCell > 1 && horseInCell < 100) {
            int offspringCount = ThreadLocalRandom.current().nextInt(1, 4); // 1–3 детёныша
            for (int i = 0; i < offspringCount; i++) {
                Horse offspring = new Horse();
                location.addAnimal(offspring);
                offspring.setLocation(location);
            }
        }
    }
}
// Аналогично для Оленя, Кролика, Мыши и т. д.
