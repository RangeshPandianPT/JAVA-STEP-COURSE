public class GameController 
{
    private String controllerBrand;
    private String connectionType;
    private boolean hasVibration;
    private int batteryLevel;
    private double sensitivity;

    public GameController() 
	{
        this.controllerBrand = "GenericPad";
        this.connectionType = "USB";
        this.hasVibration = true;
        this.batteryLevel = 100;
        this.sensitivity = 1.0;
    }

    public GameController(String controllerBrand, String connectionType,
                          boolean hasVibration, int batteryLevel, double sensitivity) 
						  {
        this.controllerBrand = controllerBrand;
        this.connectionType = connectionType;
        this.hasVibration = hasVibration;

        if (batteryLevel < 0) {
            this.batteryLevel = 0;
        } else if (batteryLevel > 100) {
            this.batteryLevel = 100;
        } else {
            this.batteryLevel = batteryLevel;
        }

        if (sensitivity < 0.1) {
            this.sensitivity = 0.1;
        } else if (sensitivity > 3.0) {
            this.sensitivity = 3.0;
        } else {
            this.sensitivity = sensitivity;
        }
    }

    public GameController(String brand, String connectionType) {
        this.controllerBrand = brand;
        this.connectionType = connectionType;
        this.hasVibration = true;   
        this.batteryLevel = 100;    
        this.sensitivity = 1.0;     
    }

    public void calibrateController() {
        System.out.println("Calibrating " + controllerBrand + " controller...");
    }

    public void displayConfiguration() 
	{
        System.out.println("Controller Brand: " + controllerBrand);
        System.out.println("Connection Type: " + connectionType);
        System.out.println("Vibration: " + (hasVibration ? "Enabled" : "Disabled"));
        System.out.println("Battery Level: " + batteryLevel + "%");
        System.out.println("Sensitivity: " + sensitivity);
    }

    public void testVibration() {
        if (hasVibration) {
            System.out.println("*BUZZ* Vibration test successful!");
        } else {
            System.out.println("Vibration disabled on this controller.");
        }
    }
    public static void main(String[] args) 
	{
        GameController defaultController = new GameController();
        defaultController.displayConfiguration();
        defaultController.calibrateController();
        defaultController.testVibration();

        GameController customController = new GameController("ProPad", "Bluetooth", false, 85, 2.5);
        customController.displayConfiguration();
        customController.calibrateController();
        customController.testVibration();

        GameController quickSetup = new GameController("SpeedPad", "Wireless");
        quickSetup.displayConfiguration();
        quickSetup.calibrateController();
        quickSetup.testVibration();

        System.out.println("Comparing Controllers:");
        defaultController.displayConfiguration();
        customController.displayConfiguration();
        quickSetup.displayConfiguration();
    }
}
