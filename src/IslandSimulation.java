import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.ThreadLocalRandom;

class IslandSimulation {
    private Island island;
    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(3);
    private ExecutorService animalPool = Executors.newFixedThreadPool(10);
    private SimulationParameters params;
    private Random random = new Random(); // Объявляем random

    public IslandSimulation(SimulationParameters params) {
        this.params = params;
        this.island = new Island(params.getWidth(), params.getHeight());
        initializeAnimals();
        initializePlants();
    }

    private void initializeAnimals() {
        // Размещение животных на острове согласно параметрам
        for (int i = 0; i < params.getInitialWolfCount(); i++) {
            Location loc = getRandomLocation();
            Wolf wolf = new Wolf();
            loc.addAnimal(wolf);
            wolf.setLocation(loc);
        }
        for (int i = 0; i < params.getInitialBoaCount(); i++) {
            Location loc = getRandomLocation();
            Boa boa = new Boa();
            loc.addAnimal(boa);
            boa.setLocation(loc);
        }
        for (int i = 0; i < params.getInitialBoaCount(); i++) {
            Location loc = getRandomLocation();
            Fox fox = new Fox();
            loc.addAnimal(fox);
            fox.setLocation(loc);
        }
        for (int i = 0; i < params.getInitialBoaCount(); i++) {
            Location loc = getRandomLocation();
            Bear bear = new Bear();
            loc.addAnimal(bear);
            bear.setLocation(loc);
        }
        for (int i = 0; i < params.getInitialBoaCount(); i++) {
            Location loc = getRandomLocation();
            Desert desert = new Desert();
            loc.addAnimal(desert);
            desert.setLocation(loc);
        }
        for (int i = 0; i < params.getInitialWolfCount(); i++) {
            Location loc = getRandomLocation();
            Caterpillar caterpillar = new Caterpillar();
            loc.addAnimal(caterpillar);
            caterpillar.setLocation(loc);
        }
        for (int i = 0; i < params.getInitialWolfCount(); i++) {
            Location loc = getRandomLocation();
            Horse horse = new Horse();
            loc.addAnimal(horse);
            horse.setLocation(loc);
        }
        for (int i = 0; i < params.getInitialBoaCount(); i++) {
            Location loc = getRandomLocation();
            Deer deer = new Deer();
            loc.addAnimal(deer);
            deer.setLocation(loc);
        }
        for (int i = 0; i < params.getInitialBoaCount(); i++) {
            Location loc = getRandomLocation();
            Goat goat = new Goat();
            loc.addAnimal(goat);
            goat.setLocation(loc);
        }
        for (int i = 0; i < params.getInitialBoaCount(); i++) {
            Location loc = getRandomLocation();
            Sheep sheep = new Sheep();
            loc.addAnimal(sheep);
            sheep.setLocation(loc);
        }
        for (int i = 0; i < params.getInitialWolfCount(); i++) {
            Location loc = getRandomLocation();
            Mouse mouse = new Mouse();
            loc.addAnimal(mouse);
            mouse.setLocation(loc);
        }
        for (int i = 0; i < params.getInitialWolfCount(); i++) {
            Location loc = getRandomLocation();
            Rabbit rabbit = new Rabbit();
            loc.addAnimal(rabbit);
            rabbit.setLocation(loc);
        }
        for (int i = 0; i < params.getInitialWolfCount(); i++) {
            Location loc = getRandomLocation();
            WildBoar wildBoar = new WildBoar();
            loc.addAnimal(wildBoar);
            wildBoar.setLocation(loc);
        }
        for (int i = 0; i < params.getInitialBoaCount(); i++) {
            Location loc = getRandomLocation();
            Buffalo buffalo = new Buffalo();
            loc.addAnimal(buffalo);
            buffalo.setLocation(loc);
        }
        for (int i = 0; i < params.getInitialWolfCount(); i++) {
            Location loc = getRandomLocation();
            Duck duck = new Duck();
            loc.addAnimal(duck);
            duck.setLocation(loc);
        }
    }

