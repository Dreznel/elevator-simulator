package elevator;

import operation.ElevatorManager;
import passenger.Passenger;

public class ElevatorSystemImpl implements ElevatorSystem {
    private ElevatorManager manager;

    public ElevatorSystemImpl() {
        this.manager = new ElevatorManager();
    }

    @Override
    public void transportPassenger(Passenger p) {
        manager.processElevatorCall(p.getElevatorCall());
    }
}
