package simulation;

public class ElevatorSimulationRunner implements SimulationRunner {
    @Override
    public void runSimulation(Timeline timeline) {
        while(timeline.hasNext()) {
            //elevatorOperator.issueCommands();
            timeline.advanceTimeline();
        }
    }
}
