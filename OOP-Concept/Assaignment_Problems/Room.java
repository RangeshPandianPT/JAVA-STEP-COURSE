import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
class Room {
    String roomNumber, roomType;
    double pricePerNight;
    boolean isAvailable; 
    int maxOccupancy;

    Room(String num, String type, double price, int maxOcc) {
        roomNumber = num; roomType = type; pricePerNight = price; maxOccupancy = maxOcc; isAvailable = true;
    }

    void display() {
        System.out.printf("%-5s %-10s %-6.0f %-3d %s%n", roomNumber, roomType, pricePerNight, maxOccupancy, (isAvailable?"OPEN":"BLOCKED"));
    }
}

class Guest {
    String guestId, guestName, phoneNumber, email;
    String[] bookingHistory = new String[20];
    int histPtr = 0;

    Guest(String id, String name, String phone, String email) {
        this.guestId = id; this.guestName = name; this.phoneNumber = phone; this.email = email;
    }
    void addHistory(String bookingId){ if(histPtr < bookingHistory.length) bookingHistory[histPtr++] = bookingId; }
    void display(){ System.out.printf("%-5s %-16s %-12s %-20s%n", guestId, guestName, phoneNumber, email); }
}

class Booking {
    String bookingId;
    Guest guest;
    Room room;
    String checkInDate, checkOutDate; 
    double totalAmount;

    Booking(String id, Guest g, Room r, String in, String out, double amount) {
        bookingId = id; guest = g; room = r; checkInDate = in; checkOutDate = out; totalAmount = amount;
    }
}

class Hotel {
    static String hotelName = "Sunrise Inn";
    static int totalBookings = 0;
    static double hotelRevenue = 0.0;

    static DateTimeFormatter F = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    static List<Room> rooms = new ArrayList<>();
    static List<Guest> guests = new ArrayList<>();
    static List<Booking> bookings = new ArrayList<>();

    static LocalDate d(String s){ return LocalDate.parse(s, F); }
    static long nights(String in, String out){ return Duration.between(d(in).atStartOfDay(), d(out).atStartOfDay()).toDays(); }

    static Guest findGuest(String guestId){
        for(Guest g: guests) if(g.guestId.equalsIgnoreCase(guestId)) return g; return null;
    }
    static Room findRoom(String roomNumber){
        for(Room r: rooms) if(r.roomNumber.equalsIgnoreCase(roomNumber)) return r; return null;
    }
    static Booking findBooking(String bookingId){
        for(Booking b: bookings) if(b.bookingId.equalsIgnoreCase(bookingId)) return b; return null;
    }
    static boolean checkAvailability(Room room, String in, String out){
        if(room==null || !room.isAvailable) return false;
        LocalDate ci = d(in), co = d(out);
        for(Booking b: bookings){
            if(b.room.roomNumber.equals(room.roomNumber)){
                LocalDate bi = d(b.checkInDate), bo = d(b.checkOutDate);
                boolean overlap = !(co.compareTo(bi) <= 0 || ci.compareTo(bo) >= 0);
                if(overlap) return false;
            }
        }
        return true;
    }
    static double calculateBill(Room room, String in, String out){
        long n = Math.max(1, nights(in, out));
        return n * room.pricePerNight;
    }
    static Booking makeReservation(String guestId, String roomNumber, String in, String out){
        Guest g = findGuest(guestId);
        Room r = findRoom(roomNumber);
        if(g==null || r==null) { System.out.println("Guest/Room not found."); return null; }
        if(!checkAvailability(r, in, out)){ System.out.println("Room not available for given dates."); return null; }
        double amt = calculateBill(r, in, out);
        String id = "B" + String.format("%04d", ++totalBookings);
        Booking b = new Booking(id, g, r, in, out, amt);
        bookings.add(b);
        g.addHistory(id);
        System.out.println("Booking confirmed: " + id + " | Amount: " + amt);
        return b;
    }

    static void cancelReservation(String bookingId){
        Booking b = findBooking(bookingId);
        if(b==null){ System.out.println("Booking not found."); return; }
        bookings.remove(b);
        System.out.println("Booking " + bookingId + " cancelled.");
    }

    static void checkout(String bookingId){
        Booking b = findBooking(bookingId);
        if(b==null){ System.out.println("Booking not found."); return; }
        hotelRevenue += b.totalAmount;
        bookings.remove(b);
        System.out.println("Checked out " + bookingId + ". Paid: " + b.totalAmount);
    }
    static double getOccupancyRate(){
        LocalDate today = LocalDate.now();
        int occ = 0;
        for(Room r: rooms){
            for(Booking b: bookings){
                if(b.room.roomNumber.equals(r.roomNumber)){
                    if(! (today.isBefore(d(b.checkInDate)) || !today.isBefore(d(b.checkOutDate)))) { occ++; break; }
                    if(!today.isBefore(d(b.checkInDate)) && today.isBefore(d(b.checkOutDate))) { occ++; break; }
                }
            }
        }
        return rooms.isEmpty()?0: (occ*100.0/rooms.size());
    }
    static double getTotalRevenue(){ return hotelRevenue; }

