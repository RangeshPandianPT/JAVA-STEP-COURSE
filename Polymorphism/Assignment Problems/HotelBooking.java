class HotelBooking {

    public void calculatePrice(String roomType, int nights) {
        double basePrice = getBasePrice(roomType);
        double total = basePrice * nights;

        System.out.println("Standard Booking:");
        System.out.println("Room Type: " + roomType);
        System.out.println("Nights: " + nights);
        System.out.println("Total: ₹" + total);
    }

    public void calculatePrice(String roomType, int nights, double seasonalMultiplier) {
        double basePrice = getBasePrice(roomType);
        double total = (basePrice * nights) * seasonalMultiplier;

        System.out.println("Seasonal Booking:");
        System.out.println("Room Type: " + roomType);
        System.out.println("Nights: " + nights);
        System.out.println("Seasonal Multiplier: " + seasonalMultiplier);
        System.out.println("Total: ₹" + total);
    }
    public void calculatePrice(String roomType, int nights, double corporateDiscount, boolean mealPackage) {
        double basePrice = getBasePrice(roomType) * nights;
        double discount = basePrice * corporateDiscount;
        double meals = mealPackage ? 500 * nights : 0;
        double total = basePrice - discount + meals;

        System.out.println("Corporate Booking:");
        System.out.println("Room Type: " + roomType);
        System.out.println("Nights: " + nights);
        System.out.println("Corporate Discount: ₹" + discount);
        System.out.println("Meal Package: ₹" + meals);
        System.out.println("Total: ₹" + total);
    }

    public void calculatePrice(String roomType, int nights, int guestCount, double decorationFee, double cateringPerGuest) {
        double basePrice = getBasePrice(roomType) * nights;
        double catering = cateringPerGuest * guestCount;
        double total = basePrice + decorationFee + catering;

        System.out.println("Wedding Booking:");
        System.out.println("Room Type: " + roomType);
        System.out.println("Nights: " + nights);
        System.out.println("Guest Count: " + guestCount);
        System.out.println("Decoration Fee: ₹" + decorationFee);
        System.out.println("Catering: ₹" + catering);
        System.out.println("Total: ₹" + total);
    }

    private double getBasePrice(String roomType) {
        switch (roomType.toLowerCase()) {
            case "single": return 2000;
            case "double": return 3500;
            case "suite": return 6000;
            default: return 2500;
        }
    }
    public static void main(String[] args) {
        HotelBooking booking = new HotelBooking();

        booking.calculatePrice("Single", 3);
        booking.calculatePrice("Double", 2, 1.5);
        booking.calculatePrice("Suite", 5, 0.2, true);
        booking.calculatePrice("Double", 2, 100, 5000, 800);
    }
}
