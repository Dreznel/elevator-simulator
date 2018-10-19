package operation;

import elevator.Elevator;
import elevator.ElevatorCall;

import java.util.Dictionary;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

//Class to give commands to all of the elevators.
public class ElevatorManager {

    private Map<String, Elevator> elevators;
    private CostCalculator costCalculator;
    private Queue<ElevatorCall> pendingCalls;

    public ElevatorManager() {

        //Make four elevators.
        for(int i=1; i<=4; i++) {
            String name = "Elevator" + Integer.toString(i);
            Elevator e = new Elevator(name);
            elevators.put(name, e);
        }

        pendingCalls = new LinkedList<ElevatorCall>();
    }

    //Method should receive a call and either assign it to an elevator or kick off the process
    //that assigns it to an elevator.
    public void processElevatorCall(ElevatorCall call) {
        Elevator selectedElevator = getBestElevator(call);

        //Do nothing and add to pending calls queue if no elevators are available.
        if(selectedElevator == null) {
            pendingCalls.add(call);
            return;
        }

        selectedElevator.addStop(call.getCallingFloor());
        selectedElevator.addStop(call.getDestinationFloor());
    }

    private Elevator getBestElevator(ElevatorCall call) {
        Cost currentCost;
        Cost lowestCost = null;
        Elevator selectedElevator = null;
        for(Elevator elevator : elevators.values()) {
            currentCost = costCalculator.getCostToStopAtFloor(elevator, call.getCallingFloor(), call.getDirection());
            if(currentCost.isPossible()) {
                if(lowestCost == null) {
                    lowestCost = currentCost;
                    selectedElevator = elevator;
                } else if (lowestCost.compareTo(currentCost) > 0) {
                    lowestCost = currentCost;
                    selectedElevator = elevator;
                }
            }
        }
        return selectedElevator;
    }


}