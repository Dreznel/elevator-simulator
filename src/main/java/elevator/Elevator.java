package elevator;

import Utility.OrderedSetQueue;
import operation.ElevatorManager;
import contracts.Actionable;
import passenger.Passenger;

import java.util.*;

import static elevator.Direction.DOWN;
import static elevator.Direction.UP;
import static elevator.Direction.STOPPED;

public class Elevator implements Actionable {

    private String elevatorId;
    private ElevatorManager manager;

    private int maxCapacity;
    private List<Passenger> passengers;

    private int currentFloor;
    private OrderedSetQueue stops; //TODO: May be a good candidate for dependency injection.
    private int nextStop;

    private boolean doorsOpen;

    public Elevator(String id) {
        elevatorId = id;

        maxCapacity = 10;
        passengers = new ArrayList<Passenger>();

        currentFloor = 15; //Assume a thirty-floor building.
        stops = new OrderedSetQueue();
        stops.setFrontOfQueueToHighest();
        nextStop = 0;

        doorsOpen = false;
    }

    public String getElevatorId() {
        return elevatorId;
    }

    @Override
    public boolean doNextAction() {
        if(isIdle()) {
            return false;
        }

        if(getDirection() == STOPPED) {
            dropOffPassengers();
            //TODO: Add passengers -- but how do I pass in the argument?
            updateStops();
        } else {
            moveElevator();
        }
        return true; //I may take out the boolean return, it could be helpful for exception handling/fault-tolerance, though.
    }

    @Override
    public boolean setNextAction() {
        return false;
    }

    //This needs to be fixed but we'll get there.
    //As it stands, the elevator can't be assigned stops that aren't in line with it's current direction.
    public void addStop(int stop) {
        if(stop > currentFloor) {
            stops.setFrontOfQueueToLowest();
        } else  if(stop < currentFloor){
            stops.setFrontOfQueueToHighest();
        }
        stops.insert(stop);
    }

    public boolean stoppingAt(int stop) {
        return stops.contains(stop);
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

    //Not a really good way to do this, but I wanted to practice stream api.
    public int getStopsBeforeFloor(int floor) {
        if(this.getDirection() == DOWN) {
            return this.stops.getSetForStreamApi().stream().filter(x -> x > floor).toArray().length;
        } else {
            //For up OR stopped.
            return this.stops.getSetForStreamApi().stream().filter(x -> x < floor).toArray().length;
        }
    }

    public boolean isIdle() {
        return stops.peek() != null;
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


    //TODO: Try to do this with lambdas. Also, fix this inefficient mess.
    private void dropOffPassengers() {
        List<Passenger> remainingPassengers = new ArrayList<Passenger>();
        for(Passenger passenger : passengers) {
            if( ! passenger.isDeparting(this.currentFloor)) {
                remainingPassengers.add(passenger);
            }
        }
        this.passengers = remainingPassengers;
    }

    /*
    //Adds members of newPassengers into passengerList and returns those who would not fit.
    private List<Passenger> addPassengers(List<Passenger> newPassengers) {
        for(int i=0; i<newPassengers.size(); i++) {
            if(getCurrentCapacity() < maxCapacity) {
                this.passengers.insert(newPassengers.get(i));
            } else {
                return newPassengers.subList(i, newPassengers.size());
            }
        }
        return null;
    }
    */

    private void updateStops() {
        if(stops.peek() == currentFloor) {
            stops.pop();
            nextStop = isIdle() ? 0 : stops.peek();
        }
    }

    private void moveElevator() {
        if(getDirection() == UP) {
            currentFloor++;
        } else if(getDirection() == DOWN) {
            currentFloor--;
        }
        System.out.println("Elevator " + elevatorId + " moving to floor " + Integer.toString(currentFloor) + ".");
    }



}
