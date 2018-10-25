package passenger;

import contracts.Actionable;
import elevator.Direction;
import elevator.Elevator;
import elevator.ElevatorCall;
import elevator.ElevatorSystemImpl;

import java.util.UUID;

public class Passenger implements Actionable {
    private final String name;
    private ElevatorCall elevatorCall;
    private Elevator assignedElevator;
    private boolean isBoarded;
    private boolean wasSkipped;
    private boolean isArrived;

    public Passenger(int startingFloor, int destinationFloor) throws Exception {
        this.name = "Employee " + UUID.randomUUID().toString();;
        this.elevatorCall = new ElevatorCall(startingFloor, destinationFloor);
        isBoarded = false;
        wasSkipped = false;
        isArrived = false;
    }

    //private int startingFloor;
    //private int destinationFloor;
    @Override
    public boolean doNextAction() {
        if(assignedElevator == null || wasSkipped) {
            ElevatorSystemImpl.getInstance().transportPassenger(this);
            wasSkipped = false;
        } else if(isBoarded && isDeparting()) {
                assignedElevator.departPassenger(this);
                printFinished();
                isArrived = true;
        } else if(isElevatorHere()) {
                isBoarded = assignedElevator.boardPassenger(this);
                wasSkipped = !isBoarded;
        }

        return true;
    }

    @Override
    public boolean setNextAction() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if(o == this) {
            return true;
        }

        if(!(o instanceof Passenger)) {
            return false;
        }

        Passenger other = (Passenger) o;

        return this.name.equals(other.getName());
    }

    @Override
    public int hashCode() {
        final int prime = 23;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    public void assignElevator(Elevator e) {
        this.assignedElevator = e;
    }

    public void boardElevator() {
        if(assignedElevator.getCurrentFloor() == elevatorCall.getCallingFloor()) {
            boolean boarded = assignedElevator.boardPassenger(this);
            if (!boarded) {

            }
        }
    }

    //TODO: delete
    public boolean isDeparting(int currentFloor) {
        return elevatorCall.getDestinationFloor() == currentFloor;
    }

    public boolean getIsBoarded() {
        return isBoarded;
    }

    public boolean getWasSkipped() {
        return wasSkipped;
    }

    public String getName() {
        return name;
    }

    public String getSmallName() { return name.substring(9, 13); }

    public ElevatorCall getElevatorCall() {
        return elevatorCall;
    }

    private boolean isDeparting() {
        if(assignedElevator == null) {
            return false;
        }
        return elevatorCall.getDestinationFloor() == assignedElevator.getCurrentFloor();
    }

    private boolean isElevatorHere() {
        if(assignedElevator == null) {
            return false;
        }
        return elevatorCall.getCallingFloor() == assignedElevator.getCurrentFloor();
    }

    private void printFinished() {
        if(!isArrived) {
            System.out.println("Passenger " + getSmallName() + " has arrived.");
            System.out.printf("Passenger traveled from floor %d to floor %d via Elevator %s.\n",
                    elevatorCall.getCallingFloor(),
                    elevatorCall.getDestinationFloor(),
                    assignedElevator.getElevatorId()
            )
            ;
        };
    }


}
