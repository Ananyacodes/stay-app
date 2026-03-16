abstract class Room {
    protected int beds;
    protected int size;
    protected double price;
    public Room(int beds, int size, double price) {
        this.beds = beds;
        this.size = size;
        this.price = price;
    }
    public void displayRoomDetails() {
        System.out.println("Beds: " + beds);
        System.out.println("Room Size: " + size + " sqft");
        System.out.println("Price per night: $" + price);
    }
    public abstract String getRoomType();
}
class SingleRoom extends Room {

    public SingleRoom() {
        super(1, 200, 100);
    }

    public String getRoomType() {
        return "Single Room";
    }
}
class DoubleRoom extends Room {

    public DoubleRoom() {
        super(2, 350, 180);
    }

    public String getRoomType() {
        return "Double Room";
    }
}
class SuiteRoom extends Room {
    public SuiteRoom() {
        super(3, 500, 300);
    }
    public String getRoomType() {
        return "Suite Room";
    }
}
public class UseCase2RoomInitialization {
    public static void main(String[] args) {
        System.out.println("====================================");
        System.out.println(" Book My Stay App - Version 2.0 ");
        System.out.println("====================================");
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();
        int singleAvailable = 5;
        int doubleAvailable = 3;
        int suiteAvailable = 2;
        System.out.println("\nRoom Details:\n");
        System.out.println(single.getRoomType());
        single.displayRoomDetails();
        System.out.println("Available Rooms: " + singleAvailable);
        System.out.println("\n-----------------------");
        System.out.println(doubleRoom.getRoomType());
        doubleRoom.displayRoomDetails();
        System.out.println("Available Rooms: " + doubleAvailable);
        System.out.println("\n-----------------------");
        System.out.println(suite.getRoomType());
        suite.displayRoomDetails();
        System.out.println("Available Rooms: " + suiteAvailable);
        System.out.println("\nApplication Terminated.");
    }
}
