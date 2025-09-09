class MovieTicket {
    String movieName;
    String theatreName;
    int seatNumber;
    double price;

    MovieTicket() {
        this("Unknown", "Not Assigned", 0, 0.0);
    }

    MovieTicket(String movieName) {
        this(movieName, "Not Assigned", 0, 200.0);
    }

    MovieTicket(String movieName, int seatNumber) {
        this(movieName, "PVR", seatNumber, 200.0);
    }

    MovieTicket(String movieName, String theatreName, int seatNumber, double price) {
        this.movieName = movieName;
        this.theatreName = theatreName;
        this.seatNumber = seatNumber;
        this.price = price;
    }

    void printTicket() {
        System.out.println("Movie: " + movieName);
        System.out.println("Theatre: " + theatreName);
        System.out.println("Seat: " + seatNumber);
        System.out.println("Price: Rs." + price);

    }
}

public class MovieTicketBooking {
    public static void main(String[] args) {
        MovieTicket t1 = new MovieTicket();
        MovieTicket t2 = new MovieTicket("Inception");
        MovieTicket t3 = new MovieTicket("Interstellar", 12);
        MovieTicket t4 = new MovieTicket("Avatar", "IMAX", 25, 500.0);

        t1.printTicket();
        t2.printTicket();
        t3.printTicket();
        t4.printTicket();
    }
}
