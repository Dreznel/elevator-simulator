package simulation;

import contracts.Actionable;

import java.util.List;

public class Timeline {
    private List<Actionable>[] eventMatrix; //This is an array of lists. Each index of the array contains a list of events that happen at that "time-slot".
    private int currentTime;

    public Timeline(List<Actionable>[] events) {
        this.eventMatrix = events; //TODO: Make this deep-copy the list.
        int currentTime = 0;
    }

    public void advanceTimeline() {
        List<Actionable> currentEvents = eventMatrix[currentTime];
        for(Actionable event : currentEvents) {
            event.doNextAction();
            event.setNextAction();
        }
        currentTime++;
    }

    public boolean hasNext() {
        return currentTime < eventMatrix.length;
    }

    public int getCurrentTime() { return currentTime; }
    public void setCurrentTime(int time) { this.currentTime = time; }
}
