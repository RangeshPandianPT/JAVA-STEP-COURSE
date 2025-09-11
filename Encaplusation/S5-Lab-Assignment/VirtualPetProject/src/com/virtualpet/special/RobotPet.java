package com.virtualpet.special;

import com.virtualpet.core.VirtualPet;
import com.virtualpet.core.PetSpecies;

public class RobotPet {
    private boolean needsCharging;
    private int batteryLevel;
    private final VirtualPet basePet;

    public RobotPet(String name) {
        this.needsCharging = false;
        this.batteryLevel = 100;
        this.basePet = new VirtualPet(name,
                new PetSpecies("Robot", new String[]{"Prototype", "Advanced", "AI"}, 500, "Laboratory"),
                0, 60, 100);
    }

    public boolean isNeedsCharging() { return needsCharging; }
    public int getBatteryLevel() { return batteryLevel; }

    public void drainBattery(int amount) {
        batteryLevel = Math.max(0, batteryLevel - amount);
        if (batteryLevel == 0) needsCharging = true;
    }

    public void recharge() {
        batteryLevel = 100;
        needsCharging = false;
    }

    public VirtualPet getBasePet() { return basePet; }

    @Override
    public String toString() {
        return "RobotPet{battery=" + batteryLevel + ", needsCharging=" + needsCharging + "}";
    }
}
