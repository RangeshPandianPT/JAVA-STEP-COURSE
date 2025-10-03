abstract class Device {
    protected String brand;
    protected String model;

    Device(String brand, String model) {
        this.brand = brand;
        this.model = model;
    }

    abstract void powerOn();
}

interface Connectable {
    void connect();
}

class Smartphone extends Device implements Connectable {
    private String os;

    Smartphone(String brand, String model, String os) {
        super(brand, model);
        this.os = os;
    }

    @Override
    void powerOn() {
        System.out.println(brand + " " + model + " powered on. Operating System: " + os);
    }

    @Override
    public void connect() {
        System.out.println(brand + " " + model + " is connecting to Wi-Fi and Bluetooth.");
    }

    void showDetails() {
        System.out.println("Device Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.println("OS: " + os);
    }
}

public class DeviceDemo {
    public static void main(String[] args) {
        Smartphone phone = new Smartphone("Samsung", "Galaxy S23", "Android 14");
        phone.showDetails();
        phone.powerOn();
        phone.connect();
    }
}
