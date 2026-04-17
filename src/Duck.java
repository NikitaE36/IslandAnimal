import java.util.concurrent.ThreadLocalRandom;

public class Duck extends Herbivore{
    public Duck() {
        super("Утка", 1, 200, 4, 0.15);
    }

    @Override
    public Direction chooseDirection() {
        return Direction.values()[ThreadLocalRandom.current().nextInt(Direction.values().length)];
    }

    @Override
    public void reproduce(Location location) {
        long duckInCell = location.getAnimals().stream()
                .filter(a -> a instanceof Duck)
                .count();

        if (duckInCell > 1 && duckInCell < 100) {
            int offspringCount = ThreadLocalRandom.current().nextInt(1, 4); // 1–3 детёныша
            for (int i = 0; i < offspringCount; i++) {
                Duck offspring = new Duck();
                location.addAnimal(offspring);
                offspring.setLocation(location);
            }
        }
    }
}

