package simulation;

import contracts.Actionable;

import java.util.List;

public class Timeline {
    private List<List<Actionable>> eventMatrix; //This is an array of lists. Each index of the array contains a list of events that happen at that "time-slot".
    private int currentTime;

    public Timeline(List<List<Actionable>> events) {
        this.eventMatrix = events; //TODO: Make this deep-copy the list.
        int currentTime = 0;
    }

    public void advanceTimeline() {

        System.out.println("\n\nAdvancing timeline. Current time: " + Integer.toString((currentTime)));

        List<Actionable> currentEvents = eventMatrix.get(currentTime);
        for(Actionable event : currentEvents) {
            event.doNextAction();
            event.setNextAction();
        }
        currentTime++;
    }

    public boolean hasNext() {
        return currentTime < eventMatrix.size();
    }

    public int getCurrentTime() { return currentTime; }
    public void setCurrentTime(int time) { this.currentTime = time; }
}
