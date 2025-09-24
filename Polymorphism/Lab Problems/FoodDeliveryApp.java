class FoodDelivery {
    double calculateDelivery(double distance) {
        double cost = distance * 5;
        System.out.println("Basic Delivery: Distance = " + distance + " km, Cost = ₹" + cost);
        return cost;
    }

    double calculateDelivery(double distance, double priorityFee) {
        double cost = (distance * 5) + priorityFee;
        System.out.println("Premium Delivery: Distance = " + distance + " km + Priority Fee = ₹" + priorityFee + ", Cost = ₹" + cost);
        return cost;
    }

    double calculateDelivery(double distance, int numberOfOrders) {
        double baseCost = distance * 5;
        double discount = numberOfOrders * 2;
        double cost = baseCost - discount;
        if (cost < 0) cost = 0;
        System.out.println("Group Delivery: Distance = " + distance + " km, Orders = " + numberOfOrders + ", Discount = ₹" + discount + ", Cost = ₹" + cost);
        return cost;
    }

    double calculateDelivery(double distance, double discountPercent, double orderAmount) {
        double cost = distance * 5;
        if (orderAmount > 500) {
            cost = 0;
            System.out.println("Festival Special: Free Delivery on order above ₹500! Cost = ₹0");
        } else {
            double discount = (cost * discountPercent) / 100;
            cost -= discount;
            System.out.println("Festival Special: Distance = " + distance + " km, Discount = " + discountPercent + "%, Final Cost = ₹" + cost);
        }
        return cost;
    }
}

public class FoodDeliveryApp {
    public static void main(String[] args) {
        FoodDelivery fd = new FoodDelivery();
        fd.calculateDelivery(10);
        fd.calculateDelivery(8, 50);
        fd.calculateDelivery(12, 4);
        fd.calculateDelivery(15, 20, 450);
        fd.calculateDelivery(20, 15, 600);
    }
}
