package elevator;

import contracts.ElevatorSystem;
import operation.ElevatorManager;
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
}
