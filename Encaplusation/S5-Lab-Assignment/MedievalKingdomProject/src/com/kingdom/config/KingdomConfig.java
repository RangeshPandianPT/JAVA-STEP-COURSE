package com.kingdom.config;

import java.util.*;

public final class KingdomConfig {
    private final String kingdomName;
    private final int foundingYear;
    private final String[] allowedStructureTypes;
    private final Map<String, Integer> resourceLimits;

    public KingdomConfig(String kingdomName, int foundingYear, String[] allowedStructureTypes,
                         Map<String, Integer> resourceLimits) {
        if (kingdomName == null || kingdomName.isBlank())
            throw new IllegalArgumentException("Kingdom name cannot be empty");
        if (foundingYear <= 0)
            throw new IllegalArgumentException("Invalid founding year");
        if (allowedStructureTypes == null || allowedStructureTypes.length == 0)
            throw new IllegalArgumentException("Must allow at least one structure type");

        this.kingdomName = kingdomName;
        this.foundingYear = foundingYear;
        this.allowedStructureTypes = allowedStructureTypes.clone();
        this.resourceLimits = Collections.unmodifiableMap(new HashMap<>(resourceLimits));
    }

    public static KingdomConfig createDefaultKingdom() {
        return new KingdomConfig(
                "Avaloria", 1024,
                new String[]{"WizardTower", "EnchantedCastle", "MysticLibrary", "DragonLair"},
                Map.of("Gold", 10000, "Mana", 5000)
        );
    }

    public static KingdomConfig createFromTemplate(String type) {
        switch (type.toLowerCase()) {
            case "wizard":
                return new KingdomConfig("MagusRealm", 800,
                        new String[]{"WizardTower"}, Map.of("Mana", 10000));
            case "dragon":
                return new KingdomConfig("Draconia", 1200,
                        new String[]{"DragonLair"}, Map.of("Gold", 50000));
            default:
                return createDefaultKingdom();
        }
    }

    public String getKingdomName() { return kingdomName; }
    public int getFoundingYear() { return foundingYear; }
    public String[] getAllowedStructureTypes() { return allowedStructureTypes.clone(); }
    public Map<String, Integer> getResourceLimits() { return new HashMap<>(resourceLimits); }

    @Override
    public String toString() {
        return "KingdomConfig{name=" + kingdomName + ", year=" + foundingYear + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof KingdomConfig)) return false;
        KingdomConfig other = (KingdomConfig) o;
        return kingdomName.equals(other.kingdomName) && foundingYear == other.foundingYear;
    }

    @Override
    public int hashCode() {
        return Objects.hash(kingdomName, foundingYear);
    }
}
