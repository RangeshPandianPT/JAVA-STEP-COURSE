package com.virtualpet.core;

import java.util.*;

public class VirtualPet {
    private final String petId;
    private final PetSpecies species;
    private final long birthTimestamp;

    private String petName;
    private int age;
    private int happiness;
    private int health;

    protected static final String[] DEFAULT_EVOLUTION_STAGES =
            {"Egg", "Baby", "Teen", "Adult", "Elder"};
    static final int MAX_HAPPINESS = 100;
    static final int MAX_HEALTH = 100;
    public static final String PET_SYSTEM_VERSION = "2.0";

    public VirtualPet() {
        this("RandomPet", PetSpecies.createDefaultSpecies(), 0, 50, 50);
    }

    public VirtualPet(String name) {
        this(name, PetSpecies.createDefaultSpecies(), 0, 60, 60);
    }

    public VirtualPet(String name, PetSpecies species) {
        this(name, species, 0, 70, 70);
    }

    public VirtualPet(String name, PetSpecies species, int age, int happiness, int health) {
        validateStat(happiness);
        validateStat(health);

        this.petId = generatePetId();
        this.birthTimestamp = System.currentTimeMillis();
        this.petName = name;
        this.species = species;
        this.age = age;
        this.happiness = happiness;
        this.health = health;
    }

    public String getPetId() { return petId; }
    public String getPetName() { return petName; }
    public void setPetName(String petName) { if (petName != null && !petName.isBlank()) this.petName = petName; }
    public PetSpecies getSpecies() { return species; }
    public long getBirthTimestamp() { return birthTimestamp; }
    public int getAge() { return age; }
    public void setAge(int age) { if (age >= 0) this.age = age; }
    public int getHappiness() { return happiness; }
    public void setHappiness(int happiness) { this.happiness = validateStat(happiness); }
    public int getHealth() { return health; }
    public void setHealth(int health) { this.health = validateStat(health); }

    public void feedPet(String foodType) {
        int bonus = calculateFoodBonus(foodType);
        modifyHealth(bonus);
        updateEvolutionStage();
    }

    public void playWithPet(String gameType) {
        int effect = calculateGameEffect(gameType);
        modifyHappiness(effect);
        updateEvolutionStage();
    }

    protected int calculateFoodBonus(String foodType) {
        switch (foodType.toLowerCase()) {
            case "fruit": return 10;
            case "meat": return 20;
            default: return 5;
        }
    }

    protected int calculateGameEffect(String gameType) {
        switch (gameType.toLowerCase()) {
            case "fetch": return 15;
            case "fly": return 25;
            default: return 10;
        }
    }

    private int validateStat(int value) {
        if (value < 0) return 0;
        if (value > 100) return 100;
        return value;
    }

    private String generatePetId() {
        return UUID.randomUUID().toString();
    }

    private void modifyHappiness(int delta) {
        this.happiness = validateStat(this.happiness + delta);
    }

    private void modifyHealth(int delta) {
        this.health = validateStat(this.health + delta);
    }

    private void updateEvolutionStage() {
        checkEvolution();
    }

    private void checkEvolution() {
        // logic for stage evolution can go here
    }

    String getInternalState() {
        return "PetState{name=" + petName +
               ", age=" + age +
               ", happy=" + happiness +
               ", health=" + health + "}";
    }

    @Override
    public String toString() {
        return "VirtualPet{" + petName + ", species=" + species.getSpeciesName() + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof VirtualPet)) return false;
        VirtualPet other = (VirtualPet) o;
        return this.petId.equals(other.petId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(petId);
    }
}
