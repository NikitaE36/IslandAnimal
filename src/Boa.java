class Boa extends Carnivore {
    public Boa() {
        super("Удав", 15, 30, 1, 3);
    }

    @Override
    public Direction chooseDirection() {
        // Удавы предпочитают оставаться на месте или двигаться медленно
        if (random.nextDouble() < 0.7) {
            return Direction.values()[random.nextInt(Direction.values().length)];
        }
        return null; // Остаётся на месте
    }

    @Override
    public void reproduce(Location location) {
        long boasInCell = location.getAnimals().stream()
                .filter(a -> a instanceof Boa)
                .count();

        if (boasInCell > 1 && boasInCell < 25) {
            int getOffspringCount = 10;
            int offspringCount = getOffspringCount;
            for (int i = 0; i < offspringCount; i++) {
                Boa offspring = new Boa();
                location.addAnimal(offspring);
                offspring.setLocation(location);
            }
        }
    }
}
