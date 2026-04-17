import java.util.List;
import java.util.stream.Collectors;

public class WildBoar extends Herbivore {
    public WildBoar() {
        super("Кабан", 400, 50, 2, 50);
    }

    @Override
    public Direction chooseDirection() {
        // Кабаны могут искать локации с большим количеством пищи
        return Direction.values()[random.nextInt(Direction.values().length)];
    }

    @Override
    public void reproduce(Location location) {
        long boarsInCell = location.getAnimals().stream()
                .filter(a -> a instanceof WildBoar)
                .count();
        if (boarsInCell > 1 && boarsInCell < 25) {
            int offspringCount = 20;
            for (int i = 0; i < offspringCount; i++) {
                WildBoar offspring = new WildBoar();
                location.addAnimal(offspring);
                offspring.setLocation(location);
            }
        }
    }

    @Override
    public void eat(Location location) {
        if (!isAlive) return;

        // Кабан может есть мышей и гусениц
        List<Animal> prey = location.getAnimals().stream()
                .filter(a -> (a instanceof Mouse || a instanceof Caterpillar) && a.isAlive())
                .collect(Collectors.toList());
        if (!prey.isEmpty()) {
            Animal target = prey.get(0);
            if (random.nextInt(100) < 50) { // 50% шанс съесть мышь
                target.die();
                System.out.printf("Кабан съел %s в локации (%d,%d)\n",
                        target.getName(), location.getX(), location.getY());
                return;
            }
        }
        super.eat(location); // Обычная растительная пища
    }
}
