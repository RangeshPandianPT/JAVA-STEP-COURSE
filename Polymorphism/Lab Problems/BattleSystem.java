abstract class Character {
    String name;

    Character(String name) {
        this.name = name;
    }

    abstract void attack();
}

class Warrior extends Character {
    Warrior(String name) {
        super(name);
    }

    @Override
    void attack() {
        System.out.println(name + " swings a mighty sword! High defense engaged.");
    }
}

class Mage extends Character {
    Mage(String name) {
        super(name);
    }

    @Override
    void attack() {
        System.out.println(name + " casts a powerful spell! Mana consumed.");
    }
}

class Archer extends Character {
    Archer(String name) {
        super(name);
    }

    @Override
    void attack() {
        System.out.println(name + " fires a precise arrow! Long-range damage dealt.");
    }
}

public class BattleSystem {
    public static void main(String[] args) {
        Character[] army = {
            new Warrior("Thor"),
            new Mage("Merlin"),
            new Archer("Robin"),
            new Warrior("Achilles"),
            new Mage("Gandalf")
        };

        for (Character c : army) {
            c.attack(); 
        }
    }
}
