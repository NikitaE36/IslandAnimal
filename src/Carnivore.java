import java.util.List;
import java.util.stream.Collectors;

//хищники
public abstract class Carnivore extends Animal {
    protected Carnivore(String name, double weight, int maxPerCell, int speed, double foodNeeded) {
        super(name, weight, maxPerCell, speed, foodNeeded);
    }

    @Override
    public void eat(Location location) {
        if (!isAlive) return;

        List<Animal> possiblePrey = location.getAnimals().stream()
                .filter(a -> a != this && a.isAlive())
                .collect(Collectors.toList());

        for (Animal prey : possiblePrey) {
            int probability = PredationMatrix.getProbability(this.getName(), prey.getName());
            if (probability > 0 && random.nextInt(100) < probability) {
                prey.die();
                System.out.printf("%s съел %s в локации (%d,%d)\n",
                        this.getName(), prey.getName(),
                        location.getX(), location.getY());
                break; // Хищник съедает только одну жертву за ход
            }
        }
    }
}
