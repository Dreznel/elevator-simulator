package contracts;

import passenger.Passenger;

public interface ElevatorSystem extends Actionable {
    public void transportPassenger(Passenger p);
}
