abstract class Shape {
    protected double area;
    protected double perimeter;

    abstract void calculateArea();
    abstract void calculatePerimeter();
}

interface Drawable {
    void draw();
}

class Circle extends Shape implements Drawable {
    private double radius;

    Circle(double radius) {
        this.radius = radius;
    }

    @Override
    void calculateArea() {
        area = Math.PI * radius * radius;
    }

    @Override
    void calculatePerimeter() {
        perimeter = 2 * Math.PI * radius;
    }

    @Override
    public void draw() {
        System.out.println("Drawing a circle with radius " + radius);
    }

    void showDetails() {
        System.out.println("Circle Details:");
        System.out.println("Radius: " + radius);
        System.out.println("Area: " + area);
        System.out.println("Perimeter: " + perimeter);
    }
}

public class ShapeDemo {
    public static void main(String[] args) {
        Circle circle = new Circle(7);
        circle.calculateArea();
        circle.calculatePerimeter();
        circle.draw();
        circle.showDetails();
    }
}
