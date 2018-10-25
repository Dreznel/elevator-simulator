package elevator;

import utility.OrderedSetQueue;
import operation.ElevatorManager;
import contracts.Actionable;
import passenger.Passenger;

import java.util.*;

import static elevator.Direction.DOWN;
import static elevator.Direction.UP;
import static elevator.Direction.STOPPED;

public class Elevator implements Actionable {

    private String elevatorId;

    private int maxCapacity;
    private List<Passenger> passengers;

    private int currentFloor;
    private Direction calledDirection;
    private OrderedSetQueue stops; //TODO: May be a good candidate for dependency injection.
    private int nextStop;




    public Elevator(String id) {
        elevatorId = id;

        maxCapacity = 10;
        passengers = new ArrayList<Passenger>();

        currentFloor = 15; //Assume a thirty-floor building.
        stops = new OrderedSetQueue();
        stops.setFrontOfQueueToHighest();
        nextStop = -1;
    }

    public String getElevatorId() {
        return elevatorId;
    }

    @Override
    public boolean doNextAction() {
        if(isIdle()) {
            return false;
        }

        if(nextStop == currentFloor) {
            //dropOffPassengers();
            //addPassengers();
            updateStops();
        } else {
            moveElevator();
        }

        return true; //I may take out the boolean return, it could be helpful for exception handling/fault-tolerance, though.
    }

    @Override
    public boolean setNextAction() {
        if(isIdle()) {
            calledDirection = STOPPED;
        }
        nextStop = stops.peek();
        updateDirection();
        return false;
    }

    public void ingestElevatorCall(ElevatorCall call) {
        if(isIdle()) {
            this.calledDirection = call.getDirection();
        } else if(call.getDirection() != this.getDirection()) {
            System.err.println("Elevator called with wrong direction: " + elevatorId);
            return;
        }

        stops.insert(call.getCallingFloor());
        stops.insert(call.getDestinationFloor());
        setQueueDirection();
    }

    public boolean stoppingAt(int stop) {
        return stops.contains(stop);
    }

    public Direction getDirection() {
        return this.calledDirection;
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
        return stops.peek() == -1;
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

    public boolean boardPassenger(Passenger p) {
        if(this.getCurrentCapacity() < this.maxCapacity) {
            passengers.add(p);
            return true;
        }
        return false;
    }

    public boolean departPassenger(Passenger p) {
        return passengers.remove(p);
    }

    private void setQueueDirection() {
        if(calledDirection == UP) {
            stops.setFrontOfQueueToLowest();
        } else if(calledDirection == DOWN) {
            stops.setFrontOfQueueToHighest();
        } else {
            System.err.println("Error: can't set queue direction on stopped elevator. How did this happen?");
        }
    }

    private void updateDirection() {
        if(nextStop < currentFloor) {
            calledDirection = DOWN;
        } else {
            calledDirection = UP;
        }
    }

    private void updateStops() {
        if(stops.peek() == currentFloor) {
            stops.pop();
        }

        nextStop = isIdle() ? -1 : stops.peek();
    }

    private void moveElevator() {
        if(currentFloor < nextStop) {
            currentFloor++;
        } else if(currentFloor > nextStop) {
            currentFloor--;
        }
        //System.out.println("Elevator " + elevatorId + " moving to floor " + Integer.toString(currentFloor) + ".");
    }





}
