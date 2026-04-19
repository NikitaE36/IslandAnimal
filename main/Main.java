package main;

import main.island.simulation.IslandSimulation;
import main.island.create.SimulationParameters;

public class Main {
    public static void main(String[] args) {
        SimulationParameters params = new SimulationParameters();
        IslandSimulation simulation = new IslandSimulation(params);

        System.out.println("Запуск симуляции экосистемы острова...");
        System.out.printf("Размер острова: %d x %d клеток\n", params.getWidth(), params.getHeight());
        System.out.printf("Начальное количество животных: %d\n",
                params.getInitialWolfCount() + params.getInitialBoaCount() +
                        params.getInitialFoxCount() + params.getInitialBearCount() +
                        params.getInitialEagleCount() + params.getInitialHorseCount() +
                        params.getInitialDeerCount() + params.getInitialRabbitCount() +
                        params.getInitialMouseCount() + params.getInitialGoatCount() +
                        params.getInitialSheepCount() + params.getInitialWildBoarCount() +
                        params.getInitialBuffaloCount() + params.getInitialDuckCount() +
                        params.getInitialCaterpillarCount());

        simulation.startSimulation();

        // Остановка через 10 минут или при вымирании всех животных
        try {
            Thread.sleep(600000); // 10 минут
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        simulation.stopSimulation();
        System.out.println("Симуляция завершена.");
    }
}
