package operation;

import elevator.Elevator;

import java.util.Dictionary;

//Class to give commands to all of the elevators.
public class ElevatorManager {

    public Dictionary<String, Elevator> elevators;

    public ElevatorManager() {

        //Make four elevators.
        for(int i=1; i<=4; i++) {
            String name = "Elevator" + Integer.toString(i);
            Elevator e = new Elevator(name);
            elevators.put(name, e);
        }
    }




}
