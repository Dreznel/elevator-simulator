package elevator;

import operation.ElevatorManager;
import simulation.Actionable;

import java.util.ArrayList;
import java.util.List;

import static elevator.Direction.DOWN;
import static elevator.Direction.UP;
import static elevator.Direction.STOPPED;

public class Elevator implements Actionable {

    private String elevatorId;
    private ElevatorManager manager;

    private int maxCapacity;
    private List<Passenger> passengers;

    private int currentFloor;
    private List<Integer> stops;
    private int nextStop;

    private boolean doorsOpen;

    public Elevator(String id) {
        elevatorId = id;

        maxCapacity = 10;
        passengers = new ArrayList<Passenger>();

        currentFloor = 15; //Assume a thirty-floor building.
        stops = new ArrayList<Integer>();
        nextStop = 0;

        doorsOpen = false;
    }

    public String getElevatorId() {
        return elevatorId;
    }

    public Direction getDirection() {
        if(nextStop > currentFloor) {
            return UP;
        } else if(nextStop < currentFloor) {
            return DOWN;
        } else {
            return STOPPED;
        }
    }

    public int getCurrentCapacity() {
        return passengers.size();
    }

    public boolean hasPassengers() {
        return getCurrentCapacity() > 0;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    /*
    public void openDoors() {
        this.doorsOpen = true;
    }

    public void closeDoors() {
        this.doorsOpen = false;
    }
    */

    public void moveElevator() {
        if(getDirection() == UP) {
            currentFloor++;
        } else if(getDirection() == DOWN) {
            currentFloor--;
        }
    }

    //TODO: Try to do this with lambdas. Also, fix this inefficient mess.
    public void dropOffPassengers() {
        List<Passenger> remainingPassengers = new ArrayList<Passenger>();
        for(Passenger passenger : passengers) {
            if( ! passenger.isDeparting(this.currentFloor)) {
                remainingPassengers.add(passenger);
            }
        }
        this.passengers = remainingPassengers;
    }

    //Adds members of newPassengers into passengerList and returns those who would not fit.
    public List<Passenger> addPassengers(List<Passenger> newPassengers) {
        for(int i=0; i<newPassengers.size(); i++) {
            if(getCurrentCapacity() < maxCapacity) {
                this.passengers.add(newPassengers.get(i));
            } else {
                return newPassengers.subList(i, newPassengers.size());
            }
        }
        return null;
    }

    @Override
    public boolean doNextAction() {
        if(getDirection() == STOPPED) {
            dropOffPassengers();
            //TODO: Add passengers -- but how do I pass in the argument?
        } else {
            moveElevator();
        }
        return true; //I may take out the boolean return, it could be helpful for exception handling/fault-tolerance, though.
    }

    @Override
    public boolean setNextAction() {
        return false;
    }
}
