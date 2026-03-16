import java.util.*;

class InvalidBookingException extends Exception {

    public InvalidBookingException(String message) {
        super(message);
    }
}

class BookingValidator {

    private Set<String> validRoomTypes;

    public BookingValidator() {
        validRoomTypes = new HashSet<>();
        validRoomTypes.add("Single Room");
        validRoomTypes.add("Double Room");
        validRoomTypes.add("Suite Room");
    }

    public void validate(String guestName, String roomType, int availableRooms) throws InvalidBookingException {

        if (guestName == null || guestName.trim().isEmpty()) {
            throw new InvalidBookingException("Guest name cannot be empty.");
        }

        if (!validRoomTypes.contains(roomType)) {
            throw new InvalidBookingException("Invalid room type selected: " + roomType);
        }

        if (availableRooms <= 0) {
            throw new InvalidBookingException("No rooms available for type: " + roomType);
        }
    }
}

class SafeBookingService {

    private Map<String, Integer> inventory;
    private BookingValidator validator;

    public SafeBookingService() {

        inventory = new HashMap<>();

        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 0);

        validator = new BookingValidator();
    }

    public void processBooking(String guestName, String roomType) {

        try {

            int available = inventory.getOrDefault(roomType, 0);

            validator.validate(guestName, roomType, available);

            inventory.put(roomType, available - 1);

            System.out.println("Booking confirmed for " + guestName + " (" + roomType + ")");

        } catch (InvalidBookingException e) {

            System.out.println("Booking failed: " + e.getMessage());

        }
    }

    public void displayInventory() {

        System.out.println("\nCurrent Inventory:");

        for (String type : inventory.keySet()) {
            System.out.println(type + " → " + inventory.get(type));
        }
    }
}

public class UseCase9ErrorHandlingValidation {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println(" Book My Stay App - Version 9.0 ");
        System.out.println("=================================");

        SafeBookingService bookingService = new SafeBookingService();

        bookingService.processBooking("Alice", "Single Room");

        bookingService.processBooking("", "Double Room");

        bookingService.processBooking("Bob", "Luxury Room");

        bookingService.processBooking("Charlie", "Suite Room");

        bookingService.displayInventory();
    }
}