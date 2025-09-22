class BasicMath {
    int calculate(int a, int b) {
        return a + b;
    }

    double calculate(double a, double b) {
        return a + b;
    }

    int calculate(int a, int b, int c) {
        return a + b + c;
    }
}

class AdvancedMath extends BasicMath {
    double calculate(double a, double b, double c) {
        return a * b * c;
    }

    double calculate(int a, double b) {
        return a - b;
    }

    int calculate(int a) {
        return a * a;
    }
}

public class MathDemo {
    public static void main(String[] args) {
        AdvancedMath m = new AdvancedMath();

        System.out.println("Add (int): " + m.calculate(5, 7));
        System.out.println("Add (double): " + m.calculate(3.2, 4.8));
        System.out.println("Add three ints: " + m.calculate(1, 2, 3));
        System.out.println("Multiply three doubles: " + m.calculate(2.0, 3.0, 4.0));
        System.out.println("Subtract int and double: " + m.calculate(10, 3.5));
        System.out.println("Square int: " + m.calculate(6));
    }
}
