import java.util.concurrent.ThreadLocalRandom;

public class Wolf extends Carnivore {
    public Wolf() {
        super("Волк", 50, 30, 3, 8);
    }

    @Override
    public Direction chooseDirection() {
        // Волки могут предпочитать двигаться к местам с большим количеством добычи
        return Direction.values()[ThreadLocalRandom.current().nextInt(Direction.values().length)];
    }

    @Override
    public void reproduce(Location location) {
        if (location.getAnimals().stream()
                .filter(a -> a instanceof Wolf && a != this)
                .count() > 0) {
            if (ThreadLocalRandom.current().nextDouble() < 0.3) { // 30% шанс размножения
                Wolf offspring = new Wolf();
                location.addAnimal(offspring);
                offspring.setLocation(location);
            }
        }
    }
}
