abstract class Vehicle {
    protected int speed;
    protected String fuelType;

    Vehicle(int speed, String fuelType) {
        this.speed = speed;
        this.fuelType = fuelType;
    }

    abstract void startEngine();
}

interface Maintainable {
    void serviceInfo();
}

class Car extends Vehicle implements Maintainable {
    private String model;

    Car(int speed, String fuelType, String model) {
        super(speed, fuelType);
        this.model = model;
    }

    @Override
    void startEngine() {
        System.out.println(model + " engine started. Fuel type: " + fuelType + ", Speed: " + speed + " km/h");
    }

    @Override
    public void serviceInfo() {
        System.out.println(model + " requires servicing every 10,000 km.");
    }

    void showDetails() {
        System.out.println("Car Model: " + model);
        System.out.println("Fuel Type: " + fuelType);
        System.out.println("Max Speed: " + speed + " km/h");
    }
}

public class VehicleDemo {
    public static void main(String[] args) {
        Car car = new Car(180, "Petrol", "Honda City");
        car.startEngine();
        car.serviceInfo();
        car.showDetails();
    }
}
