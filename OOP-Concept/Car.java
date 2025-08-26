import java.time.Year;

public class Car {
    String brand;
    String model;
    int year;
    String color;
    boolean isRunning;

    public Car(String brand, String model, int year, String color) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.color = color;
        this.isRunning = false; // default: engine is off
    }
    public void startEngine() {
        if (!isRunning) {
            isRunning = true;
            System.out.println(brand + " " + model + " engine started.");
        } else {
            System.out.println(brand + " " + model + " engine is already running.");
        }
    }

    public void stopEngine() {
        if (isRunning) {
            isRunning = false;
            System.out.println(brand + " " + model + " engine stopped.");
        } else {
            System.out.println(brand + " " + model + " engine is already off.");
        }
    }
    public void displayInfo() {
        System.out.println("Car Info: " + year + " " + brand + " " + model + " (" + color + ")");
        System.out.println("Engine Running: " + isRunning);
    }

    public int getAge() {
        int currentYear = Year.now().getValue();
        return currentYear - year;
    }

    public static void main(String[] args) {
        Car car1 = new Car("Toyota", "Corolla", 2018, "Red");
        Car car2 = new Car("Tesla", "Model 3", 2022, "White");
        Car car3 = new Car("Ford", "Mustang", 2015, "Blue");

        car1.displayInfo();
        car1.startEngine();
        car1.stopEngine();
        System.out.println("Car age: " + car1.getAge() + " years\n");

        car2.displayInfo();
        car2.startEngine();
        System.out.println("Car age: " + car2.getAge() + " years\n");

        car3.displayInfo();
        car3.startEngine();
        car3.stopEngine();
        System.out.println("Car age: " + car3.getAge() + " years\n");

        /*
         ✅ Real-world analogy explanation:
         - Each Car object is like a real car in the world.
         - They share the same "class" (blueprint) but have different attributes (brand, model, color).
         - Actions like startEngine() and stopEngine() affect only that specific car.
         - Just like in real life, one car can be running while another is parked.
        */
    }
}
