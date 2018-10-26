package elevator;

import java.util.LinkedList;
import java.util.List;

/*
    This class is used to transport tracking statistics about the elevator throughout the program
    without needing to pass direct references to the elevator itself.

    This should also serve to simplify some of the Elevator class itself.
*/
public class ElevatorStatistics {

    private String elevatorId;
    private int remainingPassengers;
    private int finalFloor;
    private int remainingStops;
    private int upMoves;
    private int downMoves;
    private List<Integer> stopHistory;

    public ElevatorStatistics(String id, int startingFloor) {
        elevatorId = id;
        stopHistory = new LinkedList<>();
        stopHistory.add(startingFloor);

        remainingPassengers = -1;
        finalFloor = -1;
        remainingStops = -1;
        upMoves = 0;
        downMoves = 0;
    }

    public void incrementUpMoves() {
        upMoves++;
    }

    public void incrementDownMoves() {
        downMoves++;
    }

    ////////////////////
    // Needed setters //
    ////////////////////
    public void addStopToHistory(int stopFloor) {
        stopHistory.add(stopFloor);
    }

    public void setRemainingPassengers(int remainingPassengers) {
        this.remainingPassengers = remainingPassengers;
    }

    public void setFinalFloor(int finalFloor) {
        this.finalFloor = finalFloor;
    }

    public void setRemainingStops(int remainingStops) {
        this.remainingStops = remainingStops;
    }

    ////////////////////
    // Simple Getters //
    ////////////////////
    public String getElevatorId() {
        return elevatorId;
    }

    public int getRemainingPassengers() {
        return remainingPassengers;
    }

    public int getFinalFloor() {
        return finalFloor;
    }

    public int getRemainingStops() {
        return remainingStops;
    }

    public int getUpMoves() {
        return upMoves;
    }

    public int getDownMoves() {
        return downMoves;
    }

    public List<Integer> getStopHistory() {
        return stopHistory;
    }
}
