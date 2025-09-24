class SmartDevice {
    protected String name;

    public SmartDevice(String name) {
        this.name = name;
    }

    public void turnOn() {
        System.out.println(name + " is now ON.");
    }

    public void turnOff() {
        System.out.println(name + " is now OFF.");
    }
}

class SmartLight extends SmartDevice {
    private int brightness;

    public SmartLight(String name, int brightness) {
        super(name);
        this.brightness = brightness;
    }

    public void setBrightness(int level) {
        this.brightness = level;
        System.out.println(name + " brightness set to " + level + "%.");
    }
}

class SmartThermostat extends SmartDevice {
    private int temperature;

    public SmartThermostat(String name, int temperature) {
        super(name);
        this.temperature = temperature;
    }

    public void setTemperature(int temp) {
        this.temperature = temp;
        System.out.println(name + " temperature set to " + temp + "°C.");
    }
}

public class SmartHome {
    public static void main(String[] args) {
        SmartDevice[] devices = {
            new SmartLight("Living Room Light", 70),
            new SmartThermostat("Bedroom Thermostat", 24),
            new SmartLight("Kitchen Light", 50)
        };

        for (SmartDevice device : devices) {
            device.turnOn();
            if (device instanceof SmartLight) {
                SmartLight light = (SmartLight) device;
                light.setBrightness(85);
            } else if (device instanceof SmartThermostat) {
                SmartThermostat thermostat = (SmartThermostat) device;
                thermostat.setTemperature(22);
            }

            device.turnOff();
            System.out.println();
        }
    }
}
