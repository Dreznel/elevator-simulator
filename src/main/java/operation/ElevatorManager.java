package operation;

import contracts.Actionable;
import contracts.Cost;
import contracts.CostCalculator;
import elevator.Elevator;
import elevator.ElevatorCall;
import passenger.Passenger;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

//Class to give commands to all of the elevators.
public class ElevatorManager implements Actionable {

    private Map<String, Elevator> elevators;
    private CostCalculator costCalculator;
    private Queue<ElevatorCall> pendingCalls;
    private Queue<Passenger> pendingPassengers;

    public ElevatorManager() {

        elevators = new HashMap<String, Elevator>();

        //Make four elevators.
        for(int i=1; i<=4; i++) {
            String name = "E[" + Integer.toString(i) + "]";
            Elevator e = new Elevator(name);
            elevators.put(name, e);
        }

        costCalculator = new TimeToBoardCostCalculator();
        pendingCalls = new LinkedList<ElevatorCall>();
        pendingPassengers = new LinkedList<Passenger>();
    }

    @Override
    public boolean doNextAction() {
        for(Elevator e : elevators.values()) {
            e.doNextAction();
        }
        printDiagram();
        return true;
    }

    @Override
    public boolean setNextAction() {
        for(Elevator e : elevators.values()) {
            e.setNextAction();
        }
        return true;
    }

    //Method should receive a passenger and assign that passenger an elevator if possible.
    //Method also gives the elevator the necessary information to pick up that passenger.
    public void processElevatorCall(Passenger p) {
        Elevator selectedElevator = getCheapestElevator(p.getElevatorCall());

        //Do nothing and add to pending calls queue if no elevators are available.
        if(selectedElevator == null) {
            pendingPassengers.add(p);
            return;
        }

        //selectedElevator.addStop(p.getElevatorCall().getCallingFloor());
        //selectedElevator.addStop(p.getElevatorCall().getDestinationFloor());
        selectedElevator.ingestElevatorCall(p.getElevatorCall());
        p.assignElevator(selectedElevator);
    }

    private Elevator getCheapestElevator(ElevatorCall call) {
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

    private void printDiagram() {
        System.out.println("###\t\t\t\t\t\t\t###");
        for(int i=0; i<30; i++) {
            System.out.print(i + ":\t");
            for(Elevator e : elevators.values()) {
                if(e.getCurrentFloor() == i) {
                    System.out.printf("%5s\t", e.getElevatorId());
                } else {
                    System.out.printf("%5s\t", "___");
                }
            }
            System.out.println();
        }
        System.out.println("###\t\t\t\t\t\t\t###");
    }


/*
    //Method should receive a call and either assign it to an elevator or kick off the process
    //that assigns it to an elevator.
    public void processElevatorCall(ElevatorCall call) {
        Elevator selectedElevator = getCheapestElevator(call);

        //Do nothing and add to pending calls queue if no elevators are available.
        if(selectedElevator == null) {
            pendingCalls.add(call);
            return;
        }

        selectedElevator.addStop(call.getCallingFloor());
        selectedElevator.addStop(call.getDestinationFloor());
    }
*/
}
