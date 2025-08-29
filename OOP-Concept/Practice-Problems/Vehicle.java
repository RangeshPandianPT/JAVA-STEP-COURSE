public class Vehicle {
    protected String make;
    protected String model;
    protected int year;
    protected double fuelLevel;

    public Vehicle(String make, String model, int year, double fuelLevel) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.fuelLevel = fuelLevel;
    }

    public void startVehicle() {
        System.out.println(make + " " + model + " started.");
    }

    public void stopVehicle() {
        System.out.println(make + " " + model + " stopped.");
    }

    public void refuel(double amount) {
        fuelLevel += amount;
        System.out.println(make + " " + model + " refueled. Current fuel: " + fuelLevel);
    }

    public void displayVehicleInfo() {
        System.out.println("Vehicle Info: " + year + " " + make + " " + model + " | Fuel Level: " + fuelLevel);
    }
}

class Car extends Vehicle {
    public Car(String make, String model, int year, double fuelLevel) {
        super(make, model, year, fuelLevel);
    }
}


class Truck extends Vehicle {
    public Truck(String make, String model, int year, double fuelLevel) {
        super(make, model, year, fuelLevel);
    }

    @Override
    public void refuel(double amount) {
        fuelLevel += amount * 0.9; // simulate fuel loss
        System.out.println(make + " " + model + " refueled (with 10% loss). Current fuel: " + fuelLevel);
    }
}

class Motorcycle extends Vehicle {
    public Motorcycle(String make, String model, int year, double fuelLevel) {
        super(make, model, year, fuelLevel);
    }
}

public class VehicleDemo {
    public static void main(String[] args) {
        Vehicle car = new Car("Toyota", "Corolla", 2020, 50);
        Vehicle truck = new Truck("Ford", "F-150", 2018, 70);
        Vehicle motorcycle = new Motorcycle("Yamaha", "R15", 2021, 15);       
        Vehicle[] vehicles = {car, truck, motorcycle};

        for (Vehicle v : vehicles) {
            v.startVehicle();
            v.refuel(20);
            v.displayVehicleInfo();
            v.stopVehicle(); 
        }

        /*
         Reusability:
         - The Vehicle class is a reusable template.
         - We don’t have to rewrite common methods (start, stop, refuel, display).
         
         Extensibility:
         - New types like Bus, EVCar, or Bicycle can extend Vehicle.
         - They can override or add behaviors without changing existing code.
         
         Benefits:
         - Less code duplication.
         - Easy maintenance.
         - Clear structure (base + specialized classes).
        */
    }
}
