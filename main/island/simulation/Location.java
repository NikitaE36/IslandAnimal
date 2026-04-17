package main.island.simulation;

import main.animals.Animal;
import main.island.create.SimulationParameters;

import java.util.*;

public class Location {
    private final int x, y;
    private List<Animal> animals = new ArrayList<>();
    private int plants = 0;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Добавляет животное, если не превышен лимит.
     */
    public synchronized void addAnimal(Animal animal) {
        if (animal == null) {
            throw new IllegalArgumentException("Animal cannot be null");
        }
        if (animals.size() < getMaxAnimalsForLocation()) {
            animals.add(animal);
        }
    }

    /**
     * Удаляет животное из локации.
     */
    public synchronized void removeAnimal(Animal animal) {
        if (animal != null) {
            animals.remove(animal);
        }
    }

    /**
     * Возвращает неизменяемую копию списка животных для потокобезопасности.
     */
    public synchronized List<Animal> getAnimals() {
        return Collections.unmodifiableList(animals);
    }

    /**
     * Получает текущее количество растений.
     */
    public synchronized int getPlants() {
        return plants;
    }

    /**
     * Добавляет растения с учётом максимального лимита.
     * @param amount количество добавляемых растений (должно быть >= 0)
     */
    public synchronized void addPlants(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Сумма не может быть отрицательной.");
        }
        plants = Math.min(plants + amount, SimulationParameters.getMaxPlantsPerCell());
    }

    /**
     * Удаляет растения (не допускает отрицательных значений).
     * @param amount количество удаляемых растений (должно быть >= 0)
     */
    public synchronized void removePlants(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Сумма не может быть отрицательной.");
        }
        plants = Math.max(0, plants - amount);
    }

    /**
     * Проверяет, можно ли добавить ещё одно животное.
     */
    public synchronized boolean canAddAnimal() {
        return animals.size() < getMaxAnimalsForLocation();
    }

    /**
     * Получает максимальное количество животных для локации.
     */
    private int getMaxAnimalsForLocation() {
        return SimulationParameters.getMaxAnimalsPerCell();
    }

    /**
     * Устанавливает точное количество растений (с учётом ограничений).
     */
    public synchronized void setPlants(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Сумма не может быть отрицательной.");
        }
        plants = Math.min(amount, SimulationParameters.getMaxPlantsPerCell());
    }

    public int getX() { return x; }
    public int getY() { return y; }
}
