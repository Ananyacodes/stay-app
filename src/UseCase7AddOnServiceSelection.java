import java.util.*;

class AddOnService {

    private String serviceName;
    private double cost;

    public AddOnService(String serviceName, double cost) {
        this.serviceName = serviceName;
        this.cost = cost;
    }

    public String getServiceName() {
        return serviceName;
    }

    public double getCost() {
        return cost;
    }

    public String toString() {
        return serviceName + " ($" + cost + ")";
    }
}

class AddOnServiceManager {

    private Map<String, List<AddOnService>> reservationServices;

    public AddOnServiceManager() {
        reservationServices = new HashMap<>();
    }

    public void addService(String reservationId, AddOnService service) {

        reservationServices
                .computeIfAbsent(reservationId, k -> new ArrayList<>())
                .add(service);
    }

    public void displayServices(String reservationId) {

        List<AddOnService> services = reservationServices.get(reservationId);

        if (services == null) {
            System.out.println("No services selected.");
            return;
        }

        double total = 0;

        System.out.println("\nServices for Reservation " + reservationId + ":");

        for (AddOnService s : services) {
            System.out.println("- " + s);
            total += s.getCost();
        }

        System.out.println("Total Add-On Cost: $" + total);
    }
}

public class UseCase7AddOnServiceSelection {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println(" Book My Stay App - Version 7.0 ");
        System.out.println("=================================");

        AddOnServiceManager manager = new AddOnServiceManager();

        String reservationId = "SR101";

        manager.addService(reservationId, new AddOnService("Breakfast", 20));
        manager.addService(reservationId, new AddOnService("Airport Pickup", 40));
        manager.addService(reservationId, new AddOnService("Extra Bed", 30));

        manager.displayServices(reservationId);
    }
}
