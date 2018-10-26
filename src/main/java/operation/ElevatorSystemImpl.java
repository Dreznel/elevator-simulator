package operation;

import contracts.ElevatorSystem;
import passenger.Passenger;

public class ElevatorSystemImpl implements ElevatorSystem {
    private static ElevatorSystemImpl instance;
    private ElevatorManager manager;

    private ElevatorSystemImpl() {
        this.manager = new ElevatorManager();
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
