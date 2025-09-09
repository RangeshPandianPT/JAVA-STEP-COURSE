import java.util.*;

abstract class MagicalStructure {
    protected String structureName;
    protected int magicPower;
    protected String location;
    protected boolean isActive;

    public MagicalStructure() {
        this("Unknown", 50, "Unknown", true);
    }

    public MagicalStructure(String structureName) {
        this(structureName, 70, "Unknown", true);
    }

    public MagicalStructure(String structureName, int magicPower, String location, boolean isActive) {
        this.structureName = structureName;
        this.magicPower = magicPower;
        this.location = location;
        this.isActive = isActive;
    }

    public abstract String castMagicSpell();
}

class WizardTower extends MagicalStructure {
    private int spellCapacity;
    private String[] knownSpells;

    public WizardTower() {
        this("Wizard Tower", 100, "Hilltop", true, 5, new String[]{"Fireball"});
    }

    public WizardTower(String name, int spellCapacity) {
        this(name, 120, "Hilltop", true, spellCapacity, new String[]{"Shield", "Lightning"});
    }

    public WizardTower(String name, int magicPower, String location, boolean isActive, int spellCapacity, String[] spells) {
        super(name, magicPower, location, isActive);
        this.spellCapacity = spellCapacity;
        this.knownSpells = spells;
    }

    public String castMagicSpell() {
        return structureName + " casts " + knownSpells[new Random().nextInt(knownSpells.length)];
    }

    public void doubleSpellCapacity() {
        spellCapacity *= 2;
    }

    public int getSpellCapacity() {
        return spellCapacity;
    }
}

class EnchantedCastle extends MagicalStructure {
    private int defenseRating;
    private boolean hasDrawbridge;

    public EnchantedCastle() {
        this("Fort", 80, "Valley", true, 50, false);
    }

    public EnchantedCastle(String name, int defenseRating) {
        this(name, 100, "Valley", true, defenseRating, true);
    }

    public EnchantedCastle(String name, int magicPower, String location, boolean isActive, int defenseRating, boolean hasDrawbridge) {
        super(name, magicPower, location, isActive);
        this.defenseRating = defenseRating;
        this.hasDrawbridge = hasDrawbridge;
    }

    public String castMagicSpell() {
        return structureName + " fortifies with magic shield of " + defenseRating;
    }

    public void tripleDefense() {
        defenseRating *= 3;
    }

    public int getDefenseRating() {
        return defenseRating;
    }
}

class MysticLibrary extends MagicalStructure {
    private int bookCount;
    private String ancientLanguage;

    public MysticLibrary() {
        this("Library", 70, "Town", true, 200, "Latin");
    }

    public MysticLibrary(String name, int bookCount) {
        this(name, 90, "Town", true, bookCount, "Greek");
    }

    public MysticLibrary(String name, int magicPower, String location, boolean isActive, int bookCount, String language) {
        super(name, magicPower, location, isActive);
        this.bookCount = bookCount;
        this.ancientLanguage = language;
    }

    public String castMagicSpell() {
        return structureName + " unveils secrets in " + ancientLanguage;
    }
}

class DragonLair extends MagicalStructure {
    private String dragonType;
    private int treasureValue;

    public DragonLair() {
        this("Dragon Lair", 150, "Mountain", true, "Fire Dragon", 1000);
    }

    public DragonLair(String dragonType, int treasureValue) {
        this("Dragon Lair", 170, "Mountain", true, dragonType, treasureValue);
    }

    public DragonLair(String name, int magicPower, String location, boolean isActive, String dragonType, int treasureValue) {
        super(name, magicPower, location, isActive);
        this.dragonType = dragonType;
        this.treasureValue = treasureValue;
    }

    public String castMagicSpell() {
        return dragonType + " breathes destruction!";
    }

    public String getDragonType() {
        return dragonType;
    }
}

class KingdomManager {
    public static boolean canStructuresInteract(MagicalStructure s1, MagicalStructure s2) {
        if (s1 instanceof WizardTower && s2 instanceof MysticLibrary) return true;
        if (s1 instanceof EnchantedCastle && s2 instanceof DragonLair) return true;
        if (s1 instanceof WizardTower && s2 instanceof WizardTower) return true;
        return false;
    }

    public static String performMagicBattle(MagicalStructure attacker, MagicalStructure defender) {
        if (attacker.magicPower > defender.magicPower) return attacker.structureName + " defeats " + defender.structureName;
        else if (attacker.magicPower < defender.magicPower) return defender.structureName + " resists " + attacker.structureName;
        else return "The battle between " + attacker.structureName + " and " + defender.structureName + " is a stalemate";
    }

    public static int calculateKingdomMagicPower(MagicalStructure[] structures) {
        int total = 0;
        for (MagicalStructure s : structures) total += s.magicPower;
        return total;
    }

    public static void applySpecialEffects(MagicalStructure[] structures) {
        for (int i = 0; i < structures.length; i++) {
            for (int j = i + 1; j < structures.length; j++) {
                if (structures[i] instanceof WizardTower && structures[j] instanceof MysticLibrary) {
                    ((WizardTower) structures[i]).doubleSpellCapacity();
                }
                if (structures[i] instanceof EnchantedCastle && structures[j] instanceof DragonLair) {
                    ((EnchantedCastle) structures[i]).tripleDefense();
                }
                if (structures[i] instanceof WizardTower && structures[j] instanceof WizardTower) {
                    System.out.println("Magic network formed between towers!");
                }
            }
        }
    }

    public static void categorizeStructures(MagicalStructure[] structures) {
        int towers = 0, castles = 0, libraries = 0, lairs = 0;
        for (MagicalStructure s : structures) {
            if (s instanceof WizardTower) towers++;
            else if (s instanceof EnchantedCastle) castles++;
            else if (s instanceof MysticLibrary) libraries++;
            else if (s instanceof DragonLair) lairs++;
        }
        System.out.println("Towers: " + towers + " | Castles: " + castles + " | Libraries: " + libraries + " | Lairs: " + lairs);
    }

    public static String determineSpecialization(MagicalStructure[] structures) {
        int magic = 0, defense = 0;
        for (MagicalStructure s : structures) {
            if (s instanceof WizardTower || s instanceof MysticLibrary) magic++;
            if (s instanceof EnchantedCastle || s instanceof DragonLair) defense++;
        }
        if (magic > defense) return "Kingdom is Magic-focused";
        else if (defense > magic) return "Kingdom is Defense-focused";
        else return "Kingdom is Balanced";
    }
}

public class MedievalKingdomBuilder {
    public static void main(String[] args) {
        MagicalStructure[] structures = {
            new WizardTower(),
            new EnchantedCastle(),
            new MysticLibrary(),
            new DragonLair("Ice Dragon", 2000),
            new WizardTower("High Tower", 10)
        };

        KingdomManager.applySpecialEffects(structures);
        for (MagicalStructure s : structures) {
            System.out.println(s.castMagicSpell());
        }
        System.out.println(KingdomManager.performMagicBattle(structures[0], structures[3]));
        System.out.println("Total Kingdom Magic Power: " + KingdomManager.calculateKingdomMagicPower(structures));
        KingdomManager.categorizeStructures(structures);
        System.out.println(KingdomManager.determineSpecialization(structures));
    }
}
