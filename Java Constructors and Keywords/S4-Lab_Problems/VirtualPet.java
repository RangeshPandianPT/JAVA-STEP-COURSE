import java.util.*;

class VirtualPet {
    private final String petId;
    private String petName;
    private String species;
    private int age;
    private int happiness;
    private int health;
    private String stage;
    private boolean isGhost;
    static final String[] EVOLUTION_STAGES = {"Egg", "Baby", "Child", "Teen", "Adult", "Elder"};
    static int totalPetsCreated;

    public VirtualPet() {
        this("Unknown", randomSpecies(), 0, 50, 50, EVOLUTION_STAGES[0]);
    }

    public VirtualPet(String petName) {
        this(petName, randomSpecies(), 0, 60, 60, EVOLUTION_STAGES[1]);
    }

    public VirtualPet(String petName, String species) {
        this(petName, species, 1, 70, 70, EVOLUTION_STAGES[2]);
    }

    public VirtualPet(String petName, String species, int age, int happiness, int health, String stage) {
        this.petId = generatePetId();
        this.petName = petName;
        this.species = species;
        this.age = age;
        this.happiness = happiness;
        this.health = health;
        this.stage = stage;
        this.isGhost = false;
        totalPetsCreated++;
    }

    public static String randomSpecies() {
        String[] speciesList = {"Dragon", "Cat", "Dog", "Phoenix", "Dinosaur"};
        return speciesList[new Random().nextInt(speciesList.length)];
    }

    public static String generatePetId() {
        return UUID.randomUUID().toString();
    }

    public void feedPet() {
        if (!isGhost) health = Math.min(100, health + 10);
    }

    public void playWithPet() {
        if (!isGhost) happiness = Math.min(100, happiness + 10);
    }

    public void healPet() {
        if (!isGhost) health = Math.min(100, health + 20);
    }

    public void simulateDay() {
        if (isGhost) return;
        age++;
        happiness -= new Random().nextInt(10);
        health -= new Random().nextInt(10);
        if (health <= 0) {
            isGhost = true;
            stage = "Ghost";
        } else {
            evolvePet();
        }
    }

    public void evolvePet() {
        if (age > 15 && !stage.equals("Elder")) stage = EVOLUTION_STAGES[5];
        else if (age > 10 && !stage.equals("Adult")) stage = EVOLUTION_STAGES[4];
        else if (age > 6 && !stage.equals("Teen")) stage = EVOLUTION_STAGES[3];
        else if (age > 3 && !stage.equals("Child")) stage = EVOLUTION_STAGES[2];
    }

    public String getPetStatus() {
        return "PetID: " + petId + " | Name: " + petName + " | Species: " + species +
               " | Age: " + age + " | Happiness: " + happiness + " | Health: " + health +
               " | Stage: " + stage + (isGhost ? " (Haunting)" : "");
    }
}

public class VirtualPetSimulator {
    public static void main(String[] args) {
        List<VirtualPet> daycare = new ArrayList<>();
        daycare.add(new VirtualPet());
        daycare.add(new VirtualPet("Fluffy"));
        daycare.add(new VirtualPet("Sparky", "Wolf"));
        daycare.add(new VirtualPet("Luna", "Phoenix", 2, 80, 90, VirtualPet.EVOLUTION_STAGES[2]));

        for (int day = 1; day <= 10; day++) {
            System.out.println("Day " + day);
            for (VirtualPet pet : daycare) {
                pet.simulateDay();
                pet.feedPet();
                pet.playWithPet();
                System.out.println(pet.getPetStatus());
            }
            System.out.println();
        }
        System.out.println("Total Pets Created: " + VirtualPet.totalPetsCreated);
    }
}
