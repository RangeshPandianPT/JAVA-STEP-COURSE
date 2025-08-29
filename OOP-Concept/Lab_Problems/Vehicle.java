class Vehicle {
    private String vehicleId;
    private String brand;
    private String model;
    private double rentPerDay;
    private boolean isAvailable;

    private static int totalVehicles = 0;
    private static double totalRevenue = 0;
    private static int rentalDays = 0;
    private static String companyName = "Default Rentals";

    public Vehicle(String brand, String model, double rentPerDay) {
        this.brand = brand;
        this.model = model;
        this.rentPerDay = rentPerDay;
        this.isAvailable = true;
        this.vehicleId = generateVehicleId();
        totalVehicles++;
    }

    public double rentVehicle(int days) {
        if (!isAvailable) {
            System.out.println("Vehicle " + vehicleId + " is not available.");
            return 0;
        }
        double rent = calculateRent(days);
        isAvailable = false;
        rentalDays += days;
        System.out.println("Vehicle " + vehicleId + " rented for " + days + " days. Amount: " + rent);
        return rent;
    }

    public void returnVehicle() {
        if (isAvailable) {
            System.out.println("Vehicle " + vehicleId + " is already available.");
        } else {
            isAvailable = true;
            System.out.println("Vehicle " + vehicleId + " returned and is now available.");
        }
    }

    private double calculateRent(int days) {
        double rent = rentPerDay * days;
        totalRevenue += rent;
        return rent;
    }

    public void displayVehicleInfo() {
        System.out.println("Vehicle ID   : " + vehicleId);
        System.out.println("Brand        : " + brand);
        System.out.println("Model        : " + model);
        System.out.println("Rent/Day     : " + rentPerDay);
        System.out.println("Available    : " + isAvailable);
    }

    public static void setCompanyName(String name) {
        companyName = name;
    }

    public static double getTotalRevenue() {
        return totalRevenue;
    }

    public static double getAverageRentPerDay() {
        if (rentalDays == 0) return 0;
        return totalRevenue / rentalDays;
    }

    public static void displayCompanyStats() {
        System.out.println("Company Name   : " + companyName);
        System.out.println("Total Vehicles : " + totalVehicles);
        System.out.println("Total Revenue  : " + totalRevenue);
        System.out.println("Total Rental Days: " + rentalDays);
        System.out.println("Average Rent/Day: " + getAverageRentPerDay());
    }

    private static String generateVehicleId() {
        return String.format("V%03d", totalVehicles + 1);
    }
}

public class VehicleRentalSystem {
    public static void main(String[] args) {
        Vehicle.setCompanyName("City Car Rentals");

        Vehicle v1 = new Vehicle("Toyota", "Corolla", 1500);
        Vehicle v2 = new Vehicle("Honda", "Civic", 1800);
        Vehicle v3 = new Vehicle("Ford", "EcoSport", 2000);

        v1.displayVehicleInfo();
        v2.displayVehicleInfo();
        v3.displayVehicleInfo();

        v1.rentVehicle(3);
        v2.rentVehicle(5);

        v1.returnVehicle();
        v1.rentVehicle(2);

        v3.rentVehicle(4);

        v1.displayVehicleInfo();
        v2.displayVehicleInfo();
        v3.displayVehicleInfo();

        Vehicle.displayCompanyStats();
    }
}
