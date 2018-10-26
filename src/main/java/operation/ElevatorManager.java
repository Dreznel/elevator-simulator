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

/*
    Class to manage the efficient assignment and tracking of elevators.
 */
public class ElevatorManager implements Actionable {

    private Map<String, Elevator> elevators;
    private CostCalculator costCalculator;
    private Queue<Passenger> pendingPassengers;

    public ElevatorManager(int numberOfElevators) {
        elevators = new HashMap<>();

        //Make four elevators.
        for(int i=1; i<=numberOfElevators; i++) {
            String name = "E[" + Integer.toString(i) + "]";
            Elevator e = new Elevator(name);
            elevators.put(name, e);
        }

        costCalculator = new TimeToBoardCostCalculator();
        pendingPassengers = new LinkedList<>();
    }

    @Override
    public boolean doNextAction() {

        /*
        if(!pendingPassengers.isEmpty()) {
            tryPendingPassengersAgain();
        }
        */

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

        selectedElevator.ingestElevatorCall(p.getElevatorCall());
        p.assignElevator(selectedElevator);
    }

    private void tryPendingPassengersAgain() {
        Queue<Passenger> currentQueue = pendingPassengers;
        pendingPassengers = new LinkedList<>();
        for(Passenger p : currentQueue) {
            processElevatorCall(p);
        }
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
                    System.out.printf("%5s%2d\t", e.getElevatorId(), e.getCurrentCapacity());
                } else {
                    System.out.printf("%5s\t", "___");
                }
            }
            System.out.println();
        }
        System.out.println("###\t\t\t\t\t\t\t###");
    }
}
