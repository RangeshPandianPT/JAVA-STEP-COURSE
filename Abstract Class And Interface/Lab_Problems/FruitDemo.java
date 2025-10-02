abstract class Fruit {
    protected String color;
    protected String taste;

    Fruit(String color, String taste) {
        this.color = color;
        this.taste = taste;
    }

    abstract void showDetails();
}

interface Edible {
    void nutrientsInfo();
}

class Apple extends Fruit implements Edible {
    private String variety;

    Apple(String color, String taste, String variety) {
        super(color, taste);
        this.variety = variety;
    }

    @Override
    void showDetails() {
        System.out.println("Apple Variety: " + variety);
        System.out.println("Color: " + color);
        System.out.println("Taste: " + taste);
    }

    @Override
    public void nutrientsInfo() {
        System.out.println("Rich in fiber, Vitamin C, and antioxidants.");
    }
}

public class FruitDemo {
    public static void main(String[] args) {
        Apple apple = new Apple("Red", "Sweet", "Fuji");
        apple.showDetails();
        apple.nutrientsInfo();
    }
}
