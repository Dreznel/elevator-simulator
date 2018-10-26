package operation;

import contracts.ElevatorSystem;
import passenger.Passenger;

/*
    Abstraction of the layer that handles Passenger requests to the elevator manager.
    Uses a singleton implementation for simplicity.
 */
public class ElevatorSystemImpl implements ElevatorSystem {
    private static ElevatorSystemImpl instance;
    private ElevatorManager manager;

    private ElevatorSystemImpl() {
        this.manager = new ElevatorManager(4);
    }

    public static ElevatorSystem getInstance() {
        if(instance == null) {
            instance = new ElevatorSystemImpl();
        }

        return instance;
    }

    @Override
    public void transportPassenger(Passenger p) {
        manager.processElevatorCall(p);
    }

    @Override
    public boolean doNextAction() {
        return manager.doNextAction();
    }

    @Override
    public boolean setNextAction() {
        return manager.setNextAction();
    }
}
