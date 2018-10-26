package simulation;

import contracts.SimulationRunner;

public class ElevatorSimulationRunner implements SimulationRunner {
    @Override
    public void runSimulation(Timeline timeline) {
        while(timeline.hasNext()) {
            timeline.advanceTimeline();
        }
    }
}
