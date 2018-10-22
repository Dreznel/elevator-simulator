import contracts.Actionable;
import contracts.SimulationRunner;
import passenger.Passenger;
import simulation.ElevatorSimulationRunner;
import simulation.Timeline;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, world!");

        SimulationRunner simulation = new ElevatorSimulationRunner();

        List<List<Actionable>> eventList;
        eventList = new ArrayList<List<Actionable>>(150);
        for(int i=0; i<150; i++) {
            eventList.add(new ArrayList<Actionable>());
        }


        try {
            List<Actionable> passenger;
            eventList.set(5, getPassengerAsList(1, 29));
            eventList.set(10, getPassengerAsList(28, 1));
            eventList.set(15, getPassengerAsList(2, 10));
            eventList.set(20, getPassengerAsList(7, 10));
            eventList.set(25, getPassengerAsList(23, 13));
            eventList.set(30, getPassengerAsList(23, 13));
            eventList.set(35, getPassengerAsList(28, 12));
            eventList.set(40, getPassengerAsList(4, 6));
            eventList.set(45, getPassengerAsList(18, 8));
            eventList.set(50, getPassengerAsList(12, 5));
            eventList.set(55, getPassengerAsList(1, 7));
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

        Timeline timeline = new Timeline(eventList);
        simulation.runSimulation(timeline);
}

    private static List<Actionable> getPassengerAsList(int start, int stop) throws Exception {
        List<Actionable> passengerAsList = new ArrayList<Actionable>();
        passengerAsList.add(new Passenger(start, stop));
        return passengerAsList;
    }


    public int methodForTesting() {
        return 1;
    }
}
