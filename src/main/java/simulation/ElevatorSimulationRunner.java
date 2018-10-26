package simulation;

import contracts.SimulationRunner;
import elevator.ElevatorStatistics;
import operation.ElevatorSystemImpl;

public class ElevatorSimulationRunner implements SimulationRunner {

    @Override
    public void runSimulation(Timeline timeline) {
        while(timeline.hasNext()) {
            timeline.advanceTimeline();
        }
        printStatistics();
    }

    @Override
    public void printStatistics() {
        System.out.println("Final Results: ");
        ElevatorSystemImpl.getInstance().getElevatorStatistics().stream().forEach(stats -> printElevatorStatistics(stats));
    }

    private void printElevatorStatistics(ElevatorStatistics stats) {
        System.out.printf("\n%-15s\n", "Elevator " + stats.getElevatorId());
        System.out.printf("%-20s:%5d\n", "Remaining Passengers", stats.getRemainingPassengers());
        System.out.printf("%-20s:%5d\n", "Remaining Stops", stats.getRemainingStops());

        System.out.println();
        System.out.printf("%-20s:%5d\n", "Total Stops", stats.getStopHistory().size());
        System.out.printf("%-20s:%3s", "Stop History", "");
        for(Integer stop : stats.getStopHistory()) {
            System.out.print(Integer.toString(stop) + " -> ");
        }
        System.out.println("DONE");

        System.out.println();
        System.out.printf("%-20s:%5d\n", "Up Moves", stats.getUpMoves());
        System.out.printf("%-20s:%5d\n", "Down Moves", stats.getDownMoves());
        System.out.printf("%-20s:%5d\n", "Total Moves", stats.getUpMoves() + stats.getDownMoves());

        System.out.println();
        System.out.printf("%-20s:%5d\n", "Start Floor", stats.getStopHistory().get(0));
        System.out.printf("%-20s:%5d\n", "End Floor", stats.getFinalFloor());
        System.out.println("----------------------------");

    }
}


/*

    private String elevatorId;
    private int remainingPassengers;
    private int finalFloor;
    private int remainingStops;
    private int upMoves;
    private int downMoves;
    private List<Integer> stopHistory;
 */