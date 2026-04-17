import java.util.concurrent.ThreadLocalRandom;

public class Deer extends Herbivore {
    public Deer() {
        super("Олень", 300, 20, 4, 50);
    }

    @Override
    public Direction chooseDirection() {
        return Direction.values()[ThreadLocalRandom.current().nextInt(Direction.values().length)];
    }

    @Override
    public void reproduce(Location location) {
        long deerInCell = location.getAnimals().stream()
                .filter(a -> a instanceof Deer)
                .count();

        if (deerInCell > 1 && deerInCell < 100) {
            int offspringCount = ThreadLocalRandom.current().nextInt(1, 4); // 1–3 детёныша
            for (int i = 0; i < offspringCount; i++) {
                Deer offspring = new Deer();
                location.addAnimal(offspring);
                offspring.setLocation(location);
            }
        }
    }
}