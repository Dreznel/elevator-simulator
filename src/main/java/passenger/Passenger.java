package passenger;

import contracts.Actionable;
import elevator.Elevator;
import elevator.ElevatorCall;
import operation.ElevatorSystemImpl;

import java.util.UUID;

public class Passenger implements Actionable {
    private final String name;
    private ElevatorCall elevatorCall;
    private Elevator assignedElevator;
    private boolean isBoarded;
    private boolean wasSkipped;
    private boolean isArrived;
    private int totalTimeToArrival;

    public Passenger(int startingFloor, int destinationFloor) throws Exception {
        this.name = "Employee " + UUID.randomUUID().toString();;
        this.elevatorCall = new ElevatorCall(startingFloor, destinationFloor);
        isBoarded = false;
        wasSkipped = false;
        isArrived = false;
        totalTimeToArrival = 0;
    }

    @Override
    public boolean doNextAction() {
        if(assignedElevator == null || wasSkipped) {
            ElevatorSystemImpl.getInstance().transportPassenger(this);
            wasSkipped = false;
        } else if(isBoarded && isDeparting()) {
                assignedElevator.departPassenger(this);
                printFinished();
                isArrived = true;
        } else if(!isBoarded && isElevatorHere()) {
                isBoarded = assignedElevator.boardPassenger(this);
                wasSkipped = !isBoarded;
        }
        totalTimeToArrival++;

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

    public int getTotalTimeToArrival() {
        return totalTimeToArrival;
    }

    public String getName() {
        return name;
    }

    public String getSmallName() {
        return name.substring(9, 13);
    }

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
            );
        }
    }


}
