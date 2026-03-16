import java.util.*;

class CancellationService {

    private Map<String, Integer> inventory;
    private Map<String, String> confirmedBookings;
    private Stack<String> releasedRoomIds;

    public CancellationService() {

        inventory = new HashMap<>();
        confirmedBookings = new HashMap<>();
        releasedRoomIds = new Stack<>();

        inventory.put("Single Room", 1);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 1);

        confirmedBookings.put("R101", "Single Room");
        confirmedBookings.put("R102", "Double Room");
    }

    public void cancelBooking(String reservationId) {

        if (!confirmedBookings.containsKey(reservationId)) {
            System.out.println("Cancellation failed: Reservation not found.");
            return;
        }

        String roomType = confirmedBookings.remove(reservationId);

        inventory.put(roomType, inventory.get(roomType) + 1);

        releasedRoomIds.push(reservationId);

        System.out.println("Booking cancelled for reservation ID: " + reservationId);
    }

    public void showInventory() {

        System.out.println("\nCurrent Inventory:");

        for (String type : inventory.keySet()) {
            System.out.println(type + " → " + inventory.get(type));
        }
    }

    public void showRollbackStack() {

        System.out.println("\nReleased Room IDs (Rollback Stack):");

        for (String id : releasedRoomIds) {
            System.out.println(id);
        }
    }
}

public class UseCase10BookingCancellation {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println(" Book My Stay App - Version 10.0 ");
        System.out.println("=================================");

        CancellationService service = new CancellationService();

        service.cancelBooking("R101");

        service.cancelBooking("R999");

        service.showInventory();

        service.showRollbackStack();
    }
}