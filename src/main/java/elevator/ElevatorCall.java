package elevator;

import static elevator.Direction.DOWN;
import static elevator.Direction.UP;
import static elevator.Direction.STOPPED;

public class ElevatorCall {

    private int callingFloor;
    private int destinationFloor;
    //private Passenger passenger;

    public ElevatorCall() {
        callingFloor = 1;
        destinationFloor = 2;
    //    passenger = new Passenger(callingFloor, destinationFloor);
    }

    public boolean canCombine(ElevatorCall otherCall) {
        return this.callingFloor == otherCall.callingFloor && this.getDirection() == otherCall.getDirection();
    }

    public Direction getDirection() {
        if(callingFloor < destinationFloor) {
            return Direction.UP;
        } else if (callingFloor > destinationFloor) {
            return Direction.DOWN;
        } else {
            return Direction.STOPPED;
        }
    }

    public int getCallingFloor() {
        return callingFloor;
    }

    public int getDestinationFloor() {
        return destinationFloor;
    }

    /*
    public Passenger getPassenger() {
        return passenger;
    }
    */
}
