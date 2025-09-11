import com.kingdom.config.KingdomConfig;
import com.kingdom.structures.WizardTower;
import com.kingdom.manager.KingdomManager;

public class Main {
    public static void main(String[] args) {
        KingdomConfig config = KingdomConfig.createDefaultKingdom();
        WizardTower tower = new WizardTower("North Hills", 5);

        KingdomManager manager = new KingdomManager(config);
        manager.addStructure(tower);

        System.out.println("Kingdom: " + config);
        System.out.println("Structure: " + tower);
        System.out.println("Total Power: " +
            KingdomManager.calculateKingdomPower(new Object[]{tower}));
    }
}
