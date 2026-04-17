public class Caterpillar extends Herbivore {
    public Caterpillar() {
        super("Гусеница", 0.01, 1000, 0, 0.005); // Гусеницы почти не двигаются и мало едят
    }

    @Override
    public void eat(Location location) {
        if (!isAlive) return;
        if (location.getPlants() > 0) {
            int eaten = Math.min(location.getPlants(), (int) foodNeeded);
            location.removePlants(eaten);
            daysWithoutFood = 0; // Сбрасываем счётчик голода при поедании
            System.out.printf("Гусеница поела растений в локации (%d,%d)\n",
                    location.getX(), location.getY());
        } else {
            daysWithoutFood++;
        }
    }

    @Override
    public Direction chooseDirection() {
        // Гусеницы практически не передвигаются
        return random.nextDouble() < 0.1 ?
                Direction.values()[random.nextInt(Direction.values().length)] : null;
    }

    @Override
    public void reproduce(Location location) {
        int caterpillarsInCell = (int) location.getAnimals().stream()
                .filter(a -> a instanceof Caterpillar)
                .count();
        if (caterpillarsInCell < 500 && random.nextDouble() < 0.5) { // 50% шанс размножения
            int offspringCount = 20;
            for (int i = 0; i < offspringCount; i++) {
                Caterpillar offspring = new Caterpillar();
                location.addAnimal(offspring);
                offspring.setLocation(location);
            }
        }
    }
}
