package com.virtualpet.special;

import com.virtualpet.core.VirtualPet;
import com.virtualpet.core.PetSpecies;

public class DragonPet {
    private final String dragonType;
    private final String breathWeapon;
    private final VirtualPet basePet;

    public DragonPet(String name, String dragonType, String breathWeapon) {
        this.dragonType = dragonType;
        this.breathWeapon = breathWeapon;
        this.basePet = new VirtualPet(name,
                new PetSpecies("Dragon", new String[]{"Hatchling", "Wyrmling", "Ancient"}, 300, "Caves"),
                0, 80, 80);
    }

    public String getDragonType() { return dragonType; }
    public String getBreathWeapon() { return breathWeapon; }
    public VirtualPet getBasePet() { return basePet; }

    @Override
    public String toString() {
        return "DragonPet{" + dragonType + ", breath=" + breathWeapon + "}";
    }
}