    private void initializePlants() {
        // Начальное распределение растений
        for (int x = 0; x < params.getWidth(); x++) {
            for (int y = 0; y < params.getHeight(); y++) {
                Location loc = island.getLocation(x, y);
                loc.addPlants(params.getInitialPlantsPerCell());
            }
        }
    }

    private Location getRandomLocation() {
        int x = ThreadLocalRandom.current().nextInt(params.getWidth());
        int y = ThreadLocalRandom.current().nextInt(params.getHeight());
        return island.getLocation(x, y);
    }

    public void startSimulation() {
        // Задание роста растений
        scheduler.scheduleAtFixedRate(this::growPlants, 0, params.getPlantGrowthInterval(), TimeUnit.MILLISECONDS);

        // Задание жизненного цикла животных
        scheduler.scheduleAtFixedRate(this::processAnimalCycle, 0, params.getTickDuration(), TimeUnit.MILLISECONDS);

        // Задание вывода статистики
        scheduler.scheduleAtFixedRate(this::printStatistics, 0, params.getStatisticsInterval(), TimeUnit.MILLISECONDS);
    }

    private void growPlants() {
        for (int x = 0; x < params.getWidth(); x++) {
            for (int y = 0; y < params.getHeight(); y++) {
                Location loc = island.getLocation(x, y);
                if (loc.getPlants() < params.getMaxPlantsPerCell()) {
                    loc.addPlants(1);
                }
            }
        }
    }

    private void processAnimalCycle() {
        processHunger();
        List<Future<?>> futures = new ArrayList<>();

        for (int x = 0; x < params.getWidth(); x++) {
            for (int y = 0; y < params.getHeight(); y++) {
                Location loc = island.getLocation(x, y);
                futures.add(animalPool.submit(() -> processLocationAnimals(loc)));
            }
        }

        // Ждём завершения всех задач
        futures.forEach(f -> {
            try {
                f.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void processHunger() {
        for (int x = 0; x < params.getWidth(); x++) {
            for (int y = 0; y < params.getHeight(); y++) {
                Location loc = island.getLocation(x, y);
                for (Animal animal : loc.getAnimals()) {
                    animal.updateHunger();
                }
            }
        }
    }

    private void processLocationAnimals(Location location) {
        synchronized (location) {
            List<Animal> currentAnimals = new ArrayList<>(location.getAnimals());
            for (Animal animal : currentAnimals) {
                if (animal.isAlive()) {
                    animal.eat(location);
                    animal.reproduce(location);
                    if (random.nextDouble() < 0.7) { // 70% вероятность движения
                        animal.move(island);
                    }
                }
            }
        }
    }

    private void printStatistics() {
        System.out.println("\n=== Статистика симуляции (такт " + params.getCurrentTick() + ") ===");

        Map<String, Integer> speciesCount = new HashMap<>();
        int totalAnimals = 0, totalPlants = 0;

        for (int x = 0; x < params.getWidth(); x++) {
            for (int y = 0; y < params.getHeight(); y++) {
                Location loc = island.getLocation(x, y);
                totalPlants += loc.getPlants();

                for (Animal animal : loc.getAnimals()) {
                    if (animal.isAlive()) {
                        totalAnimals++;
                        String species = animal.getName();
                        speciesCount.put(species, speciesCount.getOrDefault(species, 0) + 1);
                    }
                }
            }
        }

        System.out.printf("Всего животных: %d\n", totalAnimals);
        System.out.printf("Всего растений: %d\n", totalPlants);

        speciesCount.forEach((species, count) ->
                System.out.printf("%s: %d особей\n", species, count));

        params.incrementTick();
    }

    public void stopSimulation() {
        scheduler.shutdown();
        animalPool.shutdown();
    }
}
