package operation;

import elevator.Direction;
import elevator.Elevator;

public interface CostCalculator {
    public Cost getCostToStopAtFloor(Elevator e, int callingFloor, Direction direction);
}
