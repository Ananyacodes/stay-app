import java.io.*;
import java.util.*;

class SystemState implements Serializable {

    private Map<String, Integer> inventory;
    private List<Reservation> bookings;

    public SystemState(Map<String, Integer> inventory, List<Reservation> bookings) {
        this.inventory = inventory;
        this.bookings = bookings;
    }

    public Map<String, Integer> getInventory() {
        return inventory;
    }

    public List<Reservation> getBookings() {
        return bookings;
    }
}

class PersistenceService {

    private static final String FILE_NAME = "hotel_state.dat";

    public void saveState(SystemState state) {

        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
            out.writeObject(state);
            out.close();
            System.out.println("System state saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving system state.");
        }
    }

    public SystemState loadState() {

        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME));
            SystemState state = (SystemState) in.readObject();
            in.close();
            System.out.println("System state restored successfully.");
            return state;
        } catch (Exception e) {
            System.out.println("No previous state found. Starting fresh.");
            return null;
        }
    }
}

public class UseCase12DataPersistenceRecovery {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println(" Book My Stay App - Version 12.0 ");
        System.out.println("=================================");

        PersistenceService persistence = new PersistenceService();

        SystemState recoveredState = persistence.loadState();

        Map<String, Integer> inventory;
        List<Reservation> bookings;

        if (recoveredState != null) {

            inventory = recoveredState.getInventory();
            bookings = recoveredState.getBookings();

        } else {

            inventory = new HashMap<>();
            inventory.put("Single Room", 2);
            inventory.put("Double Room", 1);
            inventory.put("Suite Room", 1);

            bookings = new ArrayList<>();
            bookings.add(new Reservation("Alice", "Single Room"));
            bookings.add(new Reservation("Bob", "Double Room"));
        }

        System.out.println("\nCurrent Inventory:");

        for (String type : inventory.keySet()) {
            System.out.println(type + " → " + inventory.get(type));
        }

        System.out.println("\nBooking History:");

        for (Reservation r : bookings) {
            System.out.println(r);
        }

        SystemState currentState = new SystemState(inventory, bookings);

        persistence.saveState(currentState);
    }
}
