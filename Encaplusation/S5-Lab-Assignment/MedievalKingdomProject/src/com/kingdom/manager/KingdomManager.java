package com.kingdom.manager;

import com.kingdom.config.KingdomConfig;
import com.kingdom.structures.*;
import java.util.*;

public class KingdomManager {
    private final List<Object> structures = new ArrayList<>();
    private final KingdomConfig config;

    public KingdomManager(KingdomConfig config) {
        this.config = config;
    }

    public void addStructure(Object structure) {
        structures.add(structure);
    }

    public static boolean canStructuresInteract(Object s1, Object s2) {
        return (s1 instanceof WizardTower && s2 instanceof MysticLibrary)
                || (s1 instanceof DragonLair && s2 instanceof EnchantedCastle);
    }

    public static String performMagicBattle(Object attacker, Object defender) {
        if (attacker instanceof WizardTower && defender instanceof DragonLair) {
            return "Wizard Tower hurls spells at Dragon Lair!";
        }
        return "Battle inconclusive.";
    }

    public static int calculateKingdomPower(Object[] structures) {
        int sum = 0;
        for (Object s : structures) {
            if (s instanceof MagicalStructure) {
                sum += ((MagicalStructure) s).getMagicPower();
            }
        }
        return sum;
    }

    private String determineStructureCategory(Object structure) {
        if (structure instanceof WizardTower) return "Arcane";
        if (structure instanceof EnchantedCastle) return "Defensive";
        if (structure instanceof MysticLibrary) return "Knowledge";
        if (structure instanceof DragonLair) return "Beast";
        return "Unknown";
    }
}
