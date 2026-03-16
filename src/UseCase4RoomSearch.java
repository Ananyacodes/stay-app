public class UseCase4RoomSearch {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println(" Book My Stay App - Version 4.0 ");
        System.out.println("=================================");

        RoomInventory inventory = new RoomInventory();

        Room[] rooms = {
                new SingleRoom(),
                new DoubleRoom(),
                new SuiteRoom()
        };

        System.out.println("\nAvailable Rooms:\n");

        for (Room room : rooms) {

            int available = inventory.getAvailability(room.getRoomType());

            if (available > 0) {

                System.out.println(room.getRoomType());
                room.displayRoomDetails();
                System.out.println("Available Rooms: " + available);
                System.out.println("-----------------------");
            }
        }

        System.out.println("\nSearch completed. System state unchanged.");
    }
}