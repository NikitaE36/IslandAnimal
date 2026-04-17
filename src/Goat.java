import java.util.concurrent.ThreadLocalRandom;

public class Goat extends Herbivore {
    public Goat() {
        super("Коза", 60, 140, 3, 10);
    }

    @Override
    public Direction chooseDirection() {
        return Direction.values()[ThreadLocalRandom.current().nextInt(Direction.values().length)];
    }

    @Override
    public void reproduce(Location location) {
        long goatInCell = location.getAnimals().stream()
                .filter(a -> a instanceof Goat)
                .count();

        if (goatInCell > 1 && goatInCell < 100) {
            int offspringCount = ThreadLocalRandom.current().nextInt(1, 4); // 1–3 детёныша
            for (int i = 0; i < offspringCount; i++) {
                Goat offspring = new Goat();
                location.addAnimal(offspring);
                offspring.setLocation(location);
            }
        }
    }
}