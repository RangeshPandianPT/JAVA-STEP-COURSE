class Tool {
    private String brand;
    protected String type;
    public int weight;

    Tool(String brand, String type, int weight) {
        this.brand = brand;
        this.type = type;
        this.weight = weight;
    }

    String getBrand() {
        return brand;
    }
}

class Hammer extends Tool {
    Hammer(String brand, String type, int weight) {
        super(brand, type, weight);
    }

    void showAccess() {
        System.out.println("Brand: " + getBrand()); 
        System.out.println("Type: " + type);       
        System.out.println("Weight: " + weight);   
    }
}

public class AccessTool {
    public static void main(String[] args) {
        Hammer h = new Hammer("Stanley", "Claw Hammer", 2);
        h.showAccess();
    }
}
