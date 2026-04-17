package main.animals.herbivore;

import main.Direction;
import main.island.simulation.Location;

import java.util.concurrent.ThreadLocalRandom;

public class Rabbit extends Herbivore {
    public Rabbit() {
        super("Кролик", 2, 150, 2, 0.45);
    }

    @Override
    public Direction chooseDirection() {
        return Direction.values()[ThreadLocalRandom.current().nextInt(Direction.values().length)];
    }

    @Override
    public void reproduce(Location location) {
        long rabbitsInCell = location.getAnimals().stream()
                .filter(a -> a instanceof Rabbit)
                .count();

        if (rabbitsInCell > 1 && rabbitsInCell < 100) {
            int offspringCount = ThreadLocalRandom.current().nextInt(1, 4); // 1–3 детёныша
            for (int i = 0; i < offspringCount; i++) {
                Rabbit offspring = new Rabbit();
                location.addAnimal(offspring);
                offspring.setLocation(location);
            }
        }
    }
}
