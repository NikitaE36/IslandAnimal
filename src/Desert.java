import java.util.concurrent.ThreadLocalRandom;

public class Desert extends Carnivore {
    public Desert() {
        super("Орел", 6, 20, 3, 1);
    }

    @Override
    public Direction chooseDirection() {
        // Орлы могут предпочитать двигаться к местам с большим количеством добычи
        return Direction.values()[ThreadLocalRandom.current().nextInt(Direction.values().length)];
    }

    @Override
    public void reproduce(Location location) {
        if (location.getAnimals().stream()
                .filter(a -> a instanceof Desert && a != this)
                .count() > 0) {
            if (ThreadLocalRandom.current().nextDouble() < 0.3) { // 30% шанс размножения
                Desert offspring = new Desert();
                location.addAnimal(offspring);
                offspring.setLocation(location);
            }
        }
    }
}
