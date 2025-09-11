package com.kingdom.structures;

import java.util.*;

public class WizardTower extends MagicalStructure {
    private final int maxSpellCapacity;
    private List<String> knownSpells;
    private String currentWizard;

    public WizardTower(String location) {
        this(location, 10, new ArrayList<>(), "No Wizard");
    }

    public WizardTower(String location, int capacity) {
        this(location, capacity, new ArrayList<>(List.of("Fireball", "Shield")), "Apprentice");
    }

    public WizardTower(String location, int capacity, List<String> spells, String wizard) {
        super("Wizard Tower", location, 300, true);
        this.maxSpellCapacity = capacity;
        this.knownSpells = new ArrayList<>(spells);
        this.currentWizard = wizard;
    }

    public int getMaxSpellCapacity() { return maxSpellCapacity; }
    public List<String> getKnownSpells() { return new ArrayList<>(knownSpells); }
    public String getCurrentWizard() { return currentWizard; }

    public void addSpell(String spell) {
        if (knownSpells.size() < maxSpellCapacity) knownSpells.add(spell);
    }

    @Override
    public String toString() {
        return super.toString() + " (Wizard: " + currentWizard + ")";
    }
}
