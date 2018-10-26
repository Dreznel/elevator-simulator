package contracts;

import elevator.ElevatorStatistics;
import passenger.Passenger;

import java.util.List;

/*
    Interface to abstract the reception and handling of elevator requests.
 */
public interface ElevatorSystem extends Actionable {
    public void transportPassenger(Passenger p);

    public List<ElevatorStatistics> getElevatorStatistics();
}
