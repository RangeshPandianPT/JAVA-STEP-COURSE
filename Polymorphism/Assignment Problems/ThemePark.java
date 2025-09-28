abstract class Attraction {
    String name;
    double basePrice;

    Attraction(String name, double basePrice) {
        this.name = name;
        this.basePrice = basePrice;
    }

    void visitorEntry(String visitorName) {
        System.out.println(visitorName + " is entering " + name);
    }

    abstract void operate();

    double calculateTicketPrice() {
        return basePrice;
    }

    double calculateTicketPrice(int groupSize) {
        return basePrice * groupSize * 0.9; // 10% discount for groups
    }

    double calculateTicketPrice(boolean isWeekend) {
        return isWeekend ? basePrice * 1.2 : basePrice; // weekend surcharge
    }
}

class RollerCoaster extends Attraction {
    int minHeight;
    String thrillLevel;

    RollerCoaster(String name, double basePrice, int minHeight, String thrillLevel) {
        super(name, basePrice);
        this.minHeight = minHeight;
        this.thrillLevel = thrillLevel;
    }

    @Override
    void operate() {
        System.out.println(name + " is running with thrill level: " + thrillLevel);
    }

    void safetyCheck(int visitorHeight) {
        if (visitorHeight >= minHeight) {
            System.out.println("Visitor meets the height requirement for " + name);
        } else {
            System.out.println("Visitor is too short for " + name);
        }
    }
}

class WaterRide extends Attraction {
    boolean requiresSwim;
    boolean dependsOnWeather;

    WaterRide(String name, double basePrice, boolean requiresSwim, boolean dependsOnWeather) {
        super(name, basePrice);
        this.requiresSwim = requiresSwim;
        this.dependsOnWeather = dependsOnWeather;
    }

    @Override
    void operate() {
        System.out.println(name + " is splashing through waves!");
    }

    void checkWeather(boolean isRaining) {
        if (dependsOnWeather && isRaining) {
            System.out.println(name + " is closed due to bad weather.");
        } else {
            System.out.println(name + " is open.");
        }
    }
}

class Show extends Attraction {
    int seatingCapacity;
    String rating;

    Show(String name, double basePrice, int seatingCapacity, String rating) {
        super(name, basePrice);
        this.seatingCapacity = seatingCapacity;
        this.rating = rating;
    }

    @Override
    void operate() {
        System.out.println(name + " show is starting, rating: " + rating);
    }
}

class Game extends Attraction {
    String skillLevel;
    String prizeTier;

    Game(String name, double basePrice, String skillLevel, String prizeTier) {
        super(name, basePrice);
        this.skillLevel = skillLevel;
        this.prizeTier = prizeTier;
    }

    @Override
    void operate() {
        System.out.println("Playing " + name + " - Skill: " + skillLevel + ", Prize: " + prizeTier);
    }
}

public class ThemePark {
    public static void main(String[] args) {
        Attraction[] attractions = {
            new RollerCoaster("Thunder Loop", 500, 140, "Extreme"),
            new WaterRide("Splash Zone", 300, true, true),
            new Show("Magic Show", 200, 100, "All Ages"),
            new Game("Ring Toss", 100, "Medium", "Gold")
        };

        for (Attraction a : attractions) {
            a.visitorEntry("Alex");
            a.operate();
            System.out.println("Ticket Price: " + a.calculateTicketPrice());
            System.out.println("Group Ticket Price (5): " + a.calculateTicketPrice(5));
            System.out.println("Weekend Price: " + a.calculateTicketPrice(true));

            if (a instanceof RollerCoaster) {
                RollerCoaster rc = (RollerCoaster) a;
                rc.safetyCheck(145);
            } else if (a instanceof WaterRide) {
                WaterRide wr = (WaterRide) a;
                wr.checkWeather(false);
            }
            System.out.println();
        }
    }
}
