abstract class Vehicle {
    String name;

    Vehicle(String name) {
        this.name = name;
    }

    abstract void dispatch();
}

class Bus extends Vehicle {
    int passengerCapacity;
    String route;

    Bus(String name, int passengerCapacity, String route) {
        super(name);
        this.passengerCapacity = passengerCapacity;
        this.route = route;
    }

    @Override
    void dispatch() {
        System.out.println("Dispatching Bus: " + name);
        System.out.println("Route: " + route);
        System.out.println("Passenger Capacity: " + passengerCapacity);
    }
}

class Taxi extends Vehicle {
    double ratePerKm;
    double distance;

    Taxi(String name, double ratePerKm, double distance) {
        super(name);
        this.ratePerKm = ratePerKm;
        this.distance = distance;
    }

    @Override
    void dispatch() {
        double fare = ratePerKm * distance;
        System.out.println("Dispatching Taxi: " + name);
        System.out.println("Distance: " + distance + " km");
        System.out.println("Fare: ₹" + fare);
    }
}

class Train extends Vehicle {
    int cars;
    String schedule;

    Train(String name, int cars, String schedule) {
        super(name);
        this.cars = cars;
        this.schedule = schedule;
    }

    @Override
    void dispatch() {
        System.out.println("Dispatching Train: " + name);
        System.out.println("Cars: " + cars);
        System.out.println("Schedule: " + schedule);
    }
}

class Bike extends Vehicle {
    int availableUnits;

    Bike(String name, int availableUnits) {
        super(name);
        this.availableUnits = availableUnits;
    }

    @Override
    void dispatch() {
        System.out.println("Dispatching Bike: " + name);
        System.out.println("Available Units: " + availableUnits);
        System.out.println("Eco-friendly transport for short trips");
    }
}

public class TransportSystem {
    public static void main(String[] args) {
        Vehicle v;

        v = new Bus("City Bus 101", 50, "Downtown to Uptown");
        v.dispatch();

        v = new Taxi("Taxi A1", 20, 12.5);
        v.dispatch();

        v = new Train("Express Line", 10, "Every 30 minutes");
        v.dispatch();

        v = new Bike("GreenBike", 100);
        v.dispatch();
    }
}
