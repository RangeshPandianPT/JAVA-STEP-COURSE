package com.kingdom.structures;

import java.util.*;

public class MagicalStructure {
    private final String structureId;
    private final long constructionTimestamp;

    private final String structureName;
    private final String location;

    private int magicPower;
    private boolean isActive;
    private String currentMaintainer;

    static final int MIN_MAGIC_POWER = 0;
    static final int MAX_MAGIC_POWER = 1000;
    public static final String MAGIC_SYSTEM_VERSION = "3.0";

    public MagicalStructure(String name, String location) {
        this(name, location, 100, true);
    }

    public MagicalStructure(String name, String location, int power) {
        this(name, location, power, true);
    }

    public MagicalStructure(String name, String location, int power, boolean active) {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Structure name invalid");
        if (power < MIN_MAGIC_POWER || power > MAX_MAGIC_POWER)
            throw new IllegalArgumentException("Invalid power level");

        this.structureId = UUID.randomUUID().toString();
        this.constructionTimestamp = System.currentTimeMillis();
        this.structureName = name;
        this.location = location;
        this.magicPower = power;
        this.isActive = active;
        this.currentMaintainer = "Unknown";
    }

    public String getStructureId() { return structureId; }
    public String getStructureName() { return structureName; }
    public String getLocation() { return location; }
    public int getMagicPower() { return magicPower; }
    public boolean isActive() { return isActive; }
    public String getCurrentMaintainer() { return currentMaintainer; }

    public void setMagicPower(int power) {
        if (power >= MIN_MAGIC_POWER && power <= MAX_MAGIC_POWER)
            this.magicPower = power;
    }

    public void setActive(boolean active) { this.isActive = active; }
    public void setCurrentMaintainer(String maintainer) { this.currentMaintainer = maintainer; }

    @Override
    public String toString() {
        return structureName + "@" + location + " [Power=" + magicPower + "]";
    }
}
