package simulation;

import contracts.Actionable;
import operation.ElevatorSystemImpl;
import passenger.Passenger;

import java.util.LinkedList;
import java.util.List;

/*
    Class to process an passengerEventMatrix, which is a jagged Array (or List, in this case)
    of actionable (passenger) items. The Array is indexed according to the following pattern:

    passengerEventMatrix [time slot] [list of new passengers in that time slot]

    For the purposes of this simulation, the items in the jagged array are all lists of passengers.
*/
public class Timeline {

    private List<List<Passenger>> passengerEventMatrix;
    private int currentTime;
    private List<Passenger> masterPassengerList;

    public Timeline(List<List<Passenger>> passengerEvents) {
        passengerEventMatrix = passengerEvents;
        masterPassengerList = new LinkedList<>();
        currentTime = 0;
    }

    public boolean hasNext() {
        return currentTime < passengerEventMatrix.size();
    }

    public void advanceTimeline() {

        System.out.println("\n\nAdvancing timeline. Current time: " + Integer.toString((currentTime)));

        //Advance elevators.
        ElevatorSystemImpl.getInstance().doNextAction();

        advancePreviousPassengers();

        List<Passenger> newPassengers = passengerEventMatrix.get(currentTime);

        for(Passenger newPassenger : newPassengers) {
            newPassenger.doNextAction();
            newPassenger.setNextAction();

            masterPassengerList.add(newPassenger);

        }

        //Run any next-setup actions for elevators.
        ElevatorSystemImpl.getInstance().setNextAction();

        currentTime++;
    }

    private void advancePreviousPassengers() {
        for(Passenger passenger : masterPassengerList) {
            passenger.doNextAction();
            passenger.setNextAction();
        }
    }


}
