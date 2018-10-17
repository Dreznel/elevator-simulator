package elevator;

public class Passenger {
    public String name;
    public int startingFloor;
    public int destinationFloor;

    public Passenger(int startingFloor, int destinationFloor) {
        this.name = "Employee " + Long.toString(System.currentTimeMillis());
    }

    public boolean isDeparting(int currentFloor) {
        return destinationFloor == currentFloor;
    }
}
