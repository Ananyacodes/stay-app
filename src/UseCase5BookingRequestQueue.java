import java.util.LinkedList;
import java.util.Queue;

class Reservation {

    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    public String toString() {
        return guestName + " requested " + roomType;
    }
}

class BookingRequestQueue {

    private Queue<Reservation> queue;

    public BookingRequestQueue() {
        queue = new LinkedList<>();
    }

    public void addRequest(Reservation reservation) {
        queue.offer(reservation);
    }

    public void displayRequests() {

        System.out.println("\nBooking Requests (FIFO Order):\n");

        for (Reservation r : queue) {
            System.out.println(r);
        }
    }
}

public class UseCase5BookingRequestQueue {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println(" Book My Stay App - Version 5.0 ");
        System.out.println("=================================");

        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        bookingQueue.addRequest(new Reservation("Alice", "Single Room"));
        bookingQueue.addRequest(new Reservation("Bob", "Double Room"));
        bookingQueue.addRequest(new Reservation("Charlie", "Suite Room"));
        bookingQueue.addRequest(new Reservation("Diana", "Single Room"));

        bookingQueue.displayRequests();

        System.out.println("\nRequests stored in arrival order. Awaiting allocation.");
    }
}