    static String getMostPopularRoomType(){
        Map<String,Integer> count = new HashMap<>();
        for(Booking b: bookings) count.merge(b.room.roomType, 1, Integer::sum);
        return count.entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse("N/A");
    }
}
public class HotelApp {
    static Scanner sc = new Scanner(System.in);

    static void seedData(){
        Hotel.rooms.addAll(Arrays.asList(
            new Room("101","Single",2500,1),
            new Room("102","Single",2500,1),
            new Room("201","Double",3500,2),
            new Room("202","Double",3500,2),
            new Room("301","Suite",7000,4),
            new Room("302","Suite",7000,4),
            new Room("401","Deluxe",5000,3),
            new Room("402","Deluxe",5000,3)
        ));
        addGuest("G0001","Aarav Mehta","9876543210","aarav@example.com");
        addGuest("G0002","Isha Rao","9123456789","isha@example.com");
    }

    static void addGuest(String id, String name, String phone, String email){
        Hotel.guests.add(new Guest(id,name,phone,email));
    }

    static void listRooms(){
        System.out.println("\nRooms @ "+Hotel.hotelName);
        System.out.printf("%-5s %-10s %-6s %-3s %s%n","No","Type","Price","Cap","Status");
        for(Room r: Hotel.rooms) r.display();
    }

    static void listGuests(){
        System.out.println("\nGuests");
        System.out.printf("%-5s %-16s %-12s %-20s%n","ID","Name","Phone","Email");
        for(Guest g: Hotel.guests) g.display();
    }

    static void searchAvailable(){
        System.out.print("Enter roomType (Single/Double/Deluxe/Suite): ");
        String type = sc.nextLine().trim();
        System.out.print("Check-in (yyyy-MM-dd): "); String in = sc.nextLine().trim();
        System.out.print("Check-out(yyyy-MM-dd): "); String out = sc.nextLine().trim();
        System.out.println("\nAvailable rooms:");
        for(Room r: Hotel.rooms){
            if(r.roomType.equalsIgnoreCase(type) && Hotel.checkAvailability(r, in, out))
                r.display();
        }
    }

    static void makeBooking(){
        System.out.print("Guest ID: "); String gid = sc.nextLine().trim();
        System.out.print("Room Number: "); String rn = sc.nextLine().trim();
        System.out.print("Check-in (yyyy-MM-dd): "); String in = sc.nextLine().trim();
        System.out.print("Check-out(yyyy-MM-dd): "); String out = sc.nextLine().trim();
        Hotel.makeReservation(gid, rn, in, out);
    }

    static void cancelBooking(){
        System.out.print("Booking ID to cancel: "); String bid = sc.nextLine().trim();
        Hotel.cancelReservation(bid);
    }

    static void checkout(){
        System.out.print("Booking ID to checkout: "); String bid = sc.nextLine().trim();
        Hotel.checkout(bid);
    }

    static void showReports(){
        System.out.printf("Occupancy Rate: %.1f%%%n", Hotel.getOccupancyRate());
        System.out.println("Total Revenue : " + Hotel.getTotalRevenue());
        System.out.println("Popular Type  : " + Hotel.getMostPopularRoomType());
    }

    public static void main(String[] args){
        seedData();
        while(true){
            System.out.println("\n=== "+Hotel.hotelName+" Reservation System ===");
            System.out.println("1.List Rooms  2.List Guests  3.Search Availability");
            System.out.println("4.Make Reservation  5.Cancel Reservation  6.Checkout");
            System.out.println("7.Add Guest   8.Reports     9.Exit");
            System.out.print("Choice: ");
            String ch = sc.nextLine().trim();
            switch(ch){
                case "1": listRooms(); break;
                case "2": listGuests(); break;
                case "3": searchAvailable(); break;
                case "4": makeBooking(); break;
                case "5": cancelBooking(); break;
                case "6": checkout(); break;
                case "7":
                    System.out.print("New Guest ID: "); String id=sc.nextLine();
                    System.out.print("Name: "); String name=sc.nextLine();
                    System.out.print("Phone: "); String ph=sc.nextLine();
                    System.out.print("Email: "); String em=sc.nextLine();
                    addGuest(id,name,ph,em); System.out.println("Guest added."); break;
                case "8": showReports(); break;
                case "9": System.out.println("Bye!"); return;
                default: System.out.println("Invalid.");
            }
        }
    }
}
