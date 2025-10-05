abstract class Vehicle {
    abstract void start();
    void stop() { System.out.println("Vehicle stopped."); }
}

interface Fuel {
    void refuel();
}

class Car extends Vehicle implements Fuel {
    void start() { System.out.println("Car started."); }
    public void refuel() { System.out.println("Car refueled."); }
}

public class VehicleDemo {
    public static void main(String[] args) {
        Car car = new Car();
        car.start();
        car.refuel();
        car.stop();
    }
}
