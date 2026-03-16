import java.util.*;

class ConcurrentBookingProcessor {

    private Queue<Reservation> bookingQueue;
    private Map<String, Integer> inventory;

    public ConcurrentBookingProcessor() {

        bookingQueue = new LinkedList<>();
        inventory = new HashMap<>();

        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 1);
    }

    public synchronized void addRequest(Reservation reservation) {
        bookingQueue.offer(reservation);
    }

    public synchronized Reservation getNextRequest() {
        return bookingQueue.poll();
    }

    public synchronized void allocateRoom(Reservation reservation) {

        String roomType = reservation.getRoomType();
        int available = inventory.getOrDefault(roomType, 0);

        if (available > 0) {
            inventory.put(roomType, available - 1);
            System.out.println(Thread.currentThread().getName() +
                    " confirmed booking for " + reservation.getGuestName() +
                    " (" + roomType + ")");
        } else {
            System.out.println(Thread.currentThread().getName() +
                    " failed booking for " + reservation.getGuestName() +
                    " (" + roomType + ") - No rooms available");
        }
    }

    public synchronized void displayInventory() {

        System.out.println("\nFinal Inventory:");

        for (String type : inventory.keySet()) {
            System.out.println(type + " → " + inventory.get(type));
        }
    }
}

class BookingWorker extends Thread {

    private ConcurrentBookingProcessor processor;

    public BookingWorker(ConcurrentBookingProcessor processor, String name) {
        super(name);
        this.processor = processor;
    }

    public void run() {

        Reservation reservation;

        while ((reservation = processor.getNextRequest()) != null) {
            processor.allocateRoom(reservation);
        }
    }
}

public class UseCase11ConcurrentBookingSimulation {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println(" Book My Stay App - Version 11.0 ");
        System.out.println("=================================");

        ConcurrentBookingProcessor processor = new ConcurrentBookingProcessor();

        processor.addRequest(new Reservation("Alice", "Single Room"));
        processor.addRequest(new Reservation("Bob", "Single Room"));
        processor.addRequest(new Reservation("Charlie", "Single Room"));
        processor.addRequest(new Reservation("Diana", "Double Room"));
        processor.addRequest(new Reservation("Ethan", "Suite Room"));

        BookingWorker t1 = new BookingWorker(processor, "Thread-1");
        BookingWorker t2 = new BookingWorker(processor, "Thread-2");
        BookingWorker t3 = new BookingWorker(processor, "Thread-3");

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        processor.displayInventory();
    }
}