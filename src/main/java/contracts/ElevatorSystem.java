package contracts;

import passenger.Passenger;

/*
    Interface to abstract the reception and handling of elevator requests.
 */
public interface ElevatorSystem extends Actionable {
    public void transportPassenger(Passenger p);
}
