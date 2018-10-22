package passenger;

import contracts.Actionable;
import elevator.Direction;
import elevator.ElevatorCall;

import java.util.UUID;

public class Passenger implements Actionable {
    private String name;
    private ElevatorCall elevatorCall;

    //private int startingFloor;
    //private int destinationFloor;

    public Passenger(int startingFloor, int destinationFloor) throws Exception {
        this.name = "Employee " + UUID.randomUUID().toString();;
        this.elevatorCall = new ElevatorCall(startingFloor, destinationFloor);
    }

    public boolean isDeparting(int currentFloor) {
        return elevatorCall.getDestinationFloor() == currentFloor;
    }

    public boolean isBoarding(Direction elevatorDirection) {
        return elevatorCall.getDirection() == elevatorDirection;
    }

    public ElevatorCall getElevatorCall() {
        return elevatorCall;
    }

    @Override
    public boolean doNextAction() {
        return false;
    }

    @Override
    public boolean setNextAction() {
        return false;
    }
}
