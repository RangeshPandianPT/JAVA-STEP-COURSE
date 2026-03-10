import java.util.*;

public class ParkingLot {

    static class ParkingSpot {
        String plate;
        long entryTime;
        boolean occupied;

        ParkingSpot() {
            this.plate = null;
            this.entryTime = 0;
            this.occupied = false;
        }
    }

    private ParkingSpot[] table;
    private int capacity;
    private int occupiedSpots;
    private int totalProbes;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        table = new ParkingSpot[capacity];
        for (int i = 0; i < capacity; i++) {
            table[i] = new ParkingSpot();
        }
        occupiedSpots = 0;
        totalProbes = 0;
    }

    private int hash(String plate) {
        return Math.abs(plate.hashCode()) % capacity;
    }

    public void parkVehicle(String plate) {

        int index = hash(plate);
        int probes = 0;

        while (table[index].occupied) {
            index = (index + 1) % capacity;
            probes++;
        }

        table[index].plate = plate;
        table[index].entryTime = System.currentTimeMillis();
        table[index].occupied = true;

        occupiedSpots++;
        totalProbes += probes;

        System.out.println("Vehicle " + plate + " parked at spot " + index + " (" + probes + " probes)");
    }

    public void exitVehicle(String plate) {

        int index = hash(plate);

        while (table[index].occupied) {

            if (plate.equals(table[index].plate)) {

                long duration = (System.currentTimeMillis() - table[index].entryTime) / 1000;

                double fee = duration * 0.01;

                table[index].occupied = false;
                table[index].plate = null;

                occupiedSpots--;

                System.out.println("Vehicle " + plate + " exited spot " + index + ", duration " + duration + " sec, fee $" + fee);

                return;
            }

            index = (index + 1) % capacity;
        }

        System.out.println("Vehicle not found");
    }

    public void getStatistics() {

        double occupancy = ((double) occupiedSpots / capacity) * 100;

        double avgProbes = occupiedSpots == 0 ? 0 : (double) totalProbes / occupiedSpots;

        System.out.println("Occupancy: " + occupancy + "%");
        System.out.println("Average Probes: " + avgProbes);
    }

    public static void main(String[] args) {

        ParkingLot lot = new ParkingLot(500);

        lot.parkVehicle("ABC-1234");
        lot.parkVehicle("ABC-1235");
        lot.parkVehicle("XYZ-9999");

        lot.exitVehicle("ABC-1234");

        lot.getStatistics();
    }
}