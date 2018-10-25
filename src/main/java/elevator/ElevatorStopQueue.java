package elevator;

import contracts.StopQueue;
import java.util.LinkedList;
import java.util.List;

public class ElevatorStopQueue implements StopQueue {

    private LinkedList<Integer> stops;

    public ElevatorStopQueue() {
        stops = new LinkedList<>();
    }

    public ElevatorStopQueue(int[] initialStops) {
        LinkedList<Integer> initialStopsList = new LinkedList<>();
        for(int i=0; i<initialStops.length; i++) {
            initialStopsList.add(initialStops[i];
        }
        stops = initialStopsList;
    }

    @Override
    public int peek() {
        return 0;
    }

    @Override
    public void addStop(int newStop) {

    }

    @Override
    public List<Integer> getStops() {
        return stops;
    }
}
