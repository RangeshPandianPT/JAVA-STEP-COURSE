abstract class Food {
    public final void prepare() {
        wash();
        cook();
        serve();
    }

    abstract void wash();
    abstract void cook();
    abstract void serve();
}

class Pizza extends Food {
    @Override
    void wash() {
        System.out.println("Washing vegetables and dough ingredients...");
    }

    @Override
    void cook() {
        System.out.println("Baking pizza in oven...");
    }

    @Override
    void serve() {
        System.out.println("Serving pizza in slices.");
    }
}

class Soup extends Food {
    @Override
    void wash() {
        System.out.println("Washing vegetables for soup...");
    }

    @Override
    void cook() {
        System.out.println("Boiling ingredients into soup...");
    }

    @Override
    void serve() {
        System.out.println("Serving soup in a bowl.");
    }
}

public class FoodDemo {
    public static void main(String[] args) {
        Food f1 = new Pizza();
        Food f2 = new Soup();

        System.out.println("--- Preparing Pizza ---");
        f1.prepare();

        System.out.println("\n--- Preparing Soup ---");
        f2.prepare();
    }
}
