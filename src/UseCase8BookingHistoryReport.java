import java.util.*;

class BookingHistory {

    private List<Reservation> confirmedBookings;

    public BookingHistory() {
        confirmedBookings = new ArrayList<>();
    }

    public void addBooking(Reservation reservation) {
        confirmedBookings.add(reservation);
    }

    public List<Reservation> getBookings() {
        return confirmedBookings;
    }
}

class BookingReportService {

    public void generateReport(List<Reservation> bookings) {

        System.out.println("\n=== Booking History Report ===\n");

        Map<String, Integer> roomTypeCount = new HashMap<>();

        for (Reservation r : bookings) {

            System.out.println(r.getGuestName() + " booked " + r.getRoomType());

            roomTypeCount.put(
                    r.getRoomType(),
                    roomTypeCount.getOrDefault(r.getRoomType(), 0) + 1
            );
        }

        System.out.println("\n--- Summary ---");

        for (String type : roomTypeCount.keySet()) {
            System.out.println(type + ": " + roomTypeCount.get(type) + " bookings");
        }
    }
}

public class UseCase8BookingHistoryReport {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println(" Book My Stay App - Version 8.0 ");
        System.out.println("=================================");

        BookingHistory history = new BookingHistory();

        history.addBooking(new Reservation("Alice", "Single Room"));
        history.addBooking(new Reservation("Bob", "Double Room"));
        history.addBooking(new Reservation("Charlie", "Suite Room"));
        history.addBooking(new Reservation("Diana", "Single Room"));

        BookingReportService reportService = new BookingReportService();

        reportService.generateReport(history.getBookings());
    }
}
