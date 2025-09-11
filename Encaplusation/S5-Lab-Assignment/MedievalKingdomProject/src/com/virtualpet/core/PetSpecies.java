package com.virtualpet.core;

import java.util.*;

public final class PetSpecies {
    private final String speciesName;
    private final String[] evolutionStages;
    private final int maxLifespan;
    private final String habitat;

    public PetSpecies(String speciesName, String[] evolutionStages, int maxLifespan, String habitat) {
        if (speciesName == null || speciesName.isBlank())
            throw new IllegalArgumentException("Invalid species name");
        if (maxLifespan <= 0)
            throw new IllegalArgumentException("Invalid lifespan");
        if (evolutionStages == null || evolutionStages.length == 0)
            throw new IllegalArgumentException("Species must have evolutions");

        this.speciesName = speciesName;
        this.evolutionStages = evolutionStages.clone();
        this.maxLifespan = maxLifespan;
        this.habitat = habitat;
    }

    public static PetSpecies createDefaultSpecies() {
        return new PetSpecies("BasicPet", new String[]{"Egg", "Child", "Adult"}, 100, "Home");
    }

    public String getSpeciesName() { return speciesName; }
    public String[] getEvolutionStages() { return evolutionStages.clone(); }
    public int getMaxLifespan() { return maxLifespan; }
    public String getHabitat() { return habitat; }

    @Override
    public String toString() { return "Species{" + speciesName + "}"; }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PetSpecies)) return false;
        PetSpecies other = (PetSpecies) o;
        return this.speciesName.equals(other.speciesName);
    }

    @Override
    public int hashCode() { return Objects.hash(speciesName); }
}
