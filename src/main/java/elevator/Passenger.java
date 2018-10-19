package elevator;

import java.util.UUID;

public class Passenger {
    private String name;
    private int startingFloor;
    private int destinationFloor;

    public Passenger(int startingFloor, int destinationFloor) {
        this.name = "Employee " + UUID.randomUUID().toString();;
    }

    public boolean isDeparting(int currentFloor) {
        return destinationFloor == currentFloor;
    }
}
