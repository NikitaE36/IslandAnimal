package main.island.create;

import java.util.HashMap;
import java.util.Map;

public class SimulationParameters {
    // Параметры острова
    private int width = 20;
    private int height = 100;

    // Временные параметры (в миллисекундах)
    private long tickDuration = 500; // Длительность одного такта симуляции
    private long plantGrowthInterval = 5000; // Интервал роста растений
    private long statisticsInterval = 10000; // Интервал вывода статистики

    // Начальное количество животных
    private int initialWolfCount = 10;
    private int initialBoaCount = 5;
    private int initialFoxCount = 15;
    private int initialBearCount = 2;
    private int initialEagleCount = 8;

    private int initialHorseCount = 25;
    private int initialDeerCount = 30;
    private int initialRabbitCount = 100;
    private int initialMouseCount = 500;
    private int initialGoatCount = 70;
    private int initialSheepCount = 60;
    private int initialWildBoarCount = 40;
    private int initialBuffaloCount = 15;
    private int initialDuckCount = 80;
    private int initialCaterpillarCount = 1000;

    // Параметры растений
    private int initialPlantsPerCell = 50;
    private static int maxPlantsPerCell = 200;

    // Ограничения на количество животных в локации
    private static int maxAnimalsPerCell = 300;

    // Условие остановки симуляции
    private boolean stopWhenAllAnimalsDie = true;

    // Количество потомства
    private Map<String, Integer> offspringCount = new HashMap<>();

    {
        offspringCount.put("Волк", 3);
        offspringCount.put("Удав", 2);
        offspringCount.put("Лиса", 4);
        offspringCount.put("Медведь", 2);
        offspringCount.put("Орёл", 1);
        offspringCount.put("Лошадь", 1);
        offspringCount.put("Олень", 1);
        offspringCount.put("Кролик", 5);
        offspringCount.put("Мышь", 6);
        offspringCount.put("Коза", 2);
        offspringCount.put("Овца", 2);
        offspringCount.put("Кабан", 4);
        offspringCount.put("Буйвол", 1);
        offspringCount.put("Утка", 3);
        offspringCount.put("Гусеница", 10);
    }

    // Геттеры и сеттеры
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public long getTickDuration() { return tickDuration; }
    public long getPlantGrowthInterval() { return plantGrowthInterval; }
    public long getStatisticsInterval() { return statisticsInterval; }

    public int getInitialWolfCount() { return initialWolfCount; }
    public int getInitialBoaCount() { return initialBoaCount; }
    public int getInitialFoxCount() { return initialFoxCount; }
    public int getInitialBearCount() { return initialBearCount; }
    public int getInitialEagleCount() { return initialEagleCount; }

    public int getInitialHorseCount() { return initialHorseCount; }
    public int getInitialDeerCount() { return initialDeerCount; }
    public int getInitialRabbitCount() { return initialRabbitCount; }
    public int getInitialMouseCount() { return initialMouseCount; }
    public int getInitialGoatCount() { return initialGoatCount; }
    public int getInitialSheepCount() { return initialSheepCount; }
    public int getInitialWildBoarCount() { return initialWildBoarCount; }
    public int getInitialBuffaloCount() { return initialBuffaloCount; }
    public int getInitialDuckCount() { return initialDuckCount; }
    public int getInitialCaterpillarCount() { return initialCaterpillarCount; }

    public int getInitialPlantsPerCell() { return initialPlantsPerCell; }
    public static int getMaxPlantsPerCell() { return maxPlantsPerCell; }
    public static int getMaxAnimalsPerCell() { return maxAnimalsPerCell; }
    public boolean isStopWhenAllAnimalsDie() { return stopWhenAllAnimalsDie; }

    public int getOffspringCount(String species) {
        return offspringCount.getOrDefault(species, 1);
    }

    // Текущий такт симуляции
    private int currentTick = 0;

    public int getCurrentTick() { return currentTick; }
    public void incrementTick() { currentTick++; }
}
