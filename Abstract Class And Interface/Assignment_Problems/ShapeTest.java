abstract class Shape {
    abstract double area();
    abstract double perimeter();

    void displayInfo() {
        System.out.println("This is a shape.");
    }
}

class Circle extends Shape {
    private double radius;

    Circle(double radius) {
        this.radius = radius;
    }

    double area() {
        return Math.PI * radius * radius;
    }

    double perimeter() {
        return 2 * Math.PI * radius;
    }

    void displayInfo() {
        System.out.println("Circle with radius " + radius);
        System.out.println("Area: " + area());
        System.out.println("Perimeter: " + perimeter());
    }
}

class Rectangle extends Shape {
    private double length;
    private double width;

    Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    double area() {
        return length * width;
    }

    double perimeter() {
        return 2 * (length + width);
    }

    void displayInfo() {
        System.out.println("Rectangle with length " + length + " and width " + width);
        System.out.println("Area: " + area());
        System.out.println("Perimeter: " + perimeter());
    }
}

public class ShapeTest {
    public static void main(String[] args) {
        Shape circle = new Circle(5);
        Shape rectangle = new Rectangle(4, 6);

        circle.displayInfo();
        System.out.println();
        rectangle.displayInfo();
    }
}
