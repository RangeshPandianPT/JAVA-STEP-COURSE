import com.virtualpet.core.VirtualPet;
import com.virtualpet.core.PetSpecies;
import com.virtualpet.special.DragonPet;
import com.virtualpet.special.RobotPet;

public class Main {
    public static void main(String[] args) {
        VirtualPet pet = new VirtualPet("Fluffy");
        DragonPet dragon = new DragonPet("Smaug", "Fire", "Flame Breath");
        RobotPet robot = new RobotPet("Robo");

        System.out.println("Default Pet: " + pet);
        System.out.println("Dragon Pet: " + dragon);
        System.out.println("Robot Pet: " + robot);

        pet.feedPet("fruit");
        pet.playWithPet("fetch");
        System.out.println("Updated Pet: " + pet);
    }
}
