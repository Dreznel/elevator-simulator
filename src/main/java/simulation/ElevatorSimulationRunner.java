package simulation;

import contracts.ElevatorSystem;
import contracts.SimulationRunner;
import elevator.ElevatorSystemImpl;

public class ElevatorSimulationRunner implements SimulationRunner {
    @Override
    public void runSimulation(Timeline timeline) {
        while(timeline.hasNext()) {
            timeline.advanceTimeline();
        }
    }
}
