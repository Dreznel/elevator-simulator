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
    private OrderedSetQueue stops; //TODO: May be a good candidate for dependency injection.
    private OrderedSetQueue pickups;
    private int nextStop;

    private boolean doorsOpen;

    public Elevator(String id) {
        elevatorId = id;

        maxCapacity = 10;
        passengers = new ArrayList<Passenger>();

        currentFloor = 15; //Assume a thirty-floor building.
        stops = new OrderedSetQueue();
        stops.setFrontOfQueueToHighest();
        nextStop = -1;

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
        nextStop = stops.peek();
        return false;
    }

    public void ingestElevatorCall(ElevatorCall call) {
        //
    }

    /*
    //Assumes that stops will be in line with the elevator's current direction and position.
    //We'll have to design a better way to accomplish this later.
    //Cowboy coding, weeeeee!
    public void addStop(ElevatorCall call) {

        //Weed out bad calls.
        if(call.getDirection() != this.getDirection()
                && this.getDirection() != STOPPED
                && !this.isIdle()) {
            //error case. This was an invalid assignment of a call.
            //TODO: Handle this better.
            System.err.println("Bad call for elevator " + elevatorId);
            return;
        }

        stops.insert(call.getCallingFloor());
        stops.insert(call.getDestinationFloor());

        Direction elevatorDirection = this.getDirection();
        switch(elevatorDirection) {
            case UP:
                stops.setFrontOfQueueToLowest();
                break;
            case DOWN:
                stops.setFrontOfQueueToHighest();
                break;
            case STOPPED:
                if(isIdle()) {
                    if(call.getDirection() == UP) {
                        stops.setFrontOfQueueToLowest();
                    } else if(call.getDirection() == DOWN) {
                        stops.setFrontOfQueueToHighest();
                    } else {
                        //What do we do here? Calls shouldn't be STOPPED. TODO
                    }
                    //nextStop = call.getCallingFloor();
                }
        }
    }
    */

    public boolean stoppingAt(int stop) {
        return stops.contains(stop);
    }

    public Direction getDirection() {
        if(nextStop < 0 || nextStop == currentFloor) {
            return STOPPED;
        } else if(nextStop > currentFloor) {
            return UP;
        } else { //if(nextStop < currentFloor) {
            return DOWN;
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

    private void updateStops() {
        if(stops.peek() == currentFloor) {
            stops.pop();
        }

        nextStop = isIdle() ? -1 : stops.peek();
    }

    private void moveElevator() {
        if(getDirection() == UP) {
            currentFloor++;
        } else if(getDirection() == DOWN) {
            currentFloor--;
        }
        //System.out.println("Elevator " + elevatorId + " moving to floor " + Integer.toString(currentFloor) + ".");
    }



}
