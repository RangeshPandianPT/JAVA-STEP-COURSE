abstract class SmartDevice {
    String name;

    SmartDevice(String name) {
        this.name = name;
    }

    void basicInfo() {
        System.out.println("Device: " + name);
    }
}

class SmartTV extends SmartDevice {
    int volume;
    String channel;
    String app;

    SmartTV(String name, int volume, String channel, String app) {
        super(name);
        this.volume = volume;
        this.channel = channel;
        this.app = app;
    }

    void controlTV() {
        System.out.println(name + " is set to channel: " + channel);
        System.out.println("Volume: " + volume);
        System.out.println("Streaming App: " + app);
    }
}

class SmartThermostat extends SmartDevice {
    int temperature;
    int humidity;
    boolean energySaving;

    SmartThermostat(String name, int temperature, int humidity, boolean energySaving) {
        super(name);
        this.temperature = temperature;
        this.humidity = humidity;
        this.energySaving = energySaving;
    }

    void controlThermostat() {
        System.out.println(name + " temperature: " + temperature + "°C");
        System.out.println("Humidity: " + humidity + "%");
        System.out.println("Energy Saving Mode: " + (energySaving ? "On" : "Off"));
    }
}

class SmartSecurity extends SmartDevice {
    int cameras;
    boolean alarm;
    String accessControl;

    SmartSecurity(String name, int cameras, boolean alarm, String accessControl) {
        super(name);
        this.cameras = cameras;
        this.alarm = alarm;
        this.accessControl = accessControl;
    }

    void controlSecurity() {
        System.out.println(name + " has " + cameras + " cameras");
        System.out.println("Alarm: " + (alarm ? "Active" : "Inactive"));
        System.out.println("Access Control: " + accessControl);
    }
}

class SmartKitchen extends SmartDevice {
    int cookingTime;
    int cookingTemp;
    String recipe;

    SmartKitchen(String name, int cookingTime, int cookingTemp, String recipe) {
        super(name);
        this.cookingTime = cookingTime;
        this.cookingTemp = cookingTemp;
        this.recipe = recipe;
    }

    void controlKitchen() {
        System.out.println(name + " cooking " + recipe);
        System.out.println("Cooking Time: " + cookingTime + " mins");
        System.out.println("Cooking Temperature: " + cookingTemp + "°C");
    }
}

public class SmartHome {
    public static void main(String[] args) {
        SmartDevice[] devices = {
            new SmartTV("Living Room TV", 15, "HBO", "Netflix"),
            new SmartThermostat("Nest Thermostat", 24, 40, true),
            new SmartSecurity("Home Security", 4, true, "Fingerprint"),
            new SmartKitchen("Smart Oven", 45, 180, "Lasagna")
        };

        for (SmartDevice device : devices) {
            device.basicInfo();

            if (device instanceof SmartTV) {
                ((SmartTV) device).controlTV();
            } else if (device instanceof SmartThermostat) {
                ((SmartThermostat) device).controlThermostat();
            } else if (device instanceof SmartSecurity) {
                ((SmartSecurity) device).controlSecurity();
            } else if (device instanceof SmartKitchen) {
                ((SmartKitchen) device).controlKitchen();
            }
        }
    }
}
