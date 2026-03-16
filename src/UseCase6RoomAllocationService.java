import java.util.*;

class BookingService {

    private Queue<Reservation> requestQueue;
    private RoomInventory inventory;
    private HashMap<String, Set<String>> allocatedRooms = new HashMap<>();
    private Set<String> allAllocatedIds = new HashSet<>();
    private int counter = 1;

    public BookingService(RoomInventory inventory) {
        this.inventory = inventory;
        requestQueue = new LinkedList<>();
    }

    public void addRequest(Reservation r) {
        requestQueue.offer(r);
    }

    private String generateRoomId(String type) {
        String id = type.substring(0,2).toUpperCase() + counter++;
        while(allAllocatedIds.contains(id)){
            id = type.substring(0,2).toUpperCase() + counter++;
        }
        allAllocatedIds.add(id);
        return id;
    }

    public void processBookings(){

        while(!requestQueue.isEmpty()){

            Reservation r = requestQueue.poll();
            String type = r.getRoomType();

            if(inventory.getAvailability(type) > 0){

                String id = generateRoomId(type);

                allocatedRooms
                        .computeIfAbsent(type,k->new HashSet<>())
                        .add(id);

                inventory.decrementRoom(type);

                System.out.println("Confirmed for "+r.getGuestName()+" RoomID:"+id);
            }
            else{
                System.out.println("No room available for "+r.getGuestName());
            }
        }
    }
}

public class UseCase6RoomAllocationService {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();
        BookingService service = new BookingService(inventory);

        service.addRequest(new Reservation("Alice","Single Room"));
        service.addRequest(new Reservation("Bob","Double Room"));
        service.addRequest(new Reservation("Charlie","Suite Room"));

        service.processBookings();
    }
}