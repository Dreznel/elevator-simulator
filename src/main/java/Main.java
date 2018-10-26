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

        List<List<Passenger>> passengerTimelineMatrix;
        passengerTimelineMatrix = new ArrayList<List<Passenger>>(150);
        for(int i=0; i<80; i++) {
            passengerTimelineMatrix.add(new ArrayList<Passenger>());
        }

        try {
            passengerTimelineMatrix.set(0, getPassengerAsList(1, 29));
            passengerTimelineMatrix.set(5, getPassengerAsList(24, 29));
            passengerTimelineMatrix.set(10, getPassengerAsList(28, 1));
            passengerTimelineMatrix.set(15, getPassengerAsList(2, 10));
            passengerTimelineMatrix.set(20, getPassengerAsList(7, 10));
            passengerTimelineMatrix.set(25, getPassengerAsList(23, 13));
            passengerTimelineMatrix.set(30, getPassengerAsList(23, 13));
            passengerTimelineMatrix.set(35, getPassengerAsList(28, 12));
            passengerTimelineMatrix.set(40, getPassengerAsList(4, 6));
            passengerTimelineMatrix.set(45, getPassengerAsList(18, 8));
            passengerTimelineMatrix.set(50, getPassengerAsList(12, 5));
            passengerTimelineMatrix.set(55, getPassengerAsList(1, 7));
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

        Timeline timeline = new Timeline(passengerTimelineMatrix);
        simulation.runSimulation(timeline);
}

    private static List<Passenger> getPassengerAsList(int start, int stop) throws Exception {
        List<Passenger> passengerAsList = new ArrayList<>();
        passengerAsList.add(new Passenger(start, stop));
        return passengerAsList;
    }
}
