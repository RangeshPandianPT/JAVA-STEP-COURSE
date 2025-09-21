class Instrument {
    protected String name;
    protected String material;

    public Instrument(String name, String material) {
        this.name = name;
        this.material = material;
    }

    public void play() {
        System.out.println(name + " is playing.");
    }
}

class Piano extends Instrument {
    private int keys;

    public Piano(String name, String material, int keys) {
        super(name, material);
        this.keys = keys;
    }

    @Override
    public void play() {
        System.out.println(name + " with " + keys + " keys is playing a melody.");
    }
}

class Guitar extends Instrument {
    private int strings;

    public Guitar(String name, String material, int strings) {
        super(name, material);
        this.strings = strings;
    }

    @Override
    public void play() {
        System.out.println(name + " with " + strings + " strings is strumming.");
    }
}

class Drum extends Instrument {
    private double diameter;

    public Drum(String name, String material, double diameter) {
        super(name, material);
        this.diameter = diameter;
    }

    @Override
    public void play() {
        System.out.println(name + " with diameter " + diameter + " inches is beating rhythm.");
    }
}

public class Main {
    public static void main(String[] args) {
        Instrument[] instruments = {
            new Piano("Grand Piano", "Wood", 88),
            new Guitar("Acoustic Guitar", "Maple", 6),
            new Drum("Bass Drum", "Metal", 22.5)
        };

        for (Instrument i : instruments) {
            i.play();
        }
    }
}
