import java.util.List;
import java.util.stream.Collectors;

//травоядные
abstract class Herbivore extends Animal {
    protected Herbivore(String name, double weight, int maxPerCell, int speed, double foodNeeded) {
        super(name, weight, maxPerCell, speed, foodNeeded);
    }


    @Override
    public void eat(Location location) {
        if (!isAlive) return;

        // Специальная логика для утки
        if ("Утка".equals(this.getName())) {
            List<Animal> caterpillars = location.getAnimals().stream()
                    .filter(a -> "Гусеница".equals(a.getName()) && a.isAlive())
                    .collect(Collectors.toList());
            if (!caterpillars.isEmpty()) {
                Animal caterpillar = caterpillars.get(0);
                caterpillar.die();
                System.out.printf("Утка съела гусеницу в локации (%d,%d)\n",
                        location.getX(), location.getY());
                return;
            }
        }

        // Обычная растительная пища
        if (location.getPlants() > 0) {
            int eaten = Math.min(location.getPlants(), (int) foodNeeded);
            location.removePlants(eaten);
            System.out.printf("%s поел растений в локации (%d,%d)\n",
                    this.getName(), location.getX(), location.getY());
        }
    }
}
