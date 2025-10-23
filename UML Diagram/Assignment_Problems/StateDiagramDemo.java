class Ticket {
    String state;

    Ticket() {
        state = "Created";
        onEntry("Created");
    }

    void bookTicket() {
        if (state.equals("Created")) {
            onExit("Created");
            state = "Booked";
            onEntry("Booked");
        }
    }

    void confirmTicket() {
        if (state.equals("Booked")) {
            onExit("Booked");
            state = "Confirmed";
            onEntry("Confirmed");
        }
    }

    void cancelTicket() {
        if (state.equals("Booked") || state.equals("Confirmed")) {
            onExit(state);
            state = "Cancelled";
            onEntry("Cancelled");
        }
    }

    void expireTicket() {
        if (state.equals("Created") || state.equals("Booked")) {
            onExit(state);
            state = "Expired";
            onEntry("Expired");
        }
    }

    void onEntry(String state) {
        System.out.println("Entering state: " + state);
    }

    void onExit(String state) {
        System.out.println("Exiting state: " + state);
    }

    void showState() {
        System.out.println("Current Ticket State: " + state);
    }
}

public class StateDiagramDemo {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        ticket.bookTicket();
        ticket.confirmTicket();
        ticket.cancelTicket();
    }
}
