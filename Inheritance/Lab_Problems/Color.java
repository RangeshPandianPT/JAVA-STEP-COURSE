class Color {
    protected String name;

    public Color(String name) {
        this.name = name;
        System.out.println("Color constructor: " + name);
    }
}

class PrimaryColor extends Color {
    protected int intensity;

    public PrimaryColor(String name, int intensity) {
        super(name);
        this.intensity = intensity;
        System.out.println("PrimaryColor constructor: intensity " + intensity);
    }
}

class RedColor extends PrimaryColor {
    private String shade;

    public RedColor(String name, int intensity, String shade) {
        super(name, intensity);
        this.shade = shade;
        System.out.println("RedColor constructor: shade " + shade);
    }

    public void showDetails() {
        System.out.println("Name: " + name);
        System.out.println("Intensity: " + intensity);
        System.out.println("Shade: " + shade);
    }
}

public class Main {
    public static void main(String[] args) {
        RedColor red = new RedColor("Red", 90, "Crimson");
        red.showDetails();
    }
}
