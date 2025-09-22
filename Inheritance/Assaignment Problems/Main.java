class Light {
    String type;
    int power;

    Light() {
        this("Generic Light");
        System.out.println("Light default constructor");
    }

    Light(String type) {
        this(type, 0);
        System.out.println("Light with type: " + type);
    }

    Light(String type, int power) {
        this.type = type;
        this.power = power;
        System.out.println("Light with type and power: " + type + ", " + power + "W");
    }
}

class LED extends Light {
    String color;

    LED() {
        this("White");
        System.out.println("LED default constructor");
    }

    LED(String color) {
        this(color, 5);
        System.out.println("LED with color: " + color);
    }

    LED(String color, int power) {
        super("LED", power);
        this.color = color;
        System.out.println("LED with color and power: " + color + ", " + power + "W");
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("--- First Object ---");
        new Light();

        System.out.println("\n--- Second Object ---");
        new LED();

        System.out.println("\n--- Third Object ---");
        new LED("Blue", 10);
    }
}
