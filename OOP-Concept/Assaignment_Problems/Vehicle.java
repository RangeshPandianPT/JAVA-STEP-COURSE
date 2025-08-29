import java.util.*;
import java.time.*;

abstract class Vehicle {
    String vehicleId, brand, model, fuelType, currentStatus;
    int year;
    double mileage;
    double fuelConsumed;
    static int totalVehicles = 0;
    static double fleetValue = 0.0;
    static String companyName = "TransitCo";
    static double totalFuelConsumption = 0.0;

    Vehicle(String id, String brand, String model, int year, double mileage, String fuelType, double value) {
        this.vehicleId = id; this.brand = brand; this.model = model; this.year = year;
        this.mileage = mileage; this.fuelType = fuelType; this.currentStatus = "Idle";
        this.fuelConsumed = 0.0;
        totalVehicles++; fleetValue += value;
    }

    public void assignDriver(Driver d) {
        d.assignedVehicle = this; this.currentStatus = "Assigned";
    }

    public void updateMileage(double km, double fuelLiters) {
        this.mileage += km;
        this.fuelConsumed += fuelLiters;
        totalFuelConsumption += fuelLiters;
    }

    public boolean checkServiceDue(double serviceIntervalKm, double lastServiceMileage) {
        return (mileage - lastServiceMileage) >= serviceIntervalKm;
    }

    public abstract double calculateRunningCost(double fuelPricePerLiter, double otherCostsPerKm, double km);

    public void scheduleMaintenance() {
        this.currentStatus = "Maintenance";
    }
}

class Car extends Vehicle {
    int seatingCapacity;

    Car(String id, String brand, String model, int year, double mileage, String fuelType, int seating, double value) {
        super(id, brand, model, year, mileage, fuelType, value);
        this.seatingCapacity = seating;
    }

    public double calculateRunningCost(double fuelPricePerLiter, double otherCostsPerKm, double km) {
        double avgKmPerL = 15.0;
        double fuel = km / avgKmPerL;
        return fuel * fuelPricePerLiter + km * otherCostsPerKm;
    }
}

class Bus extends Vehicle {
    int seatingCapacity;

    Bus(String id, String brand, String model, int year, double mileage, String fuelType, int seating, double value) {
        super(id, brand, model, year, mileage, fuelType, value);
        this.seatingCapacity = seating;
    }

    public double calculateRunningCost(double fuelPricePerLiter, double otherCostsPerKm, double km) {
        double avgKmPerL = 4.0;
        double fuel = km / avgKmPerL;
        return fuel * fuelPricePerLiter + km * otherCostsPerKm;
    }
}

class Truck extends Vehicle {
    double loadCapacityTons;

    Truck(String id, String brand, String model, int year, double mileage, String fuelType, double loadCapacity, double value) {
        super(id, brand, model, year, mileage, fuelType, value);
        this.loadCapacityTons = loadCapacity;
    }

    public double calculateRunningCost(double fuelPricePerLiter, double otherCostsPerKm, double km) {
        double avgKmPerL = 3.0;
        double fuel = km / avgKmPerL;
        return fuel * fuelPricePerLiter + km * otherCostsPerKm + 0.5 * loadCapacityTons * km;
    }
}

class Driver {
    String driverId;
    String driverName;
    String licenseType;
    Vehicle assignedVehicle;
    int totalTrips;

    Driver(String id, String name, String licenseType) {
        this.driverId = id; this.driverName = name; this.licenseType = licenseType; this.assignedVehicle = null; this.totalTrips = 0;
    }

    public void recordTrip(double km, double fuelLiters) {
        if (assignedVehicle != null) {
            assignedVehicle.updateMileage(km, fuelLiters);
            totalTrips++;
        }
    }

    public void unassign() { if (assignedVehicle != null) assignedVehicle.currentStatus = "Idle"; assignedVehicle = null; }
}

class FleetManager {
    List<Vehicle> vehicles = new ArrayList<>();
    List<Driver> drivers = new ArrayList<>();
    Map<String, Double> maintenanceCosts = new HashMap<>();
    Map<String, Double> lastServiceMileage = new HashMap<>();

    public void addVehicle(Vehicle v, double initialMaintenanceCost) {
        vehicles.add(v);
        maintenanceCosts.put(v.vehicleId, initialMaintenanceCost);
        lastServiceMileage.put(v.vehicleId, v.mileage);
    }

    public void addDriver(Driver d) { drivers.add(d); }

    public Vehicle findVehicle(String id) {
        for (Vehicle v : vehicles) if (v.vehicleId.equals(id)) return v;
        return null;
    }

