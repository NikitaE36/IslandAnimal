package main.island.simulation;

import java.util.HashMap;
import java.util.Map;

public class PredationMatrix {
    private static final Map<String, Map<String, Integer>> matrix = new HashMap<>();

    static {
        // Инициализация матрицы вероятностей
        matrix.put("Волк", Map.of(
                "Лошадь", 10, "Олень", 15, "Кролик", 60,
                "Мышь", 80, "Коза", 60, "Овца", 70,
                "Кабан", 15, "Буйвол", 10, "Утка", 40
        ));
        matrix.put("Удав", Map.of("Кролик", 20, "Мышь", 40, "Утка", 10));
        matrix.put("Лиса", Map.of(
                "Кролик", 70, "Мышь", 90, "Утка", 60, "Гусеница", 40
        ));
        matrix.put("Медведь", Map.of(
                "Лошадь", 40, "Олень", 80, "Кролик", 80,
                "Мышь", 90, "Коза", 70, "Овца", 70,
                "Кабан", 50, "Буйвол", 20, "Утка", 10
        ));
        matrix.put("Орёл", Map.of(
                "Кролик", 90, "Мышь", 90, "Утка", 80
        ));
    }

    public static int getProbability(String predator, String prey) {
        Map<String, Integer> predatorMap = matrix.get(predator);
        if (predatorMap == null) return 0;
        return predatorMap.getOrDefault(prey, 0);
    }
}