    public Driver findDriver(String id) {
        for (Driver d : drivers) if (d.driverId.equals(id)) return d;
        return null;
    }

    public boolean assignDriverToVehicle(String driverId, String vehicleId) {
        Driver d = findDriver(driverId);
        Vehicle v = findVehicle(vehicleId);
        if (d == null || v == null) return false;
        v.assignDriver(d);
        d.assignedVehicle = v;
        return true;
    }

    public void scheduleMaintenance(String vehicleId, double cost) {
        Vehicle v = findVehicle(vehicleId);
        if (v != null) {
            v.scheduleMaintenance();
            maintenanceCosts.put(vehicleId, maintenanceCosts.getOrDefault(vehicleId, 0.0) + cost);
            lastServiceMileage.put(vehicleId, v.mileage);
        }
    }

    public List<Vehicle> getVehiclesByType(Class<?> cls) {
        List<Vehicle> res = new ArrayList<>();
        for (Vehicle v : vehicles) if (cls.isInstance(v)) res.add(v);
        return res;
    }

    public double calculateTotalMaintenanceCost() {
        double sum = 0;
        for (double c : maintenanceCosts.values()) sum += c;
        return sum;
    }

    public double getFleetUtilization() {
        int active = 0;
        for (Vehicle v : vehicles) if ("Assigned".equalsIgnoreCase(v.currentStatus) || "OnTrip".equalsIgnoreCase(v.currentStatus)) active++;
        return vehicles.isEmpty() ? 0.0 : (active * 100.0 / vehicles.size());
    }

    public Map<String, Double> calculateRunningCostsForAll(double fuelPricePerLiter, double otherCostsPerKm, double kmPerVehicle) {
        Map<String, Double> costs = new HashMap<>();
        for (Vehicle v : vehicles) {
            double cost = v.calculateRunningCost(fuelPricePerLiter, otherCostsPerKm, kmPerVehicle);
            costs.put(v.vehicleId, cost);
        }
        return costs;
    }

    public List<Vehicle> checkServiceDue(double serviceIntervalKm) {
        List<Vehicle> due = new ArrayList<>();
        for (Vehicle v : vehicles) {
            double last = lastServiceMileage.getOrDefault(v.vehicleId, 0.0);
            if (v.checkServiceDue(serviceIntervalKm, last)) due.add(v);
        }
        return due;
    }

    public void performTrip(String driverId, String vehicleId, double km, double fuel) {
        Driver d = findDriver(driverId);
        Vehicle v = findVehicle(vehicleId);
        if (d == null || v == null) return;
        d.recordTrip(km, fuel);
        v.currentStatus = "OnTrip";
    }

    public void endTrip(String vehicleId) {
        Vehicle v = findVehicle(vehicleId);
        if (v != null) v.currentStatus = "Idle";
    }
}

public class FleetApp {
    public static void main(String[] args) {
        FleetManager fm = new FleetManager();
        Car c1 = new Car("V001","Toyota","Corolla",2019,50000,"Petrol",5,15000);
        Bus b1 = new Bus("V002","Volvo","B9R",2018,120000,"Diesel",50,80000);
        Truck t1 = new Truck("V003","Tata","407",2020,90000,"Diesel",5.0,60000);
        fm.addVehicle(c1,200); fm.addVehicle(b1,500); fm.addVehicle(t1,400);
        Driver d1 = new Driver("D001","Ramesh","HGV"); Driver d2 = new Driver("D002","Sita","Car");
        fm.addDriver(d1); fm.addDriver(d2);
        fm.assignDriverToVehicle("D001","V003");
        fm.assignDriverToVehicle("D002","V001");
        fm.performTrip("D001","V003",120.0,40.0);
        fm.performTrip("D002","V001",60.0,4.0);
        fm.endTrip("V003"); fm.endTrip("V001");
        Map<String, Double> costs = fm.calculateRunningCostsForAll(110.0, 5.0, 100.0);
        System.out.println("Running costs per vehicle for 100 km:");
        for (var e : costs.entrySet()) System.out.println(e.getKey() + " : " + e.getValue());
        System.out.println("Fleet utilization: " + fm.getFleetUtilization() + "%");
        System.out.println("Total maintenance cost: " + fm.calculateTotalMaintenanceCost());
        List<Vehicle> due = fm.checkServiceDue(10000);
        System.out.println("Vehicles due for service:");
        for (Vehicle v : due) System.out.println(v.vehicleId + " - " + v.brand + " " + v.model);
        List<Vehicle> buses = fm.getVehiclesByType(Bus.class);
        System.out.println("Buses in fleet:");
        for (Vehicle v : buses) System.out.println(v.vehicleId + " " + v.brand);
    }
}